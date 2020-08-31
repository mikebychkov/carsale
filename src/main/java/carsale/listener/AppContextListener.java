package carsale.listener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import carsale.store.Storage;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("=== contextInitialized === BEGIN ===");
        System.out.println(Storage.getInstance());
        logger.info("=== contextInitialized === END ===");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("=== contextDestroyed === BEGIN ===");
        Storage.getInstance().getSessionFactory().close();
        Storage.getInstance().getServiceRegistry().close();
        logger.info("=== contextDestroyed === END ===");
    }
}
