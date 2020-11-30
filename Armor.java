public class Armor extends Item {
    //Armor is an item that increases an IGC's defense when it is equipped
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

    // RETURNS stats of this Armor item to be added to Hero stats when Armor is equipped
    public int[] stats() {
        // health, mana, strength/dmg, defense, dexterity, agility
        int[] stats = new int[6];
        stats[3] = def;
        return stats;
    }

    public String toString(){
        return this.name + "\t\tDefense: +" + this.def + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }
}
