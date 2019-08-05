/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   Database.java                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2019/07/31 14:19:20 by tbaagman          #+#    #+#             */
/*   Updated: 2019/08/05 13:48:57 by tbaagman         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

package za.com.wethinkcode.model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			connection.setAutoCommit(false);
			createHeroTable();
		} catch (SQLException | ClassNotFoundException exception) {
			System.out.println(exception.getMessage());
			
		}
	}

	private void createHeroTable() throws SQLException {

		StringBuilder sqlCreateTable = new StringBuilder().append("CREATE TABLE IF NOT EXISTS HEROES (\n");
		sqlCreateTable.append(" name TEXT NOT NULL, \n");
		sqlCreateTable.append(" attack INTEGER, \n");
		sqlCreateTable.append(" defense INTEGER, \n");
		sqlCreateTable.append(" hitpoints INTEGER, \n");
		sqlCreateTable.append(" level INTEGER, \n");
		sqlCreateTable.append(" experience INTEGER\n");
		sqlCreateTable.append(");");
		setStatement(connection.createStatement());
		statement.execute(sqlCreateTable.toString());
		getStatement().close();
	}
	
	public void addNewHeroToTable(String heroName) {
		String sqlInsertHero = "INSERT INTO HEROES VALUES(?, ?, ?, ?, ?, ?)";
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

	public ResultSet selectAvailabeHeros() {
		String selectHeros = "SELECT * FROM HEROES;";
		ResultSet resultSet = null;
		try {
			setStatement(getConnection().createStatement());
			resultSet = getStatement().executeQuery(selectHeros);
		} catch (SQLException sqlException) {
			
		}
		return resultSet;
	}
}