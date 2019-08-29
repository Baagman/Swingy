/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Hero.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 15:33:44 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/01 10:58:14 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.characters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.artefacts.Armor;
import za.com.wethinkcode.model.artefacts.Helm;
import za.com.wethinkcode.model.artefacts.Weapon;
import za.com.wethinkcode.model.coordinates.Coordinates;

@Getter
@Setter
public abstract class Hero extends Characters {

	private String 		heroClass;
	private int			level;
	private int experience;

	@Setter(AccessLevel.NONE)
	private Weapon		weapon;
	private Armor		armor;
	private Helm		helm;

	Hero(String name, int attack, int defense, int hitPoints, int level, int experience) {
		super(name, attack, defense, hitPoints);
		setExperience(experience);
		setLevel(level);
	}

	public void equipWeapon(Weapon weapon) {

		if (weapon != null) {
			if (this.weapon != null) {
				this.attack -= this.weapon.getPoints();
				this.attack = Math.max(getAttack(), 0);
			}
			this.attack += weapon.getPoints();
		}
		this.weapon = weapon;
	}

	public void equipArmor(Armor armor) {
		if (armor != null) {
			if (this.armor != null) {
				this.defense -= getArmor().getPoints();
				this.defense = Math.max(getDefense(), 0);
			}
			this.defense += armor.getPoints();
			this.armor = armor;
		}
	}

	public void equipHelm(Helm helm) {
		if (helm != null) {
			if (this.helm != null) {
				this.hitPoints -= getHelm().getPoints();
				this.hitPoints = Math.max(getHitPoints(), 0);
			}
			this.hitPoints += helm.getPoints();
			this.helm = helm;
		}
	}

	public void Run(Coordinates previousPosition) {
		this.position = previousPosition;
	}
}