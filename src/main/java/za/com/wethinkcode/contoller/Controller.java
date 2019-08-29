/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Controller.java                             :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/18 16:06:42 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/05 14:14:04 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.contoller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.Exceptions.InvalidHero;
import za.com.wethinkcode.model.characters.*;
import za.com.wethinkcode.model.coordinates.Coordinates;
import za.com.wethinkcode.model.util.Database;
import za.com.wethinkcode.view.console.ConsoleView;

@Getter
@Setter
public class Controller {

	private Hero hero;
	private ConsoleView consoleView;
	private Scanner scanner;
	private Database database;
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static boolean quitGame = true;
	private static boolean consoleViewMode;
	private ArrayList<Villain> villains;

	public Controller(Database database) {
		this.database = database;
	}

	public void PlayerInit(String mode) throws SQLException, InvalidHero {

		this.scanner = new Scanner(System.in);
		consoleView = new ConsoleView();

		while (quitGame) {
			if (mode.equalsIgnoreCase("console")) {
				consoleViewMode = true;
				ConsoleMode();
			}
		}
		scanner.close();
	}

	private void ConsoleMode() throws SQLException, InvalidHero {

		consoleView.DisplayOptions("Player Selection");
		String userInput = scanner.nextLine();

		switch (userInput) {
			case "0":
				quitGame = false;
				break;
			case "1":
				if (consoleView.displayHeroStats(database.AvailableHeroes()) > 0) {
					System.out.print("Please Select A Hero By Entering Their Name: ");
					userInput = scanner.nextLine();
					setHero(createHero(database.selectHero(userInput)));
					if (hero != null) {
						quitGame = GameInit();
					} else
						throw new InvalidHero("No Such Hero");
				} else {
					System.out.println("No Heroes available...Please Create A New Hero..");
					System.out.println("-----------------------");
				}
				break;
			case "2":
				String heroclass;
				consoleView.DisplayOptions("selecting hero class");
				userInput = scanner.nextLine();
				heroclass = getClassHeroType(userInput);
				if (heroclass != null) {
					database.setHeroClass(heroclass);
				} else
					throw new InvalidHero("Unknown Hero Class");
				System.out.print("To Create A New Hero Enter the Hero's name: ");
				userInput = scanner.nextLine();
				if (checkIfHeroNameExist(userInput)) {
					setHero(createHero(database.selectHero(userInput)));
					if (this.hero != null) {
						hero.setHeroClass(heroclass);
						quitGame = GameInit();
					} else
						throw new InvalidHero("Cannot Create New Hero");
				} else {
					System.out.println("Hero Name Already Exists...Please Try Other Options");
					System.out.println("-----------------------");
				}
				break;
			default:
				System.out.println("Invalid Input Please Try Again");
				System.out.println("-----------------------");
		}
	}

	private void Move(String direction, int sizeOfMap) {

		switch (direction) {
			case "1":
				if (hero.getPosition().getY() > 0)
					hero.getPosition().setY(hero.getPosition().getY() - 1);
				break;
			case "2":
				if (hero.getPosition().getX() < sizeOfMap - 1)
					hero.getPosition().setX(hero.getPosition().getX() + 1);
				break;
			case "4":
				if (hero.getPosition().getY() < (sizeOfMap - 1))
					hero.getPosition().setY(hero.getPosition().getY() + 1);
				break;
			case "3":
				if (hero.getPosition().getX() > 0)
					hero.getPosition().setX(hero.getPosition().getX() - 1);
				break;
		}
	}

	private Hero createHero(ResultSet resultSet) throws SQLException {
		String heroclass;
		if ((resultSet != null) && (resultSet.next())) {
			heroclass = resultSet.getString("heroclass");
			if (heroclass.equalsIgnoreCase("Warrior")) {
				return new Warrior(resultSet.getString("name"),
						resultSet.getInt("attack"),
						resultSet.getInt("defense"),
						resultSet.getInt("hitpoints"),
						resultSet.getInt("level"),
						resultSet.getInt("experience"));
			} else if (heroclass.equalsIgnoreCase("Hunter")) {
				return  new Hunter(resultSet.getString("name"),
						resultSet.getInt("attack"),
						resultSet.getInt("defense"),
						resultSet.getInt("hitpoints"),
						resultSet.getInt("level"),
						resultSet.getInt("experience"));
			} else if (heroclass.equalsIgnoreCase("Priest")) {
				return  new Priest(resultSet.getString("name"),
						resultSet.getInt("attack"),
						resultSet.getInt("defense"),
						resultSet.getInt("hitpoints"),
						resultSet.getInt("level"),
						resultSet.getInt("experience"));
			}
		}
		return null;
	}

