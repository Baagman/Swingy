/* ************************************************************************** */
 /*                                                                            */
 /*                                                        :::      ::::::::   */
 /*   Coordinates.java                                   :+:      :+:    :+:   */
 /*                                                    +:+ +:+         +:+     */
 /*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
 /*                                                +#+#+#+#+#+   +#+           */
 /*   Created: 2019/07/19 16:22:27 by tbaagman          #+#    #+#             */
 /*   Updated: 2019/07/19 16:23:53 by tbaagman         ###   ########.fr       */
 /*                                                                            */
 /* ************************************************************************** */
package za.com.wethinkcode.model.coordinates;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class Coordinates {

    @Min(value = 0)
    private int x;
    @Min(value = 0)
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
