import java.util.*;

public class Sorcerer extends Hero {

    public Sorcerer (String n, int m, int s, int a, int dx, int startingmoney, int startingexp) {
        name = n;
        type = "Sorcerer";
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
    public Sorcerer (String n, int l, int e, int h, int m, int s, int d, int dx, int a, int c) {
        name = n;
        type = "Sorcerer";
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
            // case "Sorcerer":
                dexterity = (int)(dexterity*1.05);
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