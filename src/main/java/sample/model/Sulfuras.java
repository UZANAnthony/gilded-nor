package sample.model;

import sample.model.Item;

import java.util.Date;

public class Sulfuras extends Item {
    public Sulfuras(int ID, Date date, String name, int sellIn, int quality) {
        super(ID, date, name, sellIn, quality);
    }

    public void update(){
        if(getQuality() != 80) setQuality(80);
        if(getSellIn() != 0) setSellIn(0);
    }

}
