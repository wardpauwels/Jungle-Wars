package be.howest.junglewars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
    protected float speed;
    protected Vector2 position; // position.x and position.y
    protected Rectangle bounds; // bounds.getWidth() and bounds.getHeight()

    protected Texture texture;
    protected TextureRegion[] animationFrames; // all images in one sprite
    protected Sprite activeSprite;

    protected boolean shouldRemove;

    protected GameObject(float width, float height, String textureUrl) {
        setAnimationFrames();
        position = generateSpawnPosition();
        this.bounds = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
        this.texture = new Texture(Gdx.files.internal(textureUrl));
        this.activeSprite = new Sprite(texture);
    }

    protected abstract void setAnimationFrames();

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
