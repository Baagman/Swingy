/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Swingy.java                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/16 12:11:54 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/19 15:24:04 by tbaagman         ###   ########.fr       */

package za.com.wethinkcode;

import java.io.File;
import za.com.wethinkcode.contoller.ConsoleController;

public class Swingy {
	public static void main(String[] args) {

		if (args.length == 1) {
			File file = new File(args[0]);

			if ((file.exists()) && (file.isFile())) {
				ConsoleController consoleController = new ConsoleController(file);
				consoleController.Read();
				consoleController.GameInit();
			}
		}
	}
}