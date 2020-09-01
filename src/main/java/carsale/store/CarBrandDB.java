package carsale.store;

import carsale.model.CarBrand;

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

    public static List<CarBrand> getAll() {
        return ModelDB.tx(session -> session.createQuery("FROM carsale.model.CarBrand").list());
    }
}