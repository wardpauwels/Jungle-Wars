package be.howest.junglewars.gameobjects.power;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.concurrent.ThreadLocalRandom;

public class Power extends GameObject {
    private static final float WIDTH = 30;
    private static final float HEIGHT = 30;

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
    private int bonusValue;

    private Player owner;
    private IPowerType powerType;

    public Power(GameScreen game, String name, String defaultSpriteUrl, float lifeTime, float activeTime, PowerType powerType, float percentage) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl + "_down", WIDTH, HEIGHT, ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getWidth()), ThreadLocalRandom.current().nextInt(0, Gdx.graphics.getHeight()));

        this.lifeTime = lifeTime;
        this.activeTime = activeTime;
        this.isHidden = (Math.random() < 0.5);
        this.isPowerUp = (Math.random() < 0.5);
        if (isHidden) {

        } else if (isPowerUp()) {
            defaultSpriteUrl = defaultSpriteUrl + "_up";
            changeSprite(game.atlas.createSprite(ATLAS_PREFIX + defaultSpriteUrl));
        }
        this.name = name;
        this.bonusPercentage = isHidden ? percentage / 50 : percentage / 100;

        this.collectedState = CollectedState.ON_FIELD;

        this.powerType = powerType.getPower();
    }

    private void activatePower() {
        powerType.activatePower(this);
    }

    public void endAction() {
        powerType.deactivatePower(this);
        actionEnded = true;
    }

    public void collectedBy(Player player) {
        collectedState = CollectedState.COLLECTED;
        this.owner = player;
        remove = true;
        owner.addPower(this);
        this.bonusValue = powerType.initBonusValue(this);
        activatePower();
    }

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
    public String toString() {
        String sign = isPowerUp() ? "+" : "-";
        return name + " [" + sign + Math.abs(bonusValue) + "]";
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

    public IPowerType getPowerType() {
        return powerType;
    }

    public float getBonusPercentage() {
        return bonusPercentage;
    }

    public boolean isPowerUp() {
        return isPowerUp;
    }

    public Player getOwner() {
        return owner;
    }

    public int getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(int bonusValue) {
        this.bonusValue = bonusValue;
    }

    public enum CollectedState {
        ON_FIELD,
        COLLECTED
    }

}

