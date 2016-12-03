package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Player extends GameObject {
    private static final String ATLAS_PREFIX = "player/";

    private final Sprite SHOOTING_SPRITE = game.atlas.createSprite(ATLAS_PREFIX + "harambe-shoot");

    private boolean isLookingLeft;

    private boolean isShooting;
    private float shootingAnimationTime;
    private float shootingAnimationTimer;

    private float shootTime;
    private float shootTimer;
    private boolean canShoot;

    private Helper helper;
    private ArrayList<Missile> missiles;
    private ArrayList<Power> collectedPowers;
    private ArrayList<Power> activePowers;

    private String name;
    private int hitpoints;
    private float speed;
    private float scoreMultiplier = 1; // TODO: when multiplier? over time? when x score is reached, ...?
    private int xp = 0;
    private int level = 1;
    private int score = 0;
    private int collectedCoins = 0;
    private int enemiesKilled = 0;

    public Player(GameScreen game, String name, float width, float height, String defaultSpriteUrl) {
        super(game, ATLAS_PREFIX + defaultSpriteUrl, width, height, Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() / 2 - height / 2);

        this.name = name;

        missiles = new ArrayList<>();
        collectedPowers = new ArrayList<>();
        activePowers = new ArrayList<>();

        this.shootTime = .3f;
        this.shootTimer = 0;
        this.canShoot = true;

        this.isShooting = false;
        this.shootingAnimationTime = .15f;
        this.shootingAnimationTimer = 0;

        this.speed = 6;
        this.hitpoints = 100;

        helper = new Helper(game, 50, 50, "Little Helper", this, "red-wings-up");
    }

    private void handleInput() {
        boolean keyUpPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean keyDownPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        boolean keyLeftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean keyRightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        boolean topBorderTouch = body.y >= Gdx.graphics.getHeight() - body.getHeight();
        boolean bottomBorderTouch = body.y <= 0;
        boolean leftBorderTouch = body.x <= 0;
        boolean rightBorderTouch = body.x >= Gdx.graphics.getWidth() - body.getWidth();

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
            body.y = topBorderTouch ? Gdx.graphics.getHeight() - body.getHeight() : body.y + currentSpeed;
        }
        if (keyDownPressed) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            body.y = bottomBorderTouch ? 0 : body.y - currentSpeed;
        }
        if (keyLeftPressed) {
            isLookingLeft = true;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            body.x = leftBorderTouch ? 0 : body.x - currentSpeed;
        }
        if (keyRightPressed) {
            isLookingLeft = false;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            body.x = rightBorderTouch ? Gdx.graphics.getWidth() - body.getWidth() : body.x + currentSpeed;
        }
    }

    private void shoot(float destinationX, float destinationY) {
        canShoot = false;
        isShooting = true;
        shootTimer = 0;

        float spawnX = body.x + 18;
        if (!isLookingLeft) spawnX += body.getWidth() / 2;
        float spawnY = body.y + body.getHeight() - 10;

        missiles.add(
                new Missile(game, this, 30, 30, spawnX, spawnY, destinationX, destinationY, "banana", 10, 500, -10, 3)
        );
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

        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).update(dt);
            if (missiles.get(i).shouldRemove()) {
                missiles.remove(i);
                i--;
            }
        }

        helper.update(dt);
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (isShooting)
            changeSprite(SHOOTING_SPRITE);
        else
            changeSprite(DEFAULT_SPRITE);

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
        }

        helper.draw(batch);
        for (Missile missile : missiles) {
            missile.draw(batch);
        }

        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public void addScore(int score) {
        this.score += Math.round(scoreMultiplier * score);
    }

    public void addXp(int xp) {
        this.xp += xp;
        checkLevelUp();
    }

    public int addCoin(int coin) {
        this.collectedCoins += coin;
        return collectedCoins;
    }

    private void checkLevelUp() {
        // TODO: if level up condition true => level++
    }

    public Helper getHelper() {
        return helper;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public ArrayList<Power> getCollectedPowers() {
        return collectedPowers;
    }

    public ArrayList<Power> getActivePowers() {
        return activePowers;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }

    public int getXp() {
        return xp;
    }

    public int getCollectedCoins() {
        return collectedCoins;
    }

    public int getLevel() {
        return level;
    }
}