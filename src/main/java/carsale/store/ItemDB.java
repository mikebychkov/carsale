package carsale.store;

import carsale.model.CarBrand;
import carsale.model.Item;
import org.hibernate.query.Query;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringJoiner;

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

    private static String actualFilter(boolean actual) {
        if (actual) {
            return "done = null";
        }
        return "";
    }

    private static String prepFilter(String filter) {
        if (!filter.isEmpty()) {
            return " WHERE " + filter;
        }
        return filter;
    }

    private static String getFilter(boolean actual, String filterBy) {
        StringJoiner sj = new StringJoiner(" AND ");
        String af = actualFilter(actual);
        if (!af.isEmpty()) {
            sj.add(af);
        }
        if (!filterBy.isEmpty()) {
            sj.add(filterBy);
        }
        System.out.println("/".repeat(50) + " filter is: " + prepFilter(sj.toString()));
        return prepFilter(sj.toString());
    }

    public static List<Item> getItemList(boolean actual) {
        String filter = getFilter(actual, "");
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.Item " + filter + " ORDER BY created DESC").list());
    }

    public static List<Item> getItemListByDay(boolean actual) {

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(System.currentTimeMillis());

        GregorianCalendar dayStart = new GregorianCalendar(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH));

        String filter = getFilter(actual, "created >= :date");
        return ModelDB.tx(session -> {
            Query q = session.createQuery("FROM carsale.model.Item " + filter + " ORDER BY created DESC");
            q.setParameter("date", dayStart);
            return q.list();
        });
    }

    public static List<Item> getItemListByPic(boolean actual) {
        String filter = getFilter(actual, "NOT photo = null");
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.Item " + filter + " ORDER BY created DESC").list());
    }

    public static List<Item> getItemListByBrand(boolean actual, CarBrand brand) {
        String filter = getFilter(actual, "brand = :brand");
        return ModelDB.tx(session -> {
            Query q = session.createQuery("FROM carsale.model.Item " + filter + " ORDER BY created DESC");
            q.setParameter("brand", brand);
            return q.list();
        });
    }
}
