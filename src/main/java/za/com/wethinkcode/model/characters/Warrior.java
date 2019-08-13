package za.com.wethinkcode.model.characters;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Warrior extends Hero {

	public Warrior(String name, int attack, int defense, int hitPoints, int level, int xp) {
		super(name, attack, defense, hitPoints, level, xp);
		setHeroClass("Warrior");
	}
}
