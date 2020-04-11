
public class NexusTile extends Tile {
    private Market qMarket;

    public NexusTile(int[] coords) {
        super(coords);
        qMarket = null;
    }

    public void addMall(Market qM) {
        qMarket = qM;
    }

}