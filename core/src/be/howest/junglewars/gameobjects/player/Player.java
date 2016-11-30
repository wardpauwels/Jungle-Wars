package be.howest.junglewars.gameobjects.player;

import be.howest.junglewars.GameData;
import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.power.Power;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Currency;

public class Player extends GameObject {

    private final Sprite SHOOTING_SPRITE = atlas.createSprite("harambe-shoot");

    private String textureName;

    private boolean isLookingLeft;

    private boolean isShooting;
    private float shootingAnimationTime;
    private float shootingAnimationTimer;

    private float shootTime;
    private float shootTimer;
    private boolean canShoot;

    private Helper helper;
    private ArrayList<Power> collectedPowers;
    private ArrayList<Power> activePowers;
    private ArrayList<Currency> collectedCurrencies; // TODO: calculate points of this list
    private ArrayList<Missile> missiles;

    private String name;
    private int hitpoints;
    private float speed;
    private float scoreMultiplier; // TODO: when multiplier? over time? when x score is reached, ...?
    private int score;
    private int totalDamageTaken;
    private int totalDamageGiven;
    private int enemiesKilled;

    public Player(GameData gameData, String name, float width, float height, String textureName, Helper helper) {
        this.name = name;
        this.textureName = textureName;
        this.helper = helper;

        collectedPowers = new ArrayList<>();
        activePowers = new ArrayList<>();
        collectedCurrencies = new ArrayList<>();
        missiles = new ArrayList<>();

        this.shootTime = .3f;
        this.shootTimer = 0;
        this.canShoot = true;

        this.isShooting = false;
        this.shootingAnimationTime = .15f;
        this.shootingAnimationTimer = 0;

        init(gameData, width, height);
    }

    private void handleInput() {
        boolean keyUpPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean keyDownPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean keyLeftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean topBorderTouch = position.y >= Gdx.graphics.getHeight() - bounds.getHeight();
        boolean bottomBorderTouch = position.y <= 0;
        boolean leftBorderTouch = position.x <= 0;
        boolean rightBorderTouch = position.x >= Gdx.graphics.getWidth() - bounds.getWidth();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && canShoot) {
            shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }

        float currentSpeed = speed;
        float sqrtSpeed = ((float) Math.sqrt((speed * speed) / 2));

        if ((keyUpPressed && (keyLeftPressed || keyRightPressed)) ||
                (keyDownPressed && (keyLeftPressed || keyRightPressed))) {
            currentSpeed = sqrtSpeed;
        }

        if (keyUpPressed) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            position.y = topBorderTouch ? Gdx.graphics.getHeight() - bounds.getHeight() : position.y + currentSpeed;
        }
        if (keyDownPressed) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            position.y = bottomBorderTouch ? 0 : position.y - currentSpeed;
        }
        if (keyLeftPressed) {
            isLookingLeft = true;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            position.x = leftBorderTouch ? 0 : position.x - currentSpeed;
        }
        if (keyRightPressed) {
            isLookingLeft = false;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            position.x = rightBorderTouch ? Gdx.graphics.getWidth() - bounds.getWidth() : position.x + currentSpeed;
        }
    }

    private void handleCollision() {
        // Enemy missile -> player
        for (Enemy enemy : gameData.getEnemies()) {
            for (Missile missile : this.checkCollision(enemy.getMissiles())) {
                enemy.getMissiles().remove(missile);
                // TODO: player is hit
            }
        }

        // Player -> currency
        for (Currency currency : this.gameData.getCurrencies()) {
            collectedCurrencies.add(currency);
            this.gameData.getCurrencies().remove(currency);
        }

        // Player -> power
        for (Power power : this.gameData.getPowers()) {
            collectedPowers.add(power);
            this.gameData.getPowers().remove(power);
            // TODO: instant activate (=add to activatedPowers) or wait for manual activation?
        }
    }

    private void shoot(float destinationX, float destinationY) {
        canShoot = false;
        isShooting = true;
        shootTimer = 0;

        float spawnX = position.x + 18;
        if (!isLookingLeft) spawnX += bounds.getWidth() / 2;
        float spawnY = position.y + bounds.getHeight() - 10;

        // TODO: add new missile to players ArrayList
    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/players.atlas");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return new Sprite(atlas.createSprite(textureName));
    }

    @Override
    protected Vector2 initSpawnPosition() {
        return new Vector2(Gdx.graphics.getWidth() - bounds.width, Gdx.graphics.getHeight() - bounds.height);
    }

    @Override
    public void update(float dt) {
        handleInput();

        if (shootTimer > shootTime) {
            canShoot = true;
        } else {
            shootTimer += dt;
        }

        if (isShooting) {
            if (shootingAnimationTimer > shootingAnimationTime) {
                shootingAnimationTimer = 0;
                isShooting = false;
            } else {
                shootingAnimationTimer += dt;
            }
        }

        for (Missile missile : missiles) {
            missile.update(dt);
        }
        helper.update(dt);

        handleCollision();
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite = isShooting ? SHOOTING_SPRITE : defaultSprite;

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
        }

        activeSprite.setPosition(position.x, position.y);
        activeSprite.setSize(bounds.getWidth(), bounds.getHeight());
        activeSprite.draw(batch);

        for (Missile missile : missiles) {
            missile.draw(batch);
        }
        helper.draw(batch);
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public Helper getHelper() {
        return helper;
    }

}