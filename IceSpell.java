public class IceSpell extends Spell {
    //Subclass of Spell which has the effect of reducing enemy damage output when it is cast upon them
    
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
