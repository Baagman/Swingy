/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Database.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/31 14:19:20 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/01 14:39:26 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Database {

	private Connection connection;
	private Statement statement;
	
	public Database() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Swingy.db");
			if (connection != null) {
				createHeroTable();
			}
		} catch (SQLException exception) {
			System.out.println(exception.getMessage());
			
		} catch (ClassNotFoundException classNotFoundException) {
			System.out.println(classNotFoundException.getMessage());
		}
	}

	private void createHeroTable() throws SQLException {
		String sqlCreateTable = "CREATE TABLE IF NOT EXISTS HEROS (\n"
				+ " name TEXT NOT NULL, \n"
				+ " attack INTEGER, \n"
				+ " defense INTEGER, \n"
				+ " hitpoints INTEGER, \n"
				+ " level INTEGER DEFAULT 0, \n"
				+ " experience INTEGER\n"
				+ ");";
		setStatement(connection.createStatement());
		getStatement().execute(sqlCreateTable);
		resetQuery();
	}

	private void resetQuery() {
		this.statement = null;
	}

	public void addNewHeroToTable(String heroName) {
		String sqlInsertHero = "INSERT INTO HEROS VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertHero);
			preparedStatement.setString(1, heroName);
			preparedStatement.setInt(2, 1);
			preparedStatement.setInt(3, 1);
			preparedStatement.setInt(4, 1);
			preparedStatement.setInt(5, 1);
			preparedStatement.setInt(6, 1);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}