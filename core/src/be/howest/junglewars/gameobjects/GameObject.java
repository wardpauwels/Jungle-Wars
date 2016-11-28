package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

    public GameScreen game;

    // TODO: Sprite has width, height, x, y, texture, color, ...

    protected float speed;
    public Vector2 position; // position.x and position.y
    public Rectangle bounds; // bounds.getWidth() and bounds.getHeight()

    protected TextureAtlas atlas;

    protected Sprite defaultSprite;
    protected Sprite activeSprite;
    protected boolean shouldRemove;

    protected GameObject(GameScreen game, String textureName) {
        this.game = game;
        atlas = setAtlas();
        activeSprite = new Sprite();
        defaultSprite = atlas.createSprite(textureName);
    }

    protected void initBounds(float width, float height) {
        bounds = new Rectangle(position.x - width / 2, position.y - height / 2, width, height);
    }

    protected abstract TextureAtlas setAtlas();

    protected abstract void update(float dt);

    protected abstract void draw(SpriteBatch batch);

//    protected abstract void checkCollision();

    public boolean shouldRemove() {
        return shouldRemove;
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
