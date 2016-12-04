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

    private CollectedState collectedState;

    private float bonusPercentage;
    private int bonusDamage;

    private Player owner;
    private PowerType powerType;

    public enum CollectedState {
        ON_FIELD,
        COLLECTED
    }

    public enum PowerType {
        EXTRA_DAMAGE
    }

    public Power(GameScreen game, String name, float width, float height, String defaultSpriteUrl, float lifeTime, float activeTime, boolean isPowerUp, PowerType powerType, float percentage) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height, ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()), ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight()));

        this.name = name;
        this.isPowerUp = isPowerUp;
        this.lifeTime = lifeTime;
        this.activeTime = activeTime;
        this.isHidden = (Math.random() < 0.5);
        this.powerType = powerType;
        this.bonusPercentage = isHidden ? percentage * 2 : percentage;

        this.collectedState = CollectedState.ON_FIELD;
    }

    private void activatePower() {
        bonusDamage = Math.round(owner.getDamage() * (bonusPercentage / 100));
        owner.setDamage(owner.getDamage() + bonusDamage);
    }

    public void endAction() {
        owner.setDamage(owner.getDamage() - bonusDamage);
        actionEnded = true;
    }

    public void collectedBy(Player player) {
        collectedState = CollectedState.COLLECTED;
        this.owner = player;
        remove = true;
        owner.addPower(this);
        activatePower();
    }

    // TODO: work with states to know what to update
    @Override
    public void update(float dt) {
        switch (collectedState) {
            case ON_FIELD:
                updateOnField(dt);
                break;
            case COLLECTED:
                updateCollected(dt);
        }
    }

    private void updateOnField(float dt) {
        if (isHidden) {
            this.changeSprite(HIDDEN_SPRITE);
        }

        if (lifeTime < lifeTimer) {
            remove = true;
        }

        lifeTimer += dt;
    }

    private void updateCollected(float dt) {
        if (activeTime < activeTimer) {
            endAction();
            owner.getPowers().remove(this);
        }
        activeTimer += dt;
    }

    @Override
    public void draw(SpriteBatch batch) {
        switch (collectedState) {
            case ON_FIELD:
                drawOnField(batch);
                break;
            case COLLECTED:
                drawCollected(batch);
                break;
        }
    }

    private void drawOnField(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    private void drawCollected(SpriteBatch batch) {

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

    public PowerType getType() {
        return powerType;
    }

    public float getBonusPercentage() {
        return bonusPercentage;
    }
}

