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

import za.com.wethinkcode.model.util.Database;

public class Swingy {
  public static void main( String args[] ) {
	  Database database = new Database();
	  database.createDatabase();
  }
}