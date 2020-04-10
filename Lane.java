import java.util.ArrayList;
import java.util.stream.Stream;

public class Lane {
    private Tile[][] lane;
    private int lanenum;
    private int h;

    public Lane(int n, int h) {
        lanenum = n;
        this.h = h;
        lane = new Tile[h][2];
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < 2; c++) {
                int[] coords = new int[] {lanenum, r, c};
                Tile t = new Tile(coords);
                lane[r][c] = t;
            }
        }
        for (int c = 0; c < 2; c++) {
            lane[0][c].setNexus();
            lane[h-1][c].setNexus();
        }
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
        Lane L1 = new Lane(1, 8);
        System.out.println(L1);
    }
}