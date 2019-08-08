/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   GamePlay.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/08/08 11:35:34 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/08 11:35:37 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.contoller;

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.characters.Hero;

@Getter
@Setter
public class GamePlay {

	private Hero hero;

	public GamePlay(Hero hero) {
		this.hero = hero;
	}
}
