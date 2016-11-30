package be.howest.junglewars.screens;

import be.howest.junglewars.GameData;
import be.howest.junglewars.GameState;
import be.howest.junglewars.JungleWars;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.spawners.EnemySpawner;
import be.howest.junglewars.gameobjects.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameScreen extends ScreenAdapter {

    private JungleWars game;

    private GameData gameData;
    private GameState gameState;

    private EnemySpawner enemySpawner;

    private Sprite backgroundSprite;

    private SpriteBatch batch;

    public GameScreen(JungleWars game, GameData gameData) {
        this.game = game;
        this.gameData = gameData;

        gameState = GameState.READY;

        enemySpawner = new EnemySpawner(gameData.getLevel(), gameData.getDifficulty());

        // create full screen background
        // TODO: change backgrounds.atlas to a "gamescreen-assets.atlas" or something... (or one assets atlas for all screens in JungleWars.java?)
        backgroundSprite = new TextureAtlas("atlas/backgrounds.atlas").createSprite("game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // TODO: https://github.com/libgdx/libgdx/wiki/Managing-your-assets#loading-a-ttf-using-the-assethandler
        // Fonts
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 20;
//        smallFont = generator.generateFont(parameter);
//        parameter.size = 24;
//        bigFont = generator.generateFont(parameter);
//        generator.dispose();

        gameData.getPlayers().add(
                new Player()
        );

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

        for (Player player : gameData.getPlayers()) {
            player.update(dt);
        }

        enemySpawner.generateEnemies(); // TODO: return enemy/enemies if one/multiple should join and add them to GameData

        for (Enemy enemy : gameData.getEnemies()) {
            enemy.update(dt);
        }

        // TODO: currencySpawner.generateCurrencies() + update currencies

        // TODO: powerSpawner.generatePowers() + update powers

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        update(delta);

        SpriteBatch batch = game.batch;
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        for (Player player : gameData.getPlayers()) {
            player.draw(batch);
            player.getHelper().draw(batch);

            // TODO: score and stats (handle font with assets manager -> https://github.com/libgdx/libgdx/wiki/Managing-your-assets#loading-a-ttf-using-the-assethandler
//            bigFont.setColor(0, 0, 0, 1);
//            bigFont.draw(batch, "Player 1", 20, JungleWarsGame.HEIGHT - 20);
//            smallFont.draw(batch, "Score: " + player.getName(), 20, JungleWarsGame.HEIGHT - 40);
//            smallFont.draw(batch, "Score: " + player.getScore(), 20, JungleWarsGame.HEIGHT - 60);
//            smallFont.draw(batch, "Lives: " + player.getLives(), 20, JungleWarsGame.HEIGHT - 80);
        }

        // TODO: draw currencies

        // TODO: draw powers

//        bigFont.draw(batch, "LEVEL " + level, JungleWarsGame.WIDTH / 2, JungleWarsGame.HEIGHT - 20);
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

}