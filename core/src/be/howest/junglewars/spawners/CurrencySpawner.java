package be.howest.junglewars.spawners;

import be.howest.junglewars.data.entities.CurrencyEntity;
import be.howest.junglewars.gameobjects.Currency;

public class CurrencySpawner implements ISpawner {

    private long currentId = 0;

    private SpawnerManager manager;

    private float secondsBetweenSpawn;
    private float spawnTimer;

    // TODO: should be database
    private CurrencyEntity[] currencies;

    public CurrencySpawner(SpawnerManager manager) {
        this.manager = manager;

        currencies = new CurrencyEntity[1]; // TODO: length is size of resultset of DB

        currencies[0] = new CurrencyEntity();
//        currencies[0].
    }

    @Override
    public void reset() {

    }

    @Override
    public void spawnNext() {

    }
}
