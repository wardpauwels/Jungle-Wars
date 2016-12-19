package be.howest.junglewars.spawners;

import be.howest.junglewars.GameData;

import java.util.HashSet;
import java.util.Set;

public class SpawnerManager {

    private GameData data;

    private int currentWave;

    private Set<ISpawner> spawners;
    private EnemySpawner enemySpawner;
    private PowerSpawner powerSpawner;
    private CurrencySpawner currencySpawner;

    public SpawnerManager(GameData data) {
        this.data = data;
        this.currentWave = data.getWave();

        spawners = new HashSet<>();
        enemySpawner = new EnemySpawner(this);
        spawners.add(enemySpawner);
        powerSpawner = new PowerSpawner(this);
//        spawners.add(powerSpawner);
        currencySpawner = new CurrencySpawner(this);
//        spawners.add(currencySpawner);
    }

    public void manageAllSpawners() {
        if (data.getWave() > currentWave) {
            currentWave = data.getWave();
            for (ISpawner spawner : spawners) {
                spawner.reset();
            }
            return;
        }

        for (ISpawner spawner : spawners) {
            spawner.spawnNext();
        }
    }

    public GameData getData() {
        return data;
    }
}
