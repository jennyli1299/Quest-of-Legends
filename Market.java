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


    public static void main(String[] args) {
        HashMap<String, Item> allItems = SetUp.AllItems();
        Market m = new Market(allItems); 
        m.printMarket();
    }
}