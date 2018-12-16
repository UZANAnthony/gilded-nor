package sample.model;

import java.util.ArrayList;

public class Historic {

    private ArrayList<String> purchase;
    private ArrayList<String> sold;

    public Historic(ArrayList<String> purchase, ArrayList<String> sold) {
        super();
        this.purchase = purchase;
        this.sold = sold;
    }

    public ArrayList<String> getPurchase() {
        return purchase;
    }

    public ArrayList<String> getSellin() {
        return sold;
    }

    public void addToSold(String sale) {
        System.out.println(sale);
        sold.add(sale);
    }

    public void addToPurchase(String buy) {
        System.out.println(buy);
        purchase.add(buy);
    }



}
