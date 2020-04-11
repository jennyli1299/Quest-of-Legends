import java.util.HashMap;
// import java.util.Map;
// import java.util.Arrays;
import java.util.*;

public class Market {
    // private HashMap<String, Weapon> W; //Weapons
    // private HashMap<String, Armor> A; //Armor
    // private HashMap<String, Potion> P; //Potions
    // private HashMap<String, Spell> S; //Spells
    private HashMap<String, Item> M;

    /**public Market(Item[][] specs) {
        W = new HashMap<String, Weapon>();
        A = new HashMap<>();
        P = new HashMap<>();
        S = new HashMap<>();
        for (int t = 0; t < 4; t++) {
            for (Item i: specs[t]) {
                switch(t) {
                    case 0:
                        W.put(i.getItemName(), i);
                        break;
                    case 1:
                        A.put(i.getItemName(), i);
                        break;
                    case 2:
                        P.put(i.getItemName(), i);
                        break;
                    case 3:
                        S.put(i.getItemName(), i);
                        break;
                }
            }
        }
    } */
    /**
    private Item[] W; //Weapons
    private Item[] A; //Armor
    private Item[] P; //Potions
    private Item[] S; //Spells

    public Market(Item[][] specs) {
        W = specs[0];
        A = specs[1];
        P = specs[2];
        S = specs[3];
    }
    */

    public Market (HashMap<String, Item> themarket) {
        M = themarket;
    }

    public void printMarket() {
        //PRINT ITEMS IN MARKET
        // inv.forEach((k,v) -> System.out.println(v));
        System.out.println("Welcome to the Market! I hope you find all the gear you need for your Quest!\nWe offer the folllowing Items: ");
        String[] t = {"Weapon", "Armor", "Potion", "Spell"};
        String[] pt = {"Weapons", "Armor", "Potions", "Spells"};
        for (int i = 0; i < 4; i++) {
            int count = 0;
            // System.out.println(pt[i]);
            for (Item it: M.values()) {
                if (it.getType().equals(t[i])) {
                    count++;
                    if (count == 1) System.out.println("\n"+ pt[i] +"\n");
                    System.out.println(it);
                }
            }
        }
    }

    public HashMap<String, Item> getItems() {
        return this.M;
    }

    // TODO BUY ITEM IN MARKET
    // UNDONE THIS IS IMPLEMENTED IN QUEST CLASS INSTEAD
    /** 
    public void shop() {
        Scanner shopper = new Scanner(System.in);
        boolean done = false;
        do {
            printMarket();
            System.out.println("See anything that interests you?");
            done = true;
        }
        while (!done);
    }
    */


    public static void main(String[] args) {
        HashMap<String, Item> allItems = SetUp.AllItems();
        Market m = new Market(allItems); 
        m.printMarket();
    }
}