/* ************************************************************************** */
 /*                                                                            */
 /*                                                        :::      ::::::::   */
 /*   InvalidHero.java                                   :+:      :+:    :+:   */
 /*                                                    +:+ +:+         +:+     */
 /*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
 /*                                                +#+#+#+#+#+   +#+           */
 /*   Created: 2019/08/09 12:59:00 by tbaagman          #+#    #+#             */
 /*   Updated: 2019/08/09 12:59:03 by tbaagman         ###   ########.fr       */
 /*                                                                            */
 /* ************************************************************************** */
package za.com.wethinkcode.Exceptions;

public class InvalidHero extends Exception {

    public InvalidHero(String message) {
        super(message);
    }
}
