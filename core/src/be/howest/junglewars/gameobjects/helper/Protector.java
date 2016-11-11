package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.missiles.HelperMissile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by jensthiel on 10/11/16.
 */

//TODO WERKT AAN GEEN KANTEN
public class Protector extends Helper {

    private Texture defaultTexture;
    private Sprite defaultSprite;
    private Texture flyTexture;
    private Sprite flySprite;



    private Player owner;

    private ArrayList<HelperMissile> missiles;



    public Protector(Player owner) {
        super(owner);

        defaultTexture = new Texture(Gdx.files.internal("characters/helpers/green/drone_default_white_wings.png"));
        defaultSprite = new Sprite(defaultTexture);
        defaultSprite.setSize(width, height);
        flyTexture = new Texture(Gdx.files.internal("characters/helpers/green/drone_default_white_wings_down.png"));
        flySprite = new Sprite(flyTexture);
        flySprite.setSize(width, height);

        missiles = new ArrayList<HelperMissile>();

    }

    public ArrayList<HelperMissile> getMissiles(){return missiles;}



}

