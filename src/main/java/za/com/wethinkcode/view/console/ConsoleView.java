/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ConsoleView.java                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/19 10:48:49 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/29 13:52:01 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.view.console;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import za.com.wethinkcode.model.characters.Hero;

@Getter
@Setter
public class ConsoleView {
	
	@NotNull
	private Hero 		hero;
	private String[][] 	map;
	private int 		mapSize;
	
	public ConsoleView(Hero hero) {
		this.hero = hero;
		this.mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);
	}
	
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
			System.out.println("");
		}
		System.out.println("");
		return ;
	}

	public void DisplayMenu() {
		System.out.println("1 - North");
		System.out.println("2 - East");
		System.out.println("3 - South");
		System.out.println("4 - West");
		System.out.println("5 - Exit");
	}
}