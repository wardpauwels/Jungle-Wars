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

    public void setPosition() {

    }

    public void setSize() {

    }

    public void setSpeed() {

    }

    public float getSpeed() {
        return speed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void render(SpriteBatch batch){

    };

}
