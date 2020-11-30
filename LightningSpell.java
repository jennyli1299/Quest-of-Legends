// import java.util.*;

public class LightningSpell extends Spell {
    //Subclass of Spell which has the added effect of reducing agility of an enemy that is hit by it
    
    public LightningSpell (String n, int p, int u, int d, int mc) {
        type = "Spell";
        name = n;
        price = p;
        unlock = u;
        equipped = false;
        manacost = mc;
        dmg = d;
        spelltype = "Lightning";
    }

    public String toString() {
        return this.name + "\t\tSpell Type: " + this.spelltype + "\tSpell Dmg:" + this.dmg + "\tMana Cost: " + this.manacost + "\tUnlocks at Level " + unlock + "\tPrice: " + price + "coins\tSells for: " + price/2 + "coins";
    }

}
