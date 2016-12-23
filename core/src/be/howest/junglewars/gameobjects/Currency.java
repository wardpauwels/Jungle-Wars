package be.howest.junglewars.gameobjects;

import be.howest.junglewars.GameData;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.ThreadLocalRandom;

// Buy upgrades for your helpers with these
// Looks like a Power where the "power" just adds some coins...
public class Currency extends GameObject {
    private static final float WIDTH = 30;
    private static final float HEIGHT = 30;

    private static final String ATLAS_PREFIX = "currency/";

    private float lifeTime;
    private float lifeTimer;

    public Currency(float lifeTime, String defaultSpriteUrl, GameData data) {
        super(ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT, ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()), ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight()), data);

        this.lifeTime = lifeTime;
    }

    public void collectedBy(Player player) {
        player.addCoin(1);
        remove = true;
    }

    public Vector2 getPosition(){
        return new Vector2(body.x, body.y);
    }

    @Override
    public void update(float dt) {
        if (lifeTime < lifeTimer) {
            remove = true;
        }
        lifeTimer += dt;
    }

    @Override
    public void render(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }
}
