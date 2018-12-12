package sample.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    static public Inventory GetItemsFromJson(String filename, Inventory Items) {

        JSONParser parser = new JSONParser();

        try {

            FileReader fileReader = new FileReader(filename);
            Object obj = parser.parse(fileReader);
            JSONArray items = (JSONArray) obj;


            for(Object o : items)
            {
                String line = o.toString();
                JsonObject item = new JsonParser().parse(line).getAsJsonObject();

                String name = item.get("name").getAsString();
                int sellin = item.get("sellIn").getAsInt();
                int quality = item.get("quality").getAsInt();
                String type = item.get("class").getAsString();

                Item i = Items.newItem(type, name, sellin, quality);
                Items.addItem(i);
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
