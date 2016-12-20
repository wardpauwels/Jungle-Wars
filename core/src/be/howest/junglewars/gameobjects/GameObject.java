package be.howest.junglewars.gameobjects;

import be.howest.junglewars.GameData;
import be.howest.junglewars.screens.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.io.*;
import java.util.*;

public abstract class GameObject implements Serializable {

    public String defaultSpriteUrl;
    protected final Sprite DEFAULT_SPRITE;
    public GameData data;
    public boolean remove = false;
    protected Rectangle body; // has: .width, .height, .overlaps(), .x, .y
    protected float speed = 0;
    protected Sprite activeSprite;

    protected GameObject(GameData data, String defaultSpriteUrl, float width, float height, float x, float y) {
        this.defaultSpriteUrl = defaultSpriteUrl;
        this.data = data;
        body = initBody(width, height, x, y);
        DEFAULT_SPRITE = data.getGame().atlas.createSprite(defaultSpriteUrl);
        changeSprite(DEFAULT_SPRITE);
    }

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
        if (objects.size() == 0) return null;

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
        float diffX = Math.abs(this.body.x - object.body.x);
        float diffY = Math.abs(this.body.y - object.body.y);

        double dist = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

        return (float) dist;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public Rectangle getBody() {
        return body;
    }

    public void setBody(Rectangle body) {
        this.body = body;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
