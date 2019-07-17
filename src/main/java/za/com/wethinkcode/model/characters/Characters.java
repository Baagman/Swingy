/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Characters.java                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 15:23:10 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/17 16:39:51 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class Characters {

	protected String    name;
	protected int       attack;
	protected int       defense;
	protected int       hitPoints;
	
	protected Characters(String name, int attack, int defense, int hitPoints) {
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		this.hitPoints = hitPoints;
	}
}
