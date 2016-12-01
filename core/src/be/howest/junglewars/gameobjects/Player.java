package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends GameObject {

    private final Sprite SHOOTING_SPRITE;

    private String textureName;

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
    private ArrayList<Currency> collectedCurrencies; // TODO: calculate points of this list

    private String name;
    private int hitpoints;
    private float speed;
    private float scoreMultiplier; // TODO: when multiplier? over time? when x score is reached, ...?
    private int score;
    private int totalDamageTaken;
    private int totalDamageGiven;
    private int enemiesKilled;

    public Player(GameScreen game, String name, float width, float height, String textureName) {
        this.name = name;
        this.textureName = textureName;

        missiles = new ArrayList<>();
        collectedPowers = new ArrayList<>();
        activePowers = new ArrayList<>();
        collectedCurrencies = new ArrayList<>();

        this.shootTime = .3f;
        this.shootTimer = 0;
        this.canShoot = true;

        this.isShooting = false;
        this.shootingAnimationTime = .15f;
        this.shootingAnimationTimer = 0;

        this.speed = 6;

        init(game, width, height);

        helper = new Helper(game, "Little Helper", 50, 50, this, "red-wings-down");

        SHOOTING_SPRITE = atlas.createSprite("harambe-shoot");
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

    private void shoot(float destinationX, float destinationY) {
        canShoot = false;
        isShooting = true;
        shootTimer = 0;

        float spawnX = position.x + 18;
        if (!isLookingLeft) spawnX += bounds.getWidth() / 2;
        float spawnY = position.y + bounds.getHeight() - 10;

        missiles.add(
                new Missile(game, this, 30, 30, spawnX, spawnY, destinationX, destinationY, "helper-bullet", 10, 500, -10, 3)
        );
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
    protected Vector2 initSpawnPosition(float width, float height) {
        return new Vector2(Gdx.graphics.getWidth() / 2 - width / 2, Gdx.graphics.getHeight() / 2 - height / 2);
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
        activeSprite = isShooting ? SHOOTING_SPRITE : defaultSprite;

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
        }

        activeSprite.setPosition(position.x, position.y);
        activeSprite.setSize(bounds.getWidth(), bounds.getHeight());
        activeSprite.draw(batch);

        helper.draw(batch);
        for (Missile missile : missiles) {
            missile.draw(batch);
        }
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

    public ArrayList<Currency> getCollectedCurrencies() {
        return collectedCurrencies;
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}