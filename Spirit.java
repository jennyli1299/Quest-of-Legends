public class Spirit extends Monster {
    //Subclass of Monster that represents the spirit enemy

    public Spirit (String n, int l, int s, int d, int a) {
        super(n, "Spirit", l, s, d, a);
    }
    public Spirit (String n, int l, int h, int s, int d, int a) {
        super(n, "Spirit", l, h, s, d, a);
    }
}
