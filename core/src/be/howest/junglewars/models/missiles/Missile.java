package be.howest.junglewars.models.missiles;

import be.howest.junglewars.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

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

    public Missile(float playerX, float playerY, float destinationX, float destinationY){
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.posX = playerX;
        this.posY = playerY;

        texture = new Texture(Gdx.files.internal("missile/Banana.png"));
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth()*0.5f, sprite.getHeight()*0.5f);
        damage = 10;
        speed = 10;
        rotationSpeed = 5;
        lifeTime = 1.0f;
        lifeTimer = 0;

        radians = MathUtils.PI/2;

        dx = MathUtils.cos(radians/2) * (destinationX - playerX) * speed;
        dy = MathUtils.sin(radians/2) * (destinationY - playerY) * speed;
    }

    public void update(float dt){
        posX += dx * dt;
        posY += dy * dt;

        lifeTimer += dt;
        if (lifeTimer > lifeTime){
            remove = true;
        }
    }

    public void render(SpriteBatch batch){
        sprite.setOriginCenter();
        sprite.setPosition(posX - sprite.getWidth()/2, posY - sprite.getHeight()/2);
        sprite.draw(batch);
    }

    public boolean shouldRemove(){ return remove;}
}
