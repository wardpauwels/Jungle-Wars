package be.howest.junglewars.entities;

import java.util.Arrays;

public class Enemy {

    private String name;
    private String spriteUrl;
    private int scoreWhenKilled;
    private int xpWhenKilled;
    private int baseDamage;
    private int baseSpeed;
    private int baseHp;
    private int rarity;
    private String[] movementTypes;
    private String[] attackTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public void setSpriteUrl(String spriteUrl) {
        this.spriteUrl = spriteUrl;
    }

    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public void setScoreWhenKilled(int scoreWhenKilled) {
        this.scoreWhenKilled = scoreWhenKilled;
    }

    public int getXpWhenKilled() {
        return xpWhenKilled;
    }

    public void setXpWhenKilled(int xpWhenKilled) {
        this.xpWhenKilled = xpWhenKilled;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public int getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(int baseHp) {
        this.baseHp = baseHp;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String[] getMovementTypes() {
        return movementTypes;
    }

    public void setMovementTypes(String[] movementTypes) {
        this.movementTypes = movementTypes;
    }

    public String[] getAttackTypes() {
        return attackTypes;
    }

    public void setAttackTypes(String[] attackTypes) {
        this.attackTypes = attackTypes;
    }

    @Override
    public String toString() {
        return "EnemyModel{" +
                "name='" + name + '\'' +
                ", spriteUrl='" + spriteUrl + '\'' +
                ", scoreWhenKilled=" + scoreWhenKilled +
                ", xpWhenKilled=" + xpWhenKilled +
                ", baseDamage=" + baseDamage +
                ", baseSpeed=" + baseSpeed +
                ", baseHp=" + baseHp +
                ", rarity=" + rarity +
                ", movementTypes=" + Arrays.toString(movementTypes) +
                ", attackTypes=" + Arrays.toString(attackTypes) +
                '}';
    }
}
