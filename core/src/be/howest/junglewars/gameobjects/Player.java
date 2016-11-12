package be.howest.junglewars.gameobjects;

import be.howest.junglewars.main.JungleWarsGame;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.ShootingHelper;
import be.howest.junglewars.gameobjects.missiles.HelperMissile;
import be.howest.junglewars.gameobjects.missiles.Missile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    private String name;

    private ArrayList<Missile> missiles;
    private Helper helper;

    private int lives;
    private int score;

    private float shootTime;
    private float shootTimer;
    private boolean canShoot;

    private float shootAnimationTime;
    private float shootAnimationTimer;
    private boolean shootAnimationOn;

    private boolean isLookingLeft;

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private boolean topBorderTouch;
    private boolean bottomBorderTouch;
    private boolean rightBorderTouch;
    private boolean leftBorderTouch;

    private float sqrtSpeed;

    private Texture defaultTexture;
    private Sprite defaultSprite;
    private Texture shootingTexture;
    private Sprite shootingSprite;

    public Player(String name) {
        this.name = name;

        this.lives = 3;
        this.score = 0;

        missiles = new ArrayList<Missile>();
        isLookingLeft = false;

        helper = new ShootingHelper(this);

        speed = 6;
        sqrtSpeed = ((float) Math.sqrt((speed * speed) / 2));

        shootTime = .3f;
        shootTimer = 0;
        canShoot = true;
        shootAnimationTime = .15f;
        shootAnimationTimer = 0;
        shootAnimationOn = false;

        width = 80;
        height = 70;

        defaultTexture = new Texture(Gdx.files.internal("characters/harambe_default.png"));
        defaultSprite = new Sprite(defaultTexture);
        defaultSprite.setSize(width, height);
        shootingTexture = new Texture(Gdx.files.internal("characters/harambe_shooting.png"));
        shootingSprite = new Sprite(shootingTexture);
        shootingSprite.setSize(width, height);

        activeSprite = defaultSprite;

        x = JungleWarsGame.WIDTH / 2 - activeSprite.getWidth() / 2;
        y = JungleWarsGame.HEIGHT / 2 - activeSprite.getHeight() / 2;

    }

    public void handleInput() {
        upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        topBorderTouch = y >= JungleWarsGame.HEIGHT - (activeSprite.getHeight());
        bottomBorderTouch = y <= 0;
        leftBorderTouch = x <= 0;
        rightBorderTouch = x >= JungleWarsGame.WIDTH - (activeSprite.getWidth());

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && canShoot) {
            shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            activeSprite = shootingSprite;
            shootAnimationOn = true;
        } else if (!shootAnimationOn) {
            activeSprite = defaultSprite;
        }

        float currentSpeed = speed;

        if ((upPressed && (leftPressed || rightPressed)) ||
                (downPressed && (leftPressed || rightPressed))) {
            currentSpeed = sqrtSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            y = topBorderTouch ? JungleWarsGame.HEIGHT - (activeSprite.getHeight()) : y + currentSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            y = bottomBorderTouch ? 0 : y - currentSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            isLookingLeft = true;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            x = leftBorderTouch ? 0 : x - currentSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            isLookingLeft = false;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            x = rightBorderTouch ? JungleWarsGame.WIDTH - (activeSprite.getWidth()) : x + currentSpeed;
        }

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
            helper.activeSprite.flip(true, false);
        }

    }

    public void update(float dt) {
        if (shootTimer > shootTime) {
            canShoot = true;
            shootTimer = 0;
        } else {
            shootTimer += dt;
        }

        if (shootAnimationOn) {
            if (shootAnimationTimer > shootAnimationTime) {
                activeSprite = defaultSprite;
                shootAnimationTimer = 0;
                shootAnimationOn = false;
            } else {
                shootAnimationTimer += dt;
            }
        }

        for (int i = 0; i < missiles.size(); i++) {
            if (missiles.get(i).shouldRemove()) {
                missiles.remove(i);
                i--;
            }
            //bullets van helper removen
        }
        for (int i = 0; i < getHelper().getMissiles().size(); i++) {
            if (getHelper().getMissiles().get(i).shouldRemove()) {
                getHelper().getMissiles().remove(i);
                i--;
            }
        }
    }

    public void render(SpriteBatch batch) {

        //render player
        activeSprite.setOriginCenter();
        activeSprite.setPosition(x, y);
        activeSprite.draw(batch);

        //render bananas
        for (Missile missile : missiles) {
            missile.render(batch);
        }
        //render Helper
        helper.render(batch);
        for (HelperMissile missile : helper.getMissiles()) {
            missile.render(batch);
        }


    }

    public boolean getIslookingLeft() {
        return isLookingLeft;
    }

    private void shoot(float clickX, float clickY) {
        canShoot = false;
        shootTimer = 0;
        helper.shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        clickX -= 16;
        clickY -= 14;
        float missileX = x;
        if (!isLookingLeft) {
            missileX += activeSprite.getWidth() / 2 + 8;
        }
        float missileY = y + activeSprite.getHeight() - 28;
        float radians = MathUtils.atan2(clickY - missileY, clickX - missileX);
        missiles.add(new Missile(this, missileX, missileY, radians));
    }


    public List<Missile> getMissiles() {
        return missiles;
    }

    public Helper getHelper() {
        return helper;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getLives() {
        return lives;
    }

    public void substractLife() {
        lives -= 1;
    }

    public String getName() {
        return name;
    }
}
