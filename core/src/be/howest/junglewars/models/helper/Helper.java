package be.howest.junglewars.models.helper;

import be.howest.junglewars.models.*;

public class Helper extends Model {

    private Player owner;

    public Helper(Player owner) {

        this.owner = owner;
        width = 50;
        height = 50;
        setCoordinates();


    }

    public void update(float dt){
        setCoordinates();

    }

    private void setCoordinates(){
        x = owner.getX() - 60;
        y = owner.getY() + 70;
    }
}
