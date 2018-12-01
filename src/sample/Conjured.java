package sample;

public class Conjured extends Item{
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void update(){
        if(getQuality() > 0){
            setQuality(getQuality() - 2);
            if (getSellIn() < 0){
                setQuality(getQuality() - 2);
            }
        }
    }
}
