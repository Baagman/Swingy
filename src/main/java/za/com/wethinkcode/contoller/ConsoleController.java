/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ConsoleController.java                             :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/18 16:06:42 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/19 15:49:54 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.contoller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.coordinates.Coordinates;
import za.com.wethinkcode.view.console.ConsoleView;

@Getter
@Setter
public class ConsoleController {
	
	String[] heroStats = null;
	String lineRead = null;
	Hero hero;
	ConsoleView consoleView;

	public ConsoleController(File file) {
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ((lineRead = bufferedReader.readLine()) != null)
				heroStats = lineRead.split(",");
			bufferedReader.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		this.hero = new Hero(heroStats[0], 
				Integer.parseInt(heroStats[1]), 
				Integer.parseInt(heroStats[2]), 
				Integer.parseInt(heroStats[3]),
				Integer.parseInt(heroStats[4]),
				Integer.parseInt(heroStats[5]));
		
		setConsoleView(new ConsoleView(getHero()));
		this.hero.setPosition(new Coordinates(getConsoleView().getMapSize() / 2, getConsoleView().getMapSize() / 2));
	}

	public void ConsoleStartGame() {
		getConsoleView().printAndUpdateMap();
	}
}