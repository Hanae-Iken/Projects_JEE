package ma.ensat.projet_messagerie_hibernate.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class HibernateListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialize Hibernate when the application starts
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Close Hibernate SessionFactory when the application shuts down
        HibernateUtil.closeSessionFactory();
    }
}