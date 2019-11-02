package za.com.wethinkcode.view.gui;

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.contoller.Controller;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.util.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Getter
@Setter
public class Gui {

    private JPanel buttonPanel;
    private JFrame mainFrame;
    private JButton selectHeroButton;
    private JButton createNewHeroButton;
    private JButton exitButton;
    private Database database;
    private Controller controller;
    private Hero hero;

    public Gui(Database database) {
        if (database != null) {
            this.database = database;
            controller = new Controller(database);
        }
    }

    public void prepareGui() {
        createUIComponents();
        // TODO -- Game play and start Game When the hero object has be created
    }

    private void createUIComponents() {
        mainFrame = new JFrame("Swingy");
        buttonPanel = new JPanel(new GridLayout(0, 3));
        selectHeroButton = new JButton("Select Existing Hero");
        exitButton = new JButton("Exit");
        createNewHeroButton = new JButton("Create New Hero");

        //mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.setSize(700, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        selectHeroButton.setActionCommand("Select Existing Hero");
        createNewHeroButton.setActionCommand("Create New Hero");
        exitButton.setActionCommand("Exit");

        MainButtonClickListener buttonListener = new MainButtonClickListener();
        selectHeroButton.addActionListener(buttonListener);
        exitButton.addActionListener(buttonListener);
        createNewHeroButton.addActionListener(buttonListener);

        buttonPanel.add(createNewHeroButton);
        buttonPanel.add(selectHeroButton);
        buttonPanel.add(exitButton);
        mainFrame.setContentPane(buttonPanel);
        mainFrame.setVisible(true);
    }

    class MainButtonClickListener implements ActionListener, ItemListener {

        private JCheckBox warriorCheckBox = new JCheckBox("Warrior");
        private JCheckBox hunterCheckBox = new JCheckBox("Hunter");
        private JCheckBox priestCheckBox = new JCheckBox("Priest");
        private JTextField heroName = new JTextField();

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String command = actionEvent.getActionCommand();
            JPanel heroPanel = new JPanel(new GridLayout(2, 1));
            final ResultSet heroesAvailableFromDatabase;
            ArrayList<String> heroesAvailable = new ArrayList<>();
            JComboBox<String> heroNames;
            switch (command) {
                case "Exit":
                    System.exit(0);
                    break;
                case "Select Existing Hero":
                    selectHeroButton.setVisible(false);
                    try {
                        heroesAvailableFromDatabase = controller.getAvailableHeroes();
                        while (heroesAvailableFromDatabase.next()) {
                            heroesAvailable.add(heroesAvailableFromDatabase.getString("name"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    heroNames = new JComboBox<>();
                    if (!heroesAvailable.isEmpty()) {
                        for (String heroName : heroesAvailable) {
                            heroNames.addItem(heroName);
                        }
                    }
                    heroPanel.add(heroNames);
                    createNewHeroButton.setVisible(false);
                    mainFrame.add(heroPanel);
                    break;
                case "Create New Hero":
                    if (selectHeroButton.isVisible()) {
                        warriorCheckBox.addItemListener(this);
                        hunterCheckBox.addItemListener(this);
                        priestCheckBox.addItemListener(this);

                        selectHeroButton.setVisible(false);
                        heroPanel.add(heroName);
                        // TODO -- heroPanel.add(createNewHeroButton);
                        heroPanel.add(warriorCheckBox);
                        heroPanel.add(hunterCheckBox);
                        heroPanel.add(priestCheckBox);
                        mainFrame.add(heroPanel);
                    } else {
                        if (!heroName.getText().isEmpty()) {
                            String heroClass = null;
                            if (warriorCheckBox.isSelected()) {
                                heroClass = warriorCheckBox.getText();
                            } else if (hunterCheckBox.isSelected()) {
                                heroClass = hunterCheckBox.getText();
                            } else if (priestCheckBox.isSelected()) {
                                heroClass = priestCheckBox.getText();
                            }
                            if (heroClass != null) {
                                try {
                                    if (controller.checkIfHeroNameExist(heroName.getText(), heroClass)) {
                                        setHero(controller.createHero(heroName.getText()));
                                    } else {
                                        JOptionPane.showMessageDialog(mainFrame,
                                                "Please Pick A Different Hero Name..." + heroName.getText() + " Exists",
                                                "Hero Name",
                                                JOptionPane.WARNING_MESSAGE);
                                    }
                                } catch (SQLException exception) {
                                    exception.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(mainFrame,
                                        "Please Pick A Hero Class For The Hero",
                                        "Hero Class",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(mainFrame,
                                    "Hero Name Cannot Be Empty",
                                    "Empty Hero Name",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    break;
            }
            buttonPanel.updateUI();
        }

        @Override
        public void itemStateChanged(ItemEvent checkBox) {
            if (warriorCheckBox.isSelected()) {
                hunterCheckBox.setSelected(false);
                priestCheckBox.setSelected(false);
            }
            if (priestCheckBox.isSelected()) {
                warriorCheckBox.setSelected(false);
                hunterCheckBox.setSelected(false);
            }
            if (hunterCheckBox.isSelected()) {
                warriorCheckBox.setSelected(false);
                priestCheckBox.setSelected(false);
            }
        }
    }
}
