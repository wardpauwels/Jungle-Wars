package be.howest.junglewars.spawners;

import be.howest.junglewars.gameobjects.enemy.ChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.EnemyActionType;

import java.util.LinkedHashMap;

public class EnemySpawner implements ISpawner {

    private SpawnerManager manager;

    private float secondsBetweenSpawn;
    private float spawnTimer;

    private LinkedHashMap<Enemy, Double> enemies;

    public EnemySpawner(SpawnerManager manager) {
        this.manager = manager;

        enemies = new LinkedHashMap<>();
        enemies.put(
                new Enemy(manager.getData(), "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, ChooseTargetType.NEAREST_PLAYER, EnemyActionType.STABBING),
                0.1d
        );
        enemies.put(
                new Enemy(manager.getData(), "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, ChooseTargetType.NEAREST_PLAYER, EnemyActionType.SHOOTING),
                0.5d
        );

        double totalProbability = 0;
        for (double d : enemies.values()) {
            totalProbability += d;
        }

        enemies.get

        reset();
    }

    @Override
    public void reset() {

    }

    @Override
    public void spawnNext() {

    }
}
