package carsale.store;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Storage {

    // HIBERNATE SESSION FACTORY

    private static final Logger logger = LogManager.getLogger(Storage.class);

    private final StandardServiceRegistry registry;
    private final SessionFactory sf;

    private Storage() {
        logger.debug("=== Starting Hibernate initializing ===");
        registry = new StandardServiceRegistryBuilder().configure().build();
        logger.debug("=== Registry is builded ===");
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        logger.debug("=== Session factory is builded ===");
    }

    private static class Holder {
        private static Storage st = new Storage();
    }

    public static Storage getInstance() {
        return Holder.st;
    }

    public SessionFactory getSessionFactory() {
        return sf;
    }

    public ServiceRegistry getServiceRegistry() {
        return registry;
    }
}
