package be.howest.junglewars.states;

import be.howest.junglewars.managers.StateManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
