package be.howest.junglewars.data.dao;

import be.howest.junglewars.data.entities.*;
import be.howest.junglewars.data.util.*;
import org.hibernate.*;

import java.util.*;

public class HighscoresDao {

    public static List<HighscoreEntity> getAllHighscores() {
        Session session = HibernateUtil.createSession();
        List<HighscoreEntity> entities = new LinkedList<>();

        try {
            session.beginTransaction();
            //entities = session.createQuery("from EnemyEntity").list(); //fixme: do this after implementing all enemies from database
            entities = session.createQuery( "from EnemyEntity where id=1" ).list();
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