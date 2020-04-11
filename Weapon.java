import java.util.Arrays;

public class Weapon extends Item {

    private int dmg;
    private int weldhands;

    public Weapon() {
        type = "Weapon";
        name = "basic weapon";
        price = 0;
        unlock = 0;
        equipped = false;
        dmg = 0;
        weldhands = 1;
    }

    public Weapon (String n, int p, int u, int d, int w) {
        type = "Weapon";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        dmg = d;
        weldhands = w;
    }

    public int[] stats() {
        // health, mana, strength/dmg, defense, dexterity, agility
        int[] stats = new int[6];
        stats[2] = dmg;
        return stats;
    }

    public String toString() {
        return this.name + "\t\tAttack Dmg: +" + this.dmg + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
        // return this.name + "\tAttack Dmg: +" + this.dmg + "\tWeld: " + this.weldhands + "\t\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }

    public static void main(String[] args) {
        /**
         * Name/cost/level/damage/required hands
Sword           500     1    800    1
Bow             300     2    500    2
Scythe          1000    6    1100   2
Axe             550     5    850    1
Shield          400     1    100    1
TSwords     	1400    8    1600   2
Dagger          200     1    250    1    
         */

        Weapon Scythe = new Weapon("Scythe", 1000, 6, 1100, 2);
        System.out.println(Scythe);
        System.out.println(Arrays.toString(Scythe.stats()));
    }
}