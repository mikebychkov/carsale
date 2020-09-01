package carsale.store;

import carsale.model.CarBrand;
import carsale.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

public class CarBrandDBTest {

    private static final Logger logger = LogManager.getLogger(CarBrandDBTest.class);

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

    @Test
    public void multiThreadEditingItemList() throws InterruptedException {
        Storage.getInstance();

        CountDownLatch cdl = new CountDownLatch(2);

        CarBrand brand = new CarBrand("Mercedes");

        Item i1 = new Item("Mercedes AB");
        i1.setBrand(brand);

        Item i2 = new Item("Mercedes XY");
        i2.setBrand(brand);

        Item i3 = new Item("Mercedes-Benz AB");
        i3.setBrand(brand);

        Item i4 = new Item("Mercedes-Benz XY");
        i4.setBrand(brand);

        List<Item> items1 = List.of(i1, i2);
        List<Item> items2 = List.of(i3, i4);

        new myThread(brand, items1, cdl);
        Thread.sleep(1000);
        new myThread(brand, items2, cdl);

        cdl.await();

        List<Item> rslItems = CarBrandDB.getAll().get(0).getItems();

        assertEquals(items1, rslItems);
    }

    public static class myThread implements Runnable {

        private CarBrand brand;
        private List<Item> items;
        private CountDownLatch cdl;

        public myThread(CarBrand brand, List<Item> items, CountDownLatch cdl) {
            this.brand = brand;
            this.items = items;
            this.cdl = cdl;
            new Thread(this).start();
        }

        @Override
        public void run() {
            logger.debug("THREAD: " + Thread.currentThread().getName() + " STARTED");
            items.forEach(it -> brand.addItem(it));
            try {
                CarBrandDB.save(brand);
            } catch (Exception e) {
                logger.error("=== ERROR SAVING MODEL! ===");
                e.printStackTrace();
            }
            cdl.countDown();
            logger.debug("THREAD: " + Thread.currentThread().getName() + " ENDED");
        }
    }
}
