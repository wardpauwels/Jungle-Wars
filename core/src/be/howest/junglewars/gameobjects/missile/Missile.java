package be.howest.junglewars.gameobjects.missile;

import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.gameobjects.GameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Missile extends GameObject {

    private Player owner;

    private int damage;
    private float rotationSpeed;

    private float dx;
    private float dy;

    private float lifeTime;
    private float lifeTimer;

    public Missile(Player owner, float x, float y, float radians, String textureName, int damage, int speed,
                   int rotationSpeed, int lifeTime) {
        super(x, y, textureName);
//        this.owner = owner;

//        this.damage = damage;
//        this.speed = speed;
//        this.rotationSpeed = rotationSpeed;
//
//        dx = MathUtils.cos(radians) * speed;
//        dy = MathUtils.sin(radians) * speed;
//
//        this.lifeTime = lifeTime;
//        this.lifeTimer = 0;
    }

    @Override
    protected TextureAtlas setAtlas() {
        return new TextureAtlas("atlas/missiles.atlas");
    }

    @Override
    protected Vector2 generateSpawnPosition() {
        return new Vector2();
    }

    @Override
    protected void update(float dt) {
        position.x += dx * dt;
        position.y += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            shouldRemove = true;
        }
    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x - bounds.getWidth() / 2, position.y - bounds.getHeight() / 2);
        activeSprite.rotate(rotationSpeed);
        activeSprite.draw(batch);
    }
}
