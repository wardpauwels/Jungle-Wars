package be.howest.junglewars.screens;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.JungleWars;
import be.howest.junglewars.gameobjects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.List;

// TODO: check and implement https://github.com/libgdx/libgdx/wiki/Collections
public class GameScreen extends ScreenAdapter {

    private JungleWars game;

    private GameState gameState;

    private int level;
    private Difficulty difficulty;

    private List<Player> players;
    private List<Helper> helpers;
    private List<Enemy> enemies;
    private List<Power> powers;
    private List<Currency> currencies;

    private int startingEnemies;
    private float mulitplierEnemies;
    private float amountEnemies;
    private float timeBetweenEnemySpawn;
    private float timeLastEnemySpawn;

    private Sprite backgroundSprite;
    public TextureAtlas atlas;

    private BitmapFont smallFont;
    private BitmapFont bigFont;

    enum GameState {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER, // TODO: if (all) player(s) is/are dead
        BETWEEN_WAVE; // TODO: if all enemies are dead
    }

    public GameScreen(JungleWars game, int level, Difficulty difficulty) {
        this.game = game;
        this.level = level;
        this.difficulty = difficulty;
        this.atlas = game.atlas;

        gameState = GameState.READY;

        players = new ArrayList<>();
        helpers = new ArrayList<>();
        enemies = new ArrayList<>();
        powers = new ArrayList<>();
        currencies = new ArrayList<>();

        // create full screen background
        backgroundSprite = atlas.createSprite("background/game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // TODO: https://github.com/libgdx/libgdx/wiki/Managing-your-assets#loading-a-ttf-using-the-assethandler
        // TODO: work with Actors for GUI layout (buttons, menu, etc...)?
        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        smallFont = generator.generateFont(parameter);
        parameter.size = 24;
        bigFont = generator.generateFont(parameter);
        generator.dispose();

        players.add(new Player(this, "John", 80, 70, "harambe"));

        startingEnemies = 1;
        mulitplierEnemies = 0.5f;
        spawnEnemies(false);
    }

    // TODO: clean up the update and render methods
    public void update(float dt) {
        if (dt > .02f) dt = .02f;

        switch (gameState) {
            case READY:
                updateReady(dt);
                break;
            case RUNNING:
                updateRunning(dt);
                break;
            case GAME_OVER:
                updateGameOver(dt);
                break;
        }
    }

    private void checkCollisions() {
        for (Player player : players) {
            for (Currency currency : player.checkCollision(currencies)) {
                currency.collectedBy(player);
            }
            for (Power power : player.checkCollision(powers)) {
                power.collectedBy(player);
            }
            for (Enemy enemy : enemies) {
                for (Missile missile : player.checkCollision(enemy.getMissiles())) {
                    // TODO: player hit by missile
                }
            }
        }

        for (Enemy enemy : enemies) {
            for (Player player : players) {
                for (Missile missile : enemy.checkCollision(player.getMissiles())) {
                    enemy.hitBy(missile, player);
                }
            }
        }

    }

    private void updateReady(float dt) {
        // TODO: show some message like "press any key to start"
//        if (Gdx.input.isTouched()) {
            gameState = GameState.RUNNING;
//        }
    }

    private void updateRunning(float dt) {
        spawnEnemies(true);
        spawnCurrencies();
        spawnPowers();

        for (Player player : players) {
            player.update(dt);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(dt);
            if (enemies.get(i).shouldRemove()) {
                enemies.remove(i);
                i--;
            }
        }

        for (int i = 0; i < currencies.size(); i++) {
            currencies.get(i).update(dt);
            if (currencies.get(i).shouldRemove()) {
                currencies.remove(i);
                i--;
            }
        }

        for (int i = 0; i < powers.size(); i++) {
            powers.get(i).update(dt);
            if (powers.get(i).shouldRemove()) {
                powers.remove(i);
                i--;
            }
        }

        checkCollisions();

    }

    private void spawnEnemies(boolean nextLevel) {
        if (enemies.size() == 0) {
            amountEnemies = startingEnemies + (startingEnemies * (mulitplierEnemies * level));
            for (int i = 0; i < amountEnemies; i++) {
                enemies.add(new Enemy(this, "Zookeeper", "zookeeper", 80, 70, 5, 150, 15, 2, 10, 15, 5));
            }
            if (nextLevel) level++;
        }
    }

    private void spawnCurrencies() {
        int maxCurrenciesOnField = 2;
        if (currencies.size() < maxCurrenciesOnField)
            currencies.add(new Currency(this, 30, 30, 5, "coin"));
    }

    private void spawnPowers() {
        int maxPowersOnField = 2;
        if (powers.size() < maxPowersOnField)
            powers.add(new Power(this, "Double Damage", 30, 30, "power-up", 5, 10, true));
    }

    private void updateGameOver(float dt) {

    }

    // TODO: different render methods
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        SpriteBatch batch = game.batch;
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        update(delta);

        // if all players have 0 hitpoints => game over
        boolean isGameOver = true;
        for (Player p : players) {
            if (p.getHitpoints() >= 0) {
                isGameOver = false;
                break;
            }
        }

        if (isGameOver) {
            gameState = GameState.GAME_OVER;
            return;
        }


        for (Player player : players) {
            player.draw(batch);
            player.getHelper().draw(batch);

            // TODO: work with LibGDX Actors instead?
            bigFont.setColor(0, 0, 0, 1);
            bigFont.draw(batch, "Player 1", 20, Gdx.graphics.getHeight() - 20);
            smallFont.draw(batch, "Name: " + player.getName(), 20, Gdx.graphics.getHeight() - 40);
            smallFont.draw(batch, "Score: " + player.getScore(), 20, Gdx.graphics.getHeight() - 60);
            smallFont.draw(batch, "Level: " + player.getLevel(), 20, Gdx.graphics.getHeight() - 80);
            smallFont.draw(batch, "XP: " + player.getXp(), 20, Gdx.graphics.getHeight() - 100); // TODO: xp till next level
            smallFont.draw(batch, "Coins collected: " + player.getCollectedCoins(), 20, Gdx.graphics.getHeight() - 120);
            smallFont.draw(batch, "Hitpoints: " + player.getHitpoints(), 20, Gdx.graphics.getHeight() - 140);
            smallFont.draw(batch, "ACTIVE POWERS: ", 300, Gdx.graphics.getHeight() - 20);
            for (int i = 0; i < player.getPowers().size(); i++) {
                smallFont.draw(batch, player.getPowers().get(i).getName() + " [" + player.getPowers().get(i).getTimeLeft() + " seconds left]", 300, Gdx.graphics.getHeight() - 20 * (i+2));
            }
        }

        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        for (Currency currency : currencies) {
            currency.draw(batch);
        }

        for (Power power : powers) {
            power.draw(batch);
        }

        bigFont.draw(batch, "LEVEL " + level, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    @Override
    public void dispose() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Helper> getHelpers() {
        return helpers;
    }

    public void setHelpers(List<Helper> helpers) {
        this.helpers = helpers;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}