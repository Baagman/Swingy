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

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.artefacts.Armor;
import za.com.wethinkcode.model.artefacts.Helm;
import za.com.wethinkcode.model.artefacts.Weapon;
import za.com.wethinkcode.model.coordinates.Coordinates;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public abstract class Hero extends Characters {

    @NotBlank(message = "Hero Class Cannot Be Empty")
    private String heroClass;
    @Min(value = 1, message = "Min Start Level Is 1")
    @Max(value = 5, message = "Max Level Is 5")
    private int level;
    @Min(value = 1000, message = "Min Experience Is 1000")
    @Max(value = 12200, message = "Max Experience Is 12200")
    private int experience;

    private Weapon weapon;
    private Armor armor;
    private Helm helm;

    Hero(String name, int attack, int defense, int hitPoints, int level, int experience) {
        super(name, attack, defense, hitPoints);
        setExperience(experience);
        setLevel(level);
    }

    public void equipWeapon(Weapon weapon) {
        if (weapon != null) {
            if (this.weapon != null) {
                this.attack -= this.weapon.getPoints();
                this.attack = getAttack() > 0 ? getAttack() : 0;
            }
            this.attack += weapon.getPoints();
            this.weapon = weapon;
        }
    }

    public void equipArmor(Armor armor) {
        if (armor != null) {
            if (this.armor != null) {
                this.defense -= getArmor().getPoints();
                this.defense = getDefense() > 0 ? getDefense() : 0;
            }
            this.defense += armor.getPoints();
            this.armor = armor;
        }
    }

    public void equipHelm(Helm helm) {
        if (helm != null) {
            if (this.helm != null) {
                this.hitPoints -= getHelm().getPoints();
                this.hitPoints = getHitPoints() > 0 ? getHitPoints() : 0;
            }
            this.hitPoints += helm.getPoints();
            this.helm = helm;
        }
    }

    public void Run(Coordinates previousPosition) {
        this.position = previousPosition;
    }
}
