/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Characters.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 15:23:10 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/19 12:02:42 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.characters;

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.coordinates.Coordinates;

@Getter
@Setter
abstract public class Characters {

	protected String    	name;
	protected int       	attack;
	protected int       	defense;
	protected int       	hitPoints;
	protected Coordinates 	position;
	
	protected Characters(String name, int attack, int defense, int hitPoints) {
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.hitPoints = hitPoints;
	}
}
