package sample.controller;


import javafx.scene.chart.BarChart;

import javafx.scene.chart.*;

import javafx.scene.control.Button;
import sample.model.Historic;
import sample.model.Inventory;
import sample.model.Item;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.List;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;


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
    ListView purchaseList;
    @FXML
    TextField itemID;
    @FXML
    TextField date;
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
    @FXML
    BarChart barchart2;
    @FXML
    CategoryAxis xAxis ;
    @FXML
    NumberAxis yAxis ;
    @FXML
    BarChart<?, ?> SellinChart;
    @FXML
    CategoryAxis x;
    @FXML
    NumberAxis y;
    @FXML
    Button sell_button;


    Image DragOn = new Image("/fxml/DragOn.png");
    Image DragOff = new Image("/fxml/DragOff.png");


    public Inventory inventory = new Inventory();
    public Historic h = new Historic(new ArrayList<String>(), new ArrayList<String>());
    public ObservableList<PieChart.Data> itemFrequency;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchInventory();
        //inventory.GetSellInStat();
        list.getSelectionModel().selectedItemProperty().addListener((e -> displayItemDetails(list.getSelectionModel().getSelectedIndex())));
        itemFrequency = FXCollections.observableArrayList();
        displayPiechart();
        setBarchart2();
        displayBarchart_1();
    }

    public void fetchInventory(){
        ObservableList<String> inventory_view;
        inventory_view = FXCollections.observableArrayList(inventory.toList());
        list.setItems(inventory_view);
        sell_button.setDisable(true);
    }



    public void PurchaseView()
    {
        ObservableList<String> purchase;
        purchase = FXCollections.observableArrayList(h.getPurchase());
        purchaseList.setItems(purchase);
        sell_button.setDisable(true);
    }



    public void displayItemDetails(int index)
    {
        try {
            Item item = this.inventory.getItems()[index];
            itemID.setText(String.valueOf(item.getID()));
            date.setText(String.valueOf(item.getDate()));
            name.setText(item.getName());
            sellin.setText(String.valueOf(item.getSellIn()));
            quality.setText(String.valueOf(item.getQuality()));
            type.setText(item.getClass().getSimpleName());
            if(itemID.getText() != ""){
                sell_button.setDisable(false);
            }
        }
        catch (Exception e)
        {

        }
    }

    public void update(){
        this.inventory.updateQuality();
        displayItemDetails(list.getSelectionModel().getSelectedIndex());
        displayBarchart_1();
    }


    public void displayPiechart()
    {
        piechartItemCount.getData().removeAll(Collections.singleton(piechartItemCount.getData().setAll()));

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

    public void setBarchart2(){
        barchart2.getData().removeAll(Collections.singleton(barchart2.getData().setAll()));
        xAxis = new CategoryAxis();
        xAxis.setLabel("Date");
        yAxis = new NumberAxis();
        yAxis.setLabel("Number of items");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("Items");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Integer> numberDate = new ArrayList<>();
        ArrayList<String> listDate = new ArrayList<>();
        for (Item item : inventory.getItems()){
            if(listDate.contains(dateFormat.format(item.getDate()))){
                numberDate.set(listDate.indexOf(dateFormat.format(item.getDate())), numberDate.get(listDate.indexOf(dateFormat.format(item.getDate()))) + 1);
            }
            else{
                numberDate.add(1);
                listDate.add(dateFormat.format(item.getDate()));
            }
        }
        for (int i = 0; i < listDate.size(); i++){
            dataSeries1.getData().add(new XYChart.Data(listDate.get(i), numberDate.get(i)));
        }
        barchart2.getData().add(dataSeries1);
    }

    public void displayBarchart_1(){
        SellinChart.getData().removeAll(Collections.singleton(SellinChart.getData().setAll()));
        inventory.GetSellInStat();
        XYChart.Series set = new XYChart.Series();

        ArrayList<String> x = inventory.getKeys();
        ArrayList<Integer> y = inventory.getValues();

        //System.out.println(inventory.getKeys());

        for(int i = 0; i < x.size(); i++){
            set.getData().add(new XYChart.Data(x.get(i), y.get(i)));
        }

        SellinChart.getData().add(set);
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
        inventory = JSONReader.GetItemsFromJson(file, inventory, h);
        fetchInventory();
        initializeItemFrequency();
        displayPiechart();
        setBarchart2();
        displayBarchart_1();
    }

    public void sellItemView(){
        inventory.SellItem(Integer.valueOf(itemID.getText()));
        fetchInventory();

        itemID.setText("");
        date.setText("");
        name.setText("");
        sellin.setText("");
        quality.setText("");
        type.setText("");

        displayBarchart_1();
        displayPiechart();
        setBarchart2();
    }


}
