package sample;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
    private Inventory inventory;
    private Item[] items;

    @Given("^I have a new inventory$")
    public void iHaveANewInventory() throws Throwable
    {
        inventory = new Inventory();
        items = inventory.getItems();
    }

    @Then("^the quality of Sulfuras item is (\\d+)$")
    public void theQualityOfSulfurasIs(int sulfurasQuality) throws Throwable
    {
        assertThat(inventory.fetchItem("Sulfuras, Hand of Ragnaros").getQuality(), is(sulfurasQuality));
    }

    @When("^I update the inventory$")
    public void iUpdateTheInventory() throws Throwable
    {
        inventory.updateQuality();
    }
}
