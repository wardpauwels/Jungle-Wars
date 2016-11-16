package be.howest.junglewars.data;

import be.howest.junglewars.entities.EnemyEntity;

import java.util.List;

public abstract class DB {

    private static DB db = new JsonDB();

    public static DB getDB() {
        return db;
    }

    public abstract List<EnemyEntity> getAllEnemies();

}
