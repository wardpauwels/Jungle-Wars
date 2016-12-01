package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements Serializable {

    public GameScreen game;

    protected TextureAtlas atlas;

    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.width and bounds.height // .overlaps() for collision
    protected float speed = 0;

    protected Sprite defaultSprite;
    protected Sprite activeSprite;

    public boolean remove = false;

    protected void init(GameScreen game, float width, float height) {
        this.game = game;
        atlas = initAtlas();
        position = initSpawnPosition(width, height);
        bounds = initBounds(width, height);
        defaultSprite = initDefaultSprite();
        activeSprite = defaultSprite;
    }

    // TODO: should not be necessary when using one global atlas (loaded at game startup...)
    protected abstract TextureAtlas initAtlas();

    protected abstract Sprite initDefaultSprite();

    protected abstract Vector2 initSpawnPosition(float width, float height);

    protected Rectangle initBounds(float width, float height) {
        return new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

    public <T extends GameObject> ArrayList<T> checkCollision(List<T> objects) {
        ArrayList<T> collision = new ArrayList<>();

        for (T go : objects) {
            if (this.bounds.overlaps(go.bounds)) {
                collision.add(go);
            }
        }

        return collision;
    }

    public <T extends GameObject> T getNearest(List<T> objects) {
        T nearest = objects.get(0);
        float dist = this.getDistanceTo(objects.get(0));

        for (T go : objects) {
            float nDist = this.getDistanceTo(go);
            if (dist > nDist) {
                nearest = go;
                dist = nDist;
            }
        }

        return nearest;
    }

    protected float getDistanceTo(GameObject object) {
        float diffX = Math.abs(this.bounds.x - object.bounds.y);
        float diffY = Math.abs(this.bounds.y - object.bounds.y);

        double dist = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

        return (float) dist;
    }

    public boolean shouldRemove() {
        return remove;
    }

}
