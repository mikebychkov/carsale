package carsale.store;

import carsale.model.CarBrand;
import carsale.model.Item;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CarBrandDBTest {

    @Test
    public void storeAndGet() {

        CarBrand brand = new CarBrand("Mercedes");

        Item item = new Item("Mercedes-Benz SLK 350");
        item.setBrand(brand);

        brand.addItem(item);

        CarBrandDB.save(brand);

        //

        CarBrand rslBrand = CarBrandDB.getAll().get(0);

        assertEquals("Mercedes", rslBrand.getName());

        //

        String rslItemDesc = rslBrand.getItems().get(0).getDesc();

        assertEquals("Mercedes-Benz SLK 350", rslItemDesc);

        //

        String rslItemBrandName = ItemDB.getItemList().get(0).getBrand().getName();

        assertEquals("Mercedes", rslItemBrandName);
    }
}
