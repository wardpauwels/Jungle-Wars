package be.howest.junglewars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private int lives;
    private int score;

    private float shootTime;
    private float shootTimer;
    private boolean canShoot;

    private boolean isLookingLeft;

    private int level;

    public Player(String name, float width, float height, String textureUrl) {
        super(width, height, textureUrl);
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
            position.x = rightBorderTouch ? 0 : position.x - currentSpeed;
        }
        if (keyRightPressed) {
            isLookingLeft = false;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            position.x = rightBorderTouch ? Gdx.graphics.getWidth() - bounds.getWidth() : position.x + currentSpeed;
        }

        if (activeSprite.isFlipX() != isLookingLeft) {
            activeSprite.flip(true, false);
        }
    }

    private void shoot(float x, float y) {
        canShoot = false;
        shootTimer = 0;
        x -= 16;
        y -= 14;

        float missileX = position.x;
        if (!isLookingLeft) missileX += bounds.getWidth() / 2 + 8;

        float missileY = position.y + bounds.getHeight() - 28;
        float radians = MathUtils.atan2(y - missileY, x - missileX);
        missiles.add(new Missile(this, missileX, missileY, radians, "banana.png"));
    }

    @Override
    protected void setAnimationFrames() {

    }

    @Override
    protected Vector2 generateSpawnPosition() {
        return new Vector2();
    }

    @Override
    protected void update(float dt) {
        handleInput();

        // TODO remove missiles
//        for (int i = 0; i < missiles.size(); i++) {
//            if (missiles.get(i).shouldRemove()) {
//                missiles.remove(i);
//                i--;
//            }
//        }
//        for (int i = 0; i < getHelper().getMissiles().size(); i++) {
//            if (getHelper().getMissiles().get(i).shouldRemove()) {
//                getHelper().getMissiles().remove(i);
//                i--;
//            }
//        }
    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x, position.y);
        activeSprite.draw(batch);

        // TODO render missiles and helper?
        /*
        for (Missile missile : missiles) {
            missile.draw(batch);
        }
        helper.render(batch);
        for (HelperMissile missile : helper.getMissiles()) {
            missile.draw(batch);
        }
         */
    }
}
