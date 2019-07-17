/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Hero.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 15:33:44 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/17 17:50:51 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.characters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hero extends Characters {

	private String 	heroClass;
	private int 	level;
	private int		xp;
	
    public Hero(String name, int attack, int defense, int hitPoints) {
        super(name, attack, defense, hitPoints);
	}
}
