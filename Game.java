public abstract class Game implements Playable {

    /**
     * Abstract Game class from which concrete games extend
     */

    protected Board b;
    protected boolean gameOver;

    public abstract void play();

}