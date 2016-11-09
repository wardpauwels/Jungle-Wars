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
    private float bananaX;
    private float bananaY;
    private float dx;
    private float dy;

    private float rico;

    public Missile(float playerX, float playerY, float destinationX, float destinationY){
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.bananaX = playerX;
        this.bananaY = playerY;

        texture = new Texture(Gdx.files.internal("missile/Banana.png"));
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth()*0.5f, sprite.getHeight()*0.5f);
        damage = 10;
        speed = 2f;
        rotationSpeed = -10;
        //lifeTime = 10f;
        lifeTimer = 0;

        radians = MathUtils.PI/2;

        System.out.printf("radians %f%n destinationsx %f%n destinationy %f%n playerX %f%n playerY %f%n speed %f%n cos %f%n sin %f%n",
                radians, destinationX, destinationY, this.bananaX, this.bananaY, speed, MathUtils.cos(radians), MathUtils.sin(radians));

        dx = (MathUtils.cos(radians)) + (destinationX - playerX) * speed;
        dy = (MathUtils.sin(radians)) + (destinationY - playerY) * speed;
        System.out.println("dx: "+dx);
        System.out.println("dy: "+dy);
    }

    public void update(float dt){

        bananaX += dx * dt;
        bananaY += dy * dt;

        /*lifeTimer += dt;
        if (lifeTimer > lifeTime){
            remove = true;
        }*/
    }

    public void render(SpriteBatch batch){
        sprite.setOriginCenter();
        sprite.setPosition(bananaX - sprite.getWidth()/2, bananaY - sprite.getHeight()/2);
        sprite.rotate(rotationSpeed);
        sprite.draw(batch);
    }

    public boolean shouldRemove(){ return remove;}
}
