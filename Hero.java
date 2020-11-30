import java.util.HashMap;
import java.util.ArrayList;
// import java.util.Iterator;
// import java.util.Map;
// import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Hero extends InGameCharacter {
    //Subclass of InGameCharacter which represents the hero, AKA human-controlled characters.

    // could have arrays to store information ://
    protected int maxmana;
    protected int mana;
    protected int dexterity;
    protected int coins;
    protected HashMap<String, Item> inv; // Inventory
    protected HashMap<String, Integer> invnum;
    protected Monster attacking;
    protected int[] ogstats; // Strength, Defense, Dexterity, Agility
    protected Tile base;

    public Hero() {
        name = "a Hero";
        type = "Useless";
        HM = "Hero";
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
        ogstats = new int[] { strength, defense, dexterity, agility };

        loc = null;
        base = null;
        boardpiece = null;
        nearbyEnemies = new ArrayList<InGameCharacter>();
    }

    public Hero(String n, String t, int m, int s, int a, int dx, int startingmoney, int startingexp) {
        name = n;
        type = t;
        HM = "Hero";
        lvl = (int) Math.ceil((double) startingexp / 10);
        exp = startingexp;
        maxhp = 100 * lvl;
        hp = maxhp;
        strength = s;
        defense = 0;
        agility = a;
        dexterity = dx;
        maxmana = m;
        mana = maxmana;
        coins = startingmoney;
        exp = startingexp;
        attacking = new Monster(); // maybe null
        inv = new HashMap<>();
        invnum = new HashMap<>();
        ogstats = new int[] { strength, defense, dexterity, agility };

        loc = null;
        base = null;
        boardpiece = null;
        nearbyEnemies = new ArrayList<InGameCharacter>();
    }

    public Hero(String n, String t, int l, int e, int h, int m, int s, int d, int dx, int a, int c) { // SUPER CUSTOM
                                                                                                      // HERO
        name = n;
        type = t;
        HM = "Hero";
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
        attacking = new Monster(); // maybe null
        inv = new HashMap<>();
        invnum = new HashMap<>();
        coins = c;
        ogstats = new int[] { strength, defense, dexterity, agility };

        loc = null;
        base = null;
        boardpiece = null;
        nearbyEnemies = new ArrayList<InGameCharacter>();
    }

    @Override
    public void spawn(Tile loc, int n) {
        moveTo(loc);
        setPiece(n);
        setBase(loc);
    }
    public void setBase(Tile b) {
        base = b;
    }
    public Tile getBase() {
        return base;
    }

    public void BacktoBase() {
        this.moveTo(base);
    }

    @Override
    public void setPiece(int n) {
        this.boardpiece = "H" + Integer.toString(n);

    }

    // Updates a Hero's attributes based on int array (argument param)
    private void updAttributes(int[] a) {
        this.hp += a[0];
        this.mana += a[1];
        this.strength += a[2];
        this.defense += a[3];
        this.dexterity += a[4];
        this.agility += a[5];
    }

    public void setAttacking(InGameCharacter m) {
        this.attacking = (Monster) m;
    }
    public Monster getAttacking() {
        return this.attacking;
    }

    public HashMap<String, Item> getInv() {
        return this.inv;
    }

    // Print Inventory
    public void printInv() {
        // PRINT INVENTORY TO SELL ITEMS IN MARKET
        // inv.forEach((k,v) -> System.out.println(v));
        System.out.println(this.name + "'s Inventory:");
        String[] t = { "Weapon", "Armor", "Potion", "Spell" };
        String[] pt = { "Weapons", "Armor", "Potions", "Spells" };
        for (int i = 0; i < 4; i++) {
            int count = 0;
            // System.out.println(pt[i]);
            for (Item it : inv.values()) {
                if (it.getType().equals(t[i])) {
                    count++;
                    if (count == 1)
                        System.out.println("\n" + pt[i] + "\n");
                    System.out.println(invnum.get(it.getItemName()).toString() + "\t" + it.toString());
                }
            }
        }
        System.out.println();
    }
    // Print specifc section of Inventory according to String itemtype argument
    public void printSpecificINV(String itemtype) {
        if (itemtype.equals("Armor")) {
            System.out.println(this.name + "'s " + itemtype + ":");
        }
        else System.out.println(this.name + "'s " + itemtype + "s:");
        for (Item it : inv.values()) {
            if (it.getType().equals(itemtype)) {
                System.out.println(invnum.get(it.getItemName()).toString() + "\t" + it.toString());
            }
        }
    }

    /**
     * Hero's SHOP: Buy/Sell at Market M
     */

    // Explore the Market / Window Shop
    public void exploreMarket(Market M, Scanner qScan) {
        System.out.println("Let's go shopping!");
        boolean BSL = false;
        String shop = "";
        do {
            System.out.println("Would you like to buy or sell items or leave the Market? Enter Buy, Sell, or Leave");
            shop = qScan.nextLine();
            shop.toLowerCase();
            if (shop.equals("buy") || shop.equals("Buy")) {
                System.out.println("YAY! I love to shop!");
                shop(M, qScan);
            } else if (shop.equals("Sell") || shop.equals("sell")) {
                System.out.println("Alright! All this gear was getting kind of heavy anyway.");
                sell(M, qScan);
            } else if (shop.equals("Leave") || shop.equals("leave")) {
                BSL = true;
            } else {
                System.out.println("I don't recognize that request.");
            }
        } while (!BSL);
    }

    // BUY
    public void shop(Market M, Scanner qScan) {
        boolean done = false;
        M.printMarket();
        do {
            // M.printMarket();
            System.out.println(
                    "See anything that interests you? Input [Y]/[N] or [Inv] to print current Inventory or [M] to see the listings again.");
            String shop = qScan.nextLine();
            shop = shop.toLowerCase();
            if (shop.equals("y") || shop.equals("yes")) {
                boolean complete = false;
                do {
                    System.out.println(
                            "Which item? Please enter the full name of the Item as written in the catalog or enter DONE to leave the market at any time: ");
                    shop = qScan.nextLine();
                    if (shop.equals("I") || shop.equals("i")) {
                        System.out.println("\nYou asked for this Hero's stats, so HERE THEY ARE:");
                        System.out.println(this);
                    } else if (shop.toUpperCase().equals("DONE")) {
                        complete = true;
                        done = true;
                    } else if (M.getItems().containsKey(shop)) {
                        Item i = M.getItems().get(shop);
                        if (i.getType().equals("Spell")) {
                            if (inv.containsKey(shop)) {
                                System.out.println("You've already mastered this Spell!");
                            }
                            else {
                                buyItem(i);
                                complete = true;
                            }
                        }
                        else {
                            buyItem(i);
                            complete = true;
                        }
                    } else {
                        System.out.println("The market doesn't seem to carry that... Did you mispell the Item name?");
                    }
                } while (!complete);
            } else if (shop.equals("no") || shop.equals("n")) {
                done = true;
                System.out.println("Alright, maybe next time then! ONWARD with our QUEST!");
            } else if (shop.equals("m") || shop.equals("market")) {
                M.printMarket();
            } else if (shop.equals("I") || shop.equals("i")) {
                System.out.println("\nYou asked for this Hero's stats, so HERE THEY ARE:");
                System.out.println(this);
            } else if (shop.equals("inv")) {
                printInv();
            } else {
                System.out.println("Um, what?");
            }
        } while (!done);
        System.out.println("Thanks for visiting the Market!");
    }
    public boolean buyItem(Item i) {
        if (i.getUnlockLvl() > this.lvl) {
            System.out.println("Young Hero, you are not yet ready. You must be at least level " + i.getUnlockLvl()
                    + " to purchase this item.");
            return false;
        } else if (i.getPrice() > this.coins) {
            System.out.println(
                    "I'm afraid you can't purchase this item young hero. It's worth more than you have. Come again later when you have more coins.");
            return false;
        } else {
            this.coins -= i.getPrice();
            String in = i.getItemName();
            inv.put(in, i);
            if (invnum.containsKey(in)) {
                int n = invnum.get(in).intValue();
                invnum.replace(in, Integer.valueOf(n + 1));
            } else {
                invnum.put(in, Integer.valueOf(1));
                inv.put(in, i);
            }
            System.out.println(
                    i.getItemName() + " has been added to your inventory! Thanks for shopping! Come again soon!");
            return true;
        }
    }

    // SELL
    public void sell(Market M, Scanner qScan) {
        System.out.println(
                "Welcome to the Market! I'm sure you have many treasures from your QUEST. \nInterested in showing me what you got? I'll pay handsomely. If not, enter DONE at any time to leave the marketplace.");
        boolean done = false;
        HashMap<String, Item> hinv = getInv();
        printInv();
        do {
            String sell = "";
            boolean sold = false;
            do {
                System.out.println(
                        "Which Item would you like to sell? Please enter the full name of the Item as displayed in the Hero's inventory or [DONE] to exit the pawnshop.");
                sell = qScan.nextLine();
                if (hinv.containsKey(sell)) {
                    sellItem(hinv.get(sell));
                    sold = true;
                } else if (sell.equals("DONE") || sell.equals("Done") || sell.equals("done")) {
                    sold = true;
                    done = true;
                } else if (sell.equals("I") || sell.equals("i")) {
                    System.out.println(this);
                } else {
                    System.out.println("I'm not sure I've heard of that Item... Try again, please.");
                }
            } while (!sold);
        } while (!done);
        System.out.println("Thanks for visiting the Market!");
    }
    public void sellItem(Item i) {
        removefromINVs(i);
        this.coins += (int) i.getPrice() / 2;
        System.out.println("What a wonderful sale! " + i.getItemName() + " has been sold for "
                + Integer.toString((int) i.getPrice() / 2) + " coins!");
    }
    public void removefromINVs(Item i) {
        // use Potion --> decrease count in inventory
        String in = i.getItemName(); // Item Name
        if (invnum.get(in).intValue() == 1) {
            inv.remove(in);
            invnum.remove(in);
        } else {
            int n = invnum.get(in).intValue();
            invnum.replace(in, Integer.valueOf(n - 1));
        }
    }
    /**
     * Hero's Shopping ends here
     */

    /**
     * HERO'S ACTION INPUT: Fight, Use Potion, Cast Spell, Equip Gear
     */

    // Fight
    public void fight(Scanner fightinput) { 
        System.out.println("You have chosen to attack!");
        HashMap<String, Monster> hmNE = new HashMap<String, Monster>();
        for (InGameCharacter igc : nearbyEnemies) {
            Monster m = (Monster) igc;
            hmNE.put(m.getName(), m);
        }
        boolean MinNE = false;
        do {
            printNearbyEnemies();
            System.out.println(
                    getRep() + " may attack one monster. Enter the name of the Monster you would like to attack: ");
            String attackM = fightinput.nextLine();
            MinNE = hmNE.containsKey(attackM);
            if (MinNE) {
                Monster attack = hmNE.get(attackM);
                setAttacking(attack);
            } else {
                System.out.println("Sorry, that is not a valid alive monster.");
            }
        } while (!MinNE);
        MinNE = false;
        do {
            System.out.println("Would you like to Attack[A] or Cast a Spell[S] on this Monster?");
            String AorS = fightinput.nextLine();
            AorS = AorS.toLowerCase();
            if (AorS.equals("a") || AorS.equals("attack")) {
                attack(attacking);
                MinNE = true;
            } else if (AorS.equals("s")) {
                castSpell(fightinput);
                MinNE = true;
            }
        } while (!MinNE);
        System.out.println(this);
        System.out.println(attacking);
        if (attacking.getHealth() <= 0) {
            attacking.setDead();
            rewardIGC();
        }
    }

    // Cast a Spell
    public void castSpell(Scanner fightinput) {
        // CAST SPELL
        System.out.println("You have chosen to Cast a Spell! Here are all the Spells you've mastered: ");
        printSpecificINV("Spell");
        boolean done = false;
        do {
            System.out.println(getRep() + " may only Cast ONE Spell. \nEnter the name of the Spell you would like to use or None if you wish to exit Inventory and end your turn: ");
            String chosenItem = fightinput.nextLine();
            if (chosenItem.equals("None") || chosenItem.equals("none")) {
                // System.out.println("End of " + current.getName() + "'s turn.");
                done = true;
            }
            else if (inv.containsKey(chosenItem)) {
                Item e = inv.get(chosenItem);
                if (e.getType().equals("Spell")) {
                    if (canCast((Spell) e)) {
                        equip(e);
                        done = true;
                    } 
                    else {
                        System.out.println("You don't have enough mana to cast this spell! Please choose another spell");
                        done = false;
                    }
                }
                else {
                    System.out.println("Sorry, that is not a valid Spell in your repertoire.");
                    done = false;
                }
            } else {
                System.out.println("Sorry, that is not a valid Spell in your possession.");
                done = false;
            }
        } while (!done);
    }
    public boolean canCast(Spell s) {
        if (this.mana < s.getManaCost()) {
            System.out.println("Uh oh! Seems to me you don't have enough mana to cast this spell.");
            return false;
        } else
            return true;
    }
    public void castSpell(Monster m, int[] stats) {
        // Since a Hero spends mana to cast a spell, it's effect cannot be dodged but
        // the damage can be.
        int spelldmg = stats[0];
        spellDmg(m, spelldmg);
        int[] effect = Arrays.copyOfRange(stats, 1, 4);
        m.updAttributes(effect);
    }
    public void spellDmg(Monster m, int sd) {
        if (Math.random() <= m.agility / 100) {
            System.out.println(m.name + " DODGED spell damage! However, Spell residue is effective!");
        } else {
            int dmg = (int) (sd + (this.dexterity / 10000) * sd);
            if (this.getLoc().getType().equals("Bush")) { // Bush (inc dexterity (spell casting) 10%)
                dmg = (int) (dmg * 1.1);
            }
            m.updhp(dmg);
            System.out.println(m.getName() + " has taken " + dmg + " damage from " + this.name
                    + "'s SPELL! Spell Residue is effective!");
        }
    }

    // Use a Potion
    public void usePotion(Scanner fightinput) {
        // CAST SPELL
        System.out.println("You have chosen to Use a potion! Here is your inventory of Potions: ");
        printSpecificINV("Potion");
        boolean done = false;
        do {
            System.out.println(getRep() + " may only use ONE Potion. \nEnter the name of the Potion you wishto use or None if you wish to exit Inventory and end your turn: ");
            String chosenItem = fightinput.nextLine();
            if (chosenItem.equals("None") || chosenItem.equals("none")) {
                // System.out.println("End of " + current.getName() + "'s turn.");
                done = true;
            }
            else if (inv.containsKey(chosenItem)) {
                Item e = inv.get(chosenItem);
                if (e.getType().equals("Potion")) {
                    equip(e);
                    done = true;
                    System.out.println("Good Choice! Potion used. Here are " + getName() + "'s stats now: ");
                    System.out.println(this);
                }
            } else {
                System.out.println("Sorry, that is not a valid Potion in your possession.");
                done = false;
            }
        } while (!done);
    }

    // Equip Gear
    public void gearUP(Scanner fightinput) {
        //EQUIP GEAR
        System.out.println("You have chosen to equip gear! Here is your inventory: ");
        System.out.println("Armor: ");
        printSpecificINV("Armor");
        System.out.println("Weapons: ");
        printSpecificINV("Weapon");
        boolean done = false;
        do {
            System.out.println(getName() + " may only equip armor and/or weapons. \nEnter the name of an Armor or Weapon you would like to equip or None to exit Inventory and end your turn: ");
            String chosenItem = fightinput.nextLine();
            if (chosenItem.equals("None") || chosenItem.equals("none")) {
                System.out.println("I see you are done gearing up! End of turn.");
                done = true;
            }
            else if (inv.containsKey(chosenItem)) {
                Item e = inv.get(chosenItem);
                if ((e.getType().equals("Armor") || e.getType().equals("Weapon")) && (e.getEquipped() == false)) {
                    equip(e);
                    System.out.println("Good Choice! Item equipped.");
                }
                else {
                    System.out.println("Sorry, that is not a valid unequipped Armor or Weapon in your possession.");
                    done = false;
                }
            }
            else {
                System.out.println("Sorry, that is not a valid unequipped Armor or Weapon in your possession.");
                done = false;
            }
        }
        while (done == false);
        System.out.println(getRep() + "'s current stats are as follows: ");
        System.out.println(this);
    }
    public void equip(Item i) {
        // WRITE AN UNEQUIP & MAKE SURE HERO IS ONLY WIELDING ONE WEAPON AND WEARING ONE
        // ARMOR AT A TIME (done)
        String itype = i.getType();
        // boolean alrdyON = false;
        if (itype.equals("Weapon")) { // || itype.equals("Armor")) {
            for (Item thisitem : inv.values()) {
                if (thisitem.getType().equals(itype)) {
                    // alrdyON |= thisitem.getEquipped();
                    if (thisitem.getEquipped()) {
                        this.takeOffWeapon(thisitem);
                        break;
                    }
                }
            }
        }
        // check for it in inventory TO DO
        // set equiped to true
        i.setEquipped();// equipItem();
        int[] stats = i.stats();
        if (stats.length == 6) {
            this.updAttributes(stats);
            if (i.getType().equals("Potion")) {
                removefromINVs(i);
            }
            i.unEquip();
        } else if (stats.length == 4) {
            Spell s = (Spell) i;
            // if (this.mana < s.getManaCost()) {
            // System.out.println("Uh oh! Seems to me you don't have enough mana to cast
            // this spell.");
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
    // // FILL IN
    // i.unEquip();
    // }
    public void takeOffWeapon(Item i) {
        int[] statstoreverse = i.stats();
        for (int s = 0; s < statstoreverse.length; s++) {
            statstoreverse[s] = -1 * statstoreverse[s];
        }
        i.unEquip();
        this.updAttributes(statstoreverse);
    }
    /**
     * Action Ends Here
     */


    /**
     * REWARD SYSTEM
     */
    @Override
    public void levelup() {
        if (this.exp >= lvl * 10) {
            lvl++;
            maxhp = 100 * lvl;
            hp = maxhp;
            maxmana = maxmana + (int) (mana * 0.1);
            mana = maxmana;
            strength = (int) (strength * 1.05);
            defense = (int) (defense * 1.05);
            dexterity = (int) (dexterity * 1.05);
            agility = (int) (agility * 1.05);
            switch (type) {
                case "Warrior":
                    strength = (int) (strength * 1.05);
                    agility = (int) (agility * 1.05);
                    break;
                case "Sorcerer":
                    dexterity = (int) (dexterity * 1.05);
                    agility = (int) (agility * 1.05);
                    break;
                case "Paladins":
                    strength = (int) (strength * 1.05);
                    dexterity = (int) (dexterity * 1.05);
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

    public void reward() { // Reward for winning Battle: +150 coins all Heroes
        this.coins = this.coins + 150;
    }

    public void reward(int coins) { // Reward for all Heroes ALIVE: +100*highest monster level and exp based on
                                    // level INSTEAD OF +2 exp
        this.coins = this.coins + coins + 150;
        this.exp += 2;
        // this.exp = this.exp + 2 + (int)Math.ceil((this.exp - ((this.lvl-1)*10))/5);
    }

    public void regen() {
        this.hp = Math.min(this.hp + (int) (maxhp * 0.1), this.maxhp);
        this.mana = Math.min(this.mana + (int) (maxmana * 0.1), this.maxmana);
    }

    public void revive() {
        this.hp = (int) (this.maxhp * 0.5);
        this.coins = (int) this.coins / 2;
        moveTo(base);
    }

    public void resetHeroStats() {
        this.strength = ogstats[0];
        this.defense = ogstats[1];
        this.dexterity = ogstats[2];
        this.agility = ogstats[3];
        for (Item i : inv.values()) {
            if (i.getEquipped() == true) {
                i.unEquip();
            }
        }
    }
    /**
     * End of Reward System Section
     */

    public String si(int i) {
        return Integer.toString(i);
    }

    public String toString() {
        // DISPLAY INFO FOR LVL, HP/MAX HP, MANA, EXP, SKILL LEVELS etc..."
        String descr = /** "\n" + */
                name + "\t(" + type + ")" + "\tLevel: " + si(lvl) + "\tCoins: " + si(coins) + "\n\tStrength: "
                        + si(strength) + "\tDefense: " + si(defense) + "\tDexterity: " + si(dexterity) + "\tAgility: "
                        + si(agility) + /** "\n" + */
                        "\tHealth: " + si(hp) + "/" + si(maxhp) + "\t\tMana: " + si(mana) + "/" + si(maxmana)
                        + "\tExp: " + si(exp) + "/" + si(10 * lvl) /** + "\n" */
        ;
        return descr;
    }

    public static void pr(Object o) {
        System.out.println(o);
    }

}
