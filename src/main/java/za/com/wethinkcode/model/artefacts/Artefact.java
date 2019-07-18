/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Artefact.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 16:40:16 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/18 10:12:42 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.artefacts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class Artefact {
    protected String name;
    protected int points;

    protected Artefact(String name, int points) {
        this.name = name;
        this.points = points;
    }
}