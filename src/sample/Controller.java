package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    public Inventory inventory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetchInventory();
        list.getSelectionModel().selectedItemProperty().addListener((e -> displayItemDetails(list.getSelectionModel().getSelectedItem())));
    }

    public void fetchInventory(){
        this.inventory = new Inventory();
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
        type.setText(String.valueOf(item.getClass()));
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


}
