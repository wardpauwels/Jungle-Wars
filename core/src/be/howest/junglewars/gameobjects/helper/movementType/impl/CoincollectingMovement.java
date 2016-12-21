package be.howest.junglewars.gameobjects.helper.movementType.impl;

import be.howest.junglewars.gameobjects.Currency;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperMovementType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CoincollectingMovement implements IHelperMovementType {

    @Override
    public Vector2 movementType(Helper helper, float dt){ // TODO: GET NEAREST -- ROBERT

        for(Currency coin : helper.checkCollision(helper.game.getData().getCurrencies())){
            coin.collectedBy(helper.getOwner());
        }

//        helper.getNearest(helper.game.getData().getCurrencies()).getPosition();

        float speed = Math.round(helper.getOwner().getSpeed() *0.55f);
        float radians = MathUtils.atan2(helper.getNearest(helper.game.getData().getCurrencies()).getPosition().y - helper.getBody().y, helper.getNearest(helper.game.getData().getCurrencies()).getPosition().x - helper.getBody().x);
        return new Vector2(helper.getBody().x += MathUtils.cos(radians) * speed * dt, helper.getBody().y += MathUtils.sin(radians) * speed * dt);
    }
}
