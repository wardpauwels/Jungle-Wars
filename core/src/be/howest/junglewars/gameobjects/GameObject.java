package be.howest.junglewars.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected float speed;
    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.getWidth() and bounds.getHeight()

    protected GameObject(float width, float height) {
        position = generateSpawnPosition(width, height);
        this.bounds = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract Vector2 generateSpawnPosition(float width, float height);

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

}
