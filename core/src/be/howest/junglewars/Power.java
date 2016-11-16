package be.howest.junglewars;

import be.howest.junglewars.gameobjects.GameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Power extends GameObject {

    private boolean isSecret; // TODO: random

    public Power(float width, float height, String textureUrl) {
        super(width, height, textureUrl);
    }

    @Override
    protected void setAnimationFrames() {

    }

    @Override
    protected Vector2 generateSpawnPosition() {
        return null;
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}

// TODO: powerActivation enum