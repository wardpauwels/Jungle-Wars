package be.howest.junglewars.models;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Model {

    protected float speed;

    protected float x;
    protected float y;

    protected float width;
    protected float height;

    protected boolean remove;

    protected Texture texture;
    protected Sprite sprite;

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

    public void contains(float x, float y){

    }

}
