/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Swingy.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/16 12:11:54 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/19 15:24:04 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode;

import java.io.File;

import za.com.wethinkcode.contoller.ConsoleController;

public class Swingy {
	public static void main(String[] args) {
		File heroFile = new File(args[0]);
		ConsoleController consoleController = new ConsoleController(heroFile);
		consoleController.ConsoleStartGame();
	}
}