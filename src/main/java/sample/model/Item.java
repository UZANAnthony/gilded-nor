package sample.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {

    private int ID;
    private Date date;
    private String name;
    private int sellIn;
    private int quality;

    public Item(int ID, Date date, String name, int sellIn, int quality) {
        super();

        this.ID = ID;
        this.date = date;
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public int getID() { return ID; }

    public Date getDate() { return date; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Item{" +
                "ID:" + ID +
                ", Date:" + date +
                ", name:'" + name + '\'' +
                ", sellIn:" + sellIn +
                ", quality:" + quality +
                '}';
    }

    public void update(){
        quality -= 1;
    }
}
