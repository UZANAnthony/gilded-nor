package sample;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.lexer.Th;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import org.junit.Assert;
import sample.controller.Controller;
import sample.model.*;

import java.util.Date;
import java.util.function.ToIntFunction;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PiechartStepdefs {
    public ObservableList<PieChart.Data> itemFrequency;
    public Inventory inventory;

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

    @Given("^the user has an inventory$")
    public void hasAnInventory() throws Throwable
    {
        inventory = new Inventory();
    }

    @And("^a list containing item frequency$")
    public void listContainingItemFrequency() throws Throwable
    {
        itemFrequency = FXCollections.observableArrayList();
        initializeItemFrequency();
    }

    @When("^the user add an item to the inventory$")
    public void addItem() throws Throwable
    {
        inventory.addItem(new Sulfuras(1,new Date(),"Sulfuras, fire dragon",0,80));
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
}
