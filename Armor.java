import java.util.Arrays;

public class Armor extends Item {

    private int def;

    public Armor () {
        type = "Armor";
        name = "basic armor";
        price = 0;
        unlock = 0;
        equipped = false;
        def = 0;
    }

    public Armor (String n, int p, int u, int d) {
        type = "Armor";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        def = d;
    }

    public int[] stats() {
        // health, mana, strength/dmg, defense, dexterity, agility
        int[] stats = new int[6];
        stats[3] = def;
        return stats;
    }

    public String toString(){
        return this.name + "\t\tDefense: +" + this.def + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }

    public static void main(String[] args) {
        /**
         * Name/cost/required level/damage reduction
Platinum_Shield       150   1   200
Breastplate           350   3   600
Breastplate           350   3   600
Full_Body_Armor       1000  8   1100
Wizard_Shield         1200  10  1500
Speed_Boots           550   4   600
         */

         Armor FBA = new Armor("Full_Body_Armor", 1000, 8, 1100);
         System.out.println(FBA);
         System.out.println(Arrays.toString(FBA.stats()));
    }
}