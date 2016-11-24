package be.howest.junglewars.gameobjects.missile;

public enum MissileImpactType {
    NORMAL_HIT {
        @Override
        public void onImpact() {

        }
    },
    EXPLODE_AOE_HIT {
        @Override
        public void onImpact() {

        }
    },
    EXPLODE_END_LIFETIME {
        @Override
        public void onImpact() {

        }
    };

    public abstract void onImpact();

    }
