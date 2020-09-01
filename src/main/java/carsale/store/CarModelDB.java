package carsale.store;

import carsale.model.CarModel;

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

    public static List<CarModel> getAll() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.CarModel").list());
    }
}
