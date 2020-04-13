import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MonsterFactory {
    HashMap<String, Monster> AllMonsters;

    public MonsterFactory() {
        AllMonsters = MonsterFactory.AllMonsters();
    }

    public Monster spawnMonster(Board b, ArrayList<Monster> m, int n) {
        // INPUT PARAMS: board, ArrayList of Monsters already spawned on the map/board, int representing the nth Monster to be spawned in Quest of Legends
        // fetches and adds the necessary properties and attributes to a random monster NOT already on the map
        // adds the created Monster to the ArrayList of Monsters in the game [COMMENTED OUT. Responsibility falls onto Quest/game class]
        // RETURNS the monster
        boolean newM = false;
        Monster tryMonster;
        do {
            int tryindex = (int)Math.floor(Math.random()*AllMonsters.size());
            Object[] allM = AllMonsters.values().toArray();
            tryMonster = (Monster)allM[tryindex];
            if (!m.contains(tryMonster)) newM = true;
        }
        while (!newM);
        Tile mspawnhere = b.getMSpawnTile(n);
        tryMonster.spawn(mspawnhere, n);
        m.add(tryMonster);
        return tryMonster;
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

    public static void main(String[] args) {
        Board b = new Board(3, 8, 2);
        MonsterFactory mf = new MonsterFactory();
        ArrayList<Monster> alm = new ArrayList<Monster>();
        Monster newM = mf.spawnMonster(b, alm, 1);
        System.out.println(b);
        System.out.println(newM);
        System.out.println(newM.getPiece());
        System.out.println(Arrays.toString(newM.getLoc().getCoords()));
        newM.moveTo(b.getTileAt(new int[] {3,7,1}));
        System.out.println(b);
        System.out.println(newM.getLoc().getType());
    }
}