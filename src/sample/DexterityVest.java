package sample;

public class DexterityVest extends Item{
    public DexterityVest(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void update(){
        if(getQuality() > 0){
            setQuality(getQuality() - 1);
            if (getSellIn() < 0){
                setQuality(getQuality() - 1);
            }
        }
    }
}
