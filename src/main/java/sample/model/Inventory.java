package sample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Inventory {

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();

        Date date = new Date();
        items = new Item[]{
                new DexterityVest(1,date,"+5 Dexterity Vest", 10, 20),
                new AgedBrie(2, date, "Aged Brie", 2, 0),
                new Elixir(3, date, "Elixir of the Mongoose", 5, 7),
                new Sulfuras(4, date, "Sulfuras, Hand of Ragnaros", 0, 80),
                new Backstage(5, date, "Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Conjured(6, date,"Conjured Mana Cake", 3, 6),
        };
    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public List<String> toList(){
        List<String> list_tmp = new ArrayList<>();
        for (Item item : items){
            list_tmp.add(item.getName());
        }
        return  list_tmp;
    }

    public Item[] getItems()
    {
        return this.items;
    }

    public Item fetchItem(String name)
    {
        boolean itemFound = false;
        int i = 0;
        Item result = null;
        while (i < this.items.length && itemFound == false)
        {
            if (this.items[i].getName() == name)
            {
                result = this.items[i];
                itemFound = true;
            }
            i = i + 1;
        }
        return result;
    }

    public void updateQuality() {
        for (Item item : items){
            item.update();
        }
    }

    public Item newItem(int ID, Date date, String type, String name, int sellIn, int quality){
        if (type != null){
            switch(type){
                case "AgedBrie":
                    return new AgedBrie(ID, date, name, sellIn, quality);
                case "Backstage":
                    return new Backstage(ID, date, name, sellIn, quality);
                case "Conjured":
                    return new Conjured(ID, date, name, sellIn, quality);
                case "DexterityVest":
                    return new DexterityVest(ID, date, name, sellIn, quality);
                case "Elixir":
                    return new Elixir(ID, date, name, sellIn, quality);
                case "Sulfuras":
                    return new Sulfuras(ID, date, name, sellIn, quality);
            }
        }
        return null;
    }

    public void addItem(Item newI){
        Item tmp_list[] = new Item[items.length];
        for(int i = 0; i < items.length; i++){
            tmp_list[i] = items[i];
        }
        items = new Item[tmp_list.length + 1];
        for(int i = 0; i < tmp_list.length; i++){
            items[i] = tmp_list[i];
        }
        items[items.length - 1] = newI;
    }
}


