package be.howest.junglewars.gameobjects;

import be.howest.junglewars.*;
import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.security.*;

public class Missile extends GameObject {
    private static final String ATLAS_PREFIX = "missile/";

    private Player playerOwner;
    private Enemy enemyOwner;

    private int damage;
    private float rotationSpeed;

    private float dx;
    private float dy;

    private float lifeTime;
    private float lifeTimer;

    private int counter = 0;

    private IMissileType effect;

    public Missile(GameObject owner, float width, float height, float spawnX, float spawnY, float destinationX, float destinationY, String defaultSpriteUrl, int damage, int speed,
                   int rotationSpeed, float lifeTime, MissileType effect, GameData data) {
        super(ATLAS_PREFIX + defaultSpriteUrl, width, height, spawnX, spawnY, data);

        if (owner instanceof Player) {
            playerOwner = (Player) owner;
        } else if (owner instanceof Enemy) {
            enemyOwner = (Enemy) owner;
        } else {
            throw new InvalidParameterException("Missile owner is no player/enemy");
        }
        this.damage = damage;
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;

        this.lifeTime = lifeTime;
        this.lifeTimer = 0;

        this.effect = effect.getEffect();
        float radians = MathUtils.atan2(destinationY - body.y, destinationX - body.x);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
    }

    @Override
    public void update(float dt) {
        body.x += dx * dt;
        body.y += dy * dt;

        lifeTimer += dt;
        System.out.println(counter++);
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }



    @Override
    public void render(SpriteBatch batch) {
        activeSprite.setPosition(body.x - body.getWidth() / 2, body.y - body.getHeight() / 2);
        activeSprite.rotate(rotationSpeed);
        activeSprite.draw(batch);
    }

    public int getDamage() {
        return damage;
    }

    public void doEffect(Player p){
        effect.doEffect(p);
    }
}
