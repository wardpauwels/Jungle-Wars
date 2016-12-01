package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.ThreadLocalRandom;

// Buy upgrades for your helpers with these
// Looks like a Power where the "power" just adds some coins...
public class Currency extends GameObject {
    private String textureUrl;

    private int coinValue;
    private float lifeTime;
    private float lifeTimer;

    public Currency(GameScreen game, float width, float height, int coinValue, float lifeTime, String textureUrl) {
        this.coinValue = coinValue;
        this.lifeTime = lifeTime;
        this.textureUrl = textureUrl;

        init(game, width, height);
    }

    public void collectedBy(Player player) {
        player.getCollectedCurrencies().add(this);
        remove = true;
    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/currencies");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return atlas.createSprite(textureUrl);
    }

    @Override
    protected Vector2 initSpawnPosition(float width, float height) {
        return new Vector2(
                ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()),
                ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight())
        );
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
        activeSprite.setPosition(position.x, position.y);
        activeSprite.setSize(bounds.getWidth(), bounds.getHeight());
        activeSprite.draw(batch);
    }
}
