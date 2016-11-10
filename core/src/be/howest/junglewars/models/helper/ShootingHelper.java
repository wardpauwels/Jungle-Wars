package be.howest.junglewars.models.helper;

import be.howest.junglewars.models.Player;
import be.howest.junglewars.models.missiles.HelperMissile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

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
    private boolean areWingsUp;

    private Sound laser;


    private ArrayList<HelperMissile> missiles;

    public ShootingHelper(Player owner) {

        super(owner);
        this.owner = owner;

        defaultTexture = new Texture(Gdx.files.internal("characters/helpers/red/drone_default_white_wings.png"));
        defaultSprite = new Sprite(defaultTexture);
        defaultSprite.setSize(width, height);
        flyTexture = new Texture(Gdx.files.internal("characters/helpers/red/drone_default_white_wings_down.png"));
        flySprite = new Sprite(flyTexture);
        flySprite.setSize(width, height);
        laser = Gdx.audio.newSound(Gdx.files.internal("sound/plop2.wav"));


        sprite = defaultSprite;
        areWingsUp = true;

        flyAnimationTime = .05f;
        flyAnimationTimer = 0;

        missiles = new ArrayList<HelperMissile>();

    }

    public void render(SpriteBatch batch) {

        //render Helper
        sprite.setOriginCenter();
        sprite.setPosition(x, y);
        sprite.draw(batch);


    }

    public void update(float dt){
        setCoordinates(owner.getX() - 30, owner.getY() + 70);

        if(flyAnimationTimer > flyAnimationTime){
            flyAnimationTimer = 0;
            if(areWingsUp){
                sprite = flySprite;
                areWingsUp = false;
            } else {
                sprite = defaultSprite;
                areWingsUp = true;

            }
        } else {
            flyAnimationTimer += dt;
        }
    }


    @Override
    public void shoot(int dx, int dy) {
        //shootTimer = 0;
        float missileX = x;
        float missileY = y;
        //float radians = MathUtils.atan2(enemy.getY() - missileY, enemy.getX() - missileX);
        float radians = MathUtils.atan2(dy - missileY, dx - missileX);
        missiles.add(new HelperMissile(this.owner, missileX, missileY, radians));
        laser.play();
    }

    public ArrayList<HelperMissile> getMissiles(){
        return missiles;
    }




}
