package be.howest.junglewars.gameobjects.helper;

public enum HelperMovementType {
    CIRCLE_AROUND_PLAYER {
        @Override
        public void move(Helper helper) {

        }

    },
    RANDOM {
        @Override
        public void move(Helper helper) {
            // TODO: randomly run around the map
        }
    },
    CURRENCY_TO_CURRENCY {
        @Override
        public void move(Helper helper) {
            // TODO: go from currency to currency
        }
    },
    POWER_TO_POWER {
        @Override
        public void move(Helper helper) {
            // TODO: go from power to power
        }
    },
    STATIC {
        @Override
        public void move(Helper helper) {
            // TODO: stays at one place
        }
    },
    FOLLOW_PLAYER {
        @Override
        public void move(Helper helper) {
            // TODO: stays near player
        }
    };

    public abstract void move(Helper helper);

    private void positionToPosition() {
        // TODO
    }
}