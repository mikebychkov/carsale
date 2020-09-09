package carsale.store;

import carsale.model.CarModel;
import org.hibernate.query.Query;

import java.util.List;

public class CarModelDB {

    private CarModelDB() {
    }

    public static CarModel save(CarModel model) {
        ModelDB.exec(session -> session.save(model));
        return model;
    }

    public static CarModel update(CarModel model) {
        ModelDB.exec(session -> session.update(model));
        return model;
    }

    public static CarModel saveOrUpdate(CarModel model) {
        ModelDB.exec(session -> session.saveOrUpdate(model));
        return model;
    }

    public static CarModel get(int id) {
        return ModelDB.execGet(session -> session.get(CarModel.class, id));
    }

    public static List<CarModel> getByName(String name) {
        return ModelDB.execGet(session -> {
            Query q = session.createQuery("FROM carsale.model.CarModel WHERE name = ?1");
            q.setParameter(1, name);
            return q.list();
        });
    }

    public static CarModel getByNameOrNew(String name) {
        if (name.isEmpty()) {
            return null;
        }
        List<CarModel> list = getByName(name);
        if (list.size() > 0) {
            return list.get(0);
        }
        CarModel newModel = new CarModel(name);
        save(newModel);
        return newModel;
    }

    public static List<CarModel> getAll() {
        return ModelDB.execGet(session -> session.createQuery("FROM carsale.model.CarModel").list());
    }
}
