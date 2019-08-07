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
import javax.validation.constraints.NotNull;
import za.com.wethinkcode.model.characters.Hero;

@Getter
@Setter
public class ConsoleView {
	
	@NotNull
	private Hero 		hero;
	private String[][] 	map;
	private int 		mapSize;
	
	public void printAndUpdateMap() {
	
		setMap(new String[this.mapSize][this.mapSize]);
		for (int y = 0; y < this.mapSize; y++) {
			for (int x = 0; x < this.mapSize; x++) {
				if ((x == getHero().getPosition().getX()) && (y == getHero().getPosition().getY())) {
					this.map[y][x] = "H";
				} else {
					this.map[y][x] = ".";
				}
				System.out.print(map[y][x]);
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}

	public void DisplayMenu(String gameMode) {
		
		switch (gameMode.toLowerCase()) {
			case "play":
				System.out.println("0 - Exit");
				System.out.println("1 - North");
				System.out.println("2 - East");
				System.out.println("3 - South");
				System.out.println("4 - West");
				break;
			case "player selection":
				System.out.println("0 - Exit");
				System.out.println("1 - Select Hero");
				System.out.println("2 - Create new Hero");
				break;				
		}
		System.out.println("-----------------------");
	}

	public int  displayHeros(ResultSet resultSet) {
		int id = 0;
		if (resultSet != null) {
			try {
				while (resultSet.next()) {
					++id;
					System.out.println("ID: " + id);
					System.out.println("Name: " + resultSet.getString("name"));
					System.out.println("Attack: " + resultSet.getInt("attack"));
					System.out.println("Defense: " + resultSet.getInt("defense"));
					System.out.println("Hit points: " + resultSet.getInt("hitpoints"));
					System.out.println("Level: " + resultSet.getInt("level"));
					System.out.println("Experience: " + resultSet.getInt("experience"));
					System.out.println("-----------------------");
				}
				return id;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
}