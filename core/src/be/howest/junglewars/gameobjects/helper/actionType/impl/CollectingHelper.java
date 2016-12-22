package be.howest.junglewars.gameobjects.helper.actionType.impl;

import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperActionType;

public class CollectingHelper implements IHelperActionType {

    @Override
    public void helperAction(Helper helper) {
        helper.setProtecting(false);
    }

    @Override
    public void helperUpgrade(Helper helper) {
        helper.setSpeed(helper.getSpeed()* 1.1f);
    }
}
