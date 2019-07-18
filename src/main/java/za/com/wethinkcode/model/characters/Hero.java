/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Hero.java                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 15:33:44 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/18 14:36:43 by tbaagman         ###   ########.fr       */
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
public class Hero extends Characters {

	private String 		heroClass;
	private int 		level;
	private int			xp;
	private Coordinates playerCoord;
	
	@Setter(AccessLevel.NONE)
	private Weapon		weapon;
	private Armor		armor;
	private Helm		helm;
	
    public Hero(String name, int attack, int defense, int hitPoints) {
        super(name, attack, defense, hitPoints);
	}

	public void equipWeapon(Weapon weapon) {
		
		if (weapon != null) {
			if (this.weapon != null) {
				this.attack -= this.weapon.getPoints();
				this.attack = getAttack() < 0 ? 0 : getAttack();
			}
			this.attack += weapon.getPoints();
			this.weapon = weapon;
		}
	}
	
	public void equipArmor(Armor armor) {
		if (armor != null) {
			if (this.armor != null) {
				this.defense -= getArmor().getPoints();
				this.defense = getDefense() < 0 ? 0 : getDefense();
			}
			this.defense += armor.getPoints();
			this.armor = armor;
		}
	}
	
	// TODO: Implement A EquidHelm method
	public void equipHelm(Helm helm) {
		
	}
	
	public void AddExperience(int addXp) {
		int levelingUPXp;
		this.xp += addXp;

		levelingUPXp = this.level*1000 + (int)Math.pow(this.level - 1, 2) * 450;
		if (getXp() >= levelingUPXp) {
			// TODO: Implement A Leveling Up method
			// TODO: Implement A Method that will update the map when the player levels up
		}
	}	
}
