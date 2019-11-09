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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
abstract public class Artefact {

    @NotEmpty(message = "Artefact Name Cannot Be Empty")
    @NotBlank
    protected String name;
    @Min(value = 1, message = "Artefacts Points Must Be Greater Or Equal To 1")
    @NotNull
    protected int points;
    @NotEmpty
    @NotNull
    private String type;

    Artefact(String name, int points, String type) {
        this.name = name;
        this.points = points;
        this.type = type;
    }
}
