public abstract class Game implements Playable {

    protected Board b;
    // protected Team[] turn;
    // protected int mod;
    protected boolean gameOver;
    // protected String playagain;

    public abstract void play();

}