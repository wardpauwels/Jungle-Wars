package be.howest.junglewars.models.missiles;

import be.howest.junglewars.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import static sun.audio.AudioPlayer.player;

public class Missile extends Model {

    private int damage;
    private float rotationSpeed;

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    private float radians;
    private float directionX;
    private float directionY;
    private float posX;
    private float posY;
    private float dx;
    private float dy;

    public Missile(float posX, float posY, float directionX, float directionY){
        this.directionX = directionX;
        this.directionY = directionY;
        this.posX = posX;
        this.posY = posY;

        texture = new Texture(Gdx.files.internal("missile/Banana.png"));
        sprite = new Sprite(texture);
        sprite.setSize(sprite.getWidth()*0.5f, sprite.getHeight()*0.5f);
        damage = 10;
        speed = 10;
        rotationSpeed = 5;
        lifeTime = 0.5f;
        lifeTimer = 0;

        radians = MathUtils.PI/2;

        dx = MathUtils.cos(radians/2) * (directionX - posX) * speed;
        dy = MathUtils.sin(radians/2) * (directionY - posY) * speed;
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
