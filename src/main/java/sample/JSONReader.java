package sample;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class JSONReader {

    public static void main(String[] args)
    {
        GetItemsFromJson();
    }


    static public Item[] GetItemsFromJson() {

        JSONParser parser = new JSONParser();

        try {

            FileReader fileReader = new FileReader("newItems.json");
            Object obj = parser.parse(fileReader);
            JSONArray items = (JSONArray) obj;

            Inventory Items = new Inventory();

            for(Object o : items)
            {
                String line = o.toString();
                System.out.println(line);
                JsonObject item = new JsonParser().parse(line).getAsJsonObject();
                //System.out.println(item);

                String name = item.get("name").getAsString();
                int sellin = item.get("sellIn").getAsInt();
                int quality = item.get("quality").getAsInt();
                String type = item.get("class").getAsString();

                Item i = Items.newItem(type, name, sellin, quality);
                Items.addItem(i);


            }

            for(Item i : Items.getItems())
            {
                //System.out.println(i);
            }

            return Items.getItems();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Item[0];
    }

}
