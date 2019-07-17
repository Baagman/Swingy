/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Villan.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 16:50:19 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/17 17:30:46 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.characters;

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.artifacts.Artifact;

@Getter
@Setter
public class Villan extends Characters {
	
	private Artifact artifact;
	
	protected Villan(String name, int attack, int defense, int hitPoints, Artifact artifact) {
		super(name, attack, defense, hitPoints);
		this.artifact = artifact;
	}
}