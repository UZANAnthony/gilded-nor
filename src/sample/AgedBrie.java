package sample;

public class AgedBrie extends Item{
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void update(){
        if (getQuality() < 50) {
            setQuality(getQuality() + 1);
        }
    }
}
