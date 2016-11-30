package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.ThreadLocalRandom;

public class Power extends GameObject {

    private String textureUrl;

    private boolean isHidden;
    private boolean isPowerUp;

    private float lifeTime;
    private float lifeTimer;

    private Player owner;

    public Power(GameScreen game, float width, float height, String textureUrl, boolean isPowerUp) {
        this.game = game;
        this.isPowerUp = isPowerUp;
        this.textureUrl = textureUrl;

        this.isHidden = (Math.random() < 0.5); // Random true or false

        init(game, width, height);
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
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("powers.atlas");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return atlas.createSprite(textureUrl);
    }

    @Override
    protected Vector2 initSpawnPosition() {
        return new Vector2(
                ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()),
                ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight())
        );
    }

    @Override
    protected void update(float dt) {
        if (lifeTime < lifeTimer) {
            game.getCurrencies().remove(this);
        }
        lifeTimer += dt;
    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x, position.y);
        activeSprite.setSize(bounds.getWidth(), bounds.getHeight());
        activeSprite.draw(batch);
    }
}

