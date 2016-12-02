package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements Serializable {

    public GameScreen game;

    protected TextureAtlas atlas;

    protected Rectangle body; // has: .width, .height, .overlaps(), .x, .y
    protected float speed = 0;

    protected final Sprite DEFAULT_SPRITE;
    protected Sprite activeSprite;

    public boolean remove = false;

    protected GameObject(GameScreen game, String defaultSpriteUrl, float width, float height, float x, float y) {
        this.game = game;
        body = initBody(width, height, x, y);
        atlas = initAtlas();
        DEFAULT_SPRITE = atlas.createSprite(defaultSpriteUrl);
        changeSprite(DEFAULT_SPRITE);
    }

    // TODO: should not be necessary when using one global atlas (loaded at game startup...)
    protected abstract TextureAtlas initAtlas();

    protected Rectangle initBody(float width, float height, float x, float y) {
        return new Rectangle(x - width / 2, y - height / 2, width, height);
    }

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

    protected void changeSprite(Sprite sprite) {
        activeSprite = sprite;
        activeSprite.setSize(body.width, body.height);
        activeSprite.setOriginCenter();
    }

    public <T extends GameObject> ArrayList<T> checkCollision(List<T> objects) {
        ArrayList<T> collision = new ArrayList<>();

        for (T go : objects) {
            if (this.body.overlaps(go.body)) {
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
        float diffX = Math.abs(this.body.x - object.body.y);
        float diffY = Math.abs(this.body.y - object.body.y);

        double dist = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

        return (float) dist;
    }

    public boolean shouldRemove() {
        return remove;
    }

}
