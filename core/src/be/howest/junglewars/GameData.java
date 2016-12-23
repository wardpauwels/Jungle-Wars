package be.howest.junglewars;

import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.enemy.*;
import be.howest.junglewars.gameobjects.power.*;
import be.howest.junglewars.net.*;
import be.howest.junglewars.spawners.*;
import be.howest.junglewars.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.*;

public class GameData {

    public TextureAtlas atlas;
    private GameState state;
    private int wave;
    private Difficulty difficulty;
    private JWClient client;
    private boolean isClient = false;
    private JWServer server;
    private SpriteBatch batch;
    private Sprite backgroundSprite;

    private Player me;

    private Map<Long, Player> players = new HashMap<>();
    private List<Helper> helpers = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<Missile> missiles = new ArrayList<>();
    private List<Power> powers = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>();

    private SpawnerManager spawnManager;


    //for server
    public GameData(JWServer server) {
        this.server = server;
        this.isClient = false;
        atlas = new TextureAtlas(Assets.IMAGES_ATLAS);
        spawnManager = new SpawnerManager(this);
    }

    //for client
    public GameData(JWClient client) {
        this.isClient = true;
        this.client = client;

        batch = new SpriteBatch();
        atlas = new TextureAtlas(Assets.IMAGES_ATLAS);

        // create full screen background
        backgroundSprite = atlas.createSprite("background/game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public void update(float dt) {
        //client ready and enemy spawned
        if (client != null && me != null) {
            if (me.handleInput(dt)) {
                //TODO udp message to server
                client.sendMessageUDP(me.getPlayerMovementState());
            }
        }

        //update players
        for (Map.Entry<Long, Player> playerEntry : players.entrySet()) {
            playerEntry.getValue().update(dt);
        }

        //update enemies
        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }

        //update missiles
        for (Missile missile : missiles) {
            missile.update(dt);
        }

        //update helper
        for (Helper helper : helpers) {
            helper.update(dt);
        }

        //update powers
        for (Power power : powers) {
            power.update(dt);
        }

        //update currencies
        for (Currency currency : currencies) {
            currency.update(dt);
        }


        //TODO collisions


        if (!isClient) {
            spawnManager.manageAllSpawners();
        }
    }

    public void render() {
        if (!isClient) return;
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        //render player
        for (Map.Entry<Long, Player> playerEntry : players.entrySet()) {
            playerEntry.getValue().render(batch);
        }

        //render enemies
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        //render missiles
        for (Missile missile : missiles) {
            missile.render(batch);
        }

        //render helper
        for (Helper helper : helpers) {
            helper.render(batch);
        }

        //render powers
        for (Power power : powers) {
            power.render(batch);
        }

        //render currencies
        for (Currency currency : currencies) {
            currency.render(batch);
        }

        batch.end();
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }

    public Map<Long, Player> getPlayers() {
        return players;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void onDisconnect() {
        this.client = null;
        this.players.clear();

        System.out.println("GameData::onDisconnect");
    }

    public void onConnect(String name) {
        if (this.me == null) {
            me = new Player("harambe", true, this);
            this.me.setId(client.id);
            this.me.setName(name);
            players.put(client.id, me);

            System.out.println("GameData::onConnect - Connected to " + client.remoteIP);
        }
    }

    public void addPlayer(Network.PlayerJoinLeave msg) {
        Player p = new Player("harambe", false, this);
        p.setId(msg.playerId);
        p.setName(msg.name);
        players.put(msg.playerId, p);
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public void addMissile(Missile missile) {
        missiles.add(missile);
    }

    public Player getPlayerById(long id) {
        return players.get(id);
    }

    public void playerMoved(Network.PlayerMovementState msg) {
        System.out.println("GameData::playerMoved");
        Player p = players.get(msg.playerId);
        if (p != null) p.setPlayerMovementState(msg);

    }

    public void onPlayerShoot(Network.PlayerShoot msg) {
        missiles.add(new Missile(getPlayerById(msg.playerId), 30, 30, msg.position.x, msg.position.y, msg.destination.x, msg.destination.y, "banana", 5, 300, -10, 3, MissileType.STANDARD, this));
    }

    public void removePlayer(Network.PlayerJoinLeave msg) {
        players.remove(msg.playerId);
    }

    public void onPlayerWasHit(Network.PlayerWasHit msg) {
        Player player = players.get(msg.playerId);

    }

    public void clientSendMessage(Object msg) {
        client.sendMessageTCP(msg);
    }

    public void serverSendMessage(Object msg) {
        server.sendMessage(msg);
    }

    public void onWaveEnd(Network.WaveEnd msg) {

    }

    public void onPlayerSpawned(Network.PlayerSpawned msg) {
        Player spawner = players.get(msg.playerId);
        if (spawner != null) {
            spawner.spawn(msg);
        }
    }

    public void onWaveStart(Network.WaveStart msg) {


    }

    public void dispose() {

    }
}
