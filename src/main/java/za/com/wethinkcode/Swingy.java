/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Swingy.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/16 12:11:54 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/05 14:14:38 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode;

import za.com.wethinkcode.contoller.ConsoleController;
import za.com.wethinkcode.model.util.Database;

public class Swingy {
    public static void main(String args[] ) {
        ConsoleController consoleController = new ConsoleController(new Database());
        consoleController.GameInit();
    }
}