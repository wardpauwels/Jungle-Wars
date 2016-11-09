package be.howest.junglewars.models.missiles;

import be.howest.junglewars.models.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Missile extends Model {

    private int damage;
    private float rotationSpeed;

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    private float radians;
    private float destinationX;
    private float destinationY;
    private float posX;
    private float posY;
    private float dx;
    private float dy;

    private float rico;

    public Missile(float playerX, float playerY, float destinationX, float destinationY) {
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.posX = playerX;
        this.posY = playerY;

        texture = new Texture(Gdx.files.internal("missile/Banana.png"));
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth() * 0.5f, sprite.getHeight() * 0.5f);
        damage = 10;
        speed = 10;
        rotationSpeed = 5;
        lifeTime = 10f;
        lifeTimer = 0;

        rico = (destinationY - posY) / (destinationX - posX);
        System.out.println(rico);

        //radians = MathUtils.PI/2;

        dx = posX;
        dy = posY;
        //dx = (MathUtils.cos(radians)) + (destinationX - posX);
        //dy = (MathUtils.sin(radians)) + (destinationY - posY);
    }

    public void update(float dt) {
        dx += rico * speed * dt;
        dy -= 1 * speed * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        sprite.setOriginCenter();
        sprite.setPosition(dx - sprite.getWidth() / 2, dy - sprite.getHeight() / 2);
        sprite.draw(batch);
    }

    public boolean shouldRemove() {
        return remove;
    }
}
