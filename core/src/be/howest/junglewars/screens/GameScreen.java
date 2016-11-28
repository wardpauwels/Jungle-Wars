package be.howest.junglewars.screens;

import be.howest.junglewars.GameData;
import be.howest.junglewars.GameState;
import be.howest.junglewars.gameobjects.enemy.EnemySpawner;
import be.howest.junglewars.gameobjects.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameScreen extends ScreenAdapter {

    private GameData gameData;
    private GameState gameState;

    private EnemySpawner enemySpawner;

    private Sprite backgroundSprite;

    public GameScreen(GameData gameData) {
        this.gameData = gameData;

        gameState = GameState.READY;

        enemySpawner = new EnemySpawner(gameData.getLevel(), gameData.getDifficulty());

        // create full screen background
        backgroundSprite = new TextureAtlas("atlas/backgrounds.atlas").createSprite("game");
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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
//        if (gameState == be.howest.junglewars.GameState.RUNNING) gameState = be.howest.junglewars.GameState.PAUSED;
    }

    @Override
    public void resume() {
//        if (gameState == be.howest.junglewars.GameState.PAUSED) gameState = be.howest.junglewars.GameState.RUNNING;
    }

    private void checkCollision() {
        for (Player player : players) {
            player.checkCollision(this);
        }
    }

}