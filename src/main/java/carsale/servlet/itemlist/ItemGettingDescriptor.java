package carsale.servlet.itemlist;

import carsale.model.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemGettingDescriptor {

    private Map<String, ItemGettingVariant> variantMap = new HashMap<>();

    public ItemGettingDescriptor() {
        init();
    }

    public void init() {
        variantMap.put("all", new getItemsAll());
        variantMap.put("day", new getItemsByDay());
        variantMap.put("pic", new getItemsByPic());
        variantMap.put("brand", new getItemsByBrand());
    }

    public void addVariant(String variantName, ItemGettingVariant variant) {
        variantMap.put(variantName, variant);
    }

    public List<Item> getItemList(String variantName, Map<String, Object> params) {
        if (!variantMap.containsKey(variantName)) {
            throw new UnsupportedOperationException(variantName);
        }
        return variantMap.get(variantName).doVariant(params);
    }
}
