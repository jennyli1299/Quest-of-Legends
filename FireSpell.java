// import java.util.*;

public class FireSpell extends Spell {
    // protected String spelltype; // Ice, Fire, Lightning; enemy attributes to be decreased upon casting
        // ice = reduces damage of enemy, fire = reduced defense of enemy, lightning = reducded agility of enemy
        // damage, enemy: reduce strength, reduce defense, reduce agility

    public FireSpell (String n, int p, int u, int d, int mc) {
        type = "Spell";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        manacost = mc;
        dmg = d;
        spelltype = "Fire";
    }

    public String toString() {
        return this.name + "\t\tSpell Type: " + this.spelltype + "\tSpell Dmg:" + this.dmg + "\tMana Cost: " + this.manacost + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }


    public static void main(String[] args) {
        
    }
}