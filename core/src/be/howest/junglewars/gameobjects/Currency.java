package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.concurrent.ThreadLocalRandom;

// Buy upgrades for your helpers with these
// Looks like a Power where the "power" just adds some coins...
public class Currency extends GameObject {
    private static final String ATLAS_PREFIX = "currency/";

    private int coinValue;
    private float lifeTime;
    private float lifeTimer;

    public Currency(GameScreen game, float width, float height, int coinValue, float lifeTime, String defaultSpriteUrl) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height, ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()), ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight()));

        this.coinValue = coinValue;
        this.lifeTime = lifeTime;
    }

    public void collectedBy(Player player) {
        player.getCollectedCurrencies().add(this);
        remove = true;
    }

    @Override
    public void update(float dt) {
        if (lifeTime < lifeTimer) {
            game.getCurrencies().remove(this);
        }
        lifeTimer += dt;
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }
}
