package be.howest.junglewars.models.missiles;

import be.howest.junglewars.models.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by jensthiel on 10/11/16.
 */
public class HelperMissile extends Missile{

    public HelperMissile(Player owner, float startX, float startY, float radians) {

        super(owner, startX, startY, radians);
        texture = new Texture(Gdx.files.internal("missile/DroneBullet.png"));
        sprite = new Sprite(texture);
        speed = 10;



    }
}
