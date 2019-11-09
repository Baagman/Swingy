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

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
abstract public class Characters {

    @Size(min = 4, max = 20, message = "Character Name Has To Be Between 4 AND 20 Characters Long")
    protected String name;
    @Min(value = 1)
    protected int attack;
    @Min(value = 1)
    protected int defense;
    @Min(value = 1)
    protected int hitPoints;
    protected Coordinates position;

    Characters(String name, int attack, int defense, int hitPoints) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
    }
}
