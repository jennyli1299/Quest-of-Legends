import java.util.Scanner;
import java.util.*;

public class Quest extends Game {

    // private Board b;
    // private Team H; //Heroes X
    // private Team M; //Monsters O
    HashMap<String, Monster> AllMonsters;
    Hero[] team;
    Market M;
    Scanner qScan;
    int teamcount;
    int rounds; //TODO rounds (every 8, a new Monster spawns random lane)
    // TODO add BATTLE
    // TODO add factory of Monsters
    Tile loc; //TODO take out
    //TODO Arraylist of Heroes and Monsters
    ArrayList<Monster> spawnedM;
    ArrayList<Hero> teamH;
    // TODO SEPARATE ACTION FOR EACH HERO
    // TODO VALIDATE MOVEMENTS not necessarily in this class

    public Quest() {
        System.out.println("\nWelcome to QUEST! Prepare yourself for a journey filled with Heroes, Monsters, MAGIC, and fun!\n");
        System.out.println("First things first. Let's put together a team of courageous heroes for you.");
        Hero[] team_prelim = SetUp.chooseYourFighters();
        team = team_prelim;
        teamcount = team.length;
        rounds = 0;
        AllMonsters = SetUp.AllMonsters();
        HashMap<String, Item> ItemInfo = SetUp.AllItems();
        M = new Market(ItemInfo);
        qScan = new Scanner(System.in);
        System.out.println("Can't go on a QUEST without land to explore, and a map to guide you along the way!");
        // int bsize = SetUp.boardsize(); //TODO if you want to make board scalable, ask for #lanes and lane distance
        b = new Board(3, 8);
        gameOver = false;
    }

    public void play() {
        // System.out.println("\nWelcome to QUEST! Prepare yourself for a journey filled with Heroes, Monsters, MAGIC, and fun!\n");
        System.out.println("Let's get all the important stuff out of the way, Shall we? \nHere is your MAP: ");
        System.out.println(b);
        System.out.println("X MARKS THE SPOT! That's where you and your team are!");
        System.out.println("M stands for Market, and that's where you'll have to go if you want to stock up on gear.\nThat means all Armor, Weapons, and magic items such as Potions and Spells can be bought at Markets.");
        System.out.println("ψD stands for DANGER and the areas on the map labled D are nonaccessible parts of the map. Trust me, you don't want to go there... and you can't!");
        System.out.println("The empty tiles may seem safe, but be careful. There's a chance we may run into some Monsters in the uncharted territory.\nIf you do run into hostiles, you MUST fight.\nIf you win a battle, your heroes will be compensated for their troubles and bravery!\nIf you lose a battle, don't sweat it. All Heroes get revived at the end of the fight... but at a cost.");
        System.out.println("Understanding everything so far? I hope so! Are you ready to continue on? Enter Y when you are: ");
        String answer;
        do {
            answer = qScan.nextLine();
            if (!answer.equals("Y")) System.out.println("I beg your pardon? Enter Y when you are ready to go onto the next part: ");
        }
        while (!answer.equals("Y"));
        System.out.println("\nFANTASTIC!\nHere are your controls: \nTo move around the map, use your W,A,S,D Keys!\n\tW -> North, A -> West, S -> South, D -> East");
        System.out.println("Enter I at any time to view your team's stats. This command works in the midst of a battle too! When in battle, you may also enter MI to view enemy monster stats.");
        System.out.println("Enter Q at any time to exit the game. However, let's not get try get sneaky and use it to escape battles. It doesn't work that way. Around here, we finish the battles we start.");

        System.out.println("\nThink that should be all! Make sure you read and understand the command thoroughly. Have fun!\n");

        // System.out.println(b);
        // boolean exit = false;
        // String dir = "";
        do {
            System.out.println(b);

            System.out.println("Where would you like your team of Heroes to explore? W: North, A: West, D: East, S: South, and I -> Show Team Stats");
            String dir = qScan.nextLine();
            if (dir.equals("I") || dir.equals("i")) {
                I();
            } // TODO add movement for T and B
            else if (dir.equals("W") || dir.equals("A") || dir.equals("D") || dir.equals("S") || dir.equals("w") || dir.equals("a") || dir.equals("d") || dir.equals("s")) {
                boolean valid = b.valid(dir);
                if (valid) {
                    loc = b.move(dir);
                    System.out.println(b);
                    explore();
                }
            }
            else if (dir.equals("Q") || dir.equals("q")) setGAMEOVER();
            else {
                System.out.println("Invalid Input!");
            }
        }
        while (!gameOver);

        System.out.println("I hope you had fun on your QUEST! Until next time :)");
    }

