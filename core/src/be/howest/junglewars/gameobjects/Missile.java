package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;

public class Missile extends GameObject {
    private static final String ATLAS_PREFIX = "missile/";

    private Player owner;

    private int damage;
    private float rotationSpeed;

    private float dx;
    private float dy;

    private float lifeTime;
    private float lifeTimer;

    public Missile(GameScreen game, Player owner, float width, float height, float spawnX, float spawnY, float destinationX, float destinationY, String defaultSpriteUrl, int damage, int speed,
                   int rotationSpeed, int lifeTime) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height, spawnX, spawnY);

        this.owner = owner;
        this.damage = damage;
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;

        this.lifeTime = lifeTime;
        this.lifeTimer = 0;

        float radians = MathUtils.atan2(destinationY - body.y, destinationX - body.x);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
    }

    @Override
    public void update(float dt) {
        body.x += dx * dt;
        body.y += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x - body.getWidth() / 2, body.y - body.getHeight() / 2);
        activeSprite.rotate(rotationSpeed);
        activeSprite.draw(batch);
    }

    public Player getOwner() {
        return owner;
    }

    public int getDamage() {
        return damage;
    }
}
