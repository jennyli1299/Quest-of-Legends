import java.util.Scanner;
import java.util.*;

public class Quest extends Game {

    // (Inherited)
    // protected Board b;
    // protected boolean gameOver; 

    HashMap<String, Monster> AllMonsters;
    MonsterFactory mf; // TODO: INCORPORATE FUNCTIONALITY INTO QUEST (add returned spawnedMonster to ArrayList spawnedM)
    Hero[] team;
    Market M;
    Scanner qScan;
    int teamcount;
    int rounds; //TODO rounds (every 8, a new Monster spawns random lane)
    // TODO add BATTLE !! THIS IS A MAJOR TODO
    Tile loc; //TODO take out
    // TODO Arraylist of Heroes and Monsters
    ArrayList<Monster> spawnedM;
    ArrayList<Hero> teamH;
    // TODO SEPARATE ACTION FOR EACH HERO
    // TODO VALIDATE MOVEMENTS not necessarily in this class

    public Quest() {
        System.out.println("\nWelcome to QUEST! Prepare yourself for a journey filled with Heroes, Monsters, MAGIC, and fun!\n");
        System.out.println("First things first. Let's put together a team of courageous heroes for you.");
        Hero[] team_prelim = SetUp.chooseYourFighters(); // TODO: create another method specifically for team of 3
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

    public void play() { // TODO PLAY OF THE GAME
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
                // make capital
                boolean valid = b.valid(dir); // TODO: validate a real and accessible position on board
                // TODO: validate allowed movement in Game (RULES)
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

    public void explore() { //TODO explore()
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

    public void battle() {  // TODO bruh. BATTLE.
        Battle fight = new Battle(team, SetUp.AllMonsters()); // switch this out for Quest's HashMap of AllMonsterss
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
                if (pos.getType().equals("NexusBase")) {
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

    // RETURNS the coordinates given an InGameCharacter and the direction it wants to move in (WASD)
    public int[] convertDirtoCoords(InGameCharacter igc, String dir) { 
        //Takes in direction input and converts it to coordinates
        int[] attemptcoords = igc.getLoc().getCoords().clone();
        switch (dir) {
            case "W":
                attemptcoords[1]--;
                break;
            case "A":
                attemptcoords[2]--;
                break;
            case "S":
                attemptcoords[1]++;
                break;
            case "D":
                attemptcoords[2]++;
                break;
        }
        return attemptcoords;
    }

    // RETURNS true if there is not a Hero on the Nexus base this Hero is trying to back to
    public boolean validateBack(Hero h) {
        int[] basecoords = h.getBase().getCoords();
        return checkTileNOclashingtypes(h, basecoords);
    }

    // STEPPING WASD: RETURNS a valid Tile t that the InGameCharacter can call moveTo(Tile t) to OTHERWISE RETURNS null
    public Tile validateMovementInput(InGameCharacter igc, String dir) { //TODO CHECK/TEST
        // ISSUES TO ADDRESS: inaccessible tile, valid "teleport"(think of each move as a teleport to that space, even if it's the next Tile)
        int[] trycoords = convertDirtoCoords(igc, dir);
        boolean validTile = b.valid(trycoords);
        if (validTile) {
            boolean validTeleport = validateTeleport(igc, trycoords);
            if (validTeleport) return b.getTileAt(trycoords);
            else return null;
        }
        else return null;
    }

    // INTENDED TELEPORT: RETURNS a valid Tile t that the InGameCharacter can TELEPORT to by calling moveTo(Tile t) to OTHERWISE RETURNS null
    public Tile validateTeleportInput(InGameCharacter igc, int[] Tcoords) {
        // ISSUES TO ADDRESS: inaccessible tile, valid ACTUAL TELEPORT, cannot teleport to same lane
        int[] curr = igc.getLoc().getCoords();
        boolean NOTsameLane = !(curr[0] == Tcoords[0]);
        if (!NOTsameLane) {
            System.out.println("You cannot teleport in the same lane you are currently in. The road to victory is not that easy. Walk.");
            return null;
        }
        boolean validTile = b.valid(Tcoords);
        if (validTile) {
            boolean validTeleport = validateTeleport(igc, Tcoords);
            if (validTeleport) return b.getTileAt(Tcoords);
            else return null;
        }
        else return null;
    }

    // check whether an InGameCharacter can move to the Tile specifies by Tcoords
    // can be called by validateMovementInput to validate stepping and not Teleporting
    public boolean validateTeleport(InGameCharacter igc, int[] Tcoords) { 
        // Assumes Tcoords is a valid position on the map/board
        boolean noclash = checkTileNOclashingtypes(igc, Tcoords);
        boolean nobackdoor = checkNObackdoor(igc, Tcoords);
        return noclash && nobackdoor;
    }

    // Called by validateTeleport and RETURNS true if a Tile does not already contain an IGC of the same type (Hero/Monster)
    public boolean checkTileNOclashingtypes(InGameCharacter igc, int[] Tcoords) {
        // Assumes Tcoords is a valid position on the map/board
        // ISSUES TO ADDRESS: same HMtype on Tile
        String HM = igc.getHM();
        Tile targetTeleport = b.getTileAt(Tcoords);
        if (HM.equals("Hero")) {
            if (targetTeleport.h_on_me() != null) {
                System.out.println("There is already a Hero on that Tile.");
                return false;
            }
            else return true;
        }
        else if (HM.equals("Monster")) {
            if (targetTeleport.m_on_me() != null) return false;
            else return true;
        }
        return false;
    }

    // Called by validateTeleport and RETURNS true if an IGC is not trying to teleport behind an enemy 
        // or trying to reach an area of the map their type has not yet been explored
    public boolean checkNObackdoor(InGameCharacter igc, int[] Tcoords) {
        // Assumes Tcoords is a valid position on the map/board
        // ISSUES TO ADDRESS: behind an enemy, ****FURTHEST HERO DISTANCE EXPLORED****
        String HM = igc.getHM();
        boolean NObackdoor = true;
        if (HM.equals("Hero")) {
            // Hero h = (Hero)igc;
            for (Monster m: spawnedM) {
                int[] mc = m.getLoc().getCoords();
                if (Tcoords[0] == mc[0]) NObackdoor &= (Tcoords[1] >= mc[1]);
            }
        }
        // Nothing for Monsters since we established Monsters would not move with the ability to attack
        if (!NObackdoor) {
            System.out.println("You cannot teleport behind a Monster! That's sneaky. An honorable Hero would never do that.");
            return NObackdoor;
        }
        if (Tcoords[1] < b.getFurthestDistanceinLane(Tcoords[0])) {
            System.out.println("That area has not been explored by Heroes yet! To help you out, the furthest distance explored in Lane " + Tcoords[0] + " is row " + b.getFurthestDistanceinLane(Tcoords[0]) + ".");
            return false;
        }
        return NObackdoor;
    }

    public static void main(String[] args) {
        Quest q = new Quest();
        q.play();
        System.out.println("Test Complete!");

    }

}