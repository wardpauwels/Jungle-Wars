package be.howest.junglewars.gameobjects.power;

public interface IPowerType {

    void activatePower(Power power);

    void deactivatePower(Power power);

    int initBonusValue(Power power);


}
