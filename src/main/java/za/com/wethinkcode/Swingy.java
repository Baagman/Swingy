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

import za.com.wethinkcode.contoller.console.ConsoleController;
import za.com.wethinkcode.model.util.Database;

import java.sql.SQLException;

public class Swingy {
    public static void main(String[] args ) {

    	if ((args.length == 1) && (args[0].equalsIgnoreCase("console"))) {
    		try {
				ConsoleController controller = new ConsoleController(new Database());
				controller.PlayerInit();
			} catch (SQLException | ClassNotFoundException | NullPointerException sqlException) {
    			System.out.print("Error: ");
    			System.out.println(sqlException.getMessage());
			}
		}
    }
}