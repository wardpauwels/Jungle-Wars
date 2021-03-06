package be.howest.junglewars.gameobjects.helper.movementType.impl;

import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperMovementType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CoincollectingMovement implements IHelperMovementType {

    @Override
    public Vector2 movementType(Helper helper, float dt){
        if(helper.upgrade){
            helper.setSpeed(helper.getSpeed()*1.1f);
            helper.upgrade = false;
        }
        for(Currency coin : helper.checkCollision(helper.game.getData().getCurrencies())){
            coin.collectedBy(helper.getOwner());
        }
        float speed = helper.getSpeed();
        float radians = MathUtils.atan2(helper.getNearest(helper.game.getData().getCurrencies()).getPosition().y - helper.getBody().y, helper.getNearest(helper.game.getData().getCurrencies()).getPosition().x - helper.getBody().x);
        return new Vector2(helper.getBody().x += MathUtils.cos(radians) * speed * dt, helper.getBody().y += MathUtils.sin(radians) * speed * dt);
    }
}
