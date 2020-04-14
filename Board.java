import java.util.Arrays;
// import java.util.Scanner;

public class Board {
    private Lane[] gameBoard;
    private int n;
    private int h;
    private int w;
    // Scalable number of Lanes and height of Lanes, but not width of Lane

    public Board(int n, int h, int w) {
        this.n = n;
        this.h = h;
        this.w = w;
        gameBoard = new Lane[n];
        for (int l = 0; l < n; l++) {
            gameBoard[l] = new Lane(l+1, h, w);
        }
        // for (int l = 0; l < n; l++) {
        //     gameBoard[1].setLane();
        // }
        for (Lane l: gameBoard) {
            l.setLane();
        }
    }

    public int getn() {
        return n;
    }
    public int geth() {
        return h;
    }
    public int getw() {
        return w;
    }
    public Lane[] getMap() {
        return this.gameBoard;
    }

    public Tile getHSpawnTile(int n) {
        int lane = (n)%this.n;
        if (lane == 0) lane = this.n;
        int widthpos = ((int)Math.floor((double)(n-1)/(this.n)) %(this.w)) + 1;
        Tile SpawnTile = getTileAt(new int[] {lane, this.h-1, widthpos});
        return SpawnTile;
    }
    public Tile getMSpawnTile(int n) {
        int lane = (n)%(this.n);
        if (n > 3) { 
            lane = (int)Math.ceil(Math.random()*this.n);
        }
        if (lane == 0) lane = this.n;
        int widthpos = ((int)Math.floor((double)(n-1)/(this.n)) %(this.w)) + 1;
        Tile SpawnTile = getTileAt(new int[] {lane, 0, widthpos});
        return SpawnTile;
    }

    public Tile getTileAt(int[] coords) {
        return (gameBoard[coords[0]-1]).getTileAt(coords);
    }
    public boolean valid(int[] coords) {
        // for (int c: coords) {
        //     if (c < 0) return false;
        //     // System.out.println("Negative coordinates do not exist on the map!");
        // }
        if (coords[0] < 1 || coords[1] < 0 || coords[2] < 1) return false;
        if (coords[0] <= n && coords[1] < h && coords[2] <= w) return true;
        else {
            System.out.println("This location does not exist on the map!");
            return false;
        }
    }

    public int getFurthestDistanceinLane(int l) {
        Lane lane = gameBoard[l-1];
        return lane.getFurthestExplored();
    }

    public String toString() { // TODO print Lane# and width# // NOT SCALED
        String map = "";
        String[] lane0 = gameBoard[0].toString().lines().toArray(String[]::new);
        String[] lane1 = gameBoard[1].toString().lines().toArray(String[]::new);
        String[] lane2 = gameBoard[2].toString().lines().toArray(String[]::new);
        Tile inaccessible = new Tile();
        String[] in = inaccessible.toString().lines().toArray(String[]::new);
        String[] x = new String[] {in[0], in[1], in[2], ""};
        for (int i = 0; i < h*4; i++) {
            if (i%4 == 3) continue;
            if (i%4 == 1) {
                map = map + Integer.toString((int)(i/4)) + "  ";
            }
            else map = map + "   ";
            map = map + lane0[i] + x[i%4] + "   " + lane1[i] + x[i%4] + "   " + lane2[i] + "\n";
            if (i%4 == 2) {
                map = map + "\n";
            }
        }
        return map;
    }

    /** public void resetBoard() {
        this.gameBoard = new Board(8);
    } */

    public static void main(String[] args) {
        Board hello = new Board(3, 8, 2);

        System.out.println(hello);
        int[] target = new int[] {2, 3, 2};
        System.out.println(hello.getTileAt(target));
        System.out.println(Arrays.toString(hello.getHSpawnTile(10).getCoords()));
        System.out.println(Arrays.toString(hello.getHSpawnTile(0).getCoords()));
        System.out.println(Arrays.toString(hello.getHSpawnTile(14).getCoords()));
        System.out.println(Arrays.toString(hello.getHSpawnTile(6).getCoords()));

        for (int i = 0; i < hello.getn(); i++) {
            System.out.println(hello.getFurthestDistanceinLane(i+1));
        }

        // for (int i = 0; i < hello.getn(); i++) {
        //     Lane inq = hello.gameBoard[i];
        //     Tile[][] test = inq.testhelp();
        //     for (Tile[] row: test) {
        //         for (Tile t: row) {
        //             if (t.getLane() != null) {
        //                 System.out.println("S");
        //             }
        //             else System.out.println("Failure");
        //         }
        //     }
        // }

        // System.out.println(hello.getHSpawnTile(10));

        // String[] lane2 = hello.gameBoard[1].toString().lines().toArray(String[]::new);
        // System.out.println(Arrays.toString(lane2));

        // Lane[] map = hello.getMap();

        // for (int i = 0; i < 14; i++) {
        //     System.out.print(i);
        //     System.out.print(" ");
        //     System.out.print((int)Math.floor((double)i/3) %2);
        //     System.out.println();
        // }

        // for (int r = 0; r < hello.getn(); r++) {
        //     for (int c = 0; c < hello.getn(); c++) {
        //         System.out.println(map[r][c].getType());
        //     }
        // }

        // Scanner m = new Scanner(System.in);
        // boolean exit = false;
        // do {
        //     System.out.println("Where would you like to move your team of Heroes? W: North, A: West, D: East, S: South, and I -> Show Team Stats");
        //     String dir = m.nextLine();
        //     if (dir.equals("I") || dir.equals("i")) {
        //         // PRINT TEAM INFO
        //     }
        //     if (dir.equals("W") || dir.equals("A") || dir.equals("D") || dir.equals("S") || dir.equals("w") || dir.equals("a") || dir.equals("d") || dir.equals("s")) {
        //         boolean valid = hello.valid(dir);
        //         if (valid) {
        //             hello.move(dir);
        //             System.out.println(hello);
        //         }
        //         // hello.move(dir);
        //     }
        //     else if (dir.equals("e")) exit = true;
        //     else {
        //         System.out.println("Invalid Input!");
        //     }
        // }
        // while (!exit);
        // m.close();
    }
}