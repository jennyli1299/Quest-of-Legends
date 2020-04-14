import java.util.Scanner;
import java.util.*;

public class Quest extends Game {

    // (Inherited)
    // protected Board b;
    // protected boolean gameOver; 

    HashMap<String, Monster> AllMonsters;
    MonsterFactory mf;
    Hero[] team;
    Market M;
    Scanner qScan;
    int teamcount;
    int round; 
    // TODO add BATTLE !! THIS IS A MAJOR TODO
    Tile loc; //TODO take out
    ArrayList<Monster> spawnedM;
    // ArrayList<Hero> teamH;
    // TODO SEPARATE ACTION FOR EACH HERO

    public Quest() {
        // int bsize = SetUp.boardsize(); // If you want to make board scalable, ask for #lanes and lane distance and land width TODO
        b = new Board(3, 8, 2);
        System.out.println("\nWelcome to QUEST! Prepare yourself for a journey filled with Heroes, Monsters, MAGIC, and fun!\n");
        System.out.println("First things first. Let's put together a team of courageous heroes for you.");
        Hero[] team_prelim = SetUp.chooseYourFighters(3);
        team = team_prelim;
        teamcount = team.length;
        for (int i = 0; i < teamcount; i++) {
            Tile base = b.getHSpawnTile(i+1);
            team[i].spawn(base, i+1);
        }
        mf = new MonsterFactory();
        AllMonsters = SetUp.AllMonsters();
        spawnedM = new ArrayList<Monster>();
        for (int i = 1; i <= teamcount; i++) {
            Monster m = mf.spawnMonster(b, spawnedM, i);
            spawnedM.add(m);
        }
        round = 0;
        HashMap<String, Item> ItemInfo = SetUp.AllItems();
        M = new Market(ItemInfo);
        qScan = new Scanner(System.in);
        // System.out.println("Can't go on a QUEST without land to explore, and a map to guide you along the way!");
        gameOver = false;
    }

    public void play() { // TODO PLAY OF THE GAME
        // System.out.println("\nWelcome to QUEST! Prepare yourself for a journey filled with Heroes, Monsters, MAGIC, and fun!\n");
        System.out.println("Let's get all the important stuff out of the way, Shall we? \nHere is your MAP: ");
        System.out.println(b);
        System.out.println("X MARKS THE SPOT! That's where you and your team are!"); // TODO: Change
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
        // ROUND SYSTEM STARTS NOW
        do {
            System.out.println(b);
            round++;
            if (round%8 == 0) {
                spawnedM.add(mf.spawnMonster(b, spawnedM, spawnedM.size()));
            }
            if (round != 1) {
                for (Hero h : team) {
                    h.regen();
                }
                System.out.println("Round " + Integer.toString(round) + "! Your team of Heroes has had time to REGEN some health and mana.");
            }
            for (Hero h : team) {
                InGameCharacter.scanNearbyEnemies(h, b);

                System.out.println(b);
                System.out.println("It is " + h.getHM() + ": " + h.getName() + "'s turn.");

                String[] options = new String[] {"[Move]: Move 1 space", "[T]: Teleport", "[B]: back to Base", "NA", "NA", "[P]: Use a Potion", "[G]: Gear up Armor/Weapons","[I]: display Team Info", "[MI]: display Enemy Info", "[Nothing]", "[Q]: quit game"};
                boolean shopnexus = false;
                boolean canfight = false;
                if (h.getLoc().getCoords()[1] == (b.geth()-1)) {
                    System.out.println(h.getHM() + ": " + h.getName() + " is on a Nexus Tile! You may shop if you wish.");
                    options[3] = "[Shop]: buy/sell items or learn Spells at the Market";
                    shopnexus = true;
                }
                if (h.canAttack()) {
                    System.out.println("There are nearby hostiles! " + h.getHM() + ": " + h.getName() + " may fight if you wish.");
                    options[4] = "[Fight]: Attack or Cast Spell on nearby enemy";
                    canfight = true;
                }

                String wwyltodo = "What would you like " + h.getPiece() + ": " + h.getName() + " to do?";

                String action = "";
                boolean acted = false;

                do {
                    SetUp.printOptions(options);
                    System.out.println(wwyltodo);
                    action = qScan.nextLine();
                    action = action.toLowerCase();
                    if (action.equals("move")) {
                        WASD(h);
                        acted = true;
                    }
                    else if (action.equals("t") || action.equals("teleport")) {
                        getValidTeleportCoordinates(h);
                        acted = true;
                    }
                    else if (action.equals("b")) {
                        boolean canB = validateBack(h);
                        if (canB) {
                            h.BacktoBase();
                            acted = true;
                        }
                    }
                    else if (action.equals("shop")) {
                        if (shopnexus) {
                            h.exploreMarket(M, qScan);
                            acted = true;
                        }
                    }
                    else if (action.equals("fight")) {
                        if (canfight) {
                            h.fight(qScan);
                            acted = true;
                        }
                    }
                    else if (action.equals("p")) {
                        h.usePotion(qScan);
                        acted = true;
                    }
                    else if (action.equals("g") || action.equals("gear")) {
                        h.gearUP(qScan);
                        acted = true;
                    }
                    else if (action.equals("i")) {
                        I();
                    }
                    else if (action.equals("mi")) {
                        MI();
                    }
                    else if (action.equals("nothing")) {
                        acted = true;
                    }
                    else if (action.equals("q") || action.equals("quit")) {
                        setGAMEOVER();
                        acted = true;
                    }
                    else {
                        acted = false;
                        System.out.println("Invalid Input.");
                    }
                }
                while(!acted);

                checkNexusBreach();

                if (gameOver) break;
            }

            if (gameOver) break;

            for (Monster m : spawnedM) {
                InGameCharacter.scanNearbyEnemies(m, b);
                boolean acted = false;
                boolean attacksuccess = m.tryAttackaction();
                if (attacksuccess) {
                    acted = true;
                    checkKill(m);
                }
                else {
                    int TandE = 0;
                    do {
                        TandE++;
                        String movement = m.tryMove(TandE);
                        if (movement.equals("outofmoves")) {
                            acted = true;
                        }
                        else {
                            Tile Mgoto = validateMovementInput(m, movement);
                            if (Mgoto != null) {
                                m.moveTo(Mgoto);
                                acted = true;
                            }
                        }
                    }
                    while (!acted);
                }
                // checkNexusBreach();
            }
            checkNexusBreach();
        }
        while (!gameOver);

        System.out.println("I hope you had fun on your QUEST! Until next time :)");
    }

    // METHOD to get WASD movement input and move Hero h
    public void WASD(Hero h) {
        boolean goodInput = false;
        do {
            System.out.println("Where would you like " + h.getPiece() + ": " + h.getName() + " to explore? W: North, A: West, D: East, S: South, I -> Show Team Stats, MI -> Show Enemy Stats: ");
            String dir = qScan.nextLine();
            if (dir.equals("I") || dir.equals("i")) {
                I();
            }
            if (dir.equals("MI") || dir.equals("mi")) {
                MI();
            }
            else if (dir.equals("W") || dir.equals("A") || dir.equals("D") || dir.equals("S") || dir.equals("w") || dir.equals("a") || dir.equals("d") || dir.equals("s")) {
                dir = dir.toUpperCase();
                Tile validTile = validateMovementInput(h, dir);
                if (validTile != null) {
                    h.moveTo(validTile);
                    goodInput = true;
                    // System.out.println(b);
                    // explore();
                }
            }
            else if (dir.equals("Q") || dir.equals("q")) setGAMEOVER();
            else {
                System.out.println("Invalid Input!");
            }
        }
        while (!goodInput);
    }

    // METHOD to get Tile t with intent to Teleport Hero h to through input
    public void getValidTeleportCoordinates(Hero h) {
        Tile teleportTile = null;

        do {
            int[] inputcoords = new int[3];
            boolean isnum = false;
            int n = 0;
            System.out.println("Where would you like to Teleport?");

            int bn = b.getn();
            String bnrep = SetUp.TeleportPossibilities(1, bn);
            // System.out.println("The Lanes in the Map are numbered: " + bnrep + "\nEnter the Lane number you want to Teleport to now: ");
            do {
                System.out.println("From L->R, the Lanes in the Map are numbered: " + bnrep + "\nEnter the Lane number you want to Teleport to now: ");
                do {
                    if(qScan.hasNextInt()) {
                        n = qScan.nextInt();
                        isnum = true;
                    }
                    else {
                        System.out.println("Invalid Input. Please enter a number.");
                        qScan.next();
                    }
                    // setup.next();
                }
                while (!isnum);
                if (!(n >= 1 && n <= bn)) {
                    // System.out.println("Invalid Input. Remember, the Lanes in the Map are numbered: " + bnrep);
                    isnum = false;
                    System.out.println("That's not a Lane on the map.");
                }
            }
            while (!(n >= 1 && n <= bn));
            inputcoords[0] = n;

            isnum = false;
            bn = b.geth();
            int maxheight = b.getFurthestDistanceinLane(n);
            bnrep = SetUp.TeleportPossibilities(maxheight, bn-1);
            do {
                System.out.println("From top-> bottom, the accessible rows in this lane currently are numbered: " + bnrep + "\nEnter the row number you want to Teleport to now: ");
                do {
                    if(qScan.hasNextInt()) {
                        n = qScan.nextInt();
                        isnum = true;
                    }
                    else {
                        System.out.println("Invalid Input. Please enter a number.");
                        qScan.next();
                    }
                    // setup.next();
                }
                while (!isnum);
                if (!(n >= maxheight && n < bn)) {
                    // System.out.println("Invalid Input. Remember, the Lanes in the Map are numbered: " + bnrep);
                    isnum = false;
                    System.out.println("That's not a row on the map you can Teleport to in this Lane.");
                }
            }
            while (!(n >= maxheight && n < bn));
            inputcoords[1] = n;

            bn = b.getw();
            bnrep = SetUp.TeleportPossibilities(1, bn);
            do {
                System.out.println("In this Lane and row Area, you may try to Teleport to: " + bnrep + "L->R \nEnter the row number you want to Teleport to now: ");
                do {
                    if(qScan.hasNextInt()) {
                        n = qScan.nextInt();
                        isnum = true;
                    }
                    else {
                        System.out.println("Invalid Input. Please enter a number.");
                        qScan.next();
                    }
                    // setup.next();
                }
                while (!isnum);
                if (!(n >= 1 && n <= bn)) {
                    // System.out.println("Invalid Input. Remember, the Lanes in the Map are numbered: " + bnrep);
                    isnum = false;
                    System.out.println("That's not a valid space in this Lane and row");
                }
            }
            while (!(n >= 1 && n <= bn));
            inputcoords[2] = n;

            teleportTile = validateTeleportInput(h, inputcoords);
        }
        while (teleportTile == null);
        h.moveTo(teleportTile);
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
        dir = dir.toUpperCase();
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
        boolean canB = checkTileNOclashingtypes(h, basecoords);
        if (!canB) System.out.println("There is a Hero on " + h.getRep() + "'s Base. " + h.getName() + " cannot back.");
        return canB;
    }

    // STEPPING WASD: RETURNS a valid Tile t that the InGameCharacter can call moveTo(Tile t) to OTHERWISE RETURNS null
    public Tile validateMovementInput(InGameCharacter igc, String dir) { //TODO CHECK/TEST
        // ISSUES TO ADDRESS: inaccessible tile, valid "teleport"(think of each move as a teleport to that space, even if it's the next Tile)
        dir = dir.toUpperCase();
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
    

    /**
     * ADDED FROM BATTLE
     */
    public void I() {
        System.out.println("\nYou asked for your team's stats, so HERE THEY ARE:");
        for (Hero h: team) {
            System.out.println(h);
        }
        System.out.println();
    }
    public void MI() {
        System.out.println("\nYou asked for the enemy's stats, so HERE THEY ARE:");
        Iterator<Monster> iterm = spawnedM.iterator();
        while (iterm.hasNext()) {
            Monster m = iterm.next();
            if (m.getHealth() > 0) System.out.println(m);
        }
        System.out.println();
    }

    public void checkKill(InGameCharacter igc) {
        String igctype = igc.getHM();
        if (igctype.equals("Hero")) {
            Hero igcH = (Hero)igc;
            if (igcH.getAttacking().getHealth() <= 0) {
                igc.rewardIGC();
            }
        }
        else { //if (igctype.equals("Monster")) {
            Monster igcM = (Monster)igc;
            if (igcM.getAttacking().getHealth() <= 0) {
                igc.rewardIGC();
            }
        }
    }

    public void revive() {// MUST REWARD BEFORE REVIVE
        for (Hero h : team) {
            if (h.getHealth() <= 0) h.revive();
        }
    }


    public static void main(String[] args) {
        Quest q = new Quest();
        q.play();
        System.out.println("Test Complete!");

    }

}