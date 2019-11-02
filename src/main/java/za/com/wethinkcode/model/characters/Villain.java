/* ************************************************************************** */
 /*                                                                            */
 /*                                                        :::      ::::::::   */
 /*   Villain.java                                        :+:      :+:    :+:   */
 /*                                                    +:+ +:+         +:+     */
 /*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
 /*                                                +#+#+#+#+#+   +#+           */
 /*   Created: 2019/07/17 16:50:19 by tbaagman          #+#    #+#             */
 /*   Updated: 2019/07/18 10:13:27 by tbaagman         ###   ########.fr       */
 /*                                                                            */
 /* ************************************************************************** */
package za.com.wethinkcode.model.characters;

import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.artefacts.Artefact;

@Getter
@Setter
public abstract class Villain extends Characters {

    Villain(String name, int attack, int defense, int hitPoints) {
        super(name, attack, defense, hitPoints);
    }
}
