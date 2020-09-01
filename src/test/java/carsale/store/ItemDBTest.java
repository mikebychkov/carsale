package carsale.store;

import carsale.model.CarBrand;
import carsale.model.Item;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemDBTest {

    @Test
    public void storeAndGet() {

        CarBrand brand = new CarBrand("Toyota");

        Item item1 = new Item("Toyota Corola");
        Item item2 = new Item("Toyota Supra");

        item1.setBrand(brand);
        item2.setBrand(brand);

        CarBrandDB.save(brand);

        ItemDB.save(item1);
        ItemDB.save(item2);

        List<Item> rslItems = CarBrandDB.getByName("Toyota").get(0).getItems();

        rslItems.forEach(it -> System.out.println(it.getDesc()));

        assertEquals(2, rslItems.size());
    }
}
