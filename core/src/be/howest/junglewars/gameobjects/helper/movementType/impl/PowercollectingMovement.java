package be.howest.junglewars.gameobjects.helper.movementType.impl;

import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperMovementType;
import be.howest.junglewars.gameobjects.power.Power;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class PowercollectingMovement implements IHelperMovementType {


    @Override
    public Vector2 movementType(Helper helper, float dt){
        for(Power power : helper.checkCollision(helper.game.getData().getPowers())){
            power.collectedBy(helper.getOwner());
        }
        float speed = helper.getSpeed();
        float radians = MathUtils.atan2(helper.getNearest(helper.game.getData().getPowers()).getPosition().y - helper.getBody().y, helper.getNearest(helper.game.getData().getPowers()).getPosition().x - helper.getBody().x);
        return new Vector2(helper.getBody().x += MathUtils.cos(radians) * speed * dt, helper.getBody().y += MathUtils.sin(radians) * speed * dt);


    }

}
