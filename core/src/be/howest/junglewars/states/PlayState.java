package be.howest.junglewars.states;

import be.howest.junglewars.game.*;
import be.howest.junglewars.managers.*;
import be.howest.junglewars.models.*;
import be.howest.junglewars.models.enemies.*;
import be.howest.junglewars.models.missiles.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.*;

import static javax.swing.UIManager.get;

public class PlayState extends State {

    private ArrayList<Player> players;
    private ArrayList<Enemy> enemies;
    private Enemy enemy;
    private int level;
    private int startingEnemies;
    private float multiplierEnemies;
    private float amountEnemies;
    private float timeBetweenEnemySpawn;
    private float timeLastEnemySpawn;


    public PlayState(StateManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/background-trees.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(JungleWarsGame.WIDTH, JungleWarsGame.HEIGHT);

        timeBetweenEnemySpawn = 1;
        timeLastEnemySpawn = 0;

        players = new ArrayList<Player>();
        enemies = new ArrayList<Enemy>();

        players.add(new Player("John"));

        level = 0;

        startingEnemies = 10;
        multiplierEnemies = 0.5f;
        amountEnemies = startingEnemies+(startingEnemies*(multiplierEnemies*level));
    }

    @Override
    public void update(float dt) {
        handleInput();

        //check collision
        checkCollision();

        //generate enemies
        if (enemies.size() == 0){
            for (int i = 0; i < amountEnemies; i++){
                enemies.add(new Enemy(players));
                enemies.get(i).update(dt);
            }

            level++;
        }

        for (Player player : players) {
            player.update(dt);
            for (Missile missile : player.getMissiles()) {
                missile.update(dt);
            }
        }

        for (int i = 0; i < enemies.size(); i++){
            enemies.get(i).update(dt);
        }
    }

    private void checkCollision() {
        //bullet-enemy collision
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            for (int j = 0; j < p.getMissiles().size(); j++) {
                Missile m = p.getMissiles().get(j);
                for (int k = 0; k < enemies.size(); k++){
                    Enemy e = enemies.get(k);
                    if (((m.getX() < (e.getX()+e.getWidth())) && (m.getX() > e.getX())) && ((m.getY() < (e.getY()+e.getHeight())) && (m.getY() > e.getY()))){
                        p.getMissiles().remove(m);
                        enemies.remove(e);
                        p.addScore(e.getScore());
                        System.out.println(p.getScore());
                    }
                }
            }
        }

        //harambe-enemy collision
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
                for (int k = 0; k < enemies.size(); k++){
                    Enemy e = enemies.get(k);
                    if (((p.getX() < (e.getX()+e.getWidth())) && (p.getX() > e.getX())) && ((p.getY() < (e.getY()+e.getHeight())) && (p.getY() > e.getY()))){
                        p.substractLife();
                        reset(p);
                    }
                }
        }
    }

    public void reset(Player player){
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
            for (int i = 0; i < enemies.size(); i++){
                enemies.get(i).render(batch);
            }
        }

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
