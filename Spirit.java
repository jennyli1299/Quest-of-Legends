public class Spirit extends Monster {

    public Spirit (String n, int l, int s, int d, int a) {
        name = n;
        type = "Spirit";
        lvl = l;
        exp = Math.max(0, (lvl-1)*10);
        maxhp = l*100;
        hp = maxhp;
        strength = s;
        defense = d;
        agility = a;
        og = new int[] {strength,defense,agility};
    }
    public Spirit (String n, int l, int h, int s, int d, int a) {
        name = n;
        type = "Spirit";
        lvl = l;
        exp = Math.max(0, (lvl-1)*10);
        maxhp = h;
        hp = maxhp;
        strength = s;
        defense = d;
        agility = a;
        og = new int[] {strength,defense,agility};
    }


    public static void main(String[] args) {
        
    }
}