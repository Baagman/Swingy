/* ************************************************************************** */
 /*                                                                            */
 /*                                                        :::      ::::::::   */
 /*   ConsoleView.java                                   :+:      :+:    :+:   */
 /*                                                    +:+ +:+         +:+     */
 /*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
 /*                                                +#+#+#+#+#+   +#+           */
 /*   Created: 2019/07/19 10:48:49 by tbaagman          #+#    #+#             */
 /*   Updated: 2019/08/05 14:18:12 by tbaagman         ###   ########.fr       */
 /*                                                                            */
 /* ************************************************************************** */
package za.com.wethinkcode.view.console;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.validation.constraints.NotNull;

import za.com.wethinkcode.Exceptions.InvalidHero;
import za.com.wethinkcode.contoller.Controller;
import za.com.wethinkcode.model.artefacts.Artefact;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.characters.Villain;
import za.com.wethinkcode.model.coordinates.Coordinates;
import za.com.wethinkcode.model.util.Database;

@Getter
@Setter
public class ConsoleView {

    @NotNull
    private Hero hero;
    private String[][] map;
    private int mapSize;
    private Database database;
    private boolean quitGame;
    private Scanner scanner;
    private Controller controller;
    private ArrayList<Villain> villains;

    public ConsoleView(Database database) {
        if (database != null) {
            this.database = database;
        }
    }

