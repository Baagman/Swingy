/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ConsoleController.java                             :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/18 16:06:42 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/05 14:14:04 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.contoller.console;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.contoller.IController;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.coordinates.Coordinates;
import za.com.wethinkcode.model.util.Database;
import za.com.wethinkcode.view.console.ConsoleView;

@Getter
@Setter
public class ConsoleController implements IController {

	private Hero hero;
	private ConsoleView consoleView;
	private Scanner scanner;
	private Database database;

	public ConsoleController(Database database) {
		this.database = database;
	}

	public void PlayerInit() throws SQLException {

		boolean quitGame = true;
		this.scanner = new Scanner(System.in);
		consoleView = new ConsoleView();

		while (quitGame) {
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
							throw new SQLException("No Such Hero");
					} else
						System.out.println("No Heroes available...Please Create A New Hero..");
					break;
				case "2":
					System.out.print("To Create A New Hero Enter the Hero's name: ");
					userInput = scanner.nextLine();
					database.addNewHeroToTable(userInput);
					break;
			}
		}
		scanner.close();
	}

	public void Move(String direction, int sizeOfMap) {


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

	public Hero createHero(ResultSet resultSet) throws SQLException {
		if ((resultSet != null) && (resultSet.next())) {
			return new Hero(resultSet.getString("name"),
					resultSet.getInt("attack"),
					resultSet.getInt("defense"),
					resultSet.getInt("hitpoints"),
					resultSet.getInt("level"),
					resultSet.getInt("experience"));
		}
		return null;
	}

	public boolean GameInit() {

		int playerPosition;

		getConsoleView().setMapSize((getHero().getLevel() - 1) * 5 + 10 - (getHero().getLevel() % 2));
		playerPosition = getConsoleView().getMapSize() / 2;
		getHero().setPosition(new Coordinates(playerPosition, playerPosition));
		getConsoleView().setHero(getHero());

		while (true) {
			String userInput;
			getConsoleView().printAndUpdateMap();
			getConsoleView().DisplayOptions("Play");
			userInput = getScanner().nextLine();
			if (userInput.equals("0"))
				break;
			else {
				Move(userInput, getConsoleView().getMapSize());
			}
		}
		return false;
	}
}