package be.howest.junglewars.gameobjects.power;

public interface IPowerAction {

    void activatePower(Power power);

    void deactivatePower(Power power);

    PowerType getPowerType();

}
