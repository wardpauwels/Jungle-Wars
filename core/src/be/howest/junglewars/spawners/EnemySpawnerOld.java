package be.howest.junglewars.spawners;

import be.howest.junglewars.gameobjects.enemy.ChooseTargetType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.EnemyActionType;
import com.badlogic.gdx.Gdx;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class EnemySpawnerOld implements ISpawner {

    private SpawnerManager manager;

    private int secondsBetweenSpawn;
    private float spawnTimer;

    private int totalEnemiesToSpawn;
    private int totalEnemiesSpawned;
    private boolean doneWithSpawning;

    //    private List<Pair<EnemyEntity, Double>> cachedEnemies;
//    private List<Pair<EnemyEntity, Double>> enemies;
    private List<Pair<Enemy, Double>> cachedEnemies;
    private List<Pair<Enemy, Double>> enemies;
    //    private EnumeratedDistribution<EnemyEntity> generator;
    private EnumeratedDistribution<Enemy> generator;

    public EnemySpawnerOld(SpawnerManager manager) {
        this.manager = manager;

        cachedEnemies = new ArrayList<>();
//        for (EnemyEntity entity : EnemyDao.getAllEnemies()) {
//            cachedEnemies.add(new Pair<>(entity, (double) entity.getSpawnProbability()));
//        }
        cachedEnemies.add(new Pair<>(
                new Enemy(manager.getData(), "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, ChooseTargetType.NEAREST_PLAYER, EnemyActionType.STABBING),
                0.1d
        ));
        cachedEnemies.add(new Pair<>(
                new Enemy(manager.getData(), "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, ChooseTargetType.NEAREST_PLAYER, EnemyActionType.SHOOTING),
                0.3d
        ));

        enemies = new ArrayList<>();
//        for (EnemyEntity entity : EnemyDao.getAllEnemies()) {
//            cachedEnemies.add(new Pair<>(entity, (double) entity.getSpawnProbability()));
//        }
//        enemies.add(new Pair<>(
//                new Enemy(manager.getData(), "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, ChooseTargetType.NEAREST_PLAYER, EnemyActionType.STABBING),
//                1d
//        ));
//        enemies.add(new Pair<>(
//                new Enemy(manager.getData(), "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5, ChooseTargetType.NEAREST_PLAYER, ChooseTargetType.NEAREST_PLAYER, EnemyActionType.SHOOTING),
//                2d
//        ));

        enemies = new ArrayList<>();

        generator = new EnumeratedDistribution<>(enemies);

        reset();
    }

    @Override
    public void reset() {
        spawnTimer = 0;
        secondsBetweenSpawn = 1; // TODO: calculate with game level and difficulty (- level/100
        doneWithSpawning = false; // TODO: set on false if all enemies are spawned
        totalEnemiesToSpawn = 10; // TODO: calculate with game level and difficulty
        totalEnemiesSpawned = 0;

    }

    public void updateStats(Enemy enemy) {
        // TODO: update stats with game difficulty and game level
    }

    @Override
    public void spawnNext() {
        if (totalEnemiesSpawned >= totalEnemiesToSpawn) {
            doneWithSpawning = true;
            return;
        }

        enemies.clear();
        enemies = new ArrayList<>(cachedEnemies);

        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer > secondsBetweenSpawn) {
            manager.getData().getEnemies().add(generator.sample());
            totalEnemiesSpawned++;
            spawnTimer = 0;
        }

//        manager.getData().getEnemies().add(
//                new Enemy();
//        );
    }

    public boolean isDoneWithSpawning() {
        return doneWithSpawning;
    }
}
