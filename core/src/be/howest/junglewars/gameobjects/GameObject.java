package be.howest.junglewars.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    // TODO: Sprite has width, height, x, y, texture, color, ...

    protected float speed;
    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.getWidth() and bounds.getHeight()

    protected TextureAtlas atlas;

    protected Sprite defaultSprite;
    protected Sprite activeSprite;
    protected boolean shouldRemove;

    protected GameObject(float width, float height, String textureName) {
        atlas = setAtlas();
        position = generateSpawnPosition();
        activeSprite = new Sprite();
        defaultSprite = atlas.createSprite(textureName);
        this.bounds = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract TextureAtlas setAtlas();

    protected abstract Vector2 generateSpawnPosition();

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

    public boolean shouldRemove() {
        return shouldRemove;
    }

    //region getters/setters
    public Vector2 getPosition() {
        return position;
    }
    //endregion

}
