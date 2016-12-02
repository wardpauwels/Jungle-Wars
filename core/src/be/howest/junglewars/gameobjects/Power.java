package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

public class Power extends GameObject {
    private static final String ATLAS_PREFIX = "power/";

    private boolean isHidden;
    private boolean isPowerUp;

    private float lifeTime;
    private float lifeTimer;

    private Player owner;

    public Power(GameScreen game, float width, float height, String defaultSpriteUrl, float lifeTime, boolean isPowerUp) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height, ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()), ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight()));

        this.isPowerUp = isPowerUp;
        this.lifeTime = lifeTime;
        this.isHidden = (Math.random() < 0.5); // Random true or false
    }

    public void activatePower() {
        // TODO
    }

    public void collectedBy(Player player) {
        this.owner = player;
        owner.getCollectedPowers().add(this);
        game.getPowers().remove(this);
    }

    @Override
    public void update(float dt) {
        if (lifeTime < lifeTimer) {
            game.getPowers().remove(this);
        }
        lifeTimer += dt;
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }
}

