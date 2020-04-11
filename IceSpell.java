// import java.util.*;

public class IceSpell extends Spell {
    // protected String spelltype; // Ice, Fire, Lightning; enemy attributes to be decreased upon casting
        // ice = reduces damage of enemy, fire = reduced defense of enemy, lightning = reducded agility of enemy
        // damage, enemy: reduce strength, reduce defense, reduce agility

    public IceSpell (String n, int p, int u, int d, int mc) {
        type = "Spell";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        manacost = mc;
        dmg = d;
        spelltype = "Ice";
    }


    public static void main(String[] args) {
        
    }
}