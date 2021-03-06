public class FireSpell extends Spell {
    //FireSpell is a subclass of Spell which has an effect of reducing the defense of the enemy it is used on

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

}
