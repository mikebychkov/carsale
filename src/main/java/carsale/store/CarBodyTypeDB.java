package carsale.store;

import carsale.model.CarBodyType;
import org.hibernate.query.Query;

import java.util.List;

public class CarBodyTypeDB {

    private CarBodyTypeDB() {
    }

    public static CarBodyType save(CarBodyType model) {
        ModelDB.tx(session -> session.save(model));
        return model;
    }

    public static CarBodyType update(CarBodyType model) {
        ModelDB.tx(session -> {session.update(model); return 0;});
        return model;
    }

    public static CarBodyType saveOrUpdate(CarBodyType model) {
        ModelDB.tx(session -> {session.saveOrUpdate(model); return 0;});
        return model;
    }

    public static CarBodyType get(int id) {
        return ModelDB.tx(session -> session.get(CarBodyType.class, id));
    }

    public static List<CarBodyType> getByName(String name) {
        return ModelDB.tx(session -> {
            Query q = session.createQuery("FROM carsale.model.CarBodyType WHERE name = ?1");
            q.setParameter(1, name);
            return q.list();
        });
    }

    public static CarBodyType getByNameOrNew(String name) {
        if (name.isEmpty()) {
            return null;
        }
        List<CarBodyType> list = getByName(name);
        if (list.size() > 0) {
            return list.get(0);
        }
        CarBodyType newType = new CarBodyType(name);
        save(newType);
        return newType;
    }

    public static List<CarBodyType> getAll() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.CarBodyType").list());
    }
}
