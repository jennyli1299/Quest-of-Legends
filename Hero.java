import java.util.HashMap;
// import java.util.Iterator;
// import java.util.Map;
// import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Hero extends InGameCharacter {

    // could have arrays to store information ://
    protected int maxmana;
    protected int mana;
    protected int dexterity;
    protected int coins;
    protected HashMap<String, Item> inv; //Inventory
    protected HashMap<String, Integer> invnum;
    protected Monster attacking;
    protected int[] ogstats; // Strength, Defense, Dexterity, Agility

    //TODO MARKET & NEXUS TILES & LEARN SPELLS

    public Hero () {
        name = "a Hero";
        type = "Useless";
        lvl = 1;
        exp = 0;
        maxhp = 0;
        hp = 0;
        maxmana = 0;
        mana = 0;
        strength = 0;
        defense = 0;
        dexterity = 0;
        agility = 0;
        coins = 0;
        attacking = new Monster();
        inv = new HashMap<>();
        invnum = new HashMap<>();
        ogstats = new int[] {strength, defense, dexterity, agility};
    }
    // FILL IN MAKE ANOTHER CONSTRUCTOR W LESS PARAMETERS according to input .txt files
    public Hero (String n, String t, int m, int s, int a, int dx, int startingmoney, int startingexp) {
        name = n;
        type = t;
        lvl = (int) Math.ceil((double)startingexp / 10);
        exp = startingexp;
        maxhp = 100*lvl;
        hp = maxhp;
        strength = s;
        defense = 0;
        agility = a;
        dexterity = dx;
        maxmana = m;
        mana = maxmana;
        coins = startingmoney;
        exp = startingexp;
        attacking = new Monster();
        inv = new HashMap<>();
        invnum = new HashMap<>();
        ogstats = new int[] {strength, defense, dexterity, agility};
    }
    public Hero (String n, String t, int l, int e, int h, int m, int s, int d, int dx, int a, int c) {
        name = n;
        type = t;
        lvl = l;
        exp = e;
        maxhp = h;
        hp = h;
        maxmana = m;
        mana = m;
        strength = s;
        defense = d;
        dexterity = dx;
        agility = a;
        attacking = new Monster();
        inv = new HashMap<>();
        invnum = new HashMap<>();
        coins = c;
        ogstats = new int[] {strength, defense, dexterity, agility};
    }
    /**
    private void updhp (int h) {
        this.hp -= h;
    }
    private void updStrength (int a) {
        this.strength -= a;
    }
    private void updDefense (int a) {
        this.defense -= a;
    }
    private void updAgility (int a) {
        this.agility -= a;
    }
    */

    // private void updhp (int h) {
    //     this.hp -= h;
    // }

    private void updAttributes (int[] a) {
        this.hp += a[0];
        this.mana += a[1];
        this.strength += a[2];
        this.defense += a[3];
        this.dexterity += a[4];
        this.agility += a[5];
    }

    // DID NOT TAKE INTO ACCOUNT DEFENSE OR DODGE CHANCE
    // SUPERCLASS ONE DOES
    // public void attack(Monster m) {
    //     m.updhp((int)(this.strength*0.05)-m.defense);
    // }
    // public void attack() {
    //     attacking.updhp((int)(this.strength*0.05));
    // }

    public void takeOffWeapon(Item i) {
        int[] statstoreverse = i.stats();
        for (int s = 0; s < statstoreverse.length; s++) {
            statstoreverse[s] = -1*statstoreverse[s];
        }
        i.unEquip();
        this.updAttributes(statstoreverse);
    }

    public void equip(Item i) { 
        //WRITE AN UNEQUIP & MAKE SURE HERO IS ONLY WIELDING ONE WEAPON AND WEARING ONE ARMOR AT A TIME (done)
        String itype = i.getType();
        // boolean alrdyON = false;
        if (itype.equals("Weapon")) { // || itype.equals("Armor")) {
            for(Item thisitem: inv.values()) {
                if (thisitem.getType().equals(itype)) {
                    // alrdyON |= thisitem.getEquipped();
                    if (thisitem.getEquipped()) {
                        this.takeOffWeapon(thisitem);
                        break;
                    }
                }
            }
        }
        //check for it in inventory TO DO
        //set equiped to true
        i.setEquipped();//equipItem();
        int[] stats = i.stats();
        if (stats.length == 6) {
            this.updAttributes(stats);
            if (i.getType().equals("Potion")) {
                removefromINVs(i);
            }
            i.unEquip();
        }
        else if (stats.length == 4) {
            Spell s = (Spell)i;
            // if (this.mana < s.getManaCost()) {
            //     System.out.println("Uh oh! Seems to me you don't have enough mana to cast this spell.");
            // }
            // else {
            if (canCast(s)) {
                mana -= s.getManaCost();
                this.castSpell(attacking, stats);
                // removefromINVs(s);
                s.unEquip();
            }
            // }
        }
        // return true;
    }
    // public void unequipAll() {
    //     // FILL IN
    //     i.unEquip();
    // }
    public boolean canCast(Spell s) {
        if (this.mana < s.getManaCost()) {
            System.out.println("Uh oh! Seems to me you don't have enough mana to cast this spell.");
            return false;
        }
        else return true;
    }
    public void castSpell(Monster m, int[] stats) {
        // Since a Hero spends mana to cast a spell, it's effect cannot be dodged but the damage can be.
        int spelldmg = stats[0];
        spellDmg(m, spelldmg);
        int[] effect = Arrays.copyOfRange(stats, 1, 4);
        m.updAttributes(effect);
    }
    public void spellDmg(Monster m, int sd) {
        if (Math.random() <= m.agility/100) {
            System.out.println(m.name + " DODGED spell damage! However, Spell residue is effective!");
        }
        else {
            int dmg = (int)(sd + (this.dexterity/10000)*sd);
            if (this.getLoc().getType().equals("Bush")) { // Bush (inc dexterity (spell casting) 10%)
                dmg = (int)(dmg*1.1);
            }
            m.updhp(dmg);
            System.out.println(m.getName() + " has taken " + dmg + " damage from " + this.name + "'s SPELL! Spell Residue is effective!");
        }
    }

    public void setAttacking (Monster m) {
        this.attacking = m;
    }
    public Monster getAttacking() {
        return this.attacking;
    }

    public HashMap<String, Item> getInv() {
        return this.inv;
    }

    public boolean buyItem(Item i) {
        if (i.getUnlockLvl() > this.lvl) {
            System.out.println("Young Hero, you are not yet ready. You must be at least level " + i.getUnlockLvl() + " to purchase this item.");
            return false;
        }
        else if (i.getPrice() > this.coins) {
            System.out.println("I'm afraid you can't purchase this item young hero. It's worth more than you have. Come again later when you have more coins.");
            return false;
        }
        else {
            this.coins -= i.getPrice();
            String in = i.getItemName();
            inv.put(in, i);
            if (invnum.containsKey(in)) {
                int n = invnum.get(in).intValue();
                invnum.replace(in, Integer.valueOf(n+1));
            }
            else {
                invnum.put(in, Integer.valueOf(1));
                inv.put(in, i);
            }
            System.out.println(i.getItemName() + " has been added to your inventory! Thanks for shopping! Come again soon!");
            return true;
        }
    }
    public void removefromINVs(Item i) {
        // use Potion --> decrease count in inventory
        String in = i.getItemName(); // Item Name
        if (invnum.get(in).intValue() == 1) {
            inv.remove(in);
            invnum.remove(in);
        }
        else {
            int n = invnum.get(in).intValue();
            invnum.replace(in, Integer.valueOf(n-1));
        }
    }
    public void sellItem(Item i) {
        removefromINVs(i);
        this.coins += (int)i.getPrice()/2;
        System.out.println("What a wonderful sale! " + i.getItemName() + " has been sold for " + Integer.toString((int)i.getPrice()/2) + " coins!");
    }
    public void printInv() {
        //PRINT INVENTORY TO SELL ITEMS IN MARKET
        // inv.forEach((k,v) -> System.out.println(v));
        System.out.println(this.name + "'s Inventory:");
        String[] t = {"Weapon", "Armor", "Potion", "Spell"};
        String[] pt = {"Weapons", "Armor", "Potions", "Spells"};
        for (int i = 0; i < 4; i++) {
            int count = 0;
            // System.out.println(pt[i]);
            for (Item it: inv.values()) {
                if (it.getType().equals(t[i])) {
                    count++;
                    if (count == 1) System.out.println("\n"+ pt[i] + "\n");
                    System.out.println(invnum.get(it.getItemName()).toString() + "\t" + it.toString());
                }
            }
        }
        System.out.println();
    }

    public String si(int i) {
        return Integer.toString(i);
    }
    public String toString() {
        // FILL IN THE DISPLAY INFO FOR LVL, HP/MAX HP, MANA, EXP, SKILL LEVELS"
        String descr = /** "\n" + */ name + "\t("+type+")"  + "\tLevel: "+si(lvl) + "\tCoins: "+si(coins) 
                    + "\n\tStrength: "+si(strength) + "\tDefense: "+si(defense) + "\tDexterity: "+si(dexterity) + "\tAgility: "+si(agility)
                    + /**"\n" +*/ "\tHealth: "+si(hp)+"/"+si(maxhp) + "\t\tMana: "+si(mana)+"/"+si(maxmana) + "\tExp: "+si(exp)+"/"+si(10*lvl) /**+ "\n"*/;
        return descr;
    }

    public void levelup() {
        if (this.exp >= lvl*10) {
        lvl++;
        maxhp = 100*lvl;
        hp = maxhp;
        maxmana = maxmana + (int)(mana*0.1);
        mana = maxmana;
        strength = (int)(strength*1.05);
        defense = (int)(defense*1.05);
        dexterity = (int)(dexterity*1.05);
        agility = (int)(agility*1.05);
        switch (type) {
            case "Warrior":
                strength = (int)(strength*1.05);
                agility = (int)(agility*1.05);
                break;
            case "Sorcerer":
                dexterity = (int)(dexterity*1.05);
                agility = (int)(agility*1.05);
                break;
            case "Paladins":
                strength = (int)(strength*1.05);
                dexterity = (int)(dexterity*1.05);
                break;
            case "Useless":
                break;
        }
        ogstats[0] = strength;
        ogstats[1] = defense;
        ogstats[2] = dexterity;
        ogstats[3] = agility;

        System.out.println(this.name + " has LEVELED UP!");
        }
    }
    /**
    public boolean checklvlup() { // check level up in levelup
        if (this.exp >= lvl*10) return true;
        else return false;
    }
    */
    public void reward() { // Reward for winning Battle: +150 coins all Heroes
        this.coins = this.coins + 150;
    }
    public void reward(int coins) { // Reward for all Heroes ALIVE: +100*highest monster level and exp based on level INSTEAD OF +2 exp
        this.coins = this.coins + coins + 150;
        this.exp += 2;
        // this.exp = this.exp + 2 + (int)Math.ceil((this.exp - ((this.lvl-1)*10))/5);
    }

    public void regen() { //TODO add regen to rounds
        this.hp = Math.min(this.hp + (int)(maxhp*0.1), this.maxhp);
        this.mana = Math.min(this.mana + (int)(maxmana*0.1), this.maxmana);
    }

    public void revive() {
        this.hp = (int)(this.maxhp*0.5);
        this.coins = (int)this.coins/2;
    }
    public void resetHeroStats() {
        this.strength = ogstats[0];
        this.defense = ogstats[1];
        this.dexterity = ogstats[2];
        this.agility = ogstats[3];
        for (Item i: inv.values()) {
            if (i.getEquipped() == true) {
                i.unEquip();
            }
        }
    }

    public static void pr(Object o) {
        System.out.println(o);
    }

    public static void main(String[] args) {
        //Name/mana/strength/agility/dexterity/starting money/starting experience
        //Rillifane_Rallathil     1300    750     450   500     2500    9
        Hero RR = new Hero("Rillifane_Rallathil", "Sorcerer", 1300, 750, 450, 500, 2500, 9);
        System.out.println(RR);
        System.out.println("---");
        RR.updhp(35);
        System.out.println(RR);
        Hero Deku = new Hero("DEKU", "Warrior", 7, 72, 3000, 700, 200, 10, 300, 20, 10000);
        System.out.println(Deku);

        Potion Hp = new Potion("Healing_Potion", 250, 1, 100, new String[] {"Health"});
        Potion MT = new Potion("Mermaid_Tears", 850, 5, 100, new String[] {"Health", "Mana", "Strength", "Dexterity", "Agility"});
        Potion Amb = new Potion("Ambrosia", 1000, 8, 150, new String[] {"Health", "Mana", "Strength", "Dexterity", "Defense", "Agility"});

        Spell HW = new Spell("Heat_Wave", 450, 2, 600, 150, "Fire");
        Spell AC = new Spell("Arctic_Storm", 700, 6, 800, 300, "Ice");
        Spell LD = new Spell("Lightning_Dagger", 400, 1, 500, 150, "Lighning");
    
        RR.buyItem(Hp);
        RR.buyItem(MT);
        Deku.buyItem(Hp);
        Deku.buyItem(MT);
        Deku.buyItem(Amb);
        Deku.buyItem(AC);

        pr(RR);
        RR.printInv();
        pr(Deku);
        Deku.printInv();

        RR.sellItem(Hp);
        // Deku.equip(MT);

        pr(RR);
        pr(Deku);

        // pr(RR.checklvlup());
        // pr(Deku.checklvlup());

        Deku.levelup();
        pr(Deku);

        pr("Test here!");
        Deku.equip(MT);
        pr(Deku);

        Deku.resetHeroStats();
        pr(Deku);
        pr("TEST END HERE!");

        Deku.buyItem(Amb);
        Deku.buyItem(Amb);
        Deku.printInv();
        Deku.equip(Amb);
        pr(Deku);

        RR.printInv();
        Deku.printInv();

        Hero[] ah = new Hero[3];
        ah[0] = RR;
        ah[1] = Deku;
        Monster Cyr = new Monster("Cyrrollalee", "Exoskeleon", 7, 700, 800, 75);
        Monster[] am = new Monster[2];
        am[0] = Cyr;
        System.out.println("A MONSTER HAS APPEARED");
        for (Monster m: am) {
            if (m != null) pr(m);
        }
        Scanner s = new Scanner(System.in);
        for (Hero h: ah){
            if (h != null) {
                pr("RR is up!");
                pr("Which Monster would you like to attack?"); 
                String at = s.nextLine();
                int count = 0;
                for (Monster m: am) {
                    if (m!=null && m.getName().equals(at)) {
                        h.setAttacking(m);
                        count++;
                    }
                }
                if (count == 0) pr("Please choose a Monster in the battle to attack.");
                pr("What would you like to do? Equip Armor/Weapons, Attack Monster, or Use Potion/Cast Spell?");
                String act = s.nextLine();
                if (act.equals("a")) {
                    h.attack(h.getAttacking());
                    pr(h.getAttacking());
                }
                if (act.equals("cs")) {
                    h.printInv();
                    pr("Which spell would you like to cast?");
                    String spn = s.nextLine();
                    if (h.getInv().containsKey(spn)) {
                        h.equip(h.getInv().get(spn));
                    }
                    h.printInv();
                    pr(h.getAttacking());
                }
            }
        }
        for (Monster m: am){
            if (m != null) {
                pr("Enemy is up!");
                pr("Enemy is attacking " + ah[0].getName());
                m.attack(ah[0]);
                pr(RR);
                pr(Deku);
            }
        }
        Cyr.resetMonster();
        pr(Cyr);
        // pr("Which Monster would you like to attack?");
        // pr(Arrays.toString(ah)); CAN'T DO THIS BC IT WILL ALSO PRINT NULL
        // pr(Arrays.toString(am)); CAN'T DO THIS BC IT WILL ALSO PRINT NULL

        // s.close();
    }
}