package be.howest.junglewars.models.missiles;

import be.howest.junglewars.models.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Missile extends Model {

    private Player owner;

    private int damage;

    private float rotationSpeed;

    private float dx;
    private float dy;

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
    private float radians;
    private float destinationX;
    private float destinationY;
    private float posX;
    private float posY;
    private float dx;
    private float dy;

    public Missile(float playerX, float playerY, float destinationX, float destinationY, Player owner){
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.posX = playerX;
        this.posY = playerY;
        this.owner = owner;
=======

    public Missile(float startX, float startY, float radians) {
        x = startX;
        y = startY;
>>>>>>> shoot animation & shoot direction fix

        texture = new Texture(Gdx.files.internal("missile/Banana.png"));
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);

        damage = 10;
<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
        speed = 10;
        rotationSpeed = 5;
        lifeTime = 0.5f;
        lifeTimer = 0;
=======
>>>>>>> shoot animation & shoot direction fix

        speed = 300;
        rotationSpeed = -10;

<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
        dx = MathUtils.cos(radians/2) * (destinationX - playerX) * speed;
        dy = MathUtils.sin(radians/2) * (destinationY - playerY) * speed;
    }

    public void update(float dt){
        posX += dx * dt;
        posY += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime){
=======
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;

        lifeTime = 3;
        lifeTimer = 0;
    }

    public void update(float dt) {
        x += dx * dt;
        y += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
>>>>>>> shoot animation & shoot direction fix
            remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        sprite.setOriginCenter();
<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
        sprite.setPosition(posX - sprite.getWidth()/2, posY - sprite.getHeight()/2);
=======
        sprite.setPosition(x - width / 2, y - height / 2);
        sprite.rotate(rotationSpeed);
>>>>>>> shoot animation & shoot direction fix
        sprite.draw(batch);
    }

    public boolean shouldRemove() {
        return remove;
    }
}
