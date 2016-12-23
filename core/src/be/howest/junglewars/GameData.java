package be.howest.junglewars;

import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.enemy.*;
import be.howest.junglewars.gameobjects.power.*;
import be.howest.junglewars.net.*;
import be.howest.junglewars.spawners.*;
import be.howest.junglewars.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;

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

    private BitmapFont smallFont;
    private BitmapFont bigFont;

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

        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        smallFont = generator.generateFont(parameter);
        parameter.size = 24;
        bigFont = generator.generateFont(parameter);
        generator.dispose();

        // create full screen background
        backgroundSprite = atlas.createSprite("background/game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    public synchronized void update(float dt) {

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
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(dt);

            if(enemies.get(i).shouldRemove()){
                enemies.remove(i);
            }
        }

        //update missiles
        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).update(dt);

            // Remove bullet if needed
            if (missiles.get(i).shouldRemove()) {
                missiles.remove(i);
            }
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
            System.out.println(enemies.size());
        }

    }

    public synchronized void render() {
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
            System.out.println("Enemy ID: " + enemy.getId());
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

        renderHUD();

        batch.end();
    }

    private synchronized void renderHUD() {
        for (Map.Entry<Long, Player> p : players.entrySet()) {
            Player player = p.getValue();
            player.render(batch);
            player.getHelper().render(batch);

            // TODO: work with LibGDX Actors instead?
            bigFont.setColor(0, 0, 0, 1);
            bigFont.draw(batch, "Player 1", 20, Gdx.graphics.getHeight() - 20);
            smallFont.draw(batch, "Name: " + player.getName(), 20, Gdx.graphics.getHeight() - 40);
            smallFont.draw(batch, "Score: " + player.getScore(), 20, Gdx.graphics.getHeight() - 60);
            smallFont.draw(batch, "Wave: " + player.getWave(), 20, Gdx.graphics.getHeight() - 80);
            smallFont.draw(batch, "XP: " + player.getXp(), 20, Gdx.graphics.getHeight() - 100); // TODO: xp till next wave
            smallFont.draw(batch, "Coins collected: " + player.getCollectedCoins(), 20, Gdx.graphics.getHeight() - 120);
            smallFont.draw(batch, "Hitpoints: " + player.getHitpoints(), 20, Gdx.graphics.getHeight() - 140);
            smallFont.draw(batch, "ACTIVE POWERS: ", 300, Gdx.graphics.getHeight() - 20);
            for (int i = 0; i < player.getPowers().size(); i++) {
                smallFont.draw(batch, player.getPowers().get(i).toString() + " [" + player.getPowers().get(i).getTimeLeft() + " seconds left]", 300, Gdx.graphics.getHeight() - 20 * (i + 2));
            }
            smallFont.draw(batch, "ATTACK SPEED: " + player.getAttackSpeed(), 20, 40);
            smallFont.draw(batch, "DAMAGE: " + player.getDamage(), 20, 60);
            smallFont.draw(batch, "MOVEMENT SPEED: " + player.getSpeed(), 20, 80);
            smallFont.draw(batch, "MISSLE SPEED: " + player.getMissleSpeed(), 20, 100);
        }
    }

    public GameState getState() {
        return state;
    }

    public synchronized void setState(GameState state) {
        this.state = state;
    }

    public int getWave() {
        return wave;
    }

    public synchronized void setWave(int wave) {
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

    public synchronized void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public synchronized void onDisconnect() {
        this.client = null;
        this.players.clear();

        System.out.println("GameData::onDisconnect");
    }

    public synchronized void onConnect(String name) {
        if (this.me == null) {
            me = new Player("harambe", true, this);
            this.me.setId(client.id);
            this.me.setName(name);
            players.put(client.id, me);

            System.out.println("GameData::onConnect - Connected to " + client.remoteIP);
        }
    }

    public synchronized void addPlayer(Network.PlayerJoinLeave msg) {
        Player p = new Player("harambe", false, this);
        p.setId(msg.playerId);
        p.setName(msg.name);
        players.put(msg.playerId, p);
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public Player getPlayerById(long id) {
        return players.get(id);
    }

    public Enemy getEnemyById(int id) {
        return enemies.get(id);
    }

    public synchronized void playerMoved(Network.PlayerMovementState msg) {
        Player p = players.get(msg.playerId);
        if (p != null) p.setPlayerMovementState(msg);

    }

    public synchronized void onPlayerShoot(Network.PlayerShoot msg) {
        missiles.add(new Missile(getPlayerById(msg.playerId), 30, 30, msg.position.x, msg.position.y, msg.destination.x, msg.destination.y, "banana", 5, 300, -10, 3, MissileType.STANDARD, this));
        getPlayerById(msg.playerId).setPlayerShoot(msg);
    }

    public synchronized void onEnemySpawn(Network.EnemySpawned msg) {
//        enemies.add(new Enemy(msg.id, this, msg.enemy));
        addEnemy(msg.id, msg.enemy);
    }

    public synchronized void removePlayer(Network.PlayerJoinLeave msg) {
        players.remove(msg.playerId);
    }

    public synchronized void onPlayerWasHit(Network.PlayerWasHit msg) {
        Player player = players.get(msg.playerId);

    }

    public synchronized void addEnemy(long id, EnemyEntity entity){
        Enemy e = new Enemy(id, this, entity);
        enemies.add(e);
        if(!isClient) {
            server.sendMessage(new Network.EnemySpawned(id, entity));
        }
    }

    public void clientSendMessage(Object msg) {
        client.sendMessageTCP(msg);
    }

    public void serverSendMessage(Object msg) {
        server.sendMessage(msg);
    }

    public synchronized void onWaveEnd(Network.WaveEnd msg) {

    }

    public synchronized void onPlayerSpawned(Network.PlayerSpawned msg) {
        Player spawner = players.get(msg.playerId);
        if (spawner != null) {
            spawner.spawn(msg);
        }
    }

    public synchronized void enemyMoved(Network.EnemyMovementState msg) {
        Enemy e = enemies.get((int) msg.id);
        if (e != null) e.setEnemyMovenentState(msg);
    }

    public void onWaveStart(Network.WaveStart msg) {


    }

    public void dispose() {

    }
}
