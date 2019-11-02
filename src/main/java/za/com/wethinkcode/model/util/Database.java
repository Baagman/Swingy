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
import za.com.wethinkcode.model.artefacts.Artefact;
import za.com.wethinkcode.model.characters.Hero;

@Getter
@Setter
public class Database {

    private Connection connection;
    private Statement statement;

    public Database() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Swingy.db");
            createHeroTable();
            createArtefactsTable();
        } catch (SQLException sqlException) {
            throw new SQLException("Unable to connect to the database: " + sqlException.getMessage());
        } catch (ClassNotFoundException classNotFoundException) {
            throw new ClassNotFoundException(classNotFoundException.getMessage());
        }
    }

    private void createHeroTable() throws SQLException {

        StringBuilder sqlCreateHeroTable = new StringBuilder().append("CREATE TABLE IF NOT EXISTS HEROES (\n");
        sqlCreateHeroTable.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        sqlCreateHeroTable.append(" name TEXT NOT NULL, \n");
        sqlCreateHeroTable.append(" heroclass TEXT NOT NULL, \n");
        sqlCreateHeroTable.append(" armor TEXT, \n");
        sqlCreateHeroTable.append(" weapon TEXT, \n");
        sqlCreateHeroTable.append(" helm TEXT, \n");
        sqlCreateHeroTable.append(" attack INTEGER, \n");
        sqlCreateHeroTable.append(" defense INTEGER, \n");
        sqlCreateHeroTable.append(" hitpoints INTEGER, \n");
        sqlCreateHeroTable.append(" level INTEGER, \n");
        sqlCreateHeroTable.append(" experience INTEGER\n");
        sqlCreateHeroTable.append(");");
        setStatement(getConnection().createStatement());
        getStatement().execute(sqlCreateHeroTable.toString());
        getStatement().close();
    }

    public void addNewHeroToTable(String heroName, String heroClass) throws SQLException {

        StringBuilder sqlInsertHero = new StringBuilder().append("INSERT INTO HEROES (name, heroclass, armor, weapon, helm, \n");
        sqlInsertHero.append("attack, defense, hitpoints, level, experience) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        int attack = 0;
        int defense = 0;
        int hitpoints = 12;

        switch (heroClass) {
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
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlInsertHero.toString());
            preparedStatement.setString(1, heroName);
            preparedStatement.setString(2, heroClass);
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
        String selectHeroes = "SELECT * FROM HEROES;";
        ResultSet resultSet;
        try {
            setStatement(getConnection().createStatement());
            resultSet = getStatement().executeQuery(selectHeroes);
        } catch (SQLException sqlException) {
            throw new SQLException("Unable to connect to database " + sqlException.getMessage());
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
            throw new SQLException("Unable to connect to database " + sqlException.getMessage());
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
                if (hero.getArmor() != null) {
                    preparedStatement.setString(5, hero.getArmor().getName());
                } else {
                    preparedStatement.setString(5, null);
                }
                if (hero.getWeapon() != null) {
                    preparedStatement.setString(6, hero.getWeapon().getName());
                } else {
                    preparedStatement.setString(6, null);
                }
                if (hero.getHelm() != null) {
                    preparedStatement.setString(7, hero.getHelm().getName());
                } else {
                    preparedStatement.setString(7, null);
                }
                preparedStatement.setInt(8, hero.getLevel());
                preparedStatement.setString(9, hero.getName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException sqlException) {
                throw new SQLException("Unable to Update hero stats in the database: " + sqlException.getMessage());
            }
        }
    }

    private void createArtefactsTable() throws SQLException {
        StringBuilder sqlCreateArtefactTable = new StringBuilder().append("CREATE TABLE IF NOT EXISTS ARTEFACTS (\n");
        sqlCreateArtefactTable.append(" ID INTEGER NOT NULL, \n");
        sqlCreateArtefactTable.append(" name TEXT NOT NULL, \n");
        sqlCreateArtefactTable.append(" type TEXT NOT NULL, \n");
        sqlCreateArtefactTable.append(" points INTEGER NOT NULL \n");
        sqlCreateArtefactTable.append(");");
        setStatement(getConnection().createStatement());
        getStatement().execute(sqlCreateArtefactTable.toString());
        getStatement().close();
    }

    public void addArtefactToTable(Artefact artefact, String heroName) throws SQLException {
        try {
            deleteArtefactFromDB(heroName, artefact.getType());
            String addArtefact = "INSERT INTO ARTEFACTS VALUES ((SELECT _ROWID_ FROM HEROES WHERE HEROES.name = ?), ?, ?, ?);";
            PreparedStatement preparedStatement = getConnection().prepareStatement(addArtefact);
            preparedStatement.setString(1, heroName);
            preparedStatement.setString(2, artefact.getName());
            preparedStatement.setString(3, artefact.getType());
            preparedStatement.setInt(4, artefact.getPoints());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException.getMessage());
        }
    }

    private void deleteArtefactFromDB(String heroName, String artefactType) throws SQLException {
        try {
            StringBuilder deleteArtefact = new StringBuilder().append("DELETE FROM ARTEFACTS WHERE ");
            deleteArtefact.append("( ID = (SELECT id FROM HEROES WHERE HEROES.name = ? )");
            deleteArtefact.append(" AND type = ?);");
            PreparedStatement preparedStatement = getConnection().prepareStatement(deleteArtefact.toString());
            preparedStatement.setString(1, heroName);
            preparedStatement.setString(2, artefactType);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException sqlException) {
            throw new SQLException(sqlException);
        }
    }

    public ResultSet selectArtefacts(int id) throws SQLException {
        String sqlGetArtefacts = "SELECT * FROM ARTEFACTS WHERE id = ?;";
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlGetArtefacts);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException sqlException) {
            throw new SQLException("Unable to connect to database: " + sqlException.getMessage());
        }
        return resultSet;
    }
}
