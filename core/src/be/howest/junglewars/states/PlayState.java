package be.howest.junglewars.states;

import be.howest.junglewars.game.*;
import be.howest.junglewars.managers.*;
import be.howest.junglewars.models.*;
import be.howest.junglewars.models.missiles.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.*;

public class PlayState extends State {

    private ArrayList<Player> players;

    public PlayState(StateManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/background-trees.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
        backgroundSprite.setSize(JungleWarsGame.WIDTH, JungleWarsGame.HEIGHT);

        players = new ArrayList<Player>();
        players.add(new Player("John"));
    }

    @Override
    public void update(float dt) {
        handleInput();

        for (Player player : players) {
            player.update(dt);
            for (Missile missile : player.getMissiles()) {
                missile.update(dt);
            }
        }
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
