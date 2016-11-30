package be.howest.junglewars.screens;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.GameState;
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

import java.util.List;

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

    private SpriteBatch batch;

    private BitmapFont smallFont;
    private BitmapFont bigFont;

    public GameScreen(JungleWars game, int level, Difficulty difficulty) {
        this.game = game;
        this.level = level;
        this.difficulty = difficulty;

        gameState = GameState.READY;

        // create full screen background
        // TODO: change backgrounds.atlas to a "gamescreen-assets.atlas" or something... (or one assets atlas for all screens in JungleWars.java?)
        backgroundSprite = new TextureAtlas("atlas/backgrounds.atlas").createSprite("game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // TODO: https://github.com/libgdx/libgdx/wiki/Managing-your-assets#loading-a-ttf-using-the-assethandler
        // Fonts
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        smallFont = generator.generateFont(parameter);
        parameter.size = 24;
        bigFont = generator.generateFont(parameter);
        generator.dispose();

    }

    public void update(float dt) {
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

    private void updateReady(float dt) {
        // TODO: show some message like "press any key to start"
        gameState = GameState.RUNNING;
    }

    private void updateRunning(float dt) {
        for (Player player : players) {
            player.update(dt);
        }

        for (Enemy enemy : enemies) {
            enemy.update(dt);
        }

        // TODO: currencySpawner.generateCurrencies() + update currencies

        // TODO: powerSpawner.generatePowers() + update powers

    }

    private void updateGameOver(float dt) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

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

        update(delta);

        SpriteBatch batch = game.batch;
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        for (Player player : players) {
            player.draw(batch);
            player.getHelper().draw(batch);

            bigFont.setColor(0, 0, 0, 1);
            bigFont.draw(batch, "Player 1", 20, Gdx.graphics.getHeight() - 20);
            smallFont.draw(batch, "Name: " + player.getName(), 20, Gdx.graphics.getHeight() - 40);
            smallFont.draw(batch, "Score: " + player.getScore(), 20, Gdx.graphics.getHeight() - 60);
            smallFont.draw(batch, "Hitpoints: " + player.getHitpoints(), 20, Gdx.graphics.getHeight() - 80);
        }

        // TODO: draw currencies

        // TODO: draw powers

        bigFont.draw(batch, "LEVEL " + level, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    @Override
    public void pause() {
//        if (gameState == be.howest.junglewars.GameState.RUNNING) gameState = be.howest.junglewars.GameState.PAUSED;
    }

    @Override
    public void resume() {
//        if (gameState == be.howest.junglewars.GameState.PAUSED) gameState = be.howest.junglewars.GameState.RUNNING;
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