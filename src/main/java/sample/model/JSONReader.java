package sample.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;



public class JSONReader {
    

    static public Inventory GetItemsFromJson(String filename, Inventory Items, Historic h) {

        JSONParser parser = new JSONParser();

        try {

            FileReader fileReader = new FileReader(filename);
            Object obj = parser.parse(fileReader);
            JSONArray items = (JSONArray) obj;


            Item[] t = Items.getItems();
            int i = t[t.length - 1].getID();


            for(Object o : items)
            {
                String line = o.toString();
                JsonObject item = new JsonParser().parse(line).getAsJsonObject();

                Date CreationDate = new Date();

                int id = i + 1;
                String name = item.get("name").getAsString();
                int sellin = item.get("sellIn").getAsInt();
                int quality = item.get("quality").getAsInt();
                String type = item.get("class").getAsString();

                Item newItem = Items.newItem(id, CreationDate, type, name, sellin, quality);
                Items.addItem(newItem);
                String purchase = (name + newItem.getDate().toString());
                h.getPurchase().add(purchase);
                i++;

            }


            return Items;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Items;
    }
}
