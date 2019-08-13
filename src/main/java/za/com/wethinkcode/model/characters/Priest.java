package za.com.wethinkcode.model.characters;

public class Priest extends Hero {

	public Priest(String name, int attack, int defense, int hitPoints, int level, int xp) {
		super(name, attack, defense, hitPoints, level, xp);
		setHeroClass("Priest");
	}
}
