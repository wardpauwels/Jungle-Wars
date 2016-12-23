package be.howest.junglewars.gameobjects;

import be.howest.junglewars.*;
import be.howest.junglewars.gameobjects.enemy.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

// TODO: should be upgradable
public class Helper extends GameObject {
    private static final float WIDTH = 50;
    private static final float HEIGHT = 50;
    private static final float BULLET_WIDTH = 12.5f;
    private static final float BULLET_HEIGHT = 12.5f;

    private static final String ATLAS_PREFIX = "helper/";

    private Player owner;
    private String name;

    private float shootTime;
    private float shootTimer;

    public Helper(String name, Player owner, String defaultSpriteUrl, GameData data) {
        super(ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, owner.body.x - 1.5f * WIDTH, owner.body.y + 1.5f * HEIGHT, data);

        this.owner = owner;
        this.name = name;

        shootTime = 2;
        shootTimer = 0;
    }

    private void doHelperAction(float dt) {
        shootTimer += dt;
        if (shootTimer > shootTime) {
            shoot();
            shootTimer = 0;
        }
    }

    private void shoot() {
        Enemy target = chooseTarget();
        if (target == null) return;

        float destinationX = target.body.x + (target.body.width / 2);
        float destinationY = target.body.y + (target.body.height / 2);

        float spawnX = body.x + (body.width / 2);
        float spawnY = body.y + (body.height / 2);

        owner.getMissiles().add(
                new Missile(owner, BULLET_WIDTH, BULLET_HEIGHT, spawnX, spawnY, destinationX, destinationY, "helper-bullet", 15, 800, 30, 1.5f, MissileType.TEAR, getData())
        );
    }

    private Enemy chooseTarget() {
        return getNearest(getData().getEnemies());
    }

    private Vector2 leftTopOfOwnerPosition(float dt) {
        return new Vector2(owner.body.x - 1.5f * body.width, owner.body.y + 1.5f * body.height);
    }

    @Override
    public void update(float dt) {
        body.setPosition(leftTopOfOwnerPosition(dt));
        doHelperAction(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public Player getOwner() {
        return owner;
    }

}