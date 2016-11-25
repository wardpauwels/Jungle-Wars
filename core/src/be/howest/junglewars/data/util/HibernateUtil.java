package be.howest.junglewars.data.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static SessionFactory sf;

    public static Session createSession() {
        Logger log1 = Logger.getLogger("org.hibernate");
        log1.setLevel(Level.WARNING);
        Logger log2 = Logger.getLogger("com.mchange.v2.c3p0");
        log2.setLevel(Level.WARNING);
        Logger log3 = Logger.getLogger("com.mchange.v2.log.MLog");
        log3.setLevel(Level.WARNING);

        if (sf == null) {
            sf = new Configuration().configure(new File("../src/hibernate.cfg.xml")).buildSessionFactory();
        }

        return sf.openSession();
    }

}