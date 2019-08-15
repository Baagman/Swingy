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

import za.com.wethinkcode.Exceptions.InvalidHero;
import za.com.wethinkcode.contoller.Controller;
import za.com.wethinkcode.model.util.Database;

import java.sql.SQLException;

public class Swingy {
	public static void main(String[] args ) {

		if (((args[0].equalsIgnoreCase("console")) || (args[0].equalsIgnoreCase("gui")))
				&& (args.length == 1)) {
			try {
				Controller controller = new Controller(new Database());
				controller.PlayerInit(args[0]);
			} catch (SQLException | ClassNotFoundException | NullPointerException | InvalidHero Exception) {
				System.out.print("Error: ");
				System.out.println(Exception.getMessage());
			}
		}
	}
}