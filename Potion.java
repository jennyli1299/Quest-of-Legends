public class Potion extends Item {
    //Subclass of item which when used, will increase an attribute of a hero by a certain amount.

    private int inc;
    private String[] attr; //attributes to be increased


    public Potion () {
        type = "Potion";
        name = "basic potion";
        price = 0;
        unlock = 0;
        equipped = false;
        inc = 0;
        attr = new String[0];
    }

    public Potion (String n, int p, int u, int ai, String[] a) {
        type = "Potion";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        inc = ai;
        attr = a;
    }

    // RETURNS stats of this Potion item to be used when Hero uses this Potion
    public int[] stats() {
        // health, mana, strength/dmg, defense, dexterity, agility
        int[] stats = new int[6];
        String[] attributes = {"Health", "Mana", "Strength", "Defense", "Dexterity", "Agility"};
        for (String s: attr) {
            for (int i = 0; i < 6; i++) {
                if (s.equals(attributes[i])) {
                    stats[i] = inc;
                }
            }
        }
        return stats;
    }

    public String toString() {
        String ret = this.name + "\t\tPower Up: +" + this.inc;
        int n = attr.length;
        int c = 0;
        ret = ret + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
        ret = ret + "\tBoosts: [";
        for (String a: attr) {
            ret = ret + a;
            c++;
            if (c<n) ret = ret + ", ";
        }
        ret = ret + "]";
        return ret;
    }
}
