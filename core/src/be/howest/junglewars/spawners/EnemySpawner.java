package be.howest.junglewars.spawners;

import be.howest.junglewars.data.dao.EnemyDao;
import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class EnemySpawner implements ISpawner {

    private int totalEnemiesToSpawn;
    private boolean doneWithSpawning;

    private List<Pair<EnemyEntity, Double>> cachedEnemies;
    private List<Pair<EnemyEntity, Double>> enemies;
    private EnumeratedDistribution<EnemyEntity> generator;

    public EnemySpawner(SpawnerManager manager) {
        cachedEnemies = new ArrayList<>();
        for (EnemyEntity entity : EnemyDao.getAllEnemies()) {
            cachedEnemies.add(new Pair<>(entity, (double) entity.getSpawnProbability()));
        }

        reset();
    }

    @Override
    public void reset() {
//        secondsBetweenSpawn = 0; // TODO: calculate with game level and difficulty (- level/100
//        doneWithSpawning = false; // TODO: set on false if all enemies are spawned
//        totalEnemiesToSpawn = 0; // TODO: calculate with game level and difficulty
//        enemies = new ArrayList<>();
//        enemies.addAll(cachedEnemies);
//        generator = new EnumeratedDistribution<>(enemies);
    }

    public void updateStats(Enemy enemy) {
        // TODO: update stats with game difficulty and game level
    }

    @Override
    public void spawnNext() {
//        manager.getData().getEnemies().add(
//                new Enemy();
//        );
    }

    public boolean isDoneWithSpawning() {
        return doneWithSpawning;
    }
}
