package be.howest.junglewars.data.dao;

import be.howest.junglewars.data.entities.EnemyEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EnemyDao {

    public static Session createSession() {
        Configuration conf = new Configuration().configure(new File("core/src/hibernate.cfg.xml"));
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sf = conf.buildSessionFactory(sr);
        return sf.openSession();
    }

    public static void main(String[] args) {
        List<EnemyEntity> entities = EnemyDao.getAllEnemies();
        entities.forEach(System.out::println);
    }

    public static List<EnemyEntity> getAllEnemies() {
        List<EnemyEntity> enemies = new ArrayList<>();

        Session session = createSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List entities = session.createQuery("from EnemyEntity").list();
            for (Object entity : entities) {
                enemies.add((EnemyEntity) entity);
                tx.commit();
            }
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }

        return enemies;
    }

}
