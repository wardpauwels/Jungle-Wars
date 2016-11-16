package be.howest.junglewars.gameobjects.power;

public enum PowerAction {
    DOUBLE_DAMAGE {
        @Override
        public void activatePower(Power power) {

        }
    };

    public abstract void activatePower(Power power);
}