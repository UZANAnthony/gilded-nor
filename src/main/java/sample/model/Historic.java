package sample.model;

import java.util.ArrayList;

public class Historic {

    private ArrayList<String> purchase;
    private ArrayList<String> sellin;

    public Historic() {};

    public Historic(ArrayList<String> purchase, ArrayList<String> sellin) {
        super();
        this.purchase = purchase;
        this.sellin = sellin;
    }

    public ArrayList<String> getPurchase() {
        return purchase;
    }

    public ArrayList<String> getSellin() {
        return sellin;
    }

}
