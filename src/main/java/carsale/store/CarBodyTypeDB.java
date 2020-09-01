package carsale.store;

import carsale.model.CarBodyType;

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

    public static List<CarBodyType> getAll() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.CarBodyType").list());
    }
}
