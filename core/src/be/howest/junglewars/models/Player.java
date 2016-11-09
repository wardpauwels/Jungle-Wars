package be.howest.junglewars.models;

import be.howest.junglewars.game.*;
import be.howest.junglewars.models.missiles.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

import java.util.*;

import java.util.*;

public class Player extends Model {

    private String name;

    private ArrayList<Missile> missiles;

    private int lives;
    private int score;

    private float missileTime;
    private float missileTimer;

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
    public static final int DEFAULT_SPRITE = 0;
    private Texture shootingTexture;
    private Sprite shootingSprite;
    public static final int SHOOTING_SPRITE = 1;

    boolean isShooting;

    public Player(String name) {
        this.name = name;

        this.lives = 3;
        this.score = 0;

        missiles = new ArrayList<Missile>();
        isLookingLeft = false;

        speed = 7;

        sqrtSpeed = ((float) Math.sqrt((speed * speed) / 2));

        defaultTexture = new Texture(Gdx.files.internal("characters/harambe_default.png"));
        defaultSprite = new Sprite(defaultTexture);
        defaultSprite.setSize(defaultTexture.getWidth() * 0.7f, defaultTexture.getHeight() * 0.7f);
        shootingTexture = new Texture(Gdx.files.internal("characters/harambe_shooting.png"));
        shootingSprite = new Sprite(shootingTexture);

        isShooting = false;

    }

    private void initSprite(Texture texture, Sprite sprite, float width, float height) {
//        sprite.setSize(sprite.getTexture().getWidth() * 0.7f, sprite.getTexture().getHeight() * 0.7f);

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
            if (!isLookingLeft) {
                sprite.flip(true, false);
                isLookingLeft = true;
            }
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            x = leftBorderTouch ? 0 : x - currentSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (isLookingLeft) {
                sprite.flip(true, false);
                isLookingLeft = false;
            }
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            x = rightBorderTouch ? JungleWarsGame.WIDTH - (sprite.getWidth()) : x + currentSpeed;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
            shoot(Gdx.input.getX(), JungleWarsGame.HEIGHT - Gdx.input.getY());
=======
            shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            isShooting = true;
        } else {
            isShooting = false;
>>>>>>> shoot animation & shoot direction fix
        }
    }

    public void update(float dt) {
        for (int i = 0; i < missiles.size(); i++) {
            if (missiles.get(i).shouldRemove()) {
                missiles.remove(i);
                i--;
            }
        }
    }

    public void render(SpriteBatch batch) {
<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
        //render bananas
        for (int i = 0; i < missiles.size(); i++) {
            missiles.get(i).render(batch);
        }

        //render player
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    public void shoot(float x, float y) {
        missiles.add(new Missile(this.getX() + sprite.getWidth() / 2, this.getY() + sprite.getHeight() / 2, x, y, this));
=======

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
        float radians = MathUtils.atan2(clickY - y, clickX - x);
        missiles.add(new Missile(x, y, radians));
>>>>>>> shoot animation & shoot direction fix
    }

    public List<Missile> getMissiles() {
        return missiles;
    }
}
