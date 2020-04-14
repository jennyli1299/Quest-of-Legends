// import java.util.Random;
// import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class InGameCharacter {
    //Abstract Class representing any entity that will be on the game board in Quest of Legends.
    
    protected String name;
    protected String type;
    protected String HM;
    protected int lvl;
    protected int exp;
    protected int maxhp;
    protected int hp;
    protected int strength;
    protected int defense;
    protected int agility;
    protected Tile loc;
    protected String boardpiece; 
    protected ArrayList<InGameCharacter> nearbyEnemies; 


    public void spawn(Tile loc, int n) {
        moveTo(loc);
        setPiece(n);
    }

    public abstract void setPiece(int n); 
    public String getPiece() {
        return boardpiece;
    }

    // public void levelup() {
    //     if (this.exp >= lvl*10) {
    //     hp = maxhp;
    //     strength = (int)(strength*1.05);
    //     defense = (int)(defense*1.05);
    //     agility = (int)(agility*1.05);
    //     }
    // }
    public int getLVL() {
        return lvl;
    }
    public int getHealth() {
        return hp;
    }
    public void gainEXP() {
        //fill
        this.exp += 2;
    }
    public String getName() {
        return name;
    }
    public String getRep() {
        return this.getPiece() + ": " + this.getName();
    }
    public String getType() {
        return type;
    }
    public String getHM() {
        return HM;
    }
    public Tile getLoc() {
        return loc;
    }

    public void moveTo(Tile target) {
        if (this.loc != null) this.loc.leaveTile(this);
        this.loc = target;
        target.setLocTile(this);
        // target.testMLocTile(this);
    }

    public boolean canAttack(){
        //check if arraylist is empty, if it is it cannot fight
        if(this.nearbyEnemies.isEmpty()){
            return false;
        }
        else{
            return true;
        }
        
    }

    public void attack(InGameCharacter c) { 
        //PLAIN = Common Tile [Safe Zone OR Monster], Bush (inc dexterity (spell casting) 10%) <- implemented in Hero's cast spell, Koulou (inc strength 10%), Cave (inc agility 10%)
        if (c.getHM().equals("Monster")) {
            if (Math.random() <= c.agility/100) {
                System.out.println(c.name + " DODGED " + this.name + "'s attack!");
            }
            else {
                String Htile = this.getLoc().getType();
                int dmg = (int)((this.strength-c.defense)*0.05);
                if (Htile.equals("Koulou")) { // Koulou (inc strength 10%)
                    dmg = (int)(((this.strength*1.1)-c.defense)*0.05);
                }
                c.updhp(dmg);
                System.out.println(this.name + "'s attack was EFFECTIVE!" + " " + c.getName() + " has taken " + Integer.toString(dmg) + " damage.");
            }
        }
        else {
            double dodgechance = c.agility*0.0002; //0.02
            if (c.getLoc().getType().equals("Cave")) { // Cave (inc agility 10%)
                dodgechance *= 1.1;
            }
            if (Math.random() <= dodgechance) {
                System.out.println(c.name + " DODGED " + this.name + "'s attack!");
            }
            else {
                int dmg = (int)((this.strength-c.defense)*0.05);
                c.updhp(dmg);
                System.out.println(this.name + "'s attack was EFFECTIVE!" + " " + c.getName() + " has taken " + Integer.toString(dmg) + " damage.");
            }
        }
    }
    public void updhp (int h) {
        if (h > 0) {
            this.hp -= h;
        }
        if (this.hp <=0 ) {
        System.out.println(this.name + " has FAINTED!");
        }
    }

    public void rewardIGC() {
        String igctype = this.HM;
        if (igctype.equals("Hero")) {
            Hero igc = (Hero)this;
            if (igc.getHealth() > 0) {
                igc.reward(igc.getAttacking().getLVL()*100);
            }
        }
        this.reward();
        this.levelup();
    }
    public abstract void reward();
    public abstract void reward(int c);
    public abstract void levelup();

    public void setNearbyEnemies(ArrayList<InGameCharacter> nE) {
        this.nearbyEnemies = nE;
    }
    public ArrayList<InGameCharacter> getNearbyEnemies() {
        return this.nearbyEnemies;
    }
    public void printNearbyEnemies() {
        String igctype = this.getHM();
        if (igctype.equals("Hero")) {
            for (InGameCharacter nE : this.nearbyEnemies) {
                Monster m = (Monster)nE;
                System.out.println(m);
            }
        }
        else if (igctype.equals("Monster")) { //TEST
            for (InGameCharacter nE : this.nearbyEnemies) {
                Hero h = (Hero)nE;
                System.out.println(h);
            }
        }
    }

    public boolean equals(InGameCharacter c) {
        return (this.name.equals(c.name));
    }

    public static void scanNearbyEnemies(InGameCharacter igc, Board b) {
        String igcType = igc.getHM();
        int[] igcCoords = igc.getLoc().getCoords(); // NEED DEEP COPY BRO
        /** 1   2   3
         *  4  igc  6
         *  7   8   9
         */
        ArrayList<InGameCharacter> nE = new ArrayList<InGameCharacter>();
        int[] scanpos = igcCoords.clone();
        // igc
        InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);
        // 4
        scanpos[2]--;
        InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);
        // 6
        scanpos = igcCoords.clone();
        scanpos[2]++;
        InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);
        if (igcType.equals("Hero")) {
            // 1
            scanpos = igcCoords.clone();
            scanpos[1]--;
            scanpos[2]--;
            InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);            
            // 2
            scanpos = igcCoords.clone();
            scanpos[1]--;
            InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);
            // 3
            scanpos = igcCoords.clone();
            scanpos[1]--;
            scanpos[2]++;
            InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);  
        }
        if (igcType.equals("Monster")) {
            // 7
            scanpos = igcCoords.clone();
            scanpos[1]++;
            scanpos[2]--;
            InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);  
            // 8
            scanpos = igcCoords.clone();
            scanpos[1]++;
            InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);  
            // 9
            scanpos = igcCoords.clone();
            scanpos[1]++;
            scanpos[2]++;
            InGameCharacter.addEnemiesatTile(igc, nE, b, scanpos);  
        }
        igc.setNearbyEnemies(nE);
    }
    public static void addEnemiesatTile(InGameCharacter igc, ArrayList<InGameCharacter> nE, Board b, int[] tcoords) {
        String igcType = igc.getHM();
        if (b.valid(tcoords)) {
            Tile pos = b.getTileAt(tcoords);
            if (igcType.equals("Hero")) {
                if (pos.m_on_me() != null) {
                    nE.add(pos.m_on_me());
                }
            }
            else if (igcType.equals("Monster")) {
                if (pos.h_on_me() != null) {
                    nE.add(pos.h_on_me());
                }
            }
        }
    }
    public static void main(String[] args) {
        
    }
}
