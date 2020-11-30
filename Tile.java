import java.util.Arrays;

public class Tile {
    //Represents a cell on the game board, can be different types and have different effects based on the type
    
    private String type; // { Nexus [N] = Market, PLAIN = Common Tile [Safe Zone OR Monster], Bush (inc dexterity 10%), Koulou (inc strength 10%), Cave (inc agility 10%) }
    private int[] coordinate; // [lane#, row#, col#]
    private Hero h;
    private Monster m;
    private Lane myLane;

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
        coordinate = coords;
        h = null;
        m = null;
        myLane = null;
    }

    public Tile() {
        type = "XInaccessible";
        coordinate = new int[] {-1,-1,-1};
        h = null;
        m = null;
        myLane = null;
    }

    public void setLane(Lane lane) {
        myLane = lane;
    }
    public Lane getLane() {
        return myLane;
    }

    // RETURNS the Hero on this Tile, if one exists
    public Hero h_on_me() {
        return h;
    }
    // RETURNS the Monster on this Tile, if one exists
    public Monster m_on_me() {
        return m;
    }

    // Sets the Hero/Monster specified in parameter to this Tile location's Hero/Monster
    public void setLocTile(InGameCharacter igc) {
        if (igc.getHM().equals("Hero")) {
            h = (Hero)igc;
            int dist = coordinate[1]; // 2
            myLane.updateFurthestExplored(dist);
        }
        else if (igc.getHM().equals("Monster")) {
            m = (Monster)igc;
        }
    }

    // Called to set this Tile location's Hero/Monster to null when Tile's H/M leaves
    public void leaveTile(InGameCharacter igc) {
        if (igc.getHM().equals("Hero"))
            h = null;
        else if (igc.getHM().equals("Monster")) {
            m = null;
        }
    }

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
    public void setBaseNexus() {
        this.type = "NexusBase";
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
}
