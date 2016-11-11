package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.*;
import be.howest.junglewars.gameobjects.missiles.HelperMissile;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class Helper extends GameObject {

    private Player owner;

    private Texture defaultTexture;
    private Sprite defaultSprite;
    private Texture flyTexture;
    private Sprite flySprite;

    private float flyAnimationTime;
    private float flyAnimationTimer;
    private boolean areWingsUp;


    private ArrayList<HelperMissile> missiles;

    public Helper(Player owner) {

        this.owner = owner;
        width = 50;
        height = 50;
        setCoordinates(owner.getX() + 20, owner.getY() + 70);


    }

    public void update(float dt){
        setCoordinates(owner.getX() - 30, owner.getY() + 70);

        if(flyAnimationTimer > flyAnimationTime){
            flyAnimationTimer = 0;
            if(areWingsUp){
                activeSprite = flySprite;
                areWingsUp = false;
            } else {
                activeSprite = defaultSprite;
                areWingsUp = true;

            }
        } else {
            flyAnimationTimer += dt;
        }


    }

    protected void setCoordinates(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void shoot(int x, int y){}

    public ArrayList<HelperMissile> getMissiles(){return missiles;}
}