    public void I() { // PRINT TEAM STATS OR INFO NOT INVENTORY
        System.out.println("\nYou asked for your team's stats, so HERE THEY ARE:");
        for (Hero h: team) {
            System.out.println(h);
        }
    }

    public void explore() {
        String tiletype = loc.getType();
        if (tiletype.equals("Common")) {
            if (Math.random() < 0.75) {
                System.out.println("OH NO! Hostile enemies approaching! Prepare for Battle!");
                battle();
            }
            else System.out.println("Lucky us! It's a safe-zone.");
        }
        else {
            System.out.println("Oh look, a market! Let's go shopping!");
            boolean valid = false;
            do {
                System.out.println("Would you like to browse? Y/N: ");
                String shop = qScan.nextLine();
                if (shop.equals("Y") || shop.equals("y")) {
                    valid = true;
                    boolean chose = false;
                    do {
                        System.out.println("Would you like to buy or sell items or leave the Market? Enter Buy, Sell, or Leave");
                        shop = qScan.nextLine();
                        if (shop.equals("buy") || shop.equals("Buy")) {
                            System.out.println("YAY! I love to shop!");
                            valid = true;
                            shop();        
                        }
                        else if (shop.equals("Sell") || shop.equals("sell")) {
                            System.out.println("Alright! All this gear was getting kind of heavy anyway.");
                            valid = true;
                            sell();
                        }
                        else if (shop.equals("Leave") || shop.equals("leave")) {
                            valid = true;
                            chose = true;
                        }
                        else if (shop.equals("Q") || shop.equals("q")) {
                            valid = true;
                            chose = true;
                            setGAMEOVER();
                        }
                        else {
                            System.out.println("Um, so is that a yes or a no? Enter Y for Yes, N for No.");
                        }
                    }
                    while (!chose);
                }
                else if (shop.equals("N") || shop.equals("n")) {
                    valid = true;
                    System.out.println("Alright, maybe next time then! ONWARD with our QUEST!");
                }
                else if (shop.equals("Q") || shop.equals("q")) {
                    valid = true;
                    setGAMEOVER();
                }
                else {
                    System.out.println("Um, so is that a yes or a no? Enter Y for Yes, N for No.");
                }
            }
            while (!valid);
        }
    }

    public void battle() {
        Battle fight = new Battle(team, SetUp.AllMonsters());
        fight.fight();
    }

    public void shop() {
        boolean done = false;
        M.printMarket();
        do {
            // M.printMarket();
            System.out.println("See anything that interests you? Input Y/N or M to see the listings again.");
            String shop = qScan.nextLine();
                if (shop.equals("Y") || shop.equals("y")) {
                    boolean complete = false;
                    do {
                        System.out.println("Which item? Please enter the full name of the Item as written in the catalog or enter DONE to leave the market at any time: ");
                        shop = qScan.nextLine();
                        if (shop.equals("Q") || shop.equals("q")) {
                            complete = true;
                            done = true;
                            setGAMEOVER();
                        }
                        else if (shop.equals("I") || shop.equals("i")) {
                            I();
                        }
                        else if (shop.equals("DONE")) {
                            complete = true;
                            done = true;
                        }
                        else if (M.getItems().containsKey(shop)) {
                            complete = true;
                            Item i = M.getItems().get(shop);
                            boolean purchased = false;
                            do {
                                System.out.println("Which Hero would you like to purchase this item for? Or enter DONE to leave the Market");
                                shop = qScan.nextLine();
                                if (shop.equals("Q") || shop.equals("q")) {
                                    purchased = true;
                                    complete = true;
                                    done = true;
                                    setGAMEOVER();
                                }
                                else if (shop.equals("I") || shop.equals("i")) {
                                    I();
                                }
                                else if (shop.equals("DONE")) {
                                    purchased = true;
                                    complete = true;
                                    done = true;
                                }
                                else if (existsHeroinTeam(shop) != -1) {
                                    purchased = team[existsHeroinTeam(shop)].buyItem(i);
                                }
                                else {
                                    System.out.println("Who? Try again, please.");
                                }
                            }
                            while (!purchased);
                        }
                        else {
                            System.out.println("The market doesn't seem to carry that... Did you mispell the Item name?");
                        }
                    }
                    while (!complete);
                }
                else if (shop.equals("N") || shop.equals("n")) {
                    done = true;
                    System.out.println("Alright, maybe next time then! ONWARD with our QUEST!");
                }
                else if (shop.equals("M") || shop.equals("m")) {
                    M.printMarket();
                }
                else if (shop.equals("Q") || shop.equals("q")) {
                    done = true;
                    setGAMEOVER();
                }
                else if (shop.equals("I") || shop.equals("i")) {
                    I();
                }
                else {
                    System.out.println("Um, so is that a yes or a no? Enter Y for Yes, N for No.");
                }
        }
        while (!done);
        System.out.println("Thanks for visiting the Market!");
    }

