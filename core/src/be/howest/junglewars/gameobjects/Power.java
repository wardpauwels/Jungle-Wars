package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

public class Power extends GameObject {
    private static final String ATLAS_PREFIX = "power/";

    private final Sprite HIDDEN_SPRITE = game.atlas.createSprite(ATLAS_PREFIX + "hidden");

    private String name;

    private boolean isHidden;
    private boolean isPowerUp;

    private float lifeTime;
    private float lifeTimer;

    private float activeTime;
    private float activeTimer;
    private boolean actionEnded = false;

    private int bonusDamage;

    private Player owner;

    public enum CollectedState{
        ON_FIELD,
        COLLECTED
    }

    public Power(GameScreen game, String name, float width, float height, String defaultSpriteUrl, float lifeTime, float activeTime, boolean isPowerUp) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height, ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()), ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight()));

        this.name = name;
        this.isPowerUp = isPowerUp;
        this.lifeTime = lifeTime;
        this.activeTime = activeTime;
        this.isHidden = (Math.random() < 0.5);
    }

    public void activatePower() {
        bonusDamage = owner.getDamage();
        owner.setDamage(owner.getDamage() + bonusDamage);
    }

    private void endAction() {
        owner.setDamage(owner.getDamage() - bonusDamage);
        actionEnded = true;
    }

    public void collectedBy(Player player) {
        this.owner = player;
        owner.addPowers(this);
        remove = true;
        activatePower();
    }

    // TODO: work with states to know what to update
    @Override
    public void update(float dt) {
        if (isHidden) {
            this.changeSprite(HIDDEN_SPRITE);
        }

        if (lifeTime < lifeTimer) {
            remove = true;
        }
        lifeTimer += dt;

        if (activeTime < activeTimer) {
            owner.getPowers().remove(this);
        }
        activeTimer += dt;
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public String getName() {
        return name;
    }

    public int getTimeLeft() {
        return Math.round(activeTime - activeTimer);
    }

    public boolean isActionEnded() {
        return actionEnded;
    }
}

