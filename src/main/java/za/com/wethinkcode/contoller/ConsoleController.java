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

package za.com.wethinkcode.contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.util.Database;
import za.com.wethinkcode.view.console.ConsoleView;

@Getter
@Setter
public class ConsoleController {

	private File file;
	private Hero hero;
	private ConsoleView consoleView;
	private Scanner scanner;
	private Database database;
	
	public ConsoleController(Database database ) {
		this.database = database;
	}

	public void Read() {
		String[] splitParams;
		BufferedReader bufferedReader;
		String line;
		
		try {
			bufferedReader = new BufferedReader(new FileReader(this.file));
			while ((line = bufferedReader.readLine()) != null) {
				splitParams = line.split(",");
				setHero(new Hero(splitParams[0], 
						Integer.parseInt(splitParams[1]),
						Integer.parseInt(splitParams[2]),
						Integer.parseInt(splitParams[3]),
						Integer.parseInt(splitParams[4]),
						Integer.parseInt(splitParams[5])));
			}
			bufferedReader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void GameInit() {
		
		boolean quitGame = true;
		scanner = new Scanner(System.in);
		consoleView = new ConsoleView();
		
		while (quitGame) {
			String userInput;
			
			consoleView.DisplayMenu("Start");
			userInput = scanner.nextLine();
			if (userInput.equals("0"))
				quitGame = false;
			else if (userInput.equals("1")) {
				if (consoleView.displayHeros(database.selectAvailabeHeros()) > 0) {
					System.out.println("Please Select A Hero By Entering Their Name:");
					userInput = scanner.nextLine();
				} else
					System.out.println("No Heroes available...Please Create A New Hero..");
			} else if (userInput.equals("2")) {
				System.out.print("Enter the Hero's name: ");
				userInput = scanner.nextLine();
				database.addNewHeroToTable(userInput);
			} else if (userInput.equals("3")) {
				
			}
		}
		scanner.close();
	}

	public void Move(int direction, int sizeOfMap) {

		switch (direction) {
			case 1:
				if (getHero().getPosition().getY() > 0)
					getHero().getPosition().setY(getHero().getPosition().getY() - 1);
				break;
			case 2:
				if (getHero().getPosition().getX() < sizeOfMap - 1)
					getHero().getPosition().setX(getHero().getPosition().getX() + 1);
				break;
			case 3:
				if (getHero().getPosition().getY() < (sizeOfMap - 1))
					getHero().getPosition().setY(getHero().getPosition().getY() + 1);
				break;
			case 4:
				if (getHero().getPosition().getX() > 0)
					getHero().getPosition().setX(getHero().getPosition().getX() - 1);
				break;
		}
	}
}