    public void sell() {
        System.out.println("Welcome to the Market! I'm sure you have many treasures from your QUEST. \nInterested in showing me what you got? I'll pay handsomely. If not, enter DONE at any time to leave the marketplace.");
        
        boolean done = false;
        do {
            System.out.println("Now let's see, whose inventory do you want to sell Items from? Otherwise, enter DONE if you have nothing more you'd like to sell.");
            String sell = qScan.nextLine();
            if (existsHeroinTeam(sell) != -1) {
                Hero h = team[existsHeroinTeam(sell)];
                HashMap<String, Item> hinv = h.getInv();
                h.printInv();
                boolean sold = false;
                do {
                    System.out.println("Which Item would you like to sell? Or, if you'd like to sell from another Hero's inventory, enter Switch. \nPlease enter the full name of the Item as displayed in the Hero's inventory.");
                    sell = qScan.nextLine();
                    if (hinv.containsKey(sell)) {
                        h.sellItem(hinv.get(sell));
                        sold = true;
                    }
                    else if (sell.equals("Switch")) {
                        sold = true;
                    }
                    else if (sell.equals("DONE")) {
                        sold = true;
                        done = true;
                    }
                    else if (sell.equals("Q") || sell.equals("q")) {
                        sold = true;
                        done = true;
                        setGAMEOVER();
                    }
                    else if (sell.equals("I") || sell.equals("i")) {
                        I();
                    }
                    else {
                        System.out.println("I'm not sure I've heard of that Item... Try again, please.");
                    }
                }
                while (!sold);
            }
            else if (sell.equals("DONE")) {
                done = true;
            }
            else if (sell.equals("Q") || sell.equals("q")) {
                done = true;
                setGAMEOVER();
            }
            else if (sell.equals("I") || sell.equals("i")) {
                I();
            }
            else {
                System.out.println("I'm not sure I've heard of that Hero... Try again, please.");
            }
        }
        while(!done);
        System.out.println("Thanks for visiting the Market!");

    }

    public void setGAMEOVER() {
        this.gameOver = true;
    }

    public void checkNexusBreach() {
        boolean v = false;
        for (Hero h: team) {
            Tile pos = h.getLoc();
            if (pos.getType().equals("Nexus")) {
                System.out.println(h.getName() + " has reached the enemy Nexus! HEROES' VICTORY!");
                v = true;
                setGAMEOVER();
            }
        }
        if (!v) {
            for (Monster m: spawnedM) {
                Tile pos = m.getLoc();
                if (pos.getType().equals("Nexus")) {
                    System.out.println(m.getName() + " has reached the enemy Nexus! DEFEAT!");
                    setGAMEOVER();
                }
            }
        }
    }

    public int existsHeroinTeam(String name) {
        int ret = -1;
        for (int i = 0; i < teamcount; i++) {
            if (team[i].getName().equals(name)) ret = i;
        }
        return ret;
    }

    public boolean validateMovementInput(String move) {
        // TODO validate Movement per Hero
        return true;
    }

    public boolean validateTeleport(int[] Tcoord) {
        // TODO validateTeleport
        return true;
    }

    public static void main(String[] args) {
        Quest q = new Quest();
        q.play();
        System.out.println("Test Complete!");

    }

}