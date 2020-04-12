// import java.util.Random;
// import java.util.*;
import java.util.ArrayList;

public abstract class InGameCharacter {
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
    protected ArrayList<InGameCharacter> nearbyEnemies; //TODO create scan for enemies based on LOC


    public void spawn(Tile loc, int n) {
        moveTo(loc);
        setPiece(n);
    }

    public void levelup() {
        if (this.exp >= lvl*10) {
        hp = maxhp;
        strength = (int)(strength*1.05);
        defense = (int)(defense*1.05);
        agility = (int)(agility*1.05);
        }
    }
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

    public void attack(InGameCharacter c) { //TODO CHANGE ATTACK
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

    public abstract void reward();
    // public abstract void levelup();
    public abstract void setPiece(int n); //TODO SCAN THROUGH LIST OF SPAWNED H/M TO GET N
    public String getPiece() {
        return boardpiece;
    }

    public boolean equals(InGameCharacter c) {
        return (this.name.equals(c.name));
    }
}