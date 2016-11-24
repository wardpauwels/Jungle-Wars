package be.howest.junglewars.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    // TODO: Sprite has width, height, x, y, texture, color, ...

    protected float speed;
    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.getWidth() and bounds.getHeight()

    protected TextureRegion[] animationFrames; // all images in one sprite
    protected TextureAtlas atlas;
    protected Animation animation;

    protected Texture defaultTexture;
    protected Sprite activeSprite;
    protected boolean shouldRemove;

    protected GameObject(float width, float height, String textureName) {
        atlas = setAtlas();
        animationFrames = setAnimationFrames();
        position = generateSpawnPosition();
        activeSprite = new Sprite();
        defaultTexture = atlas.findRegion(textureName + "-normal").getTexture();
        this.bounds = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract TextureAtlas setAtlas();

    protected abstract TextureRegion[] setAnimationFrames();

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
