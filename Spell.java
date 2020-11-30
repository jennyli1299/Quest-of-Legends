public class Spell extends Item {
    //Subclass of Item that is another way of damaging and affecting the enemy by decreasing a stat

    protected int dmg;
    protected int manacost;
    protected String spelltype; // Ice, Fire, Lightning; enemy attributes to be decreased upon casting
        // ice = reduces damage of enemy, fire = reduced defense of enemy, lightning = reducded agility of enemy
        // damage, enemy: reduce strength, reduce defense, reduce agility


    public Spell () {
        type = "Spell";
        name = "basic spell";
        price = 0;
        unlock = 0;
        equipped = false;
        dmg = 0;
        manacost = 0;
        spelltype = "";
    }

    public Spell (String n, int p, int u, int d, int mc, String st) {
        type = "Spell";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        manacost = mc;
        dmg = d;
        spelltype = st;
    }

    public int getManaCost() {
        return manacost;
    }

    // RETURNS stats of this Spell item to be used when Spell is Cast by Hero
    public int[] stats() {
        // ice = reduces damage of enemy, fire = reduced defense of enemy, lightning = reducded agility of enemy
        // damage, enemy: reduce strength, reduce defense, reduce agility
        int[] stats = new int[4];
        String[] stype = {"Ice", "Fire", "Lighning"};
        stats[0] = dmg;
        for (int i = 0; i < 3; i++) {
            if (this.spelltype.equals(stype[i])) {
                stats[i+1] = (int)dmg/5; //Skill Deterioration: -20%
            }
        }
        return stats;
    }

    public String toString() {
        return this.name + "\t\tSpell Type: " + this.spelltype + "\t\tSpell Dmg:" + this.dmg + "\tMana Cost: " + this.manacost + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }
}
