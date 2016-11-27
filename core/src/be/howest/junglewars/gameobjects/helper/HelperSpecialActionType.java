package be.howest.junglewars.gameobjects.helper;

public enum HelperSpecialActionType {
    SHOOTER {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: search nearest enemy and shoot him
        }
    },
    MELEE {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: check collision with enemy and deal damage
        }
    },
    SHIELD {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: blocks every bullet that he gets
        }
    },
    CURRENCY_COLLECTOR {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: collects currency on collision
        }
    },
    POWER_COLLECTOR {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: collects power on collision
        }
    };

    public abstract void doSpecialAction(Helper helper);
}
