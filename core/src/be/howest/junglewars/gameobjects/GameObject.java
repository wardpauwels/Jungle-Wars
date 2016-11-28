package be.howest.junglewars.gameobjects;

import be.howest.junglewars.GameData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.Serializable;

public abstract class GameObject implements Serializable {

    protected GameData gameData;

    protected TextureAtlas atlas;

    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.width and bounds.height // .overlaps() for collision

    protected Sprite defaultSprite;
    protected Sprite activeSprite;

    protected boolean shouldRemove;

    protected GameObject(GameData gameData, float width, float height) {
        this.gameData = gameData;
        atlas = setAtlas();
        defaultSprite = setDefaultSprite();
        activeSprite = defaultSprite;

        position = setSpawnPosition(width, height);
        bounds = setBounds(width, height);
    }

    protected abstract TextureAtlas setAtlas();

    protected abstract Sprite setDefaultSprite();

    protected abstract Vector2 setSpawnPosition(float width, float height);

    private Rectangle setBounds(float width, float height) {
        return new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

    public boolean shouldRemove() {
        return shouldRemove;
    }

}
