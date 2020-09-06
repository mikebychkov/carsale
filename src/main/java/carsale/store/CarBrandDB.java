package carsale.store;

import carsale.model.CarBrand;
import org.hibernate.query.Query;

import java.util.List;

public class CarBrandDB {

    private CarBrandDB() {
    }

    public static CarBrand save(CarBrand model) {
        ModelDB.tx(session -> session.save(model));
        return model;
    }

    public static CarBrand update(CarBrand model) {
        ModelDB.tx(session -> {session.update(model); return 0;});
        return model;
    }

    public static CarBrand saveOrUpdate(CarBrand model) {
        ModelDB.tx(session -> {session.saveOrUpdate(model); return 0;});
        return model;
    }

    public static CarBrand get(int id) {
        return ModelDB.tx(session -> session.get(CarBrand.class, id));
    }

    public static List<CarBrand> getByName(String name) {
        return ModelDB.tx(session -> {
            Query q = session.createQuery("FROM carsale.model.CarBrand WHERE name = ?1");
            q.setParameter(1, name);
            return q.list();
        });
    }

    public static CarBrand getByNameOrNew(String name) {
        if (name.isEmpty()) {
            return null;
        }
        List<CarBrand> list = getByName(name);
        if (list.size() > 0) {
            return list.get(0);
        }
        CarBrand newBrand = new CarBrand(name);
        save(newBrand);
        return newBrand;
    }

    public static List<CarBrand> getAll() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.CarBrand").list());
    }

    public static List<String> getAllNameList() {
        return ModelDB.tx(session -> session.createQuery("SELECT name FROM carsale.model.CarBrand").list());
    }
}
