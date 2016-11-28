package be.howest.junglewars.screens;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.JungleWarsGame;
import be.howest.junglewars.gameobjects.currency.Currency;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.EnemySpawner;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.gameobjects.power.Power;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenAdapter {

    private JungleWarsGame game;
    private GameState gameState;
    private int level;
    private Difficulty difficulty;

    private ArrayList<Player> players;
    private ArrayList<Missile> playerMissiles;
    private ArrayList<Enemy> enemies;
    private ArrayList<Missile> enemyMissiles;
    private ArrayList<Power> powers;
    private ArrayList<Currency> currencies;

    private EnemySpawner enemySpawner;

    private Sprite backgroundSprite;

    private FreeTypeFontGenerator fontGenerator;
    private BitmapFont smallFont;
    private BitmapFont bigFont;

    public GameScreen(JungleWarsGame game) {
        this.game = game;
        this.level = game.getGameLevel();
        this.difficulty = game.getGameDifficulty();

        gameState = GameState.READY;

        backgroundSprite = game.getBgAtlas().createSprite("game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Fonts
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 20;
//        smallFont = generator.generateFont(parameter);
//        parameter.size = 24;
//        bigFont = generator.generateFont(parameter);
//        generator.dispose();

        enemySpawner = new EnemySpawner(level, difficulty);

        // Players
        players = new ArrayList<>();
        playerMissiles = new ArrayList<>();
        players.add(new Player(this, "John", 80, 80, "harambe", 6));

        // Enemies
        enemies = new ArrayList<>();
        enemyMissiles = new ArrayList<>();

        powers = new ArrayList<>();
        currencies = new ArrayList<>();
    }

    public void update(float dt) {
        switch (gameState) {
            case READY:
                updateReady(dt);
                break;
            case RUNNING:
                updateRunning(dt);
                break;
        }
    }

    private void updateReady(float dt) {
        // TODO: show some message like "press any key to start"
        gameState = GameState.RUNNING;
    }

    private void updateRunning(float dt) {
        // check all objects shouldremoves()

        checkCollision();

        for (Player player : players) {
            player.update(dt);
            player.getHelper().update(dt);
        }

        enemySpawner.generateEnemies();

//        for (Player player : players) {
//            player.update(dt);
//            player.getHelper().update(dt);
//            for (Missile missile : player.getMissiles()) {
//                missile.update(dt);
//            }
//            for (HelperMissile missile : player.getHelper().getMissiles()) {
//                missile.update(dt);
//            }
//        }
//
//        for (int i = 0; i < enemies.size(); i++) {
//            enemies.get(i).update(dt);
//        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        update(delta);

        SpriteBatch batch = game.getBatch();
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        for (Player player : players) {
            player.draw(batch);
            player.getHelper().draw(batch);

            // TODO: score and stats
//            bigFont.setColor(0, 0, 0, 1);
//            bigFont.draw(batch, "Player 1", 20, JungleWarsGame.HEIGHT - 20);
//            smallFont.draw(batch, "Score: " + player.getName(), 20, JungleWarsGame.HEIGHT - 40);
//            smallFont.draw(batch, "Score: " + player.getScore(), 20, JungleWarsGame.HEIGHT - 60);
//            smallFont.draw(batch, "Lives: " + player.getLives(), 20, JungleWarsGame.HEIGHT - 80);
        }

//        bigFont.draw(batch, "LEVEL " + level, JungleWarsGame.WIDTH / 2, JungleWarsGame.HEIGHT - 20);
        batch.end();
    }

    @Override
    public void pause() {
//        if (gameState == GameState.RUNNING) gameState = GameState.PAUSED;
    }

    @Override
    public void resume() {
//        if (gameState == GameState.PAUSED) gameState = GameState.RUNNING;
    }

    private void checkCollision() {
        for (Player player : players) {
            player.checkCollision(this);
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Enemy> getEnemies() {
        return enemies;
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

    private enum GameState {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER, // TODO: if (all) player(s) is/are dead
        BETWEEN_WAVE; // TODO: if all enemies are dead
    }
}