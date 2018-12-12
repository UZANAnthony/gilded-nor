package sample.model;

import sample.model.Item;

import java.util.Date;

public class AgedBrie extends Item {
    public AgedBrie(int ID, Date date, String name, int sellIn, int quality) {
        super(ID, date, name, sellIn, quality);
    }

    public void update(){
        if (getQuality() < 50) {
            setQuality(getQuality() + 1);
        }
        if(getSellIn() > 0) {
            setSellIn(getSellIn() - 1);
        }
    }
}
