package be.howest.junglewars.data.dao;

import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.data.util.HibernateUtil;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EnemyDao {

    public static List<EnemyEntity> getAllEnemies() {
        Session session = HibernateUtil.createSession();
        List<EnemyEntity> entities = new LinkedList<>();

        try {
            session.beginTransaction();
            //entities = session.createQuery("from EnemyEntity").list(); //fixme: do this after implementing all enemies from database
            entities = session.createQuery("from EnemyEntity where id=1").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

        return entities;
    }

}
