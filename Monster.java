import java.util.ArrayList;
import java.util.Random;

/**
 * Concrete class Monster which extends InGameCharacter
 */
public class Monster extends InGameCharacter {
    //Subclass of IGC that represents the monsters of the Quest of Legends. Monsters will either attack if there are Heroes nearby, or simply move one tile downard.

    protected int[] og; //original stats PRE-SPELLS
    protected Hero attacking;

    public Monster () {
        name = "a monster";
        type =  "Friendly Foe"; //"Fiendly Adversary";
        HM = "Monster";
        lvl = 1;
        exp = 0;
        maxhp = lvl*100;
        hp = maxhp;
        strength = 0;
        defense = 0;
        agility = 0;
        og = new int[] {strength,defense,agility};

        loc = null; 
        boardpiece = null; 
        nearbyEnemies = new ArrayList<InGameCharacter>(); 
        attacking = null;
    }
    public Monster (String n, String t, int l, int s, int d, int a) {
        name = n;
        type = t;
        HM = "Monster";
        lvl = l;
        exp = Math.max(0, (lvl-1)*10);
        maxhp = l*100;
        hp = maxhp;
        strength = s;
        defense = d;
        agility = a;
        og = new int[] {strength,defense,agility};

        loc = null; 
        boardpiece = null; 
        nearbyEnemies = new ArrayList<InGameCharacter>(); 
        attacking = null;
    }
    public Monster (String n, String t, int l, int h, int s, int d, int a) {
        name = n;
        type = t;
        HM = "Monster";
        lvl = l;
        exp = Math.max(0, (lvl-1)*10);
        maxhp = h;
        hp = maxhp;
        strength = s;
        defense = d;
        agility = a;
        og = new int[] {strength,defense,agility};

        loc = null; 
        boardpiece = null; 
        nearbyEnemies = new ArrayList<InGameCharacter>(); 
        attacking = null;
    }

    @Override
    public void setPiece(int n) {
        this.boardpiece = "M" + Integer.toString(n);
    }

    // public void moveTo(Tile target) {
    //     if (this.loc != null) this.loc.leaveTile(this);
    //     this.loc = target;
    //     // target.setLocTile(this);
    //     target.testMLocTile(this);
    // }

    // Updates the attributes of a Monster based on array argument
    // Usually called when a Spell is cast on a Monster
    public void updAttributes (int[] a) {
        this.strength -= a[0];
        this.defense -= a[1];
        this.agility -= a[2];
    }

    public void resetMonster() {
        this.hp = maxhp;
        this.strength = og[0];
        this.defense = og[1];
        this.agility = og[2];
    }

    // public void rematch() {}

    public int getLVL() {
        return lvl;
    }
    public String getName() {
        return name;
    }
    public void setAttacking (InGameCharacter m) {
        this.attacking = (Hero)m;
    }
    public Hero getAttacking() {
        return this.attacking;
    }

    // RETURNS true if a Monster's nearbyEnemies ArrayList is !empty AND 
    // Sets Monster's attacking target to a random Hero in its nearbyEnemies if there are enemies in range
    public boolean canAttack(){
        //check if arraylist is empty, if it is it cannot attack, and will be forced to move downward 1 tile
        //couple this code with if(m.canAttack){choose random enemy to attack}else{move down}
        if(this.nearbyEnemies.isEmpty()){
            return false;
        }
        else{
            Random rand = new Random();
            int index = rand.nextInt(nearbyEnemies.size());
            InGameCharacter attackthis = nearbyEnemies.get(index);
            Hero attacking = (Hero)attackthis;
            this.setAttacking(attacking);
            return true;
        }
        
    }

    // Method called when determining a Monster's choice of action. 
    // TRIES to attack: Returns true when it can attack and false when there is nobody to attack
    public boolean tryAttackaction() {
        // Fight
        boolean attacked = false;
        if (canAttack()) {
            attack(this.getAttacking());
            attacked = true;
        }
        return attacked;
        // Move
    }

    // Method to determine a Monster's choice of movement
    public String tryMove(int TandE) {
        if (TandE == 1) {
            return "S";
        }
        else if (TandE == 2) return "D";
        else if (TandE == 3) return "A";
        // MONSTERS NEVER TURN BACK TOWARD THEIR NEXUS
        else if (TandE > 3) return "outofmoves";
        else return "outofmoves";
    }

    // Called when a Monster's health dips to or below ZERO, signalling "death" of this Monster and takes it off the board
    public void setDead() {
        this.getLoc().leaveTile(this);
    }

    @Override
    // Called during RewardIGC and determines if a Monster can levelup, levels up Monster if it can
    public void levelup() {
        if (this.exp >= lvl*10) {
            hp = maxhp;
            strength = (int)(strength*1.05);
            defense = (int)(defense*1.05);
            og[1] = strength;
            og[2] = defense;
        }
    }
    @Override
    public void reward() { // Reward for alive monsters that win battle
        this.exp += 1;
    }
    public void reward(int c) {    } // No effect for Monster subclass of IGC

    public String si(int i) {
        return Integer.toString(i);
    }
    public String toString() {
        String descr = name + "\t("+type+")"  + "\tLevel: "+si(lvl) 
                    + "\n\tStrength: "+si(strength) + "\tDefense: "+si(defense) + "\tAgility: "+si(agility)
                    + /**"\n" +*/ "\tExp: "+si(exp)+"/"+si(10*lvl) + "\tHealth: "+si(hp)+"/"+si(maxhp);
        return descr;
    }

    public static void main(String[] args) {
        /**
         * Name/level/damage/defense/dodge chance
Cyrrollalee     7       700        800     75
Brandobaris     3       350        450     30
BigBad-Wolf     1       150        250     15
WickedWitch     2       250        350     25
Aasterinian     4       400        500     45
Chronepsish     6       650        750     60
Kiaransalee     8       850        950     85
St-Shargaas     5       550        650     55
Merrshaullk     10      1000       900     55
St-Yeenoghu     9       950        850     90
         */

        Monster Cyr = new Monster("Cyrrollalee", "Exoskeleon", 7, 700, 800, 75);
    }
}
