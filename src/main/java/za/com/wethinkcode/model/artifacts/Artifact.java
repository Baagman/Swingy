/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Artifact.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/17 16:40:16 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/17 16:44:46 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.artifacts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class Artifact {
    protected String    name;
    protected int       points;

    protected Artifact(String name, int points) {
        this.name = name;
        this.points = points;
    }
}