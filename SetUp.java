import java.util.*;

public class SetUp { // redo so only one scanner for all
    HashMap<String, Hero> AllHeroes;
    HashMap<String, Hero> ChosenHeroes;
    HashMap<String, Monster> AllMonsters;

    HashMap<String, Weapon> allWeapons;
    HashMap<String, Armor> allArmor;
    HashMap<String, Potion> allPotions;
    HashMap<String, Spell> allSpells;

    HashMap<String, Item> allItems;

    public static HashMap<String, Hero> AllHeroes() {
        HashMap<String, Hero> AllHeroes = new HashMap<String, Hero>();
        /**
         * WARRIORS: Name   /mana/strength/agility/dexterity/starting money/starting experience
         * Gaerdal_Ironhand    100     700     500     600     1354    7
         * Sehanine_Moonbow    600     700     800     500     2500    8
         * Muamman_Duathall    300     900     500     750     2546    6
         * Flandal_Steelskin   200     750     650     700     2500    7
         * UNDEFEATED          700     1080    132     146     2017    7
         */
        // AllHeroes.put("Gaerdal_Ironhand", new Hero("Gaerdal_Ironhand", "Warrior", 100, 700, 500, 600, 1354, 7));
        // AllHeroes.put("Sehanine_Moonbow", new Hero("Sehanine_Moonbow", "Warrior", 600, 700, 800, 500, 2500, 8));
        // AllHeroes.put("Muamman_Duathall", new Hero("Muamman_Duathall", "Warrior", 300, 900, 500, 750, 2546, 6));
        // AllHeroes.put("Flandal_Steelskin", new Hero("Flandal_Steelskin", "Warrior", 200, 750, 650, 700, 2500, 7));
        // AllHeroes.put("UNDEFEATED", new Hero("UNDEFEATED", "Warrior", 700, 1080, 132, 146, 2017, 7));      
        AllHeroes.put("Gaerdal_Ironhand", new Warrior("Gaerdal_Ironhand", 100, 700, 500, 600, 1354, 7));
        AllHeroes.put("Sehanine_Moonbow", new Warrior("Sehanine_Moonbow", 600, 700, 800, 500, 2500, 8));
        AllHeroes.put("Muamman_Duathall", new Warrior("Muamman_Duathall", 300, 900, 500, 750, 2546, 6));
        AllHeroes.put("Flandal_Steelskin", new Warrior("Flandal_Steelskin", 200, 750, 650, 700, 2500, 7));
        AllHeroes.put("UNDEFEATED", new Warrior("UNDEFEATED", 700, 1080, 132, 146, 2017, 7));    
        /**
         * PALADINS: Name     /mana/strength/agility/dexterity/starting money/starting experience
         * Solonor_Thelandira   300     750     650     700     2500    7
         * Sehanine_Sunbow      300     750     700     700     2500    7
         * Skoraeus_Stonebones  250     650     600     350     2500    4
         * Garl_Glittergold     100     600     500     400     2500    5
         * eunoia*              700     1320    108     146     2016    7
         */
        // AllHeroes.put("Solonor_Thelandira", new Hero("Solonor_Thelandira", "Paladin", 300, 750, 650, 700, 2500, 7));
        // AllHeroes.put("Sehanine_Sunbow", new Hero("Sehanine_Sunbow", "Paladin", 300, 750, 700, 700, 2500, 7));
        // AllHeroes.put("Skoraeus_Stonebones", new Hero("Skoraeus_Stonebones", "Paladin", 250, 650, 600, 350, 2500, 4));
        // AllHeroes.put("Garl_Glittergold", new Hero("Garl_Glittergold", "Paladin", 100, 600, 500, 400, 2500, 5));
        // AllHeroes.put("eunoia*", new Hero("eunoia*", "Paladin", 100, 600, 500, 400, 2500, 5));
        AllHeroes.put("Solonor_Thelandira", new Paladin("Solonor_Thelandira", 300, 750, 650, 700, 2500, 7));
        AllHeroes.put("Sehanine_Sunbow", new Paladin("Sehanine_Sunbow", 300, 750, 700, 700, 2500, 7));
        AllHeroes.put("Skoraeus_Stonebones", new Paladin("Skoraeus_Stonebones", 250, 650, 600, 350, 2500, 4));
        AllHeroes.put("Garl_Glittergold", new Paladin("Garl_Glittergold", 100, 600, 500, 400, 2500, 5));
        AllHeroes.put("eunoia*", new Paladin("eunoia*", 100, 600, 500, 400, 2500, 5));
        /**
         * SORCERERS: Name        /mana/strength/agility/dexterity/starting money/starting experience
         * Reign                    700     750     700   146     2017    7
         * Calliber                1990     170     700   146     1996    7
         * HAVOC                   1996     700     700   146     1996    7
         * Skye_Soar                700     700     700   146     1999    7
         * Rillifane_Rallathil     1300     750     450   500     2500    9
         * Segojan_Earthcaller      900     800     500   650     2500    5
         */
        AllHeroes.put("Rillifane_Rallathil", new Sorcerer("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9));
        AllHeroes.put("Segojan_Earthcaller", new Sorcerer("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5));
        AllHeroes.put("Reign", new Sorcerer("Reign", 700, 750, 700, 146, 2017, 7));
        AllHeroes.put("Calliber", new Sorcerer("Calliber", 1990, 170, 700, 146, 1996, 7));
        AllHeroes.put("HAVOC", new Sorcerer("HAVOC", 1996, 700, 700, 146, 1996, 7));
        AllHeroes.put("Skye_Soar", new Sorcerer("Skye_Soar", 700, 750, 700, 146, 1999, 7));

        return AllHeroes;
    }

    public void printHeroes() {
        // inv.forEach((k,v) -> System.out.println(v));
        String[] t = {"Warrior", "Paladin", "Sorcerer"};
        String[] ht = {"Warriors", "Paladins", "Sorcerers"};
        for (int i = 0; i < 3; i++) {
            int count = 0;
            // System.out.println(pt[i]);
            for (Hero h: AllHeroes.values()) {
                if (h.getType().equals(t[i])) {
                    count++;
                    if (count == 1) System.out.println(ht[i]);
                    System.out.println(h);
                }
            }
        }
    }
    public static void printHeroes(HashMap<String, Hero> AllHeroes) {
        // inv.forEach((k,v) -> System.out.println(v));
        String[] t = {"Warrior", "Paladin", "Sorcerer"};
        String[] ht = {"Warriors", "Paladins", "Sorcerers"};
        for (int i = 0; i < 3; i++) {
            int count = 0;
            // System.out.println(pt[i]);
            for (Hero h: AllHeroes.values()) {
                if (h.getType().equals(t[i])) {
                    count++;
                    if (count == 1) {
                        System.out.println("\n"+ ht[i] + "\n");
                    }
                    System.out.println(h);
                }
            }
        }
    }


    // STATIC MONSTERS
    public static HashMap<String, Monster> AllMonsters() {
        HashMap<String, Monster> AllMonsters = new HashMap<String, Monster>();
        /**
         * DRAGONS: Name/level/damage/defense/dodge chance
         * Desghidorrah	 3       300       400     35
         * Chrysophylax  2       200       500     20
         * BunsenBurner	 4       400       500     45
         * Natsunomeryu  1       100       200     10
         * heScaleless	 7       700       600     75
         * Kas-Ethelinh  5       600       500     60
         * Alexstraszan	 10      1000     9000     55
         * Phaarthurnax	 6       600       700     60
         * D-Maleficent	 9       900       950     85
         * TheWeatherbe  8       800       900     80
         * Shard         10      1000    10000     10
         */
        AllMonsters.put("Desghidorrah", new Dragon("Desghidorrah", 3, 300, 400, 35));
        AllMonsters.put("Chrysophylax", new Dragon("Chrysophylax", 2, 200, 500, 20));
        AllMonsters.put("BunsenBurner", new Dragon("BunsenBurner", 4, 400, 500, 45));
        AllMonsters.put("Natsunomeryu", new Dragon("Natsunomeryu", 1, 100, 200, 10));
        AllMonsters.put("heScaleless",  new Dragon("heScaleless",  7, 700, 600, 75));
        AllMonsters.put("Kas-Ethelinh", new Dragon("Kas-Ethelinh", 5, 600, 500, 60));
        AllMonsters.put("Alexstraszan", new Dragon("Alexstraszan", 10, 1000, 9000, 55));
        AllMonsters.put("Phaarthurnax", new Dragon("Phaarthurnax", 6, 600, 700, 60));
        AllMonsters.put("D-Maleficent", new Dragon("D-Maleficent", 9, 900, 950, 85));
        AllMonsters.put("TheWeatherbe", new Dragon("TheWeatherbe", 8, 800, 900, 80));
        AllMonsters.put("Shard", new Dragon("Shard", 10, 1000, 10000, 10));
        /**
         * EXOSKELETONS: Name/level/damage/defense/dodge chance
         * Cyrrollalee     7       700        800     75
         * Brandobaris     3       350        450     30
         * BigBad-Wolf     1       150        250     15
         * WickedWitch     2       250        350     25
         * Aasterinian     4       400        500     45
         * Chronepsish     6       650        750     60
         * Kiaransalee     8       850        950     85
         * St-Shargaas     5       550        650     55
         * Merrshaullk     10      1000       900     55
         * St-Yeenoghu     9       950        850     90
         */
        AllMonsters.put("Cyrrollalee", new Exoskeleton("Cyrrollalee", 7, 700, 800, 75));
        AllMonsters.put("Brandobaris", new Exoskeleton("Brandobaris", 3, 350, 450, 30));
        AllMonsters.put("BigBad-Wolf", new Exoskeleton("BigBad-Wolf", 1, 150, 250, 15));
        AllMonsters.put("WickedWitch", new Exoskeleton("WickedWitch", 2, 250, 350, 25));
        AllMonsters.put("Aasterinian", new Exoskeleton("Aasterinian", 4, 400, 500, 45));
        AllMonsters.put("Chronepsish", new Exoskeleton("Chronepsish", 6, 650, 750, 60));
        AllMonsters.put("Kiaransalee", new Exoskeleton("Kiaransalee", 8, 850, 950, 85));
        AllMonsters.put("St-Shargaas", new Exoskeleton("St-Shargaas", 5, 550, 650, 55));
        AllMonsters.put("Merrshaullk", new Exoskeleton("Merrshaullk", 10, 1000, 900, 55));
        AllMonsters.put("St-Yeenoghu", new Exoskeleton("St-Yeenoghu", 9, 950, 850, 90));
        /**
         * SPIRITS: Name/level/damage/defense/dodge chance
         * Andrealphus     2       600       500     40
         * Aim-Haborym     1       450       350     35
         * Andromalius     3       550       450     25
         * Chiang-shih     4       700       600     40
         * FallenAngel     5       800       700     50
         * Ereshkigall     6       950       450     35
         * Melchiresas     7       350       150     75
         * Jormunngand     8       600       900     20
         * Rakkshasass     9       550       600     35
         * Taltecuhtli     10      300       200     50
         */
        AllMonsters.put("Andrealphus", new Spirit("Andrealphus", 2, 600, 500, 40));
        AllMonsters.put("Aim-Haborym", new Spirit("Aim-Haborym", 1, 450, 350, 35));
        AllMonsters.put("Andromalius", new Spirit("Andromalius", 3, 550, 450, 25));
        AllMonsters.put("Chiang-shih", new Spirit("Chiang-shih", 4, 700, 600, 40));
        AllMonsters.put("FallenAngel", new Spirit("FallenAngel", 5, 800, 700, 50));
        AllMonsters.put("Ereshkigall", new Spirit("Ereshkigall", 6, 950, 450, 35));
        AllMonsters.put("Melchiresas", new Spirit("Melchiresas", 7, 350, 150, 75));
        AllMonsters.put("Jormunngand", new Spirit("Jormunngand", 8, 600, 900, 20));
        AllMonsters.put("Rakkshasass", new Spirit("Rakkshasass", 9, 550, 600, 35));
        AllMonsters.put("Taltecuhtli", new Spirit("Taltecuhtli", 10, 300, 200, 50));

        return AllMonsters;
    }


    // STATIC MARKET ITEMS
    public static HashMap<String, Item> AllItems() {
        HashMap<String, Item> AllItems = new HashMap<String, Item>();
        /**
         * WEAPONS: Name  /cost /level/damage/required hands
         * Sword           500     1    800    1
         * Bow             300     2    500    2
         * Scythe         1000     6   1100    2
         * Axe             550     5    850    1
         * Shield          400     1    100    1
         * TSwords     	  1400     8   1600    2
         * Dagger          200     1    250    1      
         */
        AllItems.put("Sword", new Weapon("Sword", 500, 1, 800, 1));
        AllItems.put("Bow", new Weapon("Bow", 300, 2, 500, 2));
        AllItems.put("Scythe", new Weapon("Scythe", 1000, 6, 1100, 2));
        AllItems.put("Axe", new Weapon("Axe", 550, 5, 850, 31));
        // AllItems.put("Shield", new Weapon("Shield", 400, 1, 100, 1));
        AllItems.put("TSwords", new Weapon("TSwords", 1400, 8, 1600, 2));
        AllItems.put("Dagger", new Weapon("Dagger", 200, 1, 250, 1));
        /**
         * ARMOR: Name/cost/required level/damage reduction
         * Platinum_Shield       150   1   200
         * Shield                100   1   100
         * Breastplate           350   3   600
         * Full_Body_Armor       1000  8   1100
         * Wizard_Shield         1200  10  1500
         * Speed_Boots           550   4   600
         */
        AllItems.put("Shield", new Armor("Shield", 100, 1, 100));
        AllItems.put("Platinum_Shield", new Armor("Platinum_Shield", 150, 3, 300));
        AllItems.put("Wizard_Shield", new Armor("Wizard_Shield", 1200, 10, 1500));
        AllItems.put("Breastplate", new Armor("Breastplate", 350, 3, 600));
        AllItems.put("Full_Body_Armor", new Armor("Full_Body_Armor", 1000, 8, 1100));
        AllItems.put("Speed_Boots", new Armor("Speed_Boots", 550, 4, 600));
        /**
         * Potions: Name/cost/required level/attribute increase/attribute affected
         * Healing_Potion  250     1   100		Health
         * Strength_Potion 200     1   75		Strength
         * Magic_Potion    350     2   100		Mana
         * Luck_Elixir     500     4   65  	    Agility/Dexterity
         * Mermaid_Tears   850     5   100  	Health/Mana/Strength/Agility
         * Ambrosia        1000    8   150		All Health/Mana/Strength/Dexterity/Defense/Agility
         */
        AllItems.put("Healing_Potion", new Potion("Healing_Potion", 250, 1, 100, new String[] {"Health"}));
        AllItems.put("Strength_Potion", new Potion("Strength_Potion", 200, 1, 75, new String[] {"Strength"}));
        AllItems.put("Magic_Potion", new Potion("Magic_Potion", 350, 2, 100, new String[] {"Mana"}));
        AllItems.put("Luck_Elixir", new Potion("Luck_Elixir", 500, 4, 65, new String[] {"Agility", "Dexterity"}));
        AllItems.put("Mermaid_Tears", new Potion("Mermaid_Tears", 850, 5, 100, new String[] {"Health", "Mana", "Strength", "Agility"}));
        AllItems.put("Ambrosia", new Potion("Ambrosia", 1000, 8, 150, new String[] {"Health", "Mana", "Strength", "Dexerity", "Defense", "Agility"}));
        /**
         * SPELLS
         * // Ice, Fire, Lightning; enemy attributes to be decreased upon casting
         * // ice = reduces damage of enemy, 
         * fire = reduced defense of enemy, 
         * lightning = reducded agility of enemy
         * // damage, enemy: reduce strength, reduce defense, reduce agility
         */
         /** 
         * FIRE SPELLS: Name/cost/required level/damage/mana cost
         * Flame_Tornado   700     4   850     300
         * Breath_of_Fire  350     1   450     100
         * Heat_Wave       450     2   600     150
         * Lava_Commet     800     7   1000    550
         */
        AllItems.put("Flame_Tornado", new FireSpell("Flame_Tornado", 700, 4, 850, 300));
        AllItems.put("Breath_of_Fire", new FireSpell("Breath_of_Fire", 350, 1, 450, 100));
        AllItems.put("Heat_Wave", new FireSpell("Heat_Wave", 450, 2, 600, 150));
        AllItems.put("Lava_Commet", new FireSpell("Lava_Commet", 800, 7, 1000, 550));
        /** 
         * ICE SPELLS: Name/cost/required level/damage/mana cost
         * Snow_Cannon     500     2   650     250
         * Ice_Blade       250     1   450     100
         * Frost_Blizzard  750     5   850     350
         * Arctic_Storm    700     6   800     300
         */
        AllItems.put("Snow_Cannon", new IceSpell("Snow_Cannon", 500, 2, 650, 250));
        AllItems.put("Ice_Blade", new IceSpell("Ice_Blade", 250, 1, 450, 100));
        AllItems.put("Frost_Blizzard", new IceSpell("Frost_Blizzard", 750, 5, 850, 350));
        AllItems.put("Arctic_Storm", new IceSpell("Arctic_Storm", 700, 6, 800, 300));
        /** 
         * LIGHTNING SPELLS: Name/cost/required level/damage/mana cost
         * Lightning_Dagger      400        1       500     150
         * Thunder_Blast         750        4       950     400
         * Electric_Arrows       550        5       650     200
         * Spark_Needles         500        2       600     200
         */
        AllItems.put("LightningDagger", new LightningSpell("LightningDagger", 400, 1, 500, 150));
        AllItems.put("Thunder_Blast", new LightningSpell("Thunder_Blast", 750, 4, 950, 400));
        AllItems.put("Electric_Arrows", new LightningSpell("Electric_Arrows", 550, 5, 650, 200));
        AllItems.put("Spark_Needles", new LightningSpell("Spark_Needles", 500, 2, 600, 200));


        return AllItems;
    }


    public static Hero[] chooseYourFighters () {
        Scanner setup = new Scanner(System.in);
        boolean isnum = false;
        int n = 0;
        System.out.println("How many Heroes would you like on your team? Choose 1, 2, or 3");
        do {
            do {
                if(setup.hasNextInt()) {
                    n = setup.nextInt();
                    isnum = true;
                }
                else {
                    System.out.println("Invalid Input. Please Enter 1, 2, or 3.");
                    setup.next();
                }
                // setup.next();
            }
            while (!isnum);
            if (n < 1 || n > 3) System.out.println("Invalid Input. Please Enter 1, 2, or 3.");
        }
        while (n < 1 || n > 3);
        int num = 0;
        Hero[] team = new Hero[n];
        HashMap<String, Hero> allH = SetUp.AllHeroes();
        SetUp.printHeroes(SetUp.AllHeroes());
        do {
            System.out.println("Please enter the name of the Hero you would like on your team: ");
            String name = setup.nextLine();
            // setup.next();
            if (allH.containsKey(name)) {
                team[num] = allH.get(name);
                num++;
                System.out.println("What a mighty Hero! " + name + " has been added to your team!");
            }
            else System.out.println("That doesn't seem to match any of our Heroes. Please try again.");
        }
        while (num < n);
        System.out.println("\nYour team of Heroes is complete! Good luck on your Quest!\n");
        // setup.close();
        return team;
    }
    public static Hero[] chooseYourFighters(int n) {
        Scanner setup = new Scanner(System.in);
        int num = 0;
        Hero[] team = new Hero[n];
        HashMap<String, Hero> allH = SetUp.AllHeroes();
        SetUp.printHeroes(SetUp.AllHeroes());
        do {
            System.out.println("Please enter the name of the Hero you would like on your team: ");
            String name = setup.nextLine();
            // setup.next();
            if (allH.containsKey(name)) {
                team[num] = allH.get(name);
                num++;
                System.out.println("What a mighty Hero! " + name + " has been added to your team!");
            }
            else System.out.println("That doesn't seem to match any of our Heroes. Please try again.");
        }
        while (num < n);
        System.out.println("\nYour team of Heroes is complete! Good luck on your Quest!\n");
        // setup.close();
        return team;
    }

    public static int boardsize() {
        int n = 0;
        Scanner lolScanner = new Scanner(System.in);
        boolean isint = false;
        System.out.println("What size NxN map would you want to play with? Please enter a number 3 or greater!");
        do {
            do {
                if(lolScanner.hasNextInt()) {
                    n = lolScanner.nextInt();
                    isint = true;
                }
                else {
                    // System.out.println("That is not a valid integer. Please input an integer greater than 2.");
                    lolScanner.next();
                }
            }
            while (!isint);
            if (n < 3) {
                System.out.println("That is not a valid integer. Please input an integer greater than 2.");
            }
        }
        while (n < 3);
        // System.out.println(n);
        return n;
    }

    public static String TeleportPossibilities(int start, int end) {
        String bnrep = "";
        for (int i = start; i <= end; i++){
            bnrep = bnrep + "[";
            bnrep = bnrep + Integer.toString(i);
            bnrep = bnrep + "] ";
        }
        return bnrep;
    }
    
    public static void printOptions(String[] op) {
        String option = "";
        option = option + "This hero has the following action options:  ";
        for (String s: op) {
            if (!s.equals("NA")) {
                // option = option + "[" + s + "] ";
                option = option + s + "  ";
            }
        }
        System.out.println(option);

    }

    public static void main(String[] args) {
        Hero[] Team = SetUp.chooseYourFighters();
        // System.out.println(Arrays.toString(Team));
        // SetUp.boardsize();
    }

}