package sample.model;

import java.util.Date;

public class Conjured extends Item{
    public Conjured(int ID, Date date, String name, int sellIn, int quality) {
        super(ID, date, name, sellIn, quality);
    }

    public void update(){
        if(getQuality() >= 2){
            setQuality(getQuality() - 2);
            if (getSellIn() == 0 && getQuality() >= 2){
                setQuality(getQuality() - 2);
            }
        }
        if(getQuality() == 1) setQuality(0);
        if(getSellIn() > 0) {
            setSellIn(getSellIn() - 1);
        }
    }
}
