package be.howest.junglewars.gameobjects.helper.actionType.impl;


import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperActionType;

public class StabbingHelper implements IHelperActionType {


    @Override
    public void helperAction(Helper helper) {
        if(helper.checkCollision(helper.game.getData().getEnemies()).size()!=0){
            helper.checkCollision(helper.game.getData().getEnemies()).get(0).catchDamage(20);
        }
    }
}
