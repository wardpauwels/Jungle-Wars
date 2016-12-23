package be.howest.junglewars.gameobjects.enemy.utils;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Brick extends GameObject {
    private static final String ATLAS_PREFIX = "wall/";

    private float lifeTime;
    private float lifeTimer;

    public Brick(GameScreen g, float width, float height, float x, float y){
        super(g,ATLAS_PREFIX +"brick",width,height,x,y);
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
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x,body.y);
        activeSprite.draw(batch);
    }
}
