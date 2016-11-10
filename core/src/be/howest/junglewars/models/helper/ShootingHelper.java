package be.howest.junglewars.models.helper;

import be.howest.junglewars.models.Player;
import be.howest.junglewars.models.enemies.Enemy;
import be.howest.junglewars.models.missiles.HelperMissile;
import be.howest.junglewars.models.missiles.Missile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.List;

import java.util.ArrayList;

/**
 * Created by jensthiel on 10/11/16.
 */
public class ShootingHelper extends Helper {

    private Player owner;


    private Texture defaultTexture;
    private Sprite defaultSprite;
    private Texture flyTexture;
    private Sprite flySprite;

    private float flyAnimationTime;
    private float flyAnimationTimer;
    private boolean AnimationOn;


    private ArrayList<HelperMissile> missiles;

    public ShootingHelper(Player owner) {

        super(owner);
        this.owner = owner;

        defaultTexture = new Texture(Gdx.files.internal("characters/helpers/green/drone_default.png"));
        defaultSprite = new Sprite(defaultTexture);
        defaultSprite.setSize(width, height);
        flyTexture = new Texture(Gdx.files.internal("characters/helpers/green/drone_wings.png"));
        flySprite = new Sprite(flyTexture);
        flySprite.setSize(width, height);


        sprite = defaultSprite;

        flyAnimationTime = .15f;
        flyAnimationTimer = 0;

        missiles = new ArrayList<HelperMissile>();

    }

    public void render(SpriteBatch batch) {

        //render Helper
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);


    }


    public void shoot(int dx, int dy) {
        //shootTimer = 0;
        float missileX = x;
        float missileY = y;
        //float radians = MathUtils.atan2(enemy.getY() - missileY, enemy.getX() - missileX);
        float radians = MathUtils.atan2(dy - missileY, dx - missileX);
        missiles.add(new HelperMissile(this.owner, missileX, missileY, radians));
    }

    public ArrayList<HelperMissile> getMissiles(){
        return missiles;
    }



}
