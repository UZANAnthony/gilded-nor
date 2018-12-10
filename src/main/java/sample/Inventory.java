package sample;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        items = new Item[]{
                new DexterityVest("+5 Dexterity Vest", 10, 20),
                new AgedBrie("Aged Brie", 2, 0),
                new Elixir("Elixir of the Mongoose", 5, 7),
                new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80),
                new Backstage("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Conjured("Conjured Mana Cake", 3, 6),
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

    public Item newItem(String type, String name, int sellIn, int quality){
        if (type != null){
            switch(type){
                case "AgedBrie":
                    return new AgedBrie(name, sellIn, quality);
                    break;
                case "Backstage":
                    return new Backstage(name, sellIn, quality);
                    break;
                case "Conjured":
                    return new Conjured(name, sellIn, quality);
                    break;
                case "DexterityVest":
                    return new DexterityVest(name, sellIn, quality);
                    break;
                case "Elixir":
                    return new Elixir(name, sellIn, quality);
                    break;
                case "Sulfuras":
                    return new Sulfuras(name, sellIn, quality);
                    break;
            }
        }
        return null;
    }

    public void addItem(Item newI){
        Item new_list[] = new Item[items.length + 1];
        for(int i = 0; i < items.length; i++){
            new_list[i] = items[i];
        }
        new_list[new_list.length - 1] = newI;
    }
}
