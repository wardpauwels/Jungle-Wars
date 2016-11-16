package be.howest.junglewars.entities;

import be.howest.junglewars.gameobjects.enemy.AttackType;
import be.howest.junglewars.gameobjects.enemy.TargetSelection;

import java.util.Arrays;

public class EnemyEntity {

    private String name;
    private String spriteUrl;
    private int scoreWhenKilled;
    private int xpWhenKilled;
    private int baseDamage;
    private int baseSpeed;
    private int baseHp;
    private int rarity;
    private String movementType;
    private String attackTypes; // String of enums, seperated by commas
    private String targetSelection;
    private float width;
    private float height;

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

    public String getMovementType() {
        return movementType; // TODO: serialized getter
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getAttackTypes() {
        return attackTypes;
    }

    public void setAttackTypes(String attackTypes) {
        this.attackTypes = attackTypes;
    }

    public void setAttackTypes(AttackType[] attackTypes){ // TODO: check if this works
        String[] names = new String[attackTypes.length];

        for(int i= 0; i<attackTypes.length;i++){
            names[i] = attackTypes[i].name();
        }

        this.attackTypes = String.join(",", (CharSequence[]) names);
    }

    public AttackType[] getAttackTypesSerialized(){ // Converts database string of enums to real enum
        return (AttackType[]) Arrays.stream(attackTypes.split(",")).map(AttackType::valueOf).toArray(); // TODO: check if this works
    }

    public TargetSelection getTargetSelection(){
        return null; // TODO
    }

    public void setTargetSelection(){
        // TODO
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

}
