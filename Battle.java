// import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class Battle {
    ArrayList<Hero> Ah; // Alive Heroes Hero[] Ah
    // ArrayList<Hero> Dh; // Dead Heroes Hero[] Dh
    ArrayList<Monster> Am; // Alive Monsters Monster[] Am
    // ArrayList<Monster> Dm; // Dead Monsters Monster[] Dm
    int highestMonsterlevel;
    String victory;
    int pvp;
    int numAH;
    int numAM;
    int monturn;

    public Battle(Hero[] hlist, HashMap<String, Monster> mlist) {
        Ah = new ArrayList<Hero>();
        for (Hero h: hlist) {Ah.add(h);}
        pvp = hlist.length;
        numAH = hlist.length;
        int high = 0;
        Iterator<Hero> iter = Ah.iterator();
        while (iter.hasNext()) {
            int hl = iter.next().getLVL();
            if (hl > high) high = hl;
        }
        Am = new ArrayList<Monster>();
        ArrayList<Monster> hlM = new ArrayList<Monster>();
        ArrayList<Monster> llM = new ArrayList<Monster>();
        if (high <= 2) {
            for (Monster m: mlist.values()) {
                if (m.getLVL() == 1){
                    llM.add(m);
                    hlM.add(m);
                }
            }
            for (Monster m: mlist.values()) {
                if (m.getLVL() <= high+1){
                    hlM.add(m);
                }
            }
        }
        else {
            for (Monster m: mlist.values()) {
                if (m.getLVL() < high-1){
                    llM.add(m);
                }
                else if (m.getLVL() <= high+1){
                    hlM.add(m);
                }
            }
        }
        int m = pvp;
        // System.out.println(Arrays.toString(hlM.toArray()));
        // System.out.println(Arrays.toString(llM.toArray()));
        Iterator<Monster> iterm = hlM.iterator();
        int highest = 1;
        while (iterm.hasNext() && highest > 0) {
            Monster thism = iterm.next();
            if (!iterm.hasNext()) {
                Am.add(thism);
                m--;
                highestMonsterlevel = thism.getLVL();
            }
            else if (Math.random() < 0.49) {
                Am.add(thism);
                highest--;
                m--;
                highestMonsterlevel = thism.getLVL();
            }
        }
        do {
            ListIterator<Monster> iterlm = llM.listIterator();
            while (iterlm.hasNext() && m > 0) {
                Monster thism = iterlm.next();
                if (Math.random() < 0.49) {
                    if (!Am.contains(thism)) {
                        Am.add(thism);
                        m--;    
                    }
                }
            }
        }
        while (m > 0);
        numAM = numAH;

        // Dh = new ArrayList<Hero>();
        // Dm = new ArrayList<Monster>();
        
        // mlist.forEach((k,v) -> {
        //     if (v.getLVL() < high-1){
        //         llM.add(v);
        //     }
        //     else if (v.getLVL() <= high+1){
        //         hlM.add(v);
        //     }});
        
        victory = "";
        monturn = 0;
    }

    public void I() {
        System.out.println("\nYou asked for your team's stats, so HERE THEY ARE:");
        Iterator<Hero> iterh = Ah.iterator();
        while (iterh.hasNext()) {
            Hero h = iterh.next();
            System.out.println(h);
        }
        System.out.println();
    }
    public void MI() {
        System.out.println("\nYou asked for the enemy's stats, so HERE THEY ARE:");
        Iterator<Monster> iterm = Am.iterator();
        while (iterm.hasNext()) {
            Monster m = iterm.next();
            System.out.println(m);
        }
        System.out.println();
    }

    public void fight() {
        Scanner fightinput = new Scanner(System.in);
        // WHILE LOOP VICTORY.EQUALS("")
        while (victory.equals("")) {
        // HEROES FIGHT
            // print Stats (H, M)
            System.out.println("\nHere is the current state of things: ");
            printAliveH();
            System.out.println();
            printAliveM();
            
            Iterator<Hero> itH = Ah.iterator();
            while (itH.hasNext()) {
                Hero current = itH.next();
                if (current.getHealth() > 0) {
                System.out.println("\nIt is " + current.getName() + "'s move!\n");
                // printAliveH();
                // System.out.println();
                // printAliveM();
                // System.out.println();
                boolean cont = false;
                String action = "";
                do {
                    System.out.println(current.getName() + " may either 1: Equip gear [Armor/Weapon], 2: Attack an enemy monster, 3: Use Potion or Cast Spell");
                    System.out.println("Please enter 1, 2, or 3 corresponding to your choice of action: ");
                    action = fightinput.nextLine();
                    if (action.equals("I") || action.equals("i")) {
                        I();
                    }
                    else if (action.equals("MI")) {
                        MI();
                    }
                    else if (action.equals("1") || action.equals("2") || action.equals("3")) {
                        cont = true;
                    }
                    else {
                        System.out.println("Sorry, that is not valid input.");
                        cont = false;
                    }
                }
                while (!cont);
                cont = false;


                // either armor/sword (printINV) VALIDATE, attack(getAttacking()), use potion/cast spell (printINV) VALIDATE
                if (action.equals("1")) {
                    //EQUIP GEAR
                    System.out.println("You have chosen to equip gear! Here is your inventory: ");
                    current.printInv();
                    do {
                        System.out.println(current.getName() + " may only equip armor and/or weapons. \nEnter the name of an Armor or Weapon you would like to equip or None to exit Inventory and end your turn: ");
                        String chosenItem = fightinput.nextLine();
                        HashMap<String, Item> inv = current.getInv();
                        if (chosenItem.equals("None") || chosenItem.equals("none")) {
                            // System.out.println("End of " + current.getName() + "'s turn.");
                            System.out.println("I see you are done gearing up! End of turn.");
                            cont = true;
                        }
                        else if (chosenItem.equals("I") || chosenItem.equals("i")) I();
                        else if (chosenItem.equals("MI")) MI();
                        else if (inv.containsKey(chosenItem)) {
                            Item e = inv.get(chosenItem);
                            if ((e.getType().equals("Armor") || e.getType().equals("Weapon")) && (e.getEquipped() == false)) {
                                current.equip(e);
                                System.out.println("Good Choice! Item equipped.");
                                // System.out.println("Would you like to equip another Armor or Weapon? Enter Y if you would like to equip another item: ");
                                // String again = fightinput.nextLine();
                                // if (again.equals("Y")) {
                                //     System.out.println("Alright! It's never a bad idea to be well equipped.");
                                //     cont = false;
                                // }
                                // else {
                                //     System.out.println("I see you are done gearing up! End of turn.");
                                //     cont = true;
                                // }
                            }
                            else {
                                System.out.println("Sorry, that is not a valid unequipped Armor or Weapon in your possession.");
                                cont = false;
                            }
                        }
                        else {
                            System.out.println("Sorry, that is not a valid unequipped Armor or Weapon in your possession.");
                            cont = false;
                        }
                    }
                    while (cont == false);
                    System.out.println(current.getName() + "'s current stats are as follows: ");
                    System.out.println(current);
                }
                else if (action.equals("2")) {
                    // choose monster, VALIDATE, setAttacking
                    System.out.println("You have chosen to attack!");
                    // printAliveM();
                    do {
                        System.out.println(current.getName() + " may attack one monster. Enter the name of the Monster you would like to attack: ");
                        String attackM = fightinput.nextLine();
                        int[] valid = containsAM(attackM);
                        if (valid[0] == 1) {
                            current.setAttacking(Am.get(valid[1]));
                            cont = true;
                        }
                        else if (attackM.equals("I") || attackM.equals("i")) I();
                        else if (attackM.equals("MI")) MI();
                        else {
                            System.out.println("Sorry, that is not a valid alive monster.");
                            cont = false;
                        }
                    }
                    while (cont == false);
                    current.attack(current.getAttacking());
                }
                else if (action.equals("3")) {
                    //USE POTION/CAST SPELL
                    System.out.println("You have chosen to use a Potion or cast a Spell! Here is your inventory: ");
                    current.printInv();
                    do {
                        System.out.println(current.getName() + " may only either use ONE Potion OR cast ONE Spell. \nEnter the name of the Potion or Spell you would like to use or None if you wish to exit Inventory and end your turn: ");
                        String chosenItem = fightinput.nextLine();
                        HashMap<String, Item> inv = current.getInv();
                        if (chosenItem.equals("None") || chosenItem.equals("none")) {
                            // System.out.println("End of " + current.getName() + "'s turn.");
                            cont = true;
                        }
                        else if (chosenItem.equals("I") || chosenItem.equals("i")) I();
                        else if (chosenItem.equals("MI")) MI();
                        else if (inv.containsKey(chosenItem)) {
                            Item e = inv.get(chosenItem);
                            if (e.getType().equals("Potion")) {
                                current.equip(e);
                                cont = true;
                                System.out.println("Good Choice! Potion used. Here are " + current.getName() + "'s stats now: ");
                                System.out.println(current);
                            }
                            else if (e.getType().equals("Spell")) {
                                if (current.canCast((Spell)e)) {
                                    System.out.println("Who would you like to cast " + e.getItemName() + " on? The enemies are: ");
                                    printAliveM();
                                    do {
                                        System.out.println("Enter the name of the Monster you would like to cast this spell on: ");
                                        String attackM = fightinput.nextLine();
                                        int[] valid = containsAM(attackM);
                                        if (valid[0] == 1) {
                                            current.setAttacking(Am.get(valid[1]));
                                            cont = true;
                                        }
                                        else if (attackM.equals("I") || attackM.equals("i")) I();
                                        else if (attackM.equals("MI")) MI();
                                        else {
                                            System.out.println("Sorry, that is not a valid alive Monster.");
                                            cont = false;
                                        }
                                    }
                                    while (cont == false);
                                    current.equip(e);
                                }
                                else {
                                    System.out.println("You don't have enough mana to cast this spell! Please choose another spell");
                                    cont = false;
                                }
                            }
                        }
                        else {
                            System.out.println("Sorry, that is not a valid Potion or Spell in your possession.");
                            cont = false;
                        }
                    }
                    while (cont == false);
                }
                System.out.println("End of " + current.getName() + "'s turn.");
                cont = false;

                // checkDeath
                checkDeath();
                // checkVictory & if Victory, break;
                if (checkVictory()) break;
                }
            }

        // Monsters fight
            Iterator<Monster> itM = Am.iterator();
            System.out.println();
            while (itM.hasNext()) {
                Monster m = itM.next();
                if (m.getHealth() > 0) {
                    Hero target = Ah.get(monturn%pvp);
                    if (target.getHealth() <= 0) {
                        monturn++;
                        target = Ah.get(monturn%pvp);
                    }
                    m.attack(target);
                    checkDeath();
                    if (checkVictory()) break;
                    monturn++;
                }
            }
            // turn mod numAh
            // attack
            // inc turn
            // checkDeath
            // checkVIctory
        }
        System.out.println("The " + victory + " have won this battle! But who will win the war?");
        reward();
        revive();
        reset();
        printAliveH();
        printFallenH();
        // Print Victory
        // If Heroes win, print spoils of war
        // revive, reward, reset
        System.out.println("END OF BATTLE!\n");
        // fightinput.close();
    }

    public int[] containsAM (String name) {
        int[] ret = new int[] {-1, -1};
        Iterator<Monster> iter = Am.iterator();
        while (iter.hasNext()) {
            Monster m = iter.next();
            if (m.getName().equals(name) && (m.getHealth() > 0)) {
                ret[1] = Am.indexOf(m);
                ret [0] = 1;
            }
        }
        return ret;
    }

    public void printAliveH() {
        Iterator<Hero> iterh = Ah.iterator();
        while (iterh.hasNext()) {
            Hero h = iterh.next();
            if (h.getHealth() > 0) {
                System.out.println(h);
            }        
        }
    }
    public void printFallenH() {
        Iterator<Hero> iterh = Ah.iterator();
        while (iterh.hasNext()) {
            Hero h = iterh.next();
            if (h.getHealth() <= 0) {
                System.out.println(h);
            }
        }
    }
    public void printAliveM() {
        Iterator<Monster> iterm = Am.iterator();
        while (iterm.hasNext()) {
            Monster m = iterm.next();
            if (m.getHealth() > 0) {
                System.out.println(m);
            }
            // System.out.println(iterm.next());
        }
    }

    public void checkDeath() {
        int dead = 0;
        Iterator<Hero> hiter = Ah.iterator();
        while (hiter.hasNext()) {
            Hero h = hiter.next();
            if (h.getHealth() <= 0) {
                dead++;
            }
        }
        numAH = pvp - dead;
        dead = 0;
        Iterator<Monster> miter = Am.iterator();
        while (miter.hasNext()) {
            Monster m = miter.next();
            if (m.getHealth() <= 0) {
                dead++;
            }
        }
        numAM = pvp - dead;
    }

    public boolean checkVictory() {
        if (numAH == 0) {
            victory = "Monsters";
        }
        if (numAM == 0) {
            victory = "Heroes";
        }
        if (this.victory.equals("")) {
            return false;
        }
        else return true;
    }

    public void revive() {// MUST REWARD BEFORE REVIVE
        Iterator<Hero> hiter = Ah.iterator();
        while (hiter.hasNext()) {
            Hero h = hiter.next();
            if (h.getHealth() <= 0) h.revive();
        }
    }

    public void reward() {
        if (victory.equals("Heroes")) {
            System.out.println("GOOD WORK! Here, have a little something for your troubles!");
            Iterator<Hero> hiter = Ah.iterator();
            while (hiter.hasNext()) {
                Hero h = hiter.next();
                if (h.getHealth() > 0) {
                h.reward(highestMonsterlevel*100);
                }
                h.reward();
                // h.levelup();
            }
        }
        else if (victory.equals("Monsters")) {
            Iterator<Monster> miter = Am.iterator();
            while (miter.hasNext()) {
                Monster m = miter.next();
                if (m.getHealth() > 0) m.reward();
            }
        }
    }

    public void reset() {
        Iterator<Hero> hiter = Ah.iterator();
        while (hiter.hasNext()) {
            Hero h = hiter.next();
            h.resetHeroStats();
            h.levelup();
        }
        // Iterator<Hero> dhiter = Dh.iterator();
        // while (dhiter.hasNext()) {
        //     Hero h = dhiter.next();
        //     h.resetHeroStats();
        //     h.levelup();
        // }
        Iterator<Monster> miter = Am.iterator();
        while (miter.hasNext()) {
            Monster m = miter.next();
            m.resetMonster();
            m.levelup();
        }
        // Iterator<Monster> dmiter = Dm.iterator();
        // while (dmiter.hasNext()) {
        //     Monster m = dmiter.next();
        //     m.resetMonster();
        //     m.levelup();
        // }
    }

    public static void main(String[] args) {
        Hero[] t = SetUp.chooseYourFighters();
        Battle QB = new Battle(t, SetUp.AllMonsters());
        QB.fight();
    }
}