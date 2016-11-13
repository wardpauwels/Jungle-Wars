package be.howest.junglewars.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Currency extends GameObject{

    public Currency(float width, float height){
        super(width, height);
    }

    @Override
    protected Vector2 generateSpawnPosition(float width, float height) {
        return null;
    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void draw(SpriteBatch batch) {

    }
}
