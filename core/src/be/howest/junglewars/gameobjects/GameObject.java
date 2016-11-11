package be.howest.junglewars.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {

    protected float speed;

    protected float x;
    protected float y;

    protected float width;
    protected float height;

    protected boolean remove;

    protected Sprite activeSprite;

    public float getSpeed() {
        return speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void render(SpriteBatch batch) {

    }

    public void contains(float x, float y) {

    }

}
