package sample.controller;

import sample.model.Inventory;
import sample.model.Item;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sample.model.JSONReader;

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
    @FXML
    ImageView dragBox;
    @FXML
    Label letgo;

    Image DragOn = new Image("/fxml/DragOn.png");
    Image DragOff = new Image("/fxml/DragOff.png");


    public Inventory inventory = new Inventory();
    public ObservableList<PieChart.Data> itemFrequency;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchInventory();
        inventory.GetSellInArray();
        list.getSelectionModel().selectedItemProperty().addListener((e -> displayItemDetails(list.getSelectionModel().getSelectedIndex())));
        itemFrequency = FXCollections.observableArrayList();
        displayPiechart();
    }

    public void fetchInventory(){
        ObservableList<String> inventory_view;
        inventory_view = FXCollections.observableArrayList(inventory.toList());
        list.setItems(inventory_view);
    }

    public void displayItemDetails(int index)
    {
        try {
            Item item = this.inventory.getItems()[index];
            name.setText(item.getName());
            sellin.setText(String.valueOf(item.getSellIn()));
            quality.setText(String.valueOf(item.getQuality()));
            type.setText(item.getClass().getSimpleName());
        }
        catch (Exception e)
        {

        }
    }

    public void update(){
        this.inventory.updateQuality();
        displayItemDetails(list.getSelectionModel().getSelectedIndex());
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

    @FXML
    public void handleDragOver(DragEvent event){
        if (event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
            dragBox.setImage(DragOn);
            letgo.setVisible(true);
        }
    }

    @FXML
    public void handleDetectExitDrag(DragEvent event){
        dragBox.setImage(DragOff);
        letgo.setVisible(false);
    }

    @FXML
    public void handleDrop(DragEvent event){
        List<File> files = event.getDragboard().getFiles();
        String file = files.get(0).toString();
        inventory = JSONReader.GetItemsFromJson(file, inventory);
        fetchInventory();
        initializeItemFrequency();
        displayPiechart();
    }
}
