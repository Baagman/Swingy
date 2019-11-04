package za.com.wethinkcode.view.gui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.contoller.Controller;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.util.Database;

/**
 *
 * @author tbaagman
 */
@Getter
@Setter
public class newGui extends javax.swing.JFrame {

    private Controller controller;
    private Hero hero;

    /**
     * Creates new form newGui
     *
     * @param database
     */
    public newGui(Database database) {
        initComponents();
        this.controller = new Controller(database);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new javax.swing.JPanel();
        btnContainer = new javax.swing.JPanel();
        selectExsitingHero = new javax.swing.JButton();
        createNewHeroBtn = new javax.swing.JButton();
        exitGame = new javax.swing.JButton();
        createNewHeroPanel = new javax.swing.JPanel();
        javax.swing.JLabel heroNameLabel = new javax.swing.JLabel();
        heroName = new javax.swing.JTextField();
        warriorCheckBox = new javax.swing.JCheckBox();
        hunterCheckBox = new javax.swing.JCheckBox();
        priestCheckBox = new javax.swing.JCheckBox();
        final javax.swing.JLabel heroClassLabel = new javax.swing.JLabel();
        startGameBtn = new javax.swing.JButton();
        availableHeroPanel = new javax.swing.JPanel();
        statsPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        availableHeroes = new javax.swing.JComboBox<String>();
        playGameBtn = new javax.swing.JButton();
        mapPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mapTextArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        selectExsitingHero.setText("Select Exsiting Hero");
        selectExsitingHero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectExsitingHeroActionPerformed(evt);
            }
        });

        createNewHeroBtn.setText("Create New Hero");
        createNewHeroBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewHeroBtnActionPerformed(evt);
            }
        });

        exitGame.setText("Exit Game");
        exitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnContainerLayout = new javax.swing.GroupLayout(btnContainer);
        btnContainer.setLayout(btnContainerLayout);
        btnContainerLayout.setHorizontalGroup(
            btnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnContainerLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(btnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exitGame, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(createNewHeroBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(selectExsitingHero)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        btnContainerLayout.setVerticalGroup(
            btnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnContainerLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(selectExsitingHero, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createNewHeroBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitGame, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        heroNameLabel.setText("Hero Name :");

        warriorCheckBox.setText("Warrior");
        warriorCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                heroClassCheckBoxItemStateChanged(evt);
            }
        });

        hunterCheckBox.setText("Hunter");
        hunterCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                heroClassCheckBoxItemStateChanged(evt);
            }
        });

        priestCheckBox.setText("Priest");
        priestCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                heroClassCheckBoxItemStateChanged(evt);
            }
        });

        heroClassLabel.setText("Hero Class:");

        startGameBtn.setText("Start Game");
        startGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameBtnActionPerformed(evt);
            }
        });

        createNewHeroPanel.setVisible(false);

        javax.swing.GroupLayout createNewHeroPanelLayout = new javax.swing.GroupLayout(createNewHeroPanel);
        createNewHeroPanel.setLayout(createNewHeroPanelLayout);
        createNewHeroPanelLayout.setHorizontalGroup(
            createNewHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createNewHeroPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(createNewHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(heroClassLabel)
                    .addComponent(warriorCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hunterCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priestCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132))
            .addGroup(createNewHeroPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(createNewHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(startGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(createNewHeroPanelLayout.createSequentialGroup()
                        .addComponent(heroNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heroName, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        createNewHeroPanelLayout.setVerticalGroup(
            createNewHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createNewHeroPanelLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(createNewHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(heroNameLabel)
                    .addComponent(heroName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(heroClassLabel)
                .addGap(4, 4, 4)
                .addComponent(warriorCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hunterCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priestCheckBox)
                .addGap(18, 18, 18)
                .addComponent(startGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        startGameBtn.setVisible(false);

        availableHeroPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                availableHeroPanelComponentShown(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setEditable(false);

        availableHeroes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                availableHeroesItemStateChanged(evt);
            }
        });

        playGameBtn.setText("Play");
        playGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playGameBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout statsPanelLayout = new javax.swing.GroupLayout(statsPanel);
        statsPanel.setLayout(statsPanelLayout);
        statsPanelLayout.setHorizontalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGroup(statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                    .addGroup(statsPanelLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(availableHeroes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(statsPanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(playGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statsPanelLayout.setVerticalGroup(
            statsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statsPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(availableHeroes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(playGameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout availableHeroPanelLayout = new javax.swing.GroupLayout(availableHeroPanel);
        availableHeroPanel.setLayout(availableHeroPanelLayout);
        availableHeroPanelLayout.setHorizontalGroup(
            availableHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availableHeroPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        availableHeroPanelLayout.setVerticalGroup(
            availableHeroPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availableHeroPanelLayout.createSequentialGroup()
                .addComponent(statsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mapTextArea.setColumns(20);
        mapTextArea.setRows(5);
        jScrollPane2.setViewportView(mapTextArea);
        mapTextArea.setEditable(false);

        jButton1.setText("jButton1");

        jButton2.setText("North");

        jButton3.setText("South");

        jButton4.setText("jButton4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jButton4)))
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mapPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout containerPanelLayout = new javax.swing.GroupLayout(containerPanel);
        containerPanel.setLayout(containerPanelLayout);
        containerPanelLayout.setHorizontalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createNewHeroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(availableHeroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(mapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        containerPanelLayout.setVerticalGroup(
            containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(containerPanelLayout.createSequentialGroup()
                .addGroup(containerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createNewHeroPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, containerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(availableHeroPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        availableHeroPanel.setVisible(false);
        mapPanel.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(containerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitGameActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitGameActionPerformed

    private void createNewHeroBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewHeroBtnActionPerformed

        createNewHeroPanel.setVisible(true);
        createNewHeroBtn.setVisible(false);
        selectExsitingHero.setVisible(false);
        startGameBtn.setVisible(true);
        pack();
    }//GEN-LAST:event_createNewHeroBtnActionPerformed

    private void selectExsitingHeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectExsitingHeroActionPerformed

        availableHeroPanel.setVisible(true);
        createNewHeroBtn.setVisible(false);
        selectExsitingHero.setVisible(false);
        playGameBtn.setVisible(true);
        pack();
    }//GEN-LAST:event_selectExsitingHeroActionPerformed

    private void availableHeroPanelComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_availableHeroPanelComponentShown

        final ResultSet heroesAvailableFromDatabase;
        ArrayList<String> heroesAvailable = new ArrayList<>();
        try {
            heroesAvailableFromDatabase = controller.getAvailableHeroes();
            while (heroesAvailableFromDatabase.next()) {
                heroesAvailable.add(heroesAvailableFromDatabase.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!heroesAvailable.isEmpty()) {
            for (String heroName : heroesAvailable) {
                availableHeroes.addItem(heroName);
            }
        }
    }//GEN-LAST:event_availableHeroPanelComponentShown

    private void availableHeroesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_availableHeroesItemStateChanged

        ResultSet selectedHero = null;
        StringBuilder heroStats = new StringBuilder();
        
        try {
            selectedHero = controller.selectHero(evt.getItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(newGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (selectedHero != null) {
            try {
                heroStats.append(" Hero Name: " + selectedHero.getString("name"));
                heroStats.append("\n");
                heroStats.append(" Hero Class: " + selectedHero.getString("heroclass"));
                heroStats.append("\n");
                heroStats.append(" Armor: " + selectedHero.getString("armor"));
                heroStats.append("\n");
                heroStats.append(" Weapon: " + selectedHero.getString("weapon"));
                heroStats.append("\n");
                heroStats.append(" Attack Points: " + selectedHero.getInt("attack"));
                heroStats.append("\n");
                heroStats.append(" Defense Points: " + selectedHero.getInt("defense"));
                heroStats.append("\n");
                heroStats.append(" Helm Points: " + selectedHero.getInt("helm"));
                heroStats.append("\n");
                heroStats.append(" Hit Points: " + selectedHero.getInt("hitpoints"));
                heroStats.append("\n");
                heroStats.append(" Level: " + selectedHero.getInt("level"));
                heroStats.append("\n");
                heroStats.append(" Experience: " + selectedHero.getInt("experience"));
            } catch (SQLException ex) {
                Logger.getLogger(newGui.class.getName()).log(Level.SEVERE, null, ex);
            }
            jTextArea1.setText(heroStats.toString());
        }
    }//GEN-LAST:event_availableHeroesItemStateChanged

    private void heroClassCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_heroClassCheckBoxItemStateChanged

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
    }//GEN-LAST:event_heroClassCheckBoxItemStateChanged

    private void startGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameBtnActionPerformed

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
                        startGameBtn.setVisible(false);
                        mapPanel.setVisible(true);
                        createNewHeroPanel.setVisible(false);
                        pack();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Please Pick A Different Hero Name..." + heroName.getText() + " Already Exists",
                                "Hero Name",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Pick A Hero Class For The Hero",
                        "Hero Class",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Hero Name Cannot Be Empty",
                    "Empty Hero Name",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_startGameBtnActionPerformed

    private void playGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playGameBtnActionPerformed
        // TODO add your handling code here:
        availableHeroPanel.setVisible(false);
        mapPanel.setVisible(true);
        pack();
    }//GEN-LAST:event_playGameBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel availableHeroPanel;
    private javax.swing.JComboBox<String> availableHeroes;
    private javax.swing.JPanel btnContainer;
    private javax.swing.JPanel containerPanel;
    private javax.swing.JButton createNewHeroBtn;
    private javax.swing.JPanel createNewHeroPanel;
    private javax.swing.JButton exitGame;
    private javax.swing.JTextField heroName;
    private javax.swing.JCheckBox hunterCheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel mapPanel;
    private javax.swing.JTextArea mapTextArea;
    private javax.swing.JButton playGameBtn;
    private javax.swing.JCheckBox priestCheckBox;
    private javax.swing.JButton selectExsitingHero;
    private javax.swing.JButton startGameBtn;
    private javax.swing.JPanel statsPanel;
    private javax.swing.JCheckBox warriorCheckBox;
    // End of variables declaration//GEN-END:variables
}