    public void playerInit() throws SQLException, InvalidHero {
        quitGame = true;
        scanner = new Scanner((System.in));
        controller = new Controller(database);
        String userInput;

        while (quitGame) {
            displayOptions("player selection");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "0":
                    quitGame = false;
                    break;
                case "1":
                    selectAHero();
                    break;
                case "2": {
                    String heroClass;
                    boolean isHeroCreated = true;
                    while (isHeroCreated) {
                        displayOptions("selecting hero class");
                        userInput = scanner.nextLine();
                        heroClass = controller.getClassHeroType(userInput);
                        if (heroClass == null) {
                            System.out.println("Error: Unknown Hero Class...Please Try Again");
                        } else {
                            boolean emptyHeroName = true;
                            while (emptyHeroName) {
                                System.out.print("To Create A New Hero Enter the Hero's name: ");
                                userInput = scanner.nextLine();
                                if (!userInput.isEmpty()) {
                                    emptyHeroName = false;
                                    if (controller.checkIfHeroNameExist(userInput, heroClass)) {
                                        setHero(controller.createHero(userInput));
                                        if (hero == null) {
                                            throw new InvalidHero("Cannot Create New Hero");
                                        } else {
                                            isHeroCreated = false;
                                        }
                                    }
                                }
                            }
                            if (!isHeroCreated) {
                                consoleGameInit();
                            }
                        }
                    }
                }
                break;
                default:
                    System.out.println("Invalid Input...Please Try Again");
                    System.out.println("---------------------");
            }
        }
    }

    private void printAndUpdateMap() {
        int mapSize = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2)));
        setMap(new String[mapSize][mapSize]);
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if ((x == getHero().getPosition().getX()) && (y == getHero().getPosition().getY())) {
                    this.map[y][x] = "H";
                } else if (getVillainsToDisplay(villains, x, y)) {
                    this.map[y][x] = "E";
                } else {
                    this.map[y][x] = ".";
                }
                System.out.print(map[y][x]);
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    private void displayOptions(String gameMode) {

        boolean inputField = true;
        switch (gameMode.toLowerCase()) {
            case "play":
                System.out.println("0 - Exit");
                System.out.println("1 - North");
                System.out.println("2 - East");
                System.out.println("3 - West");
                System.out.println("4 - South");
                break;
            case "player selection":
                System.out.println("0 - Exit");
                System.out.println("1 - Select Hero");
                System.out.println("2 - Create new Hero");
                break;
            case "selecting hero class":
                System.out.println("1 - Warrior");
                System.out.println("2 - Hunter");
                System.out.println("3 - Priest");
                break;
            case "enemy ahead":
                System.out.println("1 - Run");
                System.out.println("2 - Fight");
                break;
            case "battle won":
                System.out.println("You Won The Battle");
                System.out.println("-----------------------");
                inputField = false;
                break;
            case "battle lost":
                System.out.println("You Lost...\nGame Over");
                inputField = false;
                break;
            case "game won":
                System.out.println("You Won The Game....\nEnter 1 To Continue Or Any Other Key To Quit Game");
                break;
            case "artefact dropped":
                System.out.println("Enter 1 To Pick It Up Or Press Any Other Key To Continue");
                break;
        }

        if (inputField) {
            System.out.println("-----------------------");
            System.out.print("Please Input Option: ");
        }
    }

    private int displayHeroStats(ResultSet resultSet) {
        int id = 0;
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    ++id;
                    System.out.println("Id: " + id);
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Hero Class: " + resultSet.getString("heroclass"));
                    System.out.println("Armor: " + resultSet.getString("armor"));
                    System.out.println("Weapon: " + resultSet.getString("weapon"));
                    System.out.println("Helm: " + resultSet.getString("helm"));
                    System.out.println("Attack: " + resultSet.getInt("attack"));
                    System.out.println("Defense: " + resultSet.getInt("defense"));
                    System.out.println("Hit points: " + resultSet.getInt("hitpoints"));
                    System.out.println("Level: " + resultSet.getInt("level"));
                    System.out.println("Experience: " + resultSet.getInt("experience"));
                    System.out.println("-----------------------");
                }
                return id;
            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
                return 0;
            }
        }
        return id;
    }

    private boolean getVillainsToDisplay(ArrayList<Villain> villains, int x, int y) {
        for (Villain villain : villains) {
            if (villain.getPosition().getY() == y) {
                if (villain.getPosition().getX() == x) {
                    return true;
                }
            }
        }
        return false;
    }

    private void displayVillainStats(Villain villain) {
        System.out.println(villain.getName() + " Encountered");
        System.out.println("Attack: " + villain.getAttack());
        System.out.println("Defense: " + villain.getDefense());
        System.out.println("Hit points: " + villain.getHitPoints());
        System.out.println("-----------------------");
    }

    private void displayArtefactStats(Villain villain, Artefact artefact) {
        System.out.println(villain.getName() + " Dropped An Artefact: " + artefact.getName());
        System.out.println("Points: " + artefact.getPoints());
    }

    private void consoleGameInit() {
        int playerPosition;
        String userInput;
        playerPosition = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2))) / 2;
        hero.setPosition(new Coordinates(playerPosition, playerPosition));

        villains = controller.generateVillains();
        while (quitGame) {
            printAndUpdateMap();
            displayOptions("play");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "0":
                    quitGame = false;
                    break;
                case "1":
                case "2":
                case "3":
                case "4": {
                    Villain villain = controller.checkForEnemies(userInput, hero.getPosition());
                    if (villain != null) {
                        Coordinates previousPosition = new Coordinates(hero.getPosition().getX(), hero.getPosition().getY());
                        enemyEncountered(userInput, villain, previousPosition);
                    } else {
                        controller.Move(userInput);
                    }
                    if (controller.GameWon()) {
                        printAndUpdateMap();
                        displayOptions("Game Won");
                        userInput = scanner.nextLine();
                        if (userInput.equals("1")) {
                            playerPosition = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2))) / 2;
                            hero.setPosition(new Coordinates(playerPosition, playerPosition));
                        } else {
                            quitGame = false;
                        }
                    }
                }
            }
        }
    }

    private void enemyEncountered(String userInput, Villain villain, Coordinates coordinates) {
        boolean invalidInput = true;
        controller.Move(userInput);

        while (invalidInput) {
            printAndUpdateMap();
            displayVillainStats(villain);
            displayOptions("enemy ahead");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "0":
                    quitGame = false;
                    invalidInput = false;
                    break;
                case "1":
                    hero.Run(coordinates);
                    invalidInput = false;
                    break;
                case "2":
                    invalidInput = false;
                    if (controller.simulateFight(villain)) {
                        displayOptions("battle won");
                        Artefact artefact = controller.generateRandomArtifact(villain);
                        if (artefact != null) {
                            displayArtefactStats(villain, artefact);
                            displayOptions("artefact dropped");
                            userInput = scanner.nextLine();
                            if (userInput.equals("1")) {
                                try {
                                    controller.Equip(artefact);
                                } catch (SQLException sqlException) {
                                    System.out.println("Error: " + sqlException.getMessage());
                                    quitGame = false;
                                }
                            }
                        }
                        isHeroLevelUp(villain);
                        try {
                            controller.updateHero();
                        } catch (SQLException sqlException) {
                            System.out.println("Error: " + sqlException.getMessage());
                            quitGame = false;
                        }
                    } else {
                        displayOptions("Battle Lost");
                        quitGame = false;
                    }
                    break;
                default:
                    System.out.println("Invalid Input...Please Try Again");
            }
        }
    }

    private void selectAHero() throws SQLException {

        String userInput;
        int numberOfTries = 3;

        while (true) {
            if (displayHeroStats(controller.getAvailableHeroes()) > 0) {
                System.out.print("Please  Select A Hero By Entering Their Hero Name: ");
                userInput = scanner.nextLine();
                setHero(controller.createHero(userInput));
                if (hero != null) {
                    break;
                } else {
                    numberOfTries -= 1;
                    if (numberOfTries == 0) {
                        System.out.println("You Have Used All Your Retries...Exiting");
                        quitGame = false;
                        break;
                    } else {
                        System.out.println("Warning: No Such Hero...Please Try Again");
                        System.out.println("-----------------------");
                    }
                }
            } else {
                System.out.println("No Available Heroes...Please Try Another Option");
                break;
            }
        }
        if (numberOfTries > 0) {
            consoleGameInit();
        }
    }

    private void isHeroLevelUp(Villain villain) {
        villains.remove(villain);
        int playerPosition = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2))) / 2;
        if ((playerPosition == hero.getPosition().getX()) && (playerPosition == hero.getPosition().getY())) {
            villains = null;
            villains = controller.generateVillains();
        }
    }
}
