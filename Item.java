public abstract class Item{ /** implements Usable */
    //Abstract item class representing any item that can be sold at the market and be used/equipped by an IGC.
    
    protected String type;
    protected String name;
    protected int price;
    protected int unlock;
    protected boolean equipped;

    public abstract int[] stats();

    public int getUnlockLvl() {
        return unlock;
    }
    public int getPrice() {
        return price;
    }
    public void setEquipped() { 
        this.equipped = true;
    }
    public void unEquip() {
        this.equipped = false;
    }
    public boolean getEquipped() {
        return this.equipped;
    }
    public String getItemName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public abstract String toString();

}
