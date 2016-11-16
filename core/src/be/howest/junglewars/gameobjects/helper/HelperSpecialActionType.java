package be.howest.junglewars.gameobjects.helper;

public enum HelperSpecialActionType {
    SHOOTER {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: shoots at the nearest enemy
        }
    },
    MELEE {
        @Override
        public void doSpecialAction(Helper helper) {
            // TODO: kill enemy on collision
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
