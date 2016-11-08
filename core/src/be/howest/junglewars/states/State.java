package be.howest.junglewars.states;

import be.howest.junglewars.be.howest.junglewars.managers.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public abstract class State {

    protected SpriteBatch batch;
    protected Texture backgroundTexture;
    protected Sprite backgroundSprite;

    protected StateManager sm;

    protected State(StateManager sm) {
        batch = new SpriteBatch();
        this.sm = sm;
        init();
    }

    public abstract void init();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void handleInput();

    public abstract void dispose();

}
