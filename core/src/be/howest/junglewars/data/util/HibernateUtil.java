package be.howest.junglewars.data.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static SessionFactory sf;

    public static Session createSession() {
        Logger log = Logger.getLogger("org.hibernate");
        log.setLevel(Level.WARNING);

        if (sf == null) {
            sf = new Configuration().configure().buildSessionFactory();
        }

        return sf.openSession();
    }

}