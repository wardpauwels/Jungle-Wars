package be.howest.junglewars.models.helper;

import be.howest.junglewars.models.*;
import be.howest.junglewars.models.missiles.HelperMissile;

import java.util.ArrayList;

public class Helper extends Model {

    private Player owner;

    private ArrayList<HelperMissile> missiles;

    public Helper(Player owner) {

        this.owner = owner;
        width = 50;
        height = 50;
        setCoordinates(owner.getX() + 20, owner.getY() + 70);


    }

    public void update(float dt){


    }

    protected void setCoordinates(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void shoot(int x, int y){}

    public ArrayList<HelperMissile> getMissiles(){return missiles;}
}
