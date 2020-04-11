import java.util.Arrays;
import java.util.*;

public class Tile {
    private String type; // { Nexus [N] = Market, PLAIN = Common Tile [Safe Zone OR Monster], Bush (inc dexterity 10%), Koulou (inc strength 10%), Cave (inc agility 10%) } //TODO update M&H attacks
    // private boolean onloc;
    private int[] coordinate; // [lane#, row#, col#]
    // private ArrayList<Hero> h;
    // private ArrayList<Monster> m;
    private Hero h;
    private Monster m;

    //TODO check nexus win, scan for enemies to fight, teleport, move, & set locations, MARKET AT NEXUS

    // public Tile(int[] coords, ArrayList<Hero> hl, ArrayList<Monster> ml) {
    //     double prob = Math.random();
    //     if (prob < 0.2) {
    //         type = "Bush"; // +10% dexterity
    //     }
    //     else if (prob < 0.4) {
    //         type = "Koulou"; // + 10% strength
    //     }
    //     else if (prob < 0.6) {
    //         type = "Cave"; // +10% agility
    //     }
    //     else {
    //         type = "Plain";
    //     }
    //     // onloc = false;
    //     coordinate = coords;
    //     h = hl;
    //     m = ml;
    //     // whoonme = null;
    // }
    public Tile(int[] coords) {
        double prob = Math.random();
        if (prob < 0.2) {
            type = "Bush"; // +10% dexterity
        }
        else if (prob < 0.4) {
            type = "Koulou"; // + 10% strength
        }
        else if (prob < 0.6) {
            type = "Cave"; // +10% agility
        }
        else {
            type = "Plain";
        }
        // onloc = false;
        coordinate = coords;
        // h = hl;
        // m = ml;
        h = null;
        m = null;
    }

    public Tile() {
        type = "XInaccessible";
        coordinate = new int[] {-1,-1,-1};
        h = null;
        m = null;
    }

    // public boolean onLocation() {
    //     return onloc;
    // }
    public Monster m_on_me() {
        return m;
    }
    public void setLocTile(InGameCharacter igc) { // TODO validate no Hero already on. probably in Quest/take input class
        if (igc.getHM().equals("Hero"))
            h = (Hero)igc;
        else if (igc.getHM().equals("Monster")) {
            m = (Monster)igc;
        }
    }
    public void leaveTile(InGameCharacter igc) {
        if (igc.getHM().equals("Hero"))
            h = null;
        else if (igc.getHM().equals("Monster")) {
            m = null;
        }
    }
    public Hero h_on_me() {
        return h;
    }
    // public int H_on_me() {
    //     int noone = 0;
    //     for (InGameCharacter igc: h) {
    //         if (igc.getLoc().equals(this)) return h.indexOf(igc)+1;
    //     }
    //     return noone;
    // }
    // public int M_on_me() {
    //     int noone = 0;
    //     for (InGameCharacter igc: m) {
    //         if (igc.getLoc().equals(this)) return m.indexOf(igc)+1;
    //     }
    //     return noone;
    // }

    public String getType() {
        return type;
    }
    public int[] getCoords() {
        return coordinate;
    }
    public void setCommon() {
        this.type = "Plain";
    }
    public void setNexus() {
        this.type = "Nexus";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Tile other = (Tile) o;
        // return (!(this.onloc) && !(other.onloc)) && (this.type.equals(other.type))
        return Arrays.equals(this.coordinate, other.getCoords());
    }

    // @Override
    // public String toString() { 
    //     String tiletype = this.type.substring(0,1);
    //     String boxtop = tiletype + " - " + tiletype + " - " + tiletype + "\n";
    //     String tilerep = boxtop;
    //     tilerep = tilerep + "| ";
    //     if (H_on_me() > 0) {
    //         tilerep = tilerep + "H" + Integer.toString(H_on_me());
    //     }
    //     else tilerep = tilerep + "  ";
    //     if (M_on_me() > 0) {
    //         if (M_on_me() <= 9) {
    //             tilerep = tilerep + " ";
    //         }
    //         tilerep = tilerep + "M" + Integer.toString(M_on_me());
    //     }
    //     tilerep = tilerep + " |\n";
    //     tilerep = tilerep + boxtop;
    //     return tilerep;
    // }

    @Override
    public String toString() { 
        String tiletype = this.type.substring(0,1);
        String boxtop = tiletype + " - " + tiletype + " - " + tiletype + "\n";
        String tilerep = boxtop;
        tilerep = tilerep + "| ";
        if (this.getType().equals("XInaccessible")) {
            tilerep = tilerep + "X X X";
        }
        else {
            if (h_on_me() != null) {
                tilerep = tilerep + h.getPiece();
            }
            else tilerep = tilerep + "  ";
            if (m_on_me() != null) {
                if (m.boardpiece.length() == 2) {
                    tilerep = tilerep + " ";
                }
                tilerep = tilerep + m.getPiece();
            }
            else tilerep = tilerep + "   ";
        }
        tilerep = tilerep + " |\n";
        tilerep = tilerep + boxtop;
        return tilerep;
    }

    public static void main(String[] args) {
        Tile hello = new Tile(new int[] {1,2,3});
        // hello.setTile("yo");
        System.out.println(hello);
        Tile IX = new Tile();
        System.out.println(IX);
        System.out.println("-------");
        String tiletype = "P";
        String tilerep = tiletype + " - " + tiletype + " - " + tiletype + "\n";
        tilerep = tilerep + "| " + "   M3 |";
        System.out.println(tilerep);
    }
}