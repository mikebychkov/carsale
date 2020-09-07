package carsale.servlet.itemlist;

import carsale.model.Item;
import carsale.store.ItemDB;

import java.util.List;
import java.util.Map;

public class getItemsAll implements ItemGettingVariant {

    @Override
    public List<Item> doVariant(Map<String, Object> params) {
        Boolean actual = (Boolean) params.get("actual");
        return ItemDB.getItemList(actual);
    }
}
