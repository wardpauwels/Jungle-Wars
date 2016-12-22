package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.helper.movementType.impl.CoincollectingMovement;
import be.howest.junglewars.gameobjects.helper.movementType.impl.PowercollectingMovement;
import be.howest.junglewars.gameobjects.helper.movementType.impl.RotatingMovement;
import be.howest.junglewars.gameobjects.helper.movementType.impl.FollowingMovement;

public enum HelperMovementType {

    PROTECTING_HELPER{
        @Override
        IHelperMovementType getHelperMovement() {
            return new RotatingMovement();
        }
    },
    COINCOLLECTING_HELPER{
        @Override
        IHelperMovementType getHelperMovement() {
            return new CoincollectingMovement();
        }
    },
    POWERCOLLECTING_HELPER{
        @Override
        IHelperMovementType getHelperMovement() {
            return new PowercollectingMovement();
        }
    },
    FOLLOWING_HELPER{
        @Override
        IHelperMovementType getHelperMovement() {
            return new FollowingMovement();
        }

    };

    abstract IHelperMovementType getHelperMovement();



}
