package be.howest.junglewars.gameobjects.helper.movementType.impl;


import be.howest.junglewars.gameobjects.helper.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class FollowingMovement implements IHelperMovementType {

    @Override
    public Vector2 movementType(Helper helper, float dt){ // VOLGEN
        float speed = Math.round(helper.getOwner().getSpeed() *0.85f);
        float radians = MathUtils.atan2((helper.getOwner().getBody().getY() +50) - helper.getBody().y, (helper.getOwner().getBody().getX()-25) - helper.getBody().x);
        return new Vector2(helper.getBody().x += MathUtils.cos(radians) * speed * dt, helper.getBody().y += MathUtils.sin(radians) * speed * dt);

    }

}
