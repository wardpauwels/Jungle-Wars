package be.howest.junglewars.models.missiles;
import be.howest.junglewars.models.Model;
import be.howest.junglewars.models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jensthiel on 10/11/16.
 */
public class HelperMissile extends Model{

    private int damage;

    private Player owner;

    private float rotationSpeed;

    private float dx;
    private float dy;

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;

    public HelperMissile(Player owner, float startX, float startY, float radians) {

        this.owner = owner;
        x = startX;
        y = startY;

        texture = new Texture(Gdx.files.internal("missile/DroneBullet.png"));
        sprite = new Sprite(texture);
        sprite.setSize(10,10);

        damage = 10;
        //TODO speed aanpassen naar iets dat niet afhangt van owner;
        speed = 300;
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
        sprite.setOriginCenter();
        sprite.setPosition(x - width / 2, y - height / 2);
        sprite.rotate(rotationSpeed);
        sprite.draw(batch);
    }

    public boolean shouldRemove() {
        return remove;
    }

}
