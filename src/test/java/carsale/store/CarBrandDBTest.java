package carsale.store;

import carsale.model.CarBrand;
import carsale.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarBrandDBTest {

    private static final Logger logger = LogManager.getLogger(CarBrandDBTest.class);

    @Test
    public void storeAndGet() {

        CarBrand brand = new CarBrand("BMW");

        Item item = new Item("BMW SLK 350");
        item.setBrand(brand);

        brand.addItem(item);

        CarBrandDB.save(brand);

        //

        CarBrand rslBrand = CarBrandDB.getByName("BMW").get(0);

        assertEquals("BMW", rslBrand.getName());

        //

        String rslItemDesc = rslBrand.getItems().get(0).getDesc();

        assertEquals("BMW SLK 350", rslItemDesc);

        //

        String rslItemBrandName = ItemDB.getItemList().get(0).getBrand().getName();

        assertEquals("BMW", rslItemBrandName);
    }
}
