package carsale.store;

import carsale.model.CarBrand;
import org.hibernate.query.Query;

import java.util.List;

public class CarBrandDB {

    private CarBrandDB() {
    }

    public static CarBrand save(CarBrand model) {
        ModelDB.exec(session -> session.save(model));
        return model;
    }

    public static CarBrand update(CarBrand model) {
        ModelDB.exec(session -> session.update(model));
        return model;
    }

    public static CarBrand saveOrUpdate(CarBrand model) {
        ModelDB.exec(session -> session.saveOrUpdate(model));
        return model;
    }

    public static CarBrand get(int id) {
        return ModelDB.execGet(session -> session.get(CarBrand.class, id));
    }

    public static List<CarBrand> getByName(String name) {
        return ModelDB.execGet(session -> {
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
        return ModelDB.execGet(session -> session.createQuery("FROM carsale.model.CarBrand").list());
    }

    public static List<String> getAllNameList() {
        return ModelDB.execGet(session -> session.createQuery("SELECT name FROM carsale.model.CarBrand").list());
    }
}
