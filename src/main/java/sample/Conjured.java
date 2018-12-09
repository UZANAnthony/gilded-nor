package sample;

public class Conjured extends Item{
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
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
