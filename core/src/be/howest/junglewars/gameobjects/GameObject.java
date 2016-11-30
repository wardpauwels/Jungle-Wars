package be.howest.junglewars.gameobjects;

import be.howest.junglewars.GameData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject implements Serializable {

    public GameData gameData;

    protected TextureAtlas atlas;

    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.width and bounds.height // .overlaps() for collision

    protected Sprite defaultSprite;
    protected Sprite activeSprite;



    protected GameObject(GameData gameData) {
        this.gameData = gameData;

        atlas = setAtlas();
        defaultSprite = setDefaultSprite();
        activeSprite = defaultSprite;
    }

    // TODO: should not be necessary when using one global atlas (loaded at game startup...)
    protected abstract TextureAtlas setAtlas();

    protected abstract Sprite setDefaultSprite();

    protected abstract Vector2 setSpawnPosition();

    protected Rectangle setBounds(float width, float height) {
        return new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

    protected <T extends GameObject> ArrayList<T> checkCollision(List<T> objects) {
        ArrayList<T> collision = new ArrayList<>();

        for (T go : objects) {
            if (this.bounds.overlaps(go.bounds)) {
                collision.add(go);
            }
        }

        return collision;
    }

    protected <T extends GameObject> T getNearest(T[] objects) {
        T nearest = objects[0];
        float dist = this.getDistanceTo(objects[0]);

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

}
