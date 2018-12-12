package sample.model;

import java.util.*;

public class Inventory {

    private Item[] items;
    private int[] sellInArray;

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
                case "Backstage":
                    return new Backstage(name, sellIn, quality);
                case "Conjured":
                    return new Conjured(name, sellIn, quality);
                case "DexterityVest":
                    return new DexterityVest(name, sellIn, quality);
                case "Elixir":
                    return new Elixir(name, sellIn, quality);
                case "Sulfuras":
                    return new Sulfuras(name, sellIn, quality);
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

    public void GetSellInArray(){
        Hashtable<Integer, Boolean> sellInVal = new Hashtable<Integer, Boolean>();
        for(int i = 0; i < items.length; i++){
            if(!sellInVal.containsKey(items[i])){
                sellInVal.put(items[i].getSellIn(), true);
            }
        }
        Enumeration<Integer> keys = sellInVal.keys();
        sellInArray = new int[sellInVal.keySet().size()];
        for (int i = 0; i < sellInArray.length; i++){
            sellInArray[i] = keys.nextElement();
        }
        for(int i = 0; i < sellInArray.length; i++){
            System.out.println(sellInArray[i]);
        }
    }





}


