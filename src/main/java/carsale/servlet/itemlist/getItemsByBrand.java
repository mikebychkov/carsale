package carsale.servlet.itemlist;

import carsale.model.CarBrand;
import carsale.model.Item;
import carsale.store.CarBrandDB;
import carsale.store.ItemDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class getItemsByBrand implements ItemGettingVariant {

    @Override
    public List<Item> doVariant(Map<String, Object> params) {
        Boolean actual = (Boolean) params.get("actual");
        String brandName = (String) params.get("brand");
        List<CarBrand> brands = CarBrandDB.getByName(brandName);
        if (brands.size() == 0) {
            return new ArrayList<>();
        }
        return ItemDB.getItemListByBrand(actual, brands.get(0));
    }
}
