package sample;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.javaws.jnl.XMLFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Controller implements Initializable {

    @FXML
    ListView list;
    @FXML
    TextField name;
    @FXML
    TextField sellin;
    @FXML
    TextField quality;
    @FXML
    TextField type;
    @FXML
    PieChart piechartItemCount;

    public Inventory inventory;
    public ObservableList<PieChart.Data> itemFrequency;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchInventory();
        list.getSelectionModel().selectedItemProperty().addListener((e -> displayItemDetails(list.getSelectionModel().getSelectedItem())));
        itemFrequency = FXCollections.observableArrayList();
        displayPiechart();
    }

    public void fetchInventory(){
        this.inventory = new Inventory();
        //inventory.addItem(inventory.newItem("Elixir","Jus de bagarre", 20, 49));
        //inventory.printInventory();
        ObservableList<String> inventory_view;
        inventory_view = FXCollections.observableArrayList(inventory.toList());
        list.setItems(inventory_view);
    }

    public void displayItemDetails(Object o)
    {
        String itemName = (String) o;
        Item item = fetchItemByName(itemName);
        name.setText(item.getName());
        sellin.setText(String.valueOf(item.getSellIn()));
        quality.setText(String.valueOf(item.getQuality()));
        type.setText(String.valueOf(item.getClass().getSimpleName()));
    }

    public Item fetchItemByName(String name)
    {
        int i = 0;
        boolean itemFound = false;
        Item result = null;
        while (i < this.inventory.getItems().length && itemFound == false)
        {
            if (this.inventory.getItems()[i].getName().equals(name))
            {
                itemFound = true;
                result = this.inventory.getItems()[i];
            }
            i = i + 1;
        }
        return result;
    }

    public void update(){
        this.inventory.updateQuality();
        displayItemDetails(list.getSelectionModel().getSelectedItem());
    }

    public void displayPiechart()
    {
        initializeItemFrequency();

        piechartItemCount.setData(itemFrequency);
        piechartItemCount.setTitle("Item count");
    }

    public void initializeItemFrequency()
    {
        for (Item item : inventory.getItems())
        {
            String nameItem = item.getClass().getSimpleName();
            boolean itemFound = false;
            int i = 0;
            while (i < itemFrequency.size() && itemFound == false)
            {
                if (nameItem.equals(itemFrequency.get(i).getName()))
                {
                    itemFound = true;
                    itemFrequency.get(i).setPieValue(itemFrequency.get(i).getPieValue() + 1);
                }
                i = i + 1;
            }
            if (itemFound == false)
            {
                itemFrequency.add(new PieChart.Data(nameItem,1));
            }
        }
    }

    public void updateItemFrequency(Item newItem)
    {
        String nameItem = newItem.getClass().getSimpleName();
        boolean itemFound = false;
        int i = 0;
        while (i < itemFrequency.size() && itemFound == false)
        {
            if (nameItem.equals(itemFrequency.get(i).getName()))
            {
                itemFound = true;
                itemFrequency.get(i).setPieValue(itemFrequency.get(i).getPieValue() + 1);
            }
            i = i + 1;
        }
        if (itemFound == false)
        {
            itemFrequency.add(new PieChart.Data(nameItem,1));
        }
    }

    static public Item[] GetItemsFromJson(String filename) {

        JSONParser parser = new JSONParser();

        try {

            FileReader fileReader = new FileReader(filename);
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
