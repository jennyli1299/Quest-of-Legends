import java.util.Arrays;

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

    public static void main(String[] args) {
        /**
         * Fire Spells
         * Name/cost/required level/damage/mana cost
Flame_Tornado   700     4   850     300
Breath_of_Fire  350     1   450     100
Heat_Wave       450     2   600     150
Lava_Commet     800     7   1000    550
         * 
         * Ice Spells
         * Name/cost/required level/damage/mana cost
Snow_Canon      500     2   650     250
Ice_Blade       250     1   450     100
Frost_Blizzard  750     5   850     350
Arctic_Storm    700     6   800     300
         *
         * Lightning Spells
         * Name/cost/required level/damage/mana cost
Lightning_Dagger       400        1       500     150
Thunder_Blast         750        4       950     400
Electric_Arrows       550        5       650     200
Spark_Needles         500        2       600     200
         */

         Spell HW = new Spell("Heat_Wave", 450, 2, 600, 150, "Fire");
         Spell AC = new Spell("Arctic_Storm", 700, 6, 800, 300, "Ice");
         Spell LD = new Spell("Lightning_Dagger", 400, 1, 500, 150, "Lighning");

        System.out.println(LD);
        System.out.println(Arrays.toString(AC.stats()));
    }
}
