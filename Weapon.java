public class Weapon extends Item {
    //Subclass of item which when held, will determine the amount of damage an attack by its wielder will do

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

    // RETURNS stats of this Weapon item to be added to Hero stats when Weapon is equipped
    public int[] stats() {
        // health, mana, strength/dmg, defense, dexterity, agility
        int[] stats = new int[6];
        stats[2] = dmg;
        return stats;
    }

    public String toString() {
        return this.name + "\t\tAttack Dmg: +" + this.dmg + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }
}
