package be.howest.junglewars.gameobjects.power;

public class MoreSpeedPower implements IPowerType {
    private int bonusSpeed;

    @Override
    public void activatePower(Power power) {
        bonusSpeed = Math.round(power.getOwner().getSpeed() * power.getBonusPercentage());
        power.getOwner().setSpeed(power.getOwner().getSpeed() + bonusSpeed);
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setSpeed(power.getOwner().getSpeed() - bonusSpeed);
    }
}
