package be.howest.junglewars.spawners;

import be.howest.junglewars.data.entities.*;
import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.*;

import java.util.concurrent.*;
import java.util.stream.*;

public class EnemySpawner implements ISpawner {

    private SpawnerManager manager;

    private float secondsBetweenSpawn;
    private float spawnTimer;

    private boolean doneWithSpawning;

    private int totalEnemiesSpawned;
    private int totalEnemiesToSpawn;

    // TODO should be database enemies or enemies should be clonable
    private EnemyEntity[] enemies;
    private int[] probabilities;
    private int totalProbability;

    public EnemySpawner(SpawnerManager manager) {
        this.manager = manager;

        // TODO: get from DB
        enemies = new EnemyEntity[2]; // TODO: length is size of resultset of DB

        enemies[0] = new EnemyEntity();
        enemies[0].setName("STABBER");
        enemies[0].setDefaultSpriteUrl("zookeeper");
        enemies[0].setBaseDamage(5);
        enemies[0].setBaseSpeed(150);
        enemies[0].setBaseHitpoints(15);
        enemies[0].setBaseAttackSpeed(1.5f);
        enemies[0].setExperienceWhenKilled(10);
        enemies[0].setScoreWhenKilled(15);
        enemies[0].setSpawnProbability(20);
        enemies[0].setMovementType("NEAREST_PLAYER");
        enemies[0].setTargetSelectionType("NEAREST_PLAYER");
        enemies[0].setAttackType("STABBING");

        enemies[1] = new EnemyEntity();
        enemies[1].setName("SHOOTER");
        enemies[1].setDefaultSpriteUrl("zookeeper");
        enemies[1].setBaseDamage(5);
        enemies[1].setBaseSpeed(150);
        enemies[1].setBaseHitpoints(15);
        enemies[1].setBaseAttackSpeed(1.5f);
        enemies[1].setExperienceWhenKilled(10);
        enemies[1].setScoreWhenKilled(15);
        enemies[1].setSpawnProbability(50);
        enemies[1].setMovementType("NEAREST_PLAYER");
        enemies[1].setTargetSelectionType("NEAREST_PLAYER");
        enemies[1].setAttackType("SHOOTING");

        probabilities = new int[enemies.length];
        for (int i = 0; i < probabilities.length; i++) {
            probabilities[i] = enemies[i].getSpawnProbability();
        }
        totalProbability = IntStream.of(probabilities).sum();

        reset();
    }

    @Override
    public void reset() { // TODO: when next wave begins
        totalEnemiesSpawned = 0;
        totalEnemiesToSpawn = 10; // TODO: calculate with game level and difficulty
        secondsBetweenSpawn = 1; // TODO: calculate with game level and difficulty (- level/100)
        spawnTimer = 0;
        doneWithSpawning = false; // TODO: set on false if all enemies are spawned
    }

    @Override
    public void spawnNext() {
        if (totalEnemiesSpawned >= totalEnemiesToSpawn) {
            doneWithSpawning = true;
            return;
        }

        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer > secondsBetweenSpawn) {
            spawn();
            totalEnemiesSpawned++;
            spawnTimer = 0;
        }
    }

    public void updateStats(Enemy enemy) {
        // TODO: update stats with game difficulty and game level
    }

    private void spawn() {
        int random = ThreadLocalRandom.current().nextInt(1, totalProbability + 1);
        for (EnemyEntity e : enemies) {
            random -= e.getSpawnProbability();
            if (random <= 0) {
                manager.getData().getEnemies().add(new Enemy(manager.getData(), e));
                System.out.println(e.getName());
                break;
            }
        }
    }

    public boolean isDoneWithSpawning() {
        return doneWithSpawning;
    }
}
