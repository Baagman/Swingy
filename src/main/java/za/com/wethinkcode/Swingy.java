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
import za.com.wethinkcode.model.util.Database;
import za.com.wethinkcode.view.console.ConsoleView;
import za.com.wethinkcode.view.gui.Gui;

import java.sql.SQLException;

public class Swingy {

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                Database database = new Database();
                if (args[0].equalsIgnoreCase("console")) {
                    ConsoleView consoleView = new ConsoleView(database);
                    consoleView.playerInit();
                } else if (args[0].equalsIgnoreCase("gui")) {
                    //Gui gui = new Gui(database);
                    new Gui(database).setVisible(true);
                }
            } catch (SQLException | ClassNotFoundException | InvalidHero exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
