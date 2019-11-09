/* ************************************************************************** */
 /*                                                                            */
 /*                                                        :::      ::::::::   */
 /*   Controller.java                             :+:      :+:    :+:   */
 /*                                                    +:+ +:+         +:+     */
 /*   By: tbaagman <tbaagman@student.42.fr>          +#+  +:+       +#+        */
 /*                                                +#+#+#+#+#+   +#+           */
 /*   Created: 2019/07/18 16:06:42 by tbaagman          #+#    #+#             */
 /*   Updated: 2019/08/05 14:14:04 by tbaagman         ###   ########.fr       */
 /*                                                                            */
 /* ************************************************************************** */
package za.com.wethinkcode.contoller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import za.com.wethinkcode.model.artefacts.Armor;
import za.com.wethinkcode.model.artefacts.Artefact;
import za.com.wethinkcode.model.artefacts.Helm;
import za.com.wethinkcode.model.artefacts.Weapon;
import za.com.wethinkcode.model.characters.*;
import za.com.wethinkcode.model.coordinates.Coordinates;
import za.com.wethinkcode.model.util.Database;
import za.com.wethinkcode.view.gui.Gui;
import javax.validation.*;

@Getter
@Setter
public class Controller {

    private Hero                        hero;
    private Database                    database;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static boolean              quitGame;
    private static boolean              consoleViewMode;
    private ArrayList<Villain>          villains;
    private Gui                         gui;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private HashMap<Integer, String>    armor;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private HashMap<Integer, String>    weapon;
    private Validator                   validator;

    public Controller(Database database) {
        this.database = database;
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    validator = factory.getValidator();
    }

