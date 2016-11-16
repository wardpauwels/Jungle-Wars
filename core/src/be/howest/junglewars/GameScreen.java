package be.howest.junglewars;

import be.howest.junglewars.gameobjects.powers.Power;
import be.howest.junglewars.main.JungleWarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.List;

enum GameState {
    READY,
    RUNNING,
    PAUSED,
    GAME_OVER, // TODO: if all players are dead
    BETWEEN_WAVE; // TODO: if all enemies are dead
}

public class GameScreen extends ScreenAdapter {
    private JungleWarsGame game;
    private GameState gameState;

    private int level;
    private int difficulty;
    private ArrayList<Player> players;
    private ArrayList<Enemy> enemies;
    private ArrayList<Power> powers;
    private ArrayList<Currency> currencies;
    private Sprite backgroundSprite;

    private FreeTypeFontGenerator fontGenerator;
    private BitmapFont font;
    private BitmapFont fontH2;

    public GameScreen(JungleWarsGame game, int startLevel, int difficulty) {
        this.game = game;
        this.level = startLevel;
        this.difficulty = difficulty;

        gameState = GameState.READY;

        // Background
        Texture bgTexture = new Texture(Gdx.files.internal("backgrounds/background-trees.png"));
        backgroundSprite = new Sprite(bgTexture);
        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Fonts
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 20;
        font = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 24;
        fontH2 = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose(); // To prevent memory leaks

        // Players
        players = new ArrayList<Player>();
        players.add(new Player("John"));

        // Enemies
        enemies = new ArrayList<Enemy>();

    }

    public void update(float dt) {
        switch (gameState) {
            case READY:
                updateReady();
                break;
            case RUNNING:
                updateRunning(dt);
                break;
        }
    }

    private void updateReady() {
        // TODO: show some message like "press any key to start"
        if (Gdx.input.justTouched()) {
            gameState = GameState.RUNNING;
        }
    }

    private void updateRunning(float dt) {
        for(Player player: players){
            player.handleInput();
        }

        checkCollision();

        // TODO: generate enemies in different class?
//        amountEnemies = startingEnemies + (startingEnemies * (multiplierEnemies * level));
//        if (enemies.size() == 0) {
//            for (int i = 0; i < amountEnemies; i++) {
//                enemies.add(new EnemyOld(players));
//                enemies.get(i).update(dt);
//            }
//            level++;
//        }
//
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

    private void checkCollision(){
        // TODO : global collision or collision function inside be.howest.junglewars.GameObject?
    }

    @Override
    public void render(float delta) {
        update(delta);

        SpriteBatch batch = game.getBatch();
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);

        for (Player player: players){
            player.render(batch);
            // TODO: render missiles
//            for (Missile missile : player.getMissiles()) {
//                missile.render(batch);
//            }
//            for (EnemyEntity enemy : enemies) {
//                enemy.render(batch);
//            }

            // TODO: score and stats
//            fontH2.setColor(0, 0, 0, 1);
//            fontH2.draw(batch, "Player 1", 20, JungleWarsGame.HEIGHT - 20);
//            font.draw(batch, "Score: " + player.getName(), 20, JungleWarsGame.HEIGHT - 40);
//            font.draw(batch, "Score: " + player.getScore(), 20, JungleWarsGame.HEIGHT - 60);
//            font.draw(batch, "Lives: " + player.getLives(), 20, JungleWarsGame.HEIGHT - 80);
        }

//        fontH2.draw(batch, "LEVEL " + level, JungleWarsGame.WIDTH / 2, JungleWarsGame.HEIGHT - 20);
//        batch.end();
    }

    @Override
    public void pause() {
        if (gameState == GameState.RUNNING) gameState = GameState.PAUSED;
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

}