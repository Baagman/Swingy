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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.characters.Hero;

@Getter
@Setter
public class Database {

	private Connection connection;
	private Statement statement;
	@Getter(AccessLevel.NONE)
	private String heroClass;

	public Database() throws SQLException, ClassNotFoundException {

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:Swingy.db");
			createHeroTable();
		} catch (SQLException sqlException) {
			throw new SQLException("Unable to connect to the database: " + sqlException.getMessage());
		} catch (ClassNotFoundException classNotFoundException) {
			throw new ClassNotFoundException(classNotFoundException.getMessage());
		}
	}

	private void createHeroTable() throws SQLException, NullPointerException {

		StringBuilder sqlCreateTable = new StringBuilder().append("CREATE TABLE IF NOT EXISTS HEROES (\n");
		sqlCreateTable.append(" name TEXT NOT NULL, \n");
		sqlCreateTable.append(" heroclass TEXT NOT NULL, \n");
		sqlCreateTable.append(" armor TEXT, \n");
		sqlCreateTable.append(" weapon TEXT, \n");
		sqlCreateTable.append(" helm TEXT, \n");
		sqlCreateTable.append(" attack INTEGER, \n");
		sqlCreateTable.append(" defense INTEGER, \n");
		sqlCreateTable.append(" hitpoints INTEGER, \n");
		sqlCreateTable.append(" level INTEGER, \n");
		sqlCreateTable.append(" experience INTEGER\n");
		sqlCreateTable.append(");");
		setStatement(getConnection().createStatement());
		getStatement().execute(sqlCreateTable.toString());
		getStatement().close();
	}

	public void addNewHeroToTable(String heroName) throws SQLException {

		String sqlInsertHero = "INSERT INTO HEROES VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int attack = 0;
		int defense = 0;
		int hitpoints = 12;

		switch (this.heroClass) {
			case "Warrior":
				attack = 10;
				defense = 15;
				break;
			case "Hunter":
				attack = 8;
				defense = 20;
				break;
			case "Priest":
				attack = 4;
				defense = 10;
				break;
		}

		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertHero);
			preparedStatement.setString(1, heroName);
			preparedStatement.setString(2, this.heroClass);
			preparedStatement.setString(3, null);
			preparedStatement.setString(4, null);
			preparedStatement.setString(5, null);
			preparedStatement.setInt(6, attack);
			preparedStatement.setInt(7, defense);
			preparedStatement.setInt(8, hitpoints);
			preparedStatement.setInt(9, 1);
			preparedStatement.setInt(10, 1000);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException sqlException) {
			throw new SQLException("Unable to store hero in the database: " + sqlException.getMessage());
		}
	}

	public ResultSet AvailableHeroes() throws SQLException {
		String selectHeros = "SELECT * FROM HEROES;";
		ResultSet resultSet;
		try {
			setStatement(getConnection().createStatement());
			resultSet = getStatement().executeQuery(selectHeros);
		} catch (SQLException sqlException) {
			throw new SQLException("Unable to connect to database: " + sqlException.getMessage());
		}
		return resultSet;
	}

	public ResultSet selectHero(String name) throws SQLException {
		String sqlGetHero = "SELECT * FROM HEROES WHERE name = ?;";
		ResultSet resultSet;
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(sqlGetHero);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException sqlException) {
			throw new SQLException("Unable to connect to database: " + sqlException.getMessage());
		}
		return resultSet;
	}

	public void updateHero(Hero hero) throws SQLException {
		StringBuilder sqlUpdate = new StringBuilder("UPDATE HEROES SET experience = ?, hitpoints = ?");
		sqlUpdate.append(", attack = ?, defense = ?, armor = ?, weapon = ?, helm = ?, level = ? WHERE name = ?;");
		if (hero != null) {
			try {
				PreparedStatement preparedStatement = getConnection().prepareStatement(sqlUpdate.toString());
				preparedStatement.setInt(1, hero.getExperience());
				preparedStatement.setInt(2, hero.getHitPoints());
				preparedStatement.setInt(3, hero.getAttack());
				preparedStatement.setInt(4, hero.getDefense());
				if (hero.getArmor() != null)
					preparedStatement.setString(5, hero.getArmor().getName());
				else
					preparedStatement.setString(5, null);
				if (hero.getWeapon() != null)
					preparedStatement.setString(6, hero.getWeapon().getName());
				else
					preparedStatement.setString(6, null);
				if (hero.getHelm() != null)
					preparedStatement.setString(7, hero.getHelm().getName());
				else
					preparedStatement.setString(7, null);
				preparedStatement.setInt(8, hero.getLevel());
				preparedStatement.setString(9, hero.getName());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException sqlException) {
				throw new SQLException("Unable to Update hero stats in the database: " + sqlException.getMessage());
			}
		}
	}
}