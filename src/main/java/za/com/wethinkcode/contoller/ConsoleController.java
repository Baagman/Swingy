/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ConsoleController.java                             :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/18 16:06:42 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/31 12:45:45 by tbaagman         ###   ########.fr       */
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
import za.com.wethinkcode.model.coordinates.Coordinates;
import za.com.wethinkcode.view.console.ConsoleView;

@Getter
@Setter
public class ConsoleController {

	private File file;
	private Hero hero;
	private ConsoleView consoleView;
	
	public ConsoleController(File file) {
		this.file = file;
	}

	public void Read() {
		String[] splitParams;
		BufferedReader bufferedReader;
		String line = null;
		
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
		return ;
	}
	
	public void GameInit() {
		
		boolean quitGame = true;
		consoleView = new ConsoleView(hero);
		Scanner scanner = new Scanner(System.in);
		hero.setPosition(new Coordinates(consoleView.getMapSize() / 2, consoleView.getMapSize() / 2));
		
		while (quitGame) {
			String userInput = null;
			
			consoleView.printAndUpdateMap();
			consoleView.DisplayMenu("Play");
			userInput = scanner.nextLine();
			if (userInput.equalsIgnoreCase("5"))
				quitGame = false;
			else
				Move(Integer.parseInt(userInput), getConsoleView().getMapSize());
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
