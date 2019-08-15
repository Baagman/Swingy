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
					if (getHero() != null) {
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
				heroclass = getClassHeroTyoe(userInput);
				if (heroclass != null) {
					database.setHeroClass(heroclass);
				} else
					throw new InvalidHero("Unknown Hero Class");
				System.out.print("To Create A New Hero Enter the Hero's name: ");
				userInput = scanner.nextLine();
				if (checkIfHeroNameExist(userInput)) {
					setHero(createHero(database.selectHero(userInput)));
					if (this.hero != null) {
						getHero().setHeroClass(heroclass);
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
				if (getHero().getPosition().getY() > 0)
					getHero().getPosition().setY(getHero().getPosition().getY() - 1);
				break;
			case "2":
				if (getHero().getPosition().getX() < sizeOfMap - 1)
					getHero().getPosition().setX(getHero().getPosition().getX() + 1);
				break;
			case "3":
				if (getHero().getPosition().getY() < (sizeOfMap - 1))
					getHero().getPosition().setY(getHero().getPosition().getY() + 1);
				break;
			case "4":
				if (getHero().getPosition().getX() > 0)
					getHero().getPosition().setX(getHero().getPosition().getX() - 1);
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

	private boolean GameInit() {

		int playerPosition;
		String[] movements = {"1", "2", "3", "4"};
		ArrayList<Villain> villains;

		if (consoleViewMode) {
			getConsoleView().setMapSize((getHero().getLevel() - 1) * 5 + 10 - (getHero().getLevel() % 2));
			playerPosition = getConsoleView().getMapSize() / 2;
			getHero().setPosition(new Coordinates(playerPosition, playerPosition));
			getConsoleView().setHero(getHero());
		}
		villains = generateVillains((getHero().getLevel() - 1) * 5 + 10 - (getHero().getLevel() % 2));
		while (true) {
			if (consoleViewMode) {
				String userInput;
				getConsoleView().printAndUpdateMap(villains);
				getConsoleView().DisplayOptions("Play");
				userInput = getScanner().nextLine();
				if (userInput.equals("0"))
					break;
				else if (Arrays.asList(movements).contains(userInput)) {
					Move(userInput, getConsoleView().getMapSize());
				} else {
					System.out.println("Invalid Input Please Try Again");
					System.out.println("-----------------------");
				}
			}
		}
		return false;
	}

	private String getClassHeroTyoe(String index) {
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
			else {
				database.addNewHeroToTable(heroName);
			}
		} else
			isExist = false;
		return isExist;
	}

	private ArrayList<Villain> generateVillains(int mapsize) {

		ArrayList<Villain> villains = new ArrayList<>();
		int position;
		// TODO -- FIX this villain generating algorithm
		for (int y = 1; y < mapsize * 2; y++) {
			position = ThreadLocalRandom.current().nextInt(0, mapsize + 1);
			if ((getHero().getPosition().getX() != position) || (getHero().getPosition().getY() != y)) {
				Villain villain = new Villain("Elf", 1, 1, 1, null);
				villain.setPosition(new Coordinates(position, y));
				villains.add(villain);
			}
			if ((getHero().getPosition().getX() != position) || (getHero().getPosition().getY() != y)) {
				Villain villain = new Villain("Elf", 1, 1, 1, null);
				villain.setPosition(new Coordinates(position, mapsize));
				villains.add(villain);
			}
		}
		return villains;
	}
}
