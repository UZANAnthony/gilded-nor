package sample.model;

import sample.model.Item;

public class Sulfuras extends Item {
    public Sulfuras(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void update(){
        if(getQuality() != 80) setQuality(80);
        if(getSellIn() != 0) setSellIn(0);
    }

}
