package sample;

public class Elixir extends Item{
    public Elixir(String name, int sellIn, int quality) {
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
