package be.howest.junglewars.gameobjects.power;

public class MoreDamagePower implements IPowerType {
    private int bonusDamage;

    @Override
    public void activatePower(Power power) {
        bonusDamage = Math.round(power.getOwner().getDamage() * power.getBonusPercentage());
        power.getOwner().setDamage(power.getOwner().getDamage() + bonusDamage);
    }

    @Override
    public void deactivatePower(Power power) {
        power.getOwner().setDamage(power.getOwner().getDamage() - bonusDamage);
    }
}
