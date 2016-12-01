package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Missile extends GameObject {

    private Player owner;

    private int damage;
    private float rotationSpeed;

    private float dx;
    private float dy;

    private float spawnX;
    private float spawnY;

    private float lifeTime;
    private float lifeTimer;

    public Missile(GameScreen game, Player owner, float width, float height, float spawnX, float spawnY, float destinationX, float destinationY, String textureName, int damage, int speed,
                   int rotationSpeed, int lifeTime) {
        this.owner = owner;
        this.spawnX = spawnX;
        this.spawnY = spawnY;

        this.damage = damage;
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;

        this.lifeTime = lifeTime;
        this.lifeTimer = 0;

        init(game, width, height);

        float radians = MathUtils.atan2(destinationY - position.y, destinationX - position.x);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/missiles.atlas");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return atlas.createSprite("banana");
    }

    @Override
    protected Vector2 initSpawnPosition(float width, float height) {
        return new Vector2(spawnX, spawnY);
    }

    @Override
    public void update(float dt) {
        position.x += dx * dt;
        position.y += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite = defaultSprite;

        activeSprite.setPosition(position.x - bounds.getWidth() / 2, position.y - bounds.getHeight() / 2);
        activeSprite.setSize(bounds.getWidth(), bounds.getHeight());
        activeSprite.setOriginCenter();
        activeSprite.rotate(rotationSpeed);
        activeSprite.draw(batch);
    }
}
