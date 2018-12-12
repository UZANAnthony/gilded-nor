package sample.model;

import java.util.Date;

public class Elixir extends Item{
    public Elixir(int ID, Date date, String name, int sellIn, int quality) {
        super(ID, date, name, sellIn, quality);
    }

    public void update(){
        if(getQuality() > 0){
            setQuality(getQuality() - 1);
            if (getSellIn() == 0 && getQuality() > 0){
                setQuality(getQuality() - 1);
            }
        }
        if(getSellIn() > 0) {
            setSellIn(getSellIn() - 1);
        }
    }
}
