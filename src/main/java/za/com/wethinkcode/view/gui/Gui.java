package za.com.wethinkcode.view.gui;

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.util.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter
public class Gui {
	private JPanel mainPanel;
	private JFrame mainFrame;
	private JButton selectHeroButton;
	private JButton createNewHeroButton;
	private JButton exitButton;
	private Database database;

	public Gui(Database database) {
		if (database != null) {
			this.database = database;
		}
	}
	public void prepareGui() {
		createUIComponents();
	}

	private void createUIComponents() {
		mainFrame = new JFrame("Swingy");
		mainPanel = new JPanel(new GridLayout(3, 1));
		selectHeroButton = new JButton("Select Existing Hero");
		exitButton = new JButton("Exit");
		createNewHeroButton = new JButton("Create New Hero");

		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.setSize(500, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		selectHeroButton.setActionCommand("Select Existing Hero");
		createNewHeroButton.setActionCommand("Create New Hero");
		exitButton.setActionCommand("Exit");

		selectHeroButton.addActionListener(new MainButtonClickListener());
		exitButton.addActionListener(new MainButtonClickListener());
		createNewHeroButton.addActionListener(new MainButtonClickListener());

		mainPanel.add(createNewHeroButton);
		mainPanel.add(selectHeroButton);
		mainPanel.add(exitButton);
		mainFrame.setContentPane(mainPanel);
		mainFrame.setVisible(true);
	}

	class MainButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			String command = actionEvent.getActionCommand();
			JPanel heroPanel = new JPanel(new GridLayout(1, 1));
			JTextField heroName = new JTextField();
			JCheckBox box = new JCheckBox("Warrior");
			switch (command) {
				case "Exit":
					System.exit(0);
					break;
				case "Select Existing Hero":
					break;
				case "Create New Hero":
					selectHeroButton.setVisible(false);
					createNewHeroButton.setVisible(false);
					heroPanel.add(heroName);
					heroPanel.add(box);
					mainFrame.add(heroPanel);
					break;
			}
			mainPanel.updateUI();
		}
	}
}
