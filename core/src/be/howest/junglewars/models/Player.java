package be.howest.junglewars.models;

import be.howest.junglewars.game.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Player extends Model {

    private String name;

    private int lives;
    private int score;

    private float missileTime;
    private float missileTimer;

    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    private boolean topBorderTouch;
    private boolean bottomBorderTouch;
    private boolean rightBorderTouch;
    private boolean leftBorderTouch;

    private float sqrtSpeed;

    public Player(String name) {
        this.name = name;

        this.lives = 3;
        this.score = 0;

        speed = 7;
        sqrtSpeed = ((float) Math.sqrt((speed * speed) / 2));

        texture = new Texture(Gdx.files.internal("characters/harambe_default.png"));
        sprite = new Sprite(texture);
        sprite.setSize(texture.getWidth() * 0.7f, texture.getHeight() * 0.7f);
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
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            x = leftBorderTouch ? 0 : x - currentSpeed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            x = rightBorderTouch ? JungleWarsGame.WIDTH - (sprite.getWidth()) : x + currentSpeed;
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }

    public void shoot() {
        // TODO: Schieten om halve seconden
    }

}