    public void Move(String direction) {
        int sizeOfMap = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2)));

        switch (direction) {
            case "North":
            case "1":
                if (hero.getPosition().getY() > 0) {
                    hero.getPosition().setY(hero.getPosition().getY() - 1);
                }
                break;
            case "East":
            case "2":
                if (hero.getPosition().getX() < sizeOfMap - 1) {
                    hero.getPosition().setX(hero.getPosition().getX() + 1);
                }
                break;
            case "South":
            case "4":
                if (hero.getPosition().getY() < (sizeOfMap - 1)) {
                    hero.getPosition().setY(hero.getPosition().getY() + 1);
                }
                break;
            case "West":
            case "3":
                if (hero.getPosition().getX() > 0) {
                    hero.getPosition().setX(hero.getPosition().getX() - 1);
                }
                break;
        }
    }

    public Hero createHero(String heroName) throws SQLException {
        String heroClass;
        Hero hero = null;
        ResultSet resultSet = database.selectHero(heroName);

        if ((resultSet != null) && (resultSet.next())) {
	        heroClass = resultSet.getString("heroClass");
	        if (heroClass.equalsIgnoreCase("Warrior")) {
		        hero = new Warrior(resultSet.getString("name"),
				        resultSet.getInt("attack"),
				        resultSet.getInt("defense"),
				        resultSet.getInt("hitpoints"),
				        resultSet.getInt("level"),
				        resultSet.getInt("experience"));
	        } else if (heroClass.equalsIgnoreCase("Hunter")) {
		        hero = new Hunter(resultSet.getString("name"),
				        resultSet.getInt("attack"),
				        resultSet.getInt("defense"),
				        resultSet.getInt("hitpoints"),
				        resultSet.getInt("level"),
				        resultSet.getInt("experience"));
	        } else if (heroClass.equalsIgnoreCase("Priest")) {
		        hero = new Priest(resultSet.getString("name"),
				        resultSet.getInt("attack"),
				        resultSet.getInt("defense"),
				        resultSet.getInt("hitpoints"),
				        resultSet.getInt("level"),
				        resultSet.getInt("experience"));
	        }
	        if (getArtefact(heroClass, hero, resultSet))
	        	return hero;
        }
        return null;
    }

	private boolean getArtefact(String heroClass, Hero hero, ResultSet resultSet) throws SQLException {
		ResultSet artefacts;
		boolean heroValidated = false;
		if (hero != null) {
			artefacts = database.selectArtefacts(resultSet.getInt("id"));
			int points;
			String artefactName;
			while (artefacts.next()) {
				points = artefacts.getInt("points");
				artefactName = artefacts.getString("name");
				if (artefacts.getString("type").equalsIgnoreCase("helm")) {
					hero.setHelm(generateHelm(artefactName, points));
				} else if (artefacts.getString("type").equalsIgnoreCase("armor")) {
					hero.setArmor(generateArmor(artefactName, points));
				} else if (artefacts.getString("type").equalsIgnoreCase("weapon")) {
					hero.setWeapon(generateWeapon(artefactName, points));
				}
			}
			setHero(hero);
			getHero().setHeroClass(heroClass);
			heroValidated = validateHero(hero);
		}
		return heroValidated;
	}

	private boolean validateHero(Hero hero) {
    	boolean validated = true;
    	Set<ConstraintViolation<Hero>> constraintViolations = validator.validate(hero);
    	if (constraintViolations.size() > 0) {
    		for (ConstraintViolation<Hero> violation : constraintViolations) {
    			System.out.println(violation.getMessage());
    		}
    		validated = false;
    	}
    	if (validated) {
    		if (hero.getArmor() != null) {
    			validated = validateArtefact(hero.getArmor());
    		} else if (hero.getWeapon() != null) {
    			validated = validateArtefact(hero.getWeapon());
    		} else if (hero.getHelm() != null) {
    			validated = validateArtefact(hero.getHelm());
    		}
    	}
		return validated;
    }

	private boolean validateArtefact(Artefact artefact) {
		if (artefact != null) {
			Set<ConstraintViolation<Artefact>> constraintViolations = validator.validate(artefact);
			if (constraintViolations.size() > 0) {
				for (ConstraintViolation<Artefact> violation : constraintViolations) {
					System.out.println(violation.getMessage());
				}
				return false;
			}
		}
		return true;
	}

	public Hero createNewHero(String heroName, String HeroClass) throws SQLException {
	    Hero hero = null;
	    int experience = 1000;
	    int level = 1;
	    int defense = 0;
	    int attack = 0;
	    int hitpoints = 12;

		switch (HeroClass) {
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
	    if (HeroClass.equalsIgnoreCase("Warrior")) {
	    	hero = new Warrior(heroName, attack, defense, hitpoints, level, experience);
	    } else if (HeroClass.equalsIgnoreCase("Hunter")) {
	    	hero = new Hunter(heroName, attack, defense, hitpoints, level, experience);
	    } else if (HeroClass.equalsIgnoreCase("Priest")) {
		    hero = new Priest(heroName, attack, defense, hitpoints, level, experience);
	    }

	    if (hero != null) {
		    if (validateHero(hero)) {
		        database.addNewHeroToTable(heroName, HeroClass);
		        setHero(hero);
		        hero.setHeroClass(HeroClass);
		        return hero;
		    }
	    }
	    return null;
    }

    public void Equip(Artefact artefact) throws SQLException {
        if (weapon.containsValue(artefact.getName())) {
            hero.equipWeapon((Weapon) artefact);
        } else if (armor.containsValue(artefact.getName())) {
            hero.equipArmor((Armor) artefact);
        } else if (artefact.getName().equalsIgnoreCase("Health Portion")) {
            hero.equipHelm((Helm) artefact);
        }
        database.addArtefactToTable(artefact, hero.getName());
    }

    public Villain checkForEnemies(String userInput, Coordinates coordinates) {

        int coordsY = coordinates.getY();
        int coordsX = coordinates.getX();

        switch (userInput) {
            case "North":
            case "1":
                coordsY--;
                break;
            case "East":
            case "2":
                coordsX++;
                break;
            case "South":
            case "4":
                coordsY++;
                break;
            case "West":
            case "3":
                coordsX--;
                break;
        }
        for (Villain villain : villains) {
            if (villain.getPosition().getY() == coordsY) {
                if (villain.getPosition().getX() == coordsX) {
                    return villain;
                }
            }
        }
        return null;
    }

    public String getClassHeroType(String index) {
        HashMap<String, String> heroClass = new HashMap<>();
        heroClass.put("1", "Warrior");
        heroClass.put("2", "Hunter");
        heroClass.put("3", "Priest");
        return heroClass.get(index);
    }

    public boolean checkIfHeroNameExist(String heroName, String heroClass) throws SQLException {

        boolean isExist = true;
        if ((heroName != null) && (!heroName.isEmpty())) {
            ResultSet resultSet = database.selectHero(heroName);
            if ((resultSet != null) && (resultSet.next())) {
                isExist = false;
            }
        } else {
            isExist = false;
        }
        return isExist;
    }

    public ArrayList<Villain> generateVillains() {

        int mapSize = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2)));
        ArrayList<Villain> villains = new ArrayList<>();
        int position;
        int x = 0;
        for (int y = 0; y <= mapSize * 2; y++) {
            position = ThreadLocalRandom.current().nextInt(0, mapSize);
            if ((hero.getPosition().getX() != position) || (hero.getPosition().getY() != y)) {
                Villain villain = getVillainType();
                if (villain != null) {
                    if (y > mapSize) {
                        if ((hero.getPosition().getX() != position) || (hero.getPosition().getY() != x)) {
                            villain.setPosition(new Coordinates(position, x));
                            villains.add(villain);
                        }
                        x++;
                    } else {
                        villain.setPosition(new Coordinates(position, y));
                        villains.add(villain);
                    }
                }
            }
        }
        setVillains(villains);
        return villains;
    }

    public boolean simulateFight(Villain villain) {
        Random random = new Random();
        while ((hero.getHitPoints() >= 0) && (villain.getHitPoints() >= 0)) {
            int heroAction = random.nextInt(2);
            int villainAction = random.nextInt(2);
            int damageTaken;

            if ((heroAction == 1) && (villainAction == 1)) {
                hero.setHitPoints(hero.getHitPoints() - villain.getAttack());
                villain.setHitPoints(hero.getHitPoints() - hero.getAttack());
            } else if ((heroAction == 1) || (villainAction == 0)) {
                if ((hero.getAttack() - villain.getDefense()) > 0) {
                    damageTaken = hero.getAttack() - villain.getDefense();
                } else {
                    damageTaken = 0;
                }
                villain.setHitPoints(villain.getHitPoints() - damageTaken);
            } else if ((heroAction == 0) && (villainAction == 1)) {
                if ((villain.getAttack() - hero.getDefense()) > 0) {
                    damageTaken = villain.getAttack() - hero.getDefense();
                } else {
                    damageTaken = 0;
                }
                hero.setHitPoints(villain.getHitPoints() - damageTaken);
            } else {
	            villain.setHitPoints(hero.getHitPoints() - hero.getAttack());
            }
        }
        if (hero.getHitPoints() > 0) {
            int xp = villain.getAttack() + villain.getDefense();
            villains.remove(villain);
            addExperience(xp);
            return true;
        }
        return false;
    }

    private Villain getVillainType() {
        Random random = new Random();
        HashMap<String, Integer> villainStats = new HashMap<>();
        int randomNumber = random.nextInt(2);
        int hotPoints = random.nextInt(hero.getLevel() * 5);

        if (hero.getAttack() <= 0) {
	        villainStats.put("Attack", random.nextInt(hero.getAttack()));
        } else
        	villainStats.put("Attack", random.nextInt(hero.getAttack() / 2));
        if (hero.getDefense() <= 0)
	        villainStats.put("Defense", random.nextInt(hero.getDefense()));
        else
	        villainStats.put("Defense", random.nextInt(hero.getDefense()));
        switch (randomNumber) {
            case 0:
                return new DarkElf("Dark Elf", villainStats.get("Attack"), villainStats.get("Defense"), hotPoints);
            case 1:
                return new Ogre("Ogre", villainStats.get("Attack"), villainStats.get("Defense"), hotPoints);
            default:
                return null;
        }
    }

    private void addExperience(int experience) {
        int tempLevel = hero.getLevel() + 1;
        int levelingUpXp = (tempLevel * 1000) + ((int) Math.pow((tempLevel - 1), 2) * 450);
        hero.setExperience(hero.getExperience() + experience);
        int playerPosition;

        if (hero.getExperience() >= levelingUpXp) {
            hero.setLevel(hero.getLevel() + 1);
            playerPosition = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2))) / 2;
            hero.setPosition(new Coordinates(playerPosition, playerPosition));
        }
    }

    public Artefact generateRandomArtifact(Characters character) {
        Random random = new Random();
        int points;
        weapon = new HashMap<>();
        armor = new HashMap<>();

        armor.put(0, "Leather Armor");
        armor.put(1, "Shield");

        weapon.put(0, "Sword");
        weapon.put(1, "Spear");

        if (random.nextInt(8) == 1) {
            points = random.nextInt((hero.getLevel() * 30) - character.getAttack()) + character.getAttack();
            return generateWeapon(weapon.get(random.nextInt(1)), points);
        } else if (random.nextInt(8) == 2) {
            points = character.getDefense() + 5;
            return generateArmor(armor.get(random.nextInt(1)), points);
        } else if ((random.nextInt(8) == 3) || (random.nextInt(8) == 4)) {
            points = random.nextInt(hero.getLevel() * 10);
            return generateHelm("Health portion", points);
        } else {
            return null;
        }
    }

    private Weapon generateWeapon(String name, int points) {
        return new Weapon(name, points);
    }

    private Armor generateArmor(String name, int points) {
        return new Armor(name, points);
    }

    private Helm generateHelm(String name, int points) {
        return new Helm(name, points);
    }

    public void updateHero() throws SQLException {
        database.updateHero(hero);
    }

    public boolean GameWon() {
    	boolean reachedBorder = false;
        int mapSize = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2)));
        if ((hero.getPosition().getX() == 0) || (hero.getPosition().getY() == 0)) {
        	reachedBorder = checkBorder(hero.getPosition().getX(), hero.getPosition().getY(), mapSize);
        } else if ((mapSize == hero.getPosition().getX() + 1) || (mapSize == hero.getPosition().getY() + 1)) {
            reachedBorder = checkBorder(hero.getPosition().getX(), hero.getPosition().getY(), mapSize);
        }
        return reachedBorder;
    }

    public ResultSet getAvailableHeroes() throws SQLException {
        return database.AvailableHeroes();
    }

    public ResultSet selectHero(String heroName) throws SQLException {
        return database.selectHero(heroName);
    }

    public boolean getVillainsToDisplay(ArrayList<Villain> villains, int x, int y) {
        for (Villain villain : villains) {
            if (villain.getPosition().getY() == y) {
                if (villain.getPosition().getX() == x) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public ArrayList<Villain> isHeroLevelUp() {
        int playerPosition = (((hero.getLevel() - 1) * 5) + (10 - (hero.getLevel() % 2))) / 2;
        if ((playerPosition == hero.getPosition().getX()) && (playerPosition == hero.getPosition().getY())) {
            this.villains = null;
            this.villains = generateVillains();
        }
        return this.villains;
    }
    
    public boolean runOrFight(Villain villainEncounter) {

    	if (((hero.getDefense() - (villainEncounter.getAttack())) >= 0) && (villainEncounter.getHitPoints() < hero.getHitPoints()))
    		return true;
    	else return ((villainEncounter.getDefense() - (hero.getAttack())) <= 0) && (villainEncounter.getHitPoints() < hero.getHitPoints());
    }

    private boolean checkBorder(int x, int y, int mapSize) {
    	for (Villain villain : villains) {
    		if ((villain.getPosition().getY() == y) && (villain.getPosition().getX() == x))
    			return false;
    		else if (((villain.getPosition().getX() + 1) == mapSize) && ((villain.getPosition().getY() + 1) == mapSize))
    			return false;
	    }
    	return true;
    }
}