import java.util.ArrayList;
import java.util.stream.Stream;

public class Lane {
    private Tile[][] lane;
    private int lanenum;
    private int h;
    private int w;
    private int furthestexploredH;

    public Lane(int n, int h, int w) {
        lanenum = n;
        this.h = h;
        this.w = w;
        lane = new Tile[h][w];
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                int[] coords = new int[] {lanenum, r, c+1};
                Tile t = new Tile(coords);
                lane[r][c] = t;
            }
        }
        for (int c = 0; c < w; c++) {
            lane[0][c].setNexus();
            lane[h-1][c].setBaseNexus();
        }
        furthestexploredH = h-1;
    }

    public Tile getTileAt(int[] coords) {
        return lane[coords[1]][coords[2]-1];
    }

    public void setLane() {
        for (Tile[] row: lane) {
            for (Tile t: row) {
                t.setLane(this);
            }
        }
    }

    public int getFurthestExplored() {
        return furthestexploredH;
    }
    public void updateFurthestExplored(Tile t) {
        int distexpl = t.getCoords()[1];
        if (furthestexploredH > distexpl) furthestexploredH = distexpl;
    }

    @Override
    public String toString() {
        String lanerep = "";
        for (int r = 0; r < h; r++) {
            // Stream<String> stream = lane[r][0].toString().lines();
            String[] array0 = lane[r][0].toString().lines().toArray(String[]::new);
            String[] array1 = lane[r][1].toString().lines().toArray(String[]::new);
            for (int i = 0; i < 3; i++) {
                lanerep = lanerep + array0[i] + "   " + array1[i] + "   \n";
            }
            lanerep = lanerep + "\n";
        }
        return lanerep;
    }

    public static void main(String[] args) {
        Lane L1 = new Lane(1, 8, 2);
        System.out.println(L1);
    }
}