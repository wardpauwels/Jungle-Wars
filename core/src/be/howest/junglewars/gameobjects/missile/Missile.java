package be.howest.junglewars.gameobjects.missile;

import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.screens.GameScreen;
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

    private float lifeTime;
    private float lifeTimer;

    public Missile(GameScreen game, Player owner, float width, float height, float x, float y, float destinationX, float destinationY, String textureName, int damage, int speed,
                   int rotationSpeed, int lifeTime) {
        super(game, textureName);
        this.owner = owner;

        position = new Vector2(x, y);
        initBounds(width, height);

        this.damage = damage;
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;

        float radians = MathUtils.atan2(destinationY - position.y, destinationX - position.x);
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        this.lifeTime = lifeTime;
        this.lifeTimer = 0;
    }

    @Override
    protected TextureAtlas setAtlas() {
        return new TextureAtlas("atlas/missiles.atlas");
    }

    @Override
    public void update(float dt) {
        position.x += dx * dt;
        position.y += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            shouldRemove = true;
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
