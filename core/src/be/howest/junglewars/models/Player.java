package be.howest.junglewars.models;

import be.howest.junglewars.game.*;
import be.howest.junglewars.models.missiles.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.util.*;

public class Player extends Model {

    private String name;

    private ArrayList<Missile> missiles;

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

        sprite = defaultSprite;

        x = JungleWarsGame.WIDTH / 2 - sprite.getWidth() / 2;
        y = JungleWarsGame.HEIGHT / 2 - sprite.getHeight() / 2;

    }

    public void handleInput() {
        upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        topBorderTouch = y >= JungleWarsGame.HEIGHT - (sprite.getHeight());
        bottomBorderTouch = y <= 0;
        leftBorderTouch = x <= 0;
        rightBorderTouch = x >= JungleWarsGame.WIDTH - (sprite.getWidth());

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && canShoot) {
            shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            sprite = shootingSprite;
            shootAnimationOn = true;
        } else if (!shootAnimationOn) {
            sprite = defaultSprite;
        }

        float currentSpeed = speed;

        if ((upPressed && (leftPressed || rightPressed)) ||
                (downPressed && (leftPressed || rightPressed))) {
            currentSpeed = sqrtSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            y = topBorderTouch ? JungleWarsGame.HEIGHT - (sprite.getHeight()) : y + currentSpeed;
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
            x = rightBorderTouch ? JungleWarsGame.WIDTH - (sprite.getWidth()) : x + currentSpeed;
        }

        if (sprite.isFlipX() != isLookingLeft) {
            sprite.flip(true, false);
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
                sprite = defaultSprite;
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
        }
    }

    public void render(SpriteBatch batch) {

        //render player
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);

        //render bananas
        for (Missile missile : missiles) {
            missile.render(batch);
        }

    }

    private void shoot(float clickX, float clickY) {
        canShoot = false;
        shootTimer = 0;

        clickX -= 16;
        clickY -= 14;
        float missileX = x;
        if (!isLookingLeft) {
            missileX += sprite.getWidth() / 2 + 8;
        }
        float missileY = y + sprite.getHeight() - 28;
        float radians = MathUtils.atan2(clickY - missileY, clickX - missileX);
        missiles.add(new Missile(this, missileX, missileY, radians));
    }

    public List<Missile> getMissiles() {
        return missiles;
    }

    public int getScore(){
        return score;
    }

    public void addScore(int score){
        this.score += score;
    }

    public int getLives(){
        return lives;
    }

    public void substractLife(){
        lives -= 1;
    }
}
