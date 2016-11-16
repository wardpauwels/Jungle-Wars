package be.howest.junglewars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Currency extends GameObject {

    public Currency(float width, float height, int coinValue, float secondsOnField, String textureUrl) {
        super(width, height, textureUrl);
    }

    @Override
    protected void setAnimationFrames() {

    }

    @Override
    protected Vector2 generateSpawnPosition() {
        // TODO: random on the map
        return null;
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}
