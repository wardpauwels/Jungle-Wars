package be.howest.junglewars.states;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemies.EnemyOld;
import be.howest.junglewars.gameobjects.missiles.HelperMissile;
import be.howest.junglewars.gameobjects.missiles.Missile;
import be.howest.junglewars.main.JungleWarsGame;
import be.howest.junglewars.managers.StateManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;

public class PlayGameState extends State {

    private ArrayList<Player> players;
    private ArrayList<EnemyOld> enemies;
    private EnemyOld enemyOld;
    private int level;
    private int startingEnemies;
    private float multiplierEnemies;
    private float amountEnemies;
    private float timeBetweenEnemySpawn;
    private float timeLastEnemySpawn;

    private BitmapFont font;
    private BitmapFont fontH2;
    private FreeTypeFontGenerator generator;

    public PlayGameState(StateManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/background-trees.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(JungleWarsGame.WIDTH, JungleWarsGame.HEIGHT);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        font = generator.generateFont(parameter);
        parameter.size = 24;
        fontH2 = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!

        timeBetweenEnemySpawn = 1;
        timeLastEnemySpawn = 0;

        players = new ArrayList<Player>();
        enemies = new ArrayList<EnemyOld>();

        players.add(new Player("John"));

        level = 0;

        startingEnemies = 10;
        multiplierEnemies = 0.5f;
        amountEnemies = startingEnemies + (startingEnemies * (multiplierEnemies * level));
    }

    @Override
    public void update(float dt) {
        handleInput();

        //check collision
        checkCollision();

        //generate enemies
        amountEnemies = startingEnemies + (startingEnemies * (multiplierEnemies * level));
        if (enemies.size() == 0) {
            for (int i = 0; i < amountEnemies; i++) {
                enemies.add(new EnemyOld(players));
                enemies.get(i).update(dt);
            }
            level++;
        }

        for (Player player : players) {
            player.update(dt);
            player.getHelper().update(dt);
            for (Missile missile : player.getMissiles()) {
                missile.update(dt);
            }
            for (HelperMissile missile : player.getHelper().getMissiles()) {
                missile.update(dt);
            }
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(dt);
        }
    }

    private void checkCollision() {
        //bullet-enemy collision
        for (Player p : players) {
            for (int j = 0; j < p.getMissiles().size(); j++) {
                Missile m = p.getMissiles().get(j);
                for (int k = 0; k < enemies.size(); k++) {
                    EnemyOld e = enemies.get(k);
                    if (((m.getX() < (e.getX() + e.getWidth())) && (m.getX() > e.getX())) && ((m.getY() < (e.getY() + e.getHeight())) && (m.getY() > e.getY()))) {
                        p.getMissiles().remove(m);
                        enemies.remove(e);
                        p.addScore(e.getScore());
                        System.out.println(p.getScore());
                    }
                }
            }
        }

        //harambe-enemy collision
        for (Player p : players) {
            for (EnemyOld e : enemies) {
                if (((p.getX() < (e.getX() + e.getWidth())) && (p.getX() > e.getX())) && ((p.getY() < (e.getY() + e.getHeight())) && (p.getY() > e.getY()))) {
                    p.substractLife();
                    reset(p);
                }
            }
        }
    }

    public void reset(Player player) {
        player.setX(JungleWarsGame.WIDTH / 2 - player.getWidth() / 2);
        player.setY(JungleWarsGame.HEIGHT / 2 - player.getHeight() / 2);
    }

    @Override
    public void render() {
        batch.begin();
        //render background
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        //render players and missiles
        for (Player player : players) {
            player.render(batch);
            for (Missile missile : player.getMissiles()) {
                missile.render(batch);
            }
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).render(batch);
            }
        }

        for (int i = 0; i < players.size(); i++) {
            fontH2.setColor(0, 0, 0, 1);
            fontH2.draw(batch, "Player 1", 20, JungleWarsGame.HEIGHT - 20);
            font.draw(batch, "Score: " + players.get(i).getName(), 20, JungleWarsGame.HEIGHT - 40);
            font.draw(batch, "Score: " + players.get(i).getScore(), 20, JungleWarsGame.HEIGHT - 60);
            font.draw(batch, "Lives: " + players.get(i).getLives(), 20, JungleWarsGame.HEIGHT - 80);
        }
        fontH2.draw(batch, "LEVEL " + level, JungleWarsGame.WIDTH / 2, JungleWarsGame.HEIGHT - 20);
        batch.end();
    }

    @Override
    public void handleInput() {
        for (Player player : players) {
            player.handleInput();
        }
    }

    @Override
    public void dispose() {

    }
}
