package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sample.model.Inventory;
import sample.model.Item;
import sample.model.JSONReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class JSONReaderTest {

    protected Inventory inv;
    Item[] ancienneListeDesItems;

    @Before
    public void setUp()
    {
        inv = new Inventory();
        ancienneListeDesItems = inv.getItems();
    }

    @After
    public void tearDown()
    {

    }

    @Test
    public void testIfAllObjectAreLoaded() throws Exception
    {
        Item[] items = inv.getItems();
        int lengthInv = items.length;
        int lengthJSON = 16;
        inv = JSONReader.GetItemsFromJson("./newItems.json", inv);
        assertEquals(inv.getItems().length, lengthJSON + lengthInv);
    }

    @Test
    public void testIfFirstItemIsNotModified() throws Exception
    {
        Item[] items = inv.getItems();
        String firstName = items[0].getName();
        inv = JSONReader.GetItemsFromJson("./newItems.json", inv);
        assertEquals(inv.getItems()[0].getName(), firstName);
    }

    @Test
    public void testIfLastItemIsNotModified() throws Exception
    {
        Item[] items = inv.getItems();
        int lengthInv = items.length;
        int lengthJSON = 16;
        String lastName = "Conjured Mana Cake";
        inv = JSONReader.GetItemsFromJson("./newItems.json", inv);
        assertEquals(inv.getItems()[lengthJSON+lengthInv-1].getName(), lastName);
    }
}
