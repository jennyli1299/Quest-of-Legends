import java.util.HashMap;

public class Market {
    //The market class which holds all of the items(weapons, armor, potions, and spells) in the Quest of Legends
    //Heroes can enter and purchase items

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

}
