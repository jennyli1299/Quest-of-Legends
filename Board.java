public class Board {
    //Board represents the map of the Quest of Legends and is scalable
    private Lane[] gameBoard;
    private int n;
    private int h;
    private int w;

    public Board(int n, int h, int w) {
        this.n = n;
        this.h = h;
        this.w = w;
        gameBoard = new Lane[n];
        for (int l = 0; l < n; l++) {
            gameBoard[l] = new Lane(l+1, h, w);
        }
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

    // RETURNS the Tile at which HERO #n should spawn at (B)
    public Tile getHSpawnTile(int n) {
        int lane = (n)%this.n;
        if (lane == 0) lane = this.n;
        int widthpos = ((int)Math.floor((double)(n-1)/(this.n)) %(this.w)) + 1;
        Tile SpawnTile = getTileAt(new int[] {lane, this.h-1, widthpos});
        return SpawnTile;
    }
    // RETURNS the Tile at which Monster #n should spawn at
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

    // RETURNS the Tile at specified argument coordinates
    public Tile getTileAt(int[] coords) {
        return (gameBoard[coords[0]-1]).getTileAt(coords);
    }

    // RETURNS true if specified coordinates in argument are a valid Tile on the board
    public boolean valid(int[] coords) {
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

    public String toString() { // print Lane# and width# // NOT SCALED
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

}
