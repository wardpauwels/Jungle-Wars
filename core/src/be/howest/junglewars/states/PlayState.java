package be.howest.junglewars.states;

import be.howest.junglewars.be.howest.junglewars.managers.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class PlayState extends State {

    public PlayState(StateManager sm) {
        super(sm);
    }

    @Override
    public void init() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/jungle-bg.png"));
        backgroundSprite = new Sprite(backgroundTexture);
        backgroundSprite.setPosition(0, 0);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        batch.begin();
        batch.disableBlending();
        backgroundSprite.draw(batch);
        batch.end();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }
}
