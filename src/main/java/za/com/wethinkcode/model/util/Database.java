/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Database.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/31 14:19:20 by tbaagman          #+#    #+#             */
/*   Updated: 2019/07/31 15:22:48 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public void createDatabase() {
		
		Connection connection = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Swingy.db");
			if (connection != null) {
				DatabaseMetaData metaData = connection.getMetaData();
				System.out.println("The driver name is " + metaData.getDriverName());
				System.out.println("A new Database has been created");
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException.getMessage());
		}
	}  
}