package sample;

public class Backstage extends Item{
    public Backstage(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }
    public void update(){
        if (getQuality() < 50) {
            if (getSellIn() >= 11){
                if (getQuality() < 50) {
                    setQuality(getQuality() + 1);
                }
            }
            if (getSellIn() < 11) {
                if (getQuality() < 49) {
                    setQuality(getQuality() + 2);
                }
            }
            if (getSellIn() < 6) {
                if (getQuality() < 50) {
                    setQuality(getQuality() + 1);
                }
            }
            if (getSellIn() == 0){//ADD
                setQuality(0);
            }
        }
        if(getSellIn() > 0) {
            setSellIn(getSellIn() - 1);
        }
    }

}
