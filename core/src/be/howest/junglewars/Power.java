package be.howest.junglewars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Power extends GameObject {

    private boolean isHidden; // TODO: random
    private boolean isPowerUp;

    private PowerAction powerAction;

    private Player owner;

    public Power(float width, float height, String textureUrl, PowerAction powerAction, boolean isPowerUp) {
        super(width, height, textureUrl);
        this.powerAction = powerAction;
        this.isPowerUp = isPowerUp;
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
        // TODO: activatePower when picked up
    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}

enum PowerAction {
    DOUBLE_DAMAGE {
        @Override
        public void activatePower(Power power) {

        }
    };

    public abstract void activatePower(Power power);
}