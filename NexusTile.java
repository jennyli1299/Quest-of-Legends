
public class NexusTile extends Tile {
    //subclass of Tile that holds the market and indicates that the market is available to a hero on the tile.
    
    private Market qMarket;

    public NexusTile(int[] coords) {
        super(coords);
        qMarket = null;
    }

    public void addMall(Market qM) {
        qMarket = qM;
    }

}
