package be.howest.junglewars.gameobjects.helper.movementType.impl;

import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperMovementType;
import com.badlogic.gdx.math.Vector2;

public class RotatingMovement implements IHelperMovementType {

    private double angle = 0;


    @Override
    public Vector2 movementType(Helper helper, float dt){
        angle += dt;
        angle = ((Math.PI/180) + angle);

        double rotationSpeed = angle * helper.getSpeed();

        float rotatedX = (float) (helper.getOwner().getBody().getX()+ (helper.getOwner().getBody().getWidth()/5) + Math.cos(rotationSpeed) * 100f);
        float rotatedY = (float) (helper.getOwner().getBody().getY()+ (helper.getOwner().getBody().getHeight()/5)+ Math.sin(rotationSpeed) * 100f);

        return new Vector2(rotatedX, rotatedY);
    }


}
