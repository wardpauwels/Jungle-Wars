package be.howest.junglewars.screens;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.JungleWars;
import be.howest.junglewars.gameobjects.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

// TODO: check and implement https://github.com/libgdx/libgdx/wiki/Collections
public class GameScreen extends Stage implements Screen {

    //region fields

    public TextureAtlas atlas;
    private Sprite backgroundSprite;
    private BitmapFont smallFont;
    private BitmapFont bigFont;

    private JungleWars game;
    private GameState gameState;

    private int wave;
    private Difficulty difficulty;

    private List<Player> players;
    private List<Helper> helpers;
    private List<Enemy> enemies;
    private List<Missile> enemyMissiles;
    private List<Power> powers;
    private List<Currency> currencies;

    private int startingEnemies;
    private float mulitplierEnemies;
    private float amountEnemies;

    //endregion

    public GameScreen(JungleWars game, int level, Difficulty difficulty) {
        this.game = game;
        this.wave = level;
        this.difficulty = difficulty;
        this.atlas = game.atlas;

        gameState = GameState.READY;

        players = new ArrayList<>();
        helpers = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyMissiles = new ArrayList<>();
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

        players.add(new Player(this, "John", "harambe"));

        startingEnemies = 1;
        mulitplierEnemies = 0.5f;
        spawnEnemies(false);


    }

    private void checkGameOver() {
        for (Player player : players) {
            if (player.getHitpoints() > 0) return;
        }
        gameState = GameState.GAME_OVER;
    }

    private void checkCollisions() {
        for (Player player : players) {
            for (Currency currency : player.checkCollision(currencies)) {
                currency.collectedBy(player);
            }
            for (Power power : player.checkCollision(powers)) {
                power.collectedBy(player);
            }
            for (Missile missile : player.checkCollision(enemyMissiles)) {
                player.hitBy(missile);
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

    //region spawners TODO: create standalone classes for spawners

    private void spawnEnemies(boolean nextWave) {
        if (enemies.size() == 0) {
            amountEnemies = startingEnemies + (startingEnemies * (mulitplierEnemies * wave));
            for (int i = 0; i < amountEnemies; i++) {
                enemies.add(new Enemy(this, "Zookeeper", "zookeeper", 5, 150, 15, 1.5f, 10, 15, 5));
            }
            if (nextWave) wave++;
        }
    }

    private void spawnCurrencies() {
        int maxCurrenciesOnField = 2;
        if (currencies.size() < maxCurrenciesOnField)
            currencies.add(new Currency(this, 5, "coin"));
    }

    private void spawnPowers() {
        int maxPowersOnField = 2;
        if (powers.size() < maxPowersOnField)
            powers.add(new Power(this, "More Damage", "power-up", 5, 10, true, Power.PowerType.EXTRA_DAMAGE, 40));
    }

    //endregion

    //region updates

    private void updateReady(float dt) {
        // TODO: show some message like "press any key to start wave X"
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

        for (int i = 0; i < enemyMissiles.size(); i++) {
            enemyMissiles.get(i).update(dt);
            if (enemyMissiles.get(i).shouldRemove()) {
                enemyMissiles.remove(i);
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

        checkGameOver();

    }

    private void updatePreWave(float dt) {

    }

    private void updatePaused(float dt) {

    }

    private void updateGameOver(float dt) {

    }

    //endregion

    //region renders

    private void renderReady(SpriteBatch batch) {

    }

    private void renderRunning(SpriteBatch batch) {
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
            smallFont.draw(batch, "Wave: " + player.getWave(), 20, Gdx.graphics.getHeight() - 80);
            smallFont.draw(batch, "XP: " + player.getXp(), 20, Gdx.graphics.getHeight() - 100); // TODO: xp till next wave
            smallFont.draw(batch, "Coins collected: " + player.getCollectedCoins(), 20, Gdx.graphics.getHeight() - 120);
            smallFont.draw(batch, "Hitpoints: " + player.getHitpoints(), 20, Gdx.graphics.getHeight() - 140);
            smallFont.draw(batch, "ACTIVE POWERS: ", 300, Gdx.graphics.getHeight() - 20);
            for (int i = 0; i < player.getPowers().size(); i++) {
                smallFont.draw(batch, player.getPowers().get(i).getName() + " (+" + Math.round(player.getPowers().get(i).getBonusPercentage() * 100) + "%)" + " [" + player.getPowers().get(i).getTimeLeft() + " seconds left]", 300, Gdx.graphics.getHeight() - 20 * (i + 2));
            }
            smallFont.draw(batch, "DAMAGE: " + player.getDamage(), 550, 20);
        }

        for (Enemy enemy : enemies) {
            enemy.draw(batch);
        }

        for (Missile missile : enemyMissiles) {
            missile.draw(batch);
        }

        for (Currency currency : currencies) {
            currency.draw(batch);
        }

        for (Power power : powers) {
            power.draw(batch);
        }

        bigFont.draw(batch, "LEVEL " + wave, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 20);

    }

    private void renderPreWave(SpriteBatch batch) {

    }

    private void renderPaused(SpriteBatch batch) {

    }

    private void renderGameOver(SpriteBatch batch) {
        bigFont.draw(batch, "GAME OVER!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        for (Player player : players) {
            smallFont.draw(batch, player.getName() + ": " + player.getScore() + " points", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 40);
        }
    }

    //endregion

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        SpriteBatch batch = game.batch;
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        dt = Math.min(dt, 1 / 60f);

        switch (gameState) {
            case READY:
                updateReady(dt);
                renderReady(batch);
                break;
            case RUNNING:
                updateRunning(dt);
                renderRunning(batch);
                break;
            case PRE_WAVE:
                updatePreWave(dt);
                renderPreWave(batch);
                break;
            case PAUSED:
                updatePaused(dt);
                renderPaused(batch);
                break;
            case GAME_OVER:
                updateGameOver(dt);
                renderGameOver(batch);
                break;
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //region getters/setters

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
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

    public List<Missile> getEnemyMissiles() {
        return enemyMissiles;
    }

    //endregion

    enum GameState {
        READY,
        RUNNING,
        PAUSED,
        GAME_OVER,
        PRE_WAVE;
    }
}