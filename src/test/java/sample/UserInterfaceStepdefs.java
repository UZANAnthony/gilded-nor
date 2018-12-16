package sample;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Is;
import gherkin.lexer.Th;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import org.junit.Assert;
import sample.controller.Controller;
import sample.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.function.ToIntFunction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserInterfaceStepdefs {
    public ObservableList<PieChart.Data> itemFrequency;
    public Inventory inventory;
    public Item selectedItem;

    public int numberChosenItemBeforeUpdate;
    public int numberModificator;

    public void initializeItemFrequency()
    {
        itemFrequency = FXCollections.observableArrayList();

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
            };
        }
    }

    public int fetchItemCountFromInventory(String name)
    {
        int result = 0;
        int i = 0;
        while (i < inventory.getItems().length)
        {
            if (name.equals(inventory.getItems()[i].getClass().getSimpleName()))
            {
                result = result + 1;
            }
            i = i + 1;
        }
        return result;
    }

    public int fetchItemCountFromItemFrequency(String name)
    {
        int result = 0;
        boolean itemFound = false;
        int i = 0;
        while (i < itemFrequency.size() && itemFound == false)
        {
            if (name.equals(itemFrequency.get(i).getName()))
            {
                result = (int) itemFrequency.get(i).getPieValue();
                itemFound = true;
            }
            i = i + 1;
        }
        return result;
    }

    public void displayBarchart_1_Cucumber(){
        inventory.GetSellInStat();
        XYChart.Series set = new XYChart.Series();

        ArrayList<String> x = inventory.getKeys();
        ArrayList<Integer> y = inventory.getValues();

        //System.out.println(inventory.getKeys());

        for(int i = 0; i < x.size(); i++){
            set.getData().add(new XYChart.Data(x.get(i), y.get(i)));
        }
    }

    public int fetchNumberItemsBySellin(int sellin)
    {
        String key = String.valueOf(sellin);
        int result = 0;
        int i = 0;
        boolean keyFound = false;
        while (i < inventory.getKeys().size() && keyFound == false)
        {
            if (inventory.getKeys().get(i).equals(key))
            {
                result = inventory.getValues().get(i);
                keyFound = true;
            }
            i = i + 1;
        }
        return result;
    }

    @Given("^the user has an inventory$")
    public void hasAnInventory() throws Throwable
    {
        inventory = new Inventory();
        inventory.GetSellInStat();
        numberModificator = 0;
    }

    @And("^a list containing item frequency$")
    public void listContainingItemFrequency() throws Throwable
    {
        itemFrequency = FXCollections.observableArrayList();
        initializeItemFrequency();
    }

    @When("^the user buys an item$")
    public void addItem() throws Throwable
    {
        selectedItem = new Sulfuras(999,new Date(),"Sulfuras, fire dragon",0,80);
        numberChosenItemBeforeUpdate = fetchNumberItemsBySellin(selectedItem.getSellIn());
        inventory.addItem(selectedItem);
        numberModificator = numberModificator + 1;
    }

    @When("^the user sells an item$")
    public void sellItem() throws Throwable
    {
        selectedItem = inventory.getItems()[0];
        numberChosenItemBeforeUpdate = fetchNumberItemsBySellin(selectedItem.getSellIn());
        inventory.SellItem(selectedItem.getID());
        numberModificator = numberModificator - 1;
    }

    @Then("^the item frequency list is updated$")
    public void itemFrequencyUpdated() throws Throwable
    {
        initializeItemFrequency();
        boolean itemCountMatch = true;
        for (Item item : inventory.getItems())
        {
            String name = item.getClass().getSimpleName();
            if (fetchItemCountFromInventory(name) != fetchItemCountFromItemFrequency(name))
            {
                itemCountMatch = false;
            }
        }
        assertTrue(itemCountMatch);
    }

    @Then("^the item has a creation date$")
    public void itemHasACreationDate() throws Throwable
    {
        Assert.assertNotNull(inventory.getItems()[inventory.getItems().length - 1].getDate());
    }

    @Then("^the barchart 1 \\(X = sellin and Y = number of items\\) is updated$")
    public void barchart1sUpdated() throws Throwable
    {
        displayBarchart_1_Cucumber();
        Assert.assertTrue(fetchNumberItemsBySellin(selectedItem.getSellIn()) == numberChosenItemBeforeUpdate + numberModificator);
    }

}
