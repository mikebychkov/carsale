package carsale.store;

import carsale.model.Item;

import java.util.List;

public class ItemDB {

    private ItemDB() {
    }

    public static Item save(Item item) {
        ModelDB.tx(session -> session.save(item));
        return item;
    }

    public static Item update(Item item) {
        ModelDB.tx(session -> {session.update(item); return 0;});
        return item;
    }

    public static Item saveOrUpdate(Item item) {
        ModelDB.tx(session -> {session.saveOrUpdate(item); return 0;});
        return item;
    }

    public static Item getItem(int id) {
        return ModelDB.tx(session -> session.get(Item.class, id));
    }

    public static List<Item> getItemList() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.Item").list());
    }

    public static List<Item> getActualItemList() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.Item WHERE done = null").list());
    }
}
