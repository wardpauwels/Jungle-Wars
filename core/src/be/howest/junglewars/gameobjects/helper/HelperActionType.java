package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.helper.actionType.impl.CollectingHelper;
import be.howest.junglewars.gameobjects.helper.actionType.impl.ProtectingHelper;
import be.howest.junglewars.gameobjects.helper.actionType.impl.ShootingHelper;
import be.howest.junglewars.gameobjects.helper.actionType.impl.StabbingHelper;

public enum HelperActionType {

    COLLECTING_HELPER{
        @Override
        IHelperActionType getHelperAction(){return new CollectingHelper();}
    },
    PROTECTING_HELPER{
        @Override
        IHelperActionType getHelperAction(){return new ProtectingHelper();}
    },
    SHOOTING_HELPER{
        @Override
        IHelperActionType getHelperAction(){return new ShootingHelper();}
    },
    STABBING_HELPER{
        @Override
        IHelperActionType getHelperAction(){return new StabbingHelper();}
    };

    abstract IHelperActionType getHelperAction();
}
