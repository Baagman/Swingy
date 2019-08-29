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
import javax.validation.constraints.NotNull;
import za.com.wethinkcode.model.characters.Hero;
import za.com.wethinkcode.model.characters.Villain;

@Getter
@Setter
public class ConsoleView {

	@NotNull
	private Hero 		hero;
	private String[][] 	map;
	private int 		mapSize;

	public void printAndUpdateMap(ArrayList<Villain> villains) {
		setMap(new String[this.mapSize][this.mapSize]);
		for (int y = 0; y < this.mapSize; y++) {
			for (int x = 0; x < this.mapSize; x++) {
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

	public void DisplayOptions(String gameMode) {

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
				break;
			case "battle lost":
				System.out.println("You Lost...\nGame Over");
				break;
		}
		System.out.println("-----------------------");
		System.out.print("Please Input Option: ");
	}

	public int  displayHeroStats(ResultSet resultSet) {
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

	public boolean getVillainsToDisplay(ArrayList<Villain> villains, int x, int y) {
		for (Villain villain : villains) {
			if (villain.getPosition().getY() == y) {
				if (villain.getPosition().getX() == x)
				return true;
			}
		}
		return false;
	}
}