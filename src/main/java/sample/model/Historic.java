package sample.model;

import java.util.ArrayList;

public class Historic {

    ArrayList<Item> sellin;
    ArrayList<Item> buy;

    public Historic(ArrayList<Item> sellin, ArrayList<Item> buy)
    {
        this.sellin = sellin;
        this.buy = buy;
    }

    public ArrayList<Item> getSellin() { return sellin; }
    public ArrayList<Item> getBuy() { return buy;}
}
