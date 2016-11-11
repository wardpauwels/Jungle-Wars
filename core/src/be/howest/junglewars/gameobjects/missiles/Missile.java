package be.howest.junglewars.gameobjects.missiles;

import be.howest.junglewars.gameobjects.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Missile extends GameObject {

    private int damage;

    private Player owner;

    private float rotationSpeed;

    private float dx;
    private float dy;

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;


    public Missile(Player owner, float startX, float startY, float radians) {
        this.owner = owner;
        x = startX;
        y = startY;

        texture = new Texture(Gdx.files.internal("missile/Banana.png"));
        activeSprite = new Sprite(texture);
        activeSprite.setSize(activeSprite.getWidth() * 0.5f, activeSprite.getHeight() * 0.5f);

        damage = 10;
        //TODO speed aanpassen naar iets dat niet afhangt van owner;
        speed = owner.getSpeed() * 100;
        rotationSpeed = -10;

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
            remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        activeSprite.setOriginCenter();
        activeSprite.setPosition(x - width / 2, y - height / 2);
        activeSprite.rotate(rotationSpeed);
        activeSprite.draw(batch);
    }

    public boolean shouldRemove() {
        return remove;
    }
}
