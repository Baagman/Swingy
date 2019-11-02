package za.com.wethinkcode.model.characters;

public class Hunter extends Hero {

    public Hunter(String name, int attack, int defense, int hitPoints, int level, int xp) {
        super(name, attack, defense, hitPoints, level, xp);
        setHeroClass("Hunter");
    }
}
