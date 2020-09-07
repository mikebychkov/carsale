package carsale.servlet.itemlist;

import carsale.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemGettingVariant {
    List<Item> doVariant(Map<String, Object> params);
}
