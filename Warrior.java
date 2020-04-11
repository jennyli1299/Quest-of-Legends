import java.util.*;

public class Warrior extends Hero {

    public Warrior (String n, int m, int s, int a, int dx, int startingmoney, int startingexp) {
        super(n, "Warrior", m, s, a, dx, startingmoney, startingexp);
        loc = null; // TODO CREATE SOMETHING TO GIVE IT A LOCATION
        boardpiece = null; //TODO UPDATE CONSTRUCTORS
        nearbyEnemies = null; //TODO create scan for enemies based on LOC
    }
    public Warrior (String n, int l, int e, int h, int m, int s, int d, int dx, int a, int c) {
        super(n, "Warrior", l, e, h, m, s, d, dx, a, c);
        loc = null; // TODO CREATE SOMETHING TO GIVE IT A LOCATION
        boardpiece = null; //TODO UPDATE CONSTRUCTORS
        nearbyEnemies = null; //TODO create scan for enemies based on LOC
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
        // switch (type) {
            // case "Warrior":
                strength = (int)(strength*1.05);
                agility = (int)(agility*1.05);
                // break;
        // }
        ogstats[0] = strength;
        ogstats[1] = defense;
        ogstats[2] = dexterity;
        ogstats[3] = agility;
        
        System.out.println(this.name + " has LEVELED UP!");
        }
    }

    
    public static void main(String[] args) {
        
    }
}