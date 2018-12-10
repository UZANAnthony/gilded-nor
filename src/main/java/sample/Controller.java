package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
}
