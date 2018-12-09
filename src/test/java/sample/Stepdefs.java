package sample;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Stepdefs {
    private Inventory inventory;
    private Item selectedItem;

    /*
    @Given("^I have an inventory$")
    public void iHaveAnInventory() throws Throwable
    {
        inventory = new Inventory();
    }
    */

    @Given("^I have an Aged Brie$")
    public  void iHaveAnAgedBrie() throws Throwable
    {
        selectedItem = new AgedBrie("Aged Brie",0,0);
    }

    @Given("^I have a Backstage passes to a TAFKAL80ETC concert$")
    public  void iHaveABackstage() throws Throwable
    {
        selectedItem = new Backstage("Backstage passes to a TAFKAL80ETC concert",0,0);
    }

    @Given("^I have a Conjured Mana Cake$")
    public  void iHaveAConjured() throws Throwable
    {
        selectedItem = new Conjured("Conjured Mana Cake",0,0);
    }

    @Given("^I have a \\+5 Dexterity Vest$")
    public  void iHaveADexterityVest() throws Throwable
    {
        selectedItem = new DexterityVest("+5 Dexterity Vest",0,0);
    }

    @Given("^I have an Elixir of the Mongoose$")
    public  void iHaveAnElixir() throws Throwable
    {
        selectedItem = new Elixir("Elixir of the Mongoose",0,0);
    }

    @Given("^I have a Sulfuras, Hand of Ragnaros$")
    public  void iHaveASulfuras() throws Throwable
    {
        selectedItem = new Sulfuras("Sulfuras, Hand of Ragnaros",0,0);
    }

    @And("^I set its quality as (\\d+)$")
    public void setTheQualityAs(int quality) throws Throwable
    {
        //inventory.fetchItem(selectedItem.getName()).setQuality(quality);
        selectedItem.setQuality(quality);
    }

    @And("^I set its sellin as (\\d+)$")
    public void setSellInAs(int sellin) throws Throwable
    {
        //inventory.fetchItem(selectedItem.getName()).setSellIn(sellin);
        selectedItem.setSellIn(sellin);
    }

    @Then("^its quality is (\\d+)$")
    public void itsQualityIs(int quality) throws Throwable
    {
        assertThat(selectedItem.getQuality(), is(quality));
    }

    @Then("^its sellin is (\\d+)$")
    public void itsSellinIs(int sellin) throws Throwable
    {
        assertThat(selectedItem.getSellIn(), is(sellin));
    }

    @When("^I update the inventory$")
    public void iUpdateTheInventory() throws Throwable
    {
        //inventory.updateQuality();
        selectedItem.update();
    }
}