	private boolean GameInit() throws SQLException {

		int playerPosition;
		String[] movements = {"1", "2", "3", "4"};

		if (consoleViewMode) {
			getConsoleView().setMapSize((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
			playerPosition = getConsoleView().getMapSize() / 2;
			hero.setPosition(new Coordinates(playerPosition, playerPosition));
			getConsoleView().setHero(hero);
		}
		villains = generateVillains((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
		while (true) {
			if (consoleViewMode) {
				String userInput;
				getConsoleView().printAndUpdateMap(villains);
				getConsoleView().DisplayOptions("Play");
				userInput = getScanner().nextLine();
				if (userInput.equals("0"))
					break;
				else if (Arrays.asList(movements).contains(userInput)) {
					Villain villain = checkForEnemies(userInput, hero.getPosition().getX(), hero.getPosition().getY());
					if (villain != null) {
						Coordinates previousPosition = new Coordinates(hero.getPosition().getX(), hero.getPosition().getY());
						Move(userInput, getConsoleView().getMapSize());
						getConsoleView().printAndUpdateMap(villains);
						getConsoleView().DisplayOptions("enemy ahead");
						userInput = getScanner().nextLine();
						if (userInput.equals("1")) {
							hero.Run(previousPosition);
						} else if (userInput.equals("2")) {
							if (simulateFight(villain)) {
								villains.remove(villain);
								System.out.println("You Won The Battle");
								System.out.println("-----------------------");
							} else {
								System.out.println("You Lost...\nGame Over");
								System.out.println("-----------------------");
								return false;
							}
						}
					}
					else
						Move(userInput, getConsoleView().getMapSize());
				} else {
					System.out.println("Invalid Input Please Try Again");
					System.out.println("-----------------------");
				}
			}
		}
		return false;
	}

	private Villain checkForEnemies(String userInput, int coordsX, int coordsY) {

		switch (userInput) {
			case "1":
				coordsY--;
				break;
			case "2":
				coordsX++;
				break;
			case "4":
				coordsY++;
				break;
			case "3":
				coordsX--;
				break;
		}
		for (Villain villain : villains) {
			if (villain.getPosition().getY() == coordsY) {
				if (villain.getPosition().getX() == coordsX)
					return villain;
			}
		}
		return null;
	}

	private String getClassHeroType(String index) {
		HashMap<String, String> heroClass = new HashMap<>();
		heroClass.put("1", "Warrior");
		heroClass.put("2", "Hunter");
		heroClass.put("3", "Priest");
		return heroClass.get(index);
	}

	private boolean checkIfHeroNameExist(String heroName) throws SQLException {

		boolean isExist = true;
		if ((heroName != null) && (!heroName.isEmpty())) {
			ResultSet resultSet =  database.selectHero(heroName);
			if ((resultSet != null) && (resultSet.next()))
				isExist = false;
			else
				database.addNewHeroToTable(heroName);
		} else
			isExist = false;
		return isExist;
	}

	private ArrayList<Villain> generateVillains(int mapSize) {

		ArrayList<Villain> villains = new ArrayList<>();
		int position;
		int x = 0;
		for (int y = 0; y <= mapSize * 2; y++) {
			position = ThreadLocalRandom.current().nextInt(0, mapSize);
			if ((hero.getPosition().getX() != position) || (hero.getPosition().getY() != y)) {
				Villain villain = getVillainType();
				if (villain != null) {
					if (y > mapSize) {
						if ((hero.getPosition().getX() != position) || (hero.getPosition().getY() != x)) {
							villain.setPosition(new Coordinates(position, x));
							villains.add(villain);
						}
						x++;
					} else {
						villain.setPosition(new Coordinates(position, y));
						villains.add(villain);
					}
				}
			}
		}
		setVillains(villains);
		return villains;
	}

	private boolean simulateFight(Villain villain) throws SQLException {
		Random random = new Random();
		while ((hero.getHitPoints() > 0) && (villain.getHitPoints() > 0)) {
			int heroAction = random.nextInt(2);
			int villainAction = random.nextInt(2);
			int damageTaken;

			if ((heroAction == 1) && (villainAction == 1)) {
				hero.setHitPoints(hero.getHitPoints() - villain.getAttack());
				villain.setHitPoints(hero.getHitPoints() - hero.getAttack());
			} else if ((heroAction == 1) && (villainAction == 0)) {
				damageTaken = Math.max((hero.getAttack() - villain.getDefense()), 0);
				villain.setHitPoints(villain.getHitPoints() - damageTaken);
			} else if ((heroAction == 0) && (villainAction == 1)) {
				damageTaken = Math.max((villain.getAttack() - hero.getDefense()), 0);
				hero.setHitPoints(villain.getHitPoints() - damageTaken);
			}
		}
		if (hero.getHitPoints() > 0) {
			int xp = villain.getAttack() + villain.getDefense();
			addExperience(xp);
			return true;
		}
		return false;
	}

	private Villain getVillainType() {
		Random random = new Random();
		HashMap<String, Integer> villainStats = new HashMap<>();
		int randomNumber = random.nextInt(2);
		int hotPoints = random.nextInt(hero.getHitPoints() + 1);

		villainStats.put("Attack", random.nextInt(hero.getAttack()));
		villainStats.put("Defense", random.nextInt(hero.getDefense()));
		if (randomNumber == 0) {
			return new DarkElf("Dark Elf", villainStats.get("Attack"), villainStats.get("Defense"), hotPoints, null);
		} else if (randomNumber == 1)
			return new Ogre("Ogre", villainStats.get("Attack"), villainStats.get("Defense"), hotPoints, null);
		else
			return null;
	}

	private void addExperience(int experience) throws SQLException {
		if (consoleViewMode) {
			int tempLevel = hero.getLevel() + 1;
			int levelingUpXp = tempLevel + 1 * 1000 + (int)Math.pow((tempLevel - 1), 2) * 450;
			hero.setExperience(hero.getExperience() + experience);
			database.updateHero(hero);
			int playerPosition;

			if (hero.getLevel() >= levelingUpXp) {
				hero.setLevel(hero.getLevel() + 1);
				getConsoleView().setMapSize((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
				playerPosition = getConsoleView().getMapSize() / 2;
				hero.setPosition(new Coordinates(playerPosition, playerPosition));
				villains = null;
				villains = generateVillains((hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2));
			}
		}
	}
}
