package carsale.store;

import carsale.model.CarModel;
import org.hibernate.query.Query;

import java.util.List;

public class CarModelDB {

    private CarModelDB() {
    }

    public static CarModel save(CarModel model) {
        ModelDB.tx(session -> session.save(model));
        return model;
    }

    public static CarModel update(CarModel model) {
        ModelDB.tx(session -> {session.update(model); return 0;});
        return model;
    }

    public static CarModel saveOrUpdate(CarModel model) {
        ModelDB.tx(session -> {session.saveOrUpdate(model); return 0;});
        return model;
    }

    public static CarModel get(int id) {
        return ModelDB.tx(session -> session.get(CarModel.class, id));
    }

    public static List<CarModel> getByName(String name) {
        return ModelDB.tx(session -> {
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
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.CarModel").list());
    }
}
