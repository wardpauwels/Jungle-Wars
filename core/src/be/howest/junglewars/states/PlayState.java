package be.howest.junglewars.states;

import be.howest.junglewars.be.howest.junglewars.managers.*;
import be.howest.junglewars.models.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class PlayState extends State {

    private Player player;

    public PlayState(StateManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/jungle-bg.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);

        player = new Player("John");
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render() {
        batch.begin();
        //render background
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.enableBlending();

        //render player
        player.render(batch);
        batch.end();
    }

    @Override
    public void handleInput() {
        player.handleInput(); //TODO: for loop
    }

    @Override
    public void dispose() {

    }
}
