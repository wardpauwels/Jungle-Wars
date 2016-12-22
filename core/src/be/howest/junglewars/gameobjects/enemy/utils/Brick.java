package be.howest.junglewars.gameobjects.enemy.utils;

import be.howest.junglewars.*;
import be.howest.junglewars.gameobjects.*;
import com.badlogic.gdx.graphics.g2d.*;

/**
 * Created by jensthiel on 20/12/16.
 */
public class Brick extends GameObject {
    private static final String ATLAS_PREFIX = "wall/";

    private float lifeTime;
    private float lifeTimer;

    public Brick(float width, float height, float x, float y, GameData data) {
        super(ATLAS_PREFIX + "brick", width, height, x, y, data);
        lifeTime = 10;
        lifeTimer = 0;
    }

    @Override
    public void update(float dt) {

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        activeSprite.setPosition(body.x,body.y);
        activeSprite.draw(batch);
    }
}
