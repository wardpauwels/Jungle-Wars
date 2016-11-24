package be.howest.junglewars.gameobjects.player;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.power.Power;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends GameObject {

    private boolean keyUpPressed;
    private boolean keyDownPressed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;

    private boolean topBorderTouch;
    private boolean bottomBorderTouch;
    private boolean leftBorderTouch;
    private boolean rightBorderTouch;

    private Helper helper;
    private ArrayList<Power> powers;
    private ArrayList<Missile> missiles;

    private String name;
    private int lives;
    private int score;
    private int level;
    private String textureName;

    private float shootTime;
    private float shootTimer;
    private boolean canShoot;

    private boolean isLookingLeft;

    private Sprite shootingSprite;
    private boolean isShooting;
    private float shootingAnimationTime;
    private float shootingAnimationTimer;


    public Player(String name, float width, float height, String textureName, float speed) {
        super("harambe");
        this.name = name;
        this.textureName = textureName;
        this.speed = speed;

        position = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        initBounds(width, height);

        powers = new ArrayList<>();
        missiles = new ArrayList<>();

        this.shootTime = .3f;
        this.shootTimer = 0;
        this.canShoot = true;

        shootingSprite = atlas.createSprite("harambe-shoot");
        this.isShooting = false;
        this.shootingAnimationTime = .15f;
        this.shootingAnimationTimer = 0;

    }

    private void handleInput() {
        keyUpPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        keyDownPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        keyLeftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        keyRightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        topBorderTouch = position.y >= Gdx.graphics.getHeight() - bounds.getHeight();
        bottomBorderTouch = position.y <= 0;
        leftBorderTouch = position.x <= 0;
        rightBorderTouch = position.x >= Gdx.graphics.getWidth() - bounds.getWidth();

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

        float spawnX = position.x +18 ;
        if (!isLookingLeft) spawnX += bounds.getWidth() / 2;
        float spawnY = position.y + bounds.getHeight() - 10;

        missiles.add(new Missile(this, 34, 20, spawnX, spawnY, destinationX, destinationY, "banana", 10, 500, -10, 3));
    }

    @Override
    protected TextureAtlas setAtlas() {
        return new TextureAtlas("atlas/players.atlas");
    }

    @Override
    public void update(float dt) {
        handleInput();

        if (shootTimer > shootTime) {
            canShoot = true;
            shootTimer = 0;
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

//        for (int i = 0; i < getHelper().getMissiles().size(); i++) {
//            if (getHelper().getMissiles().get(i).shouldRemove()) {
//                getHelper().getMissiles().remove(i);
//                i--;
//            }
//        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite = defaultSprite;
        if (isShooting) {
            activeSprite = shootingSprite;
        }

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
        }

        activeSprite.setPosition(position.x, position.y);
        activeSprite.setSize(bounds.getWidth(), bounds.getHeight());
        activeSprite.draw(batch);

        // TODO render missiles and helper?

        for (Missile missile : missiles) {
            missile.draw(batch);
        }
        /*
        helper.render(batch);
        for (HelperMissile missile : helper.getMissiles()) {
            missile.draw(batch);
        }
         */
    }

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}
