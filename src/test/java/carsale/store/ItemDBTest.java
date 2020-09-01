package carsale.store;

import carsale.model.CarBrand;
import carsale.model.Item;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemDBTest {

    @Test
    public void storeAndGet() {

        CarBrand brand = new CarBrand("Mercedes");

        Item item1 = new Item("Mercedes AB");
        Item item2 = new Item("Mercedes XZ");

        item1.setBrand(brand);
        item2.setBrand(brand);

        CarBrandDB.save(brand);

        ItemDB.save(item1);
        ItemDB.save(item2);

        List<Item> rslItems = CarBrandDB.getAll().get(0).getItems();

        rslItems.forEach(it -> System.out.println(it.getDesc()));

        assertEquals(2, rslItems.size());
    }
}
