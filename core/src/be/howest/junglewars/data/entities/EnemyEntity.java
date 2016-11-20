package be.howest.junglewars.data.entities;

import be.howest.junglewars.gameobjects.enemy.AttackType;
import be.howest.junglewars.gameobjects.enemy.MovementType;
import be.howest.junglewars.gameobjects.enemy.TargetSelectionType;
import be.howest.junglewars.util.EnumStringConverter;

import javax.persistence.*;

@Entity
@Table(name = "enemies", schema = "junglewars")
public class EnemyEntity {

    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "texture_file_name")
    private String textureFileName;

    @Column(name = "base_damage")
    private int baseDamage;

    @Column(name = "base_speed")
    private int baseSpeed;

    @Column(name = "base_hitpoints")
    private int baseHitpoints;

    @Column(name = "base_attack_speed")
    private float baseAttackSpeed;

    @Column(name = "experience_when_killed")
    private int experienceWhenKilled;

    @Column(name = "score_when_killed")
    private int scoreWhenKilled;

    @Column(name = "rarity")
    private int rarity;

    @Column(name = "movement_type")
    private String movementType;

    @Column(name = "target_selection_type")
    private String targetSelectionType;

    @Column(name = "attack_type")
    private String attackType;
    //endregion

    //region Getters/Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextureFileName() {
        return textureFileName;
    }

    public void setTextureFileName(String textureFileName) {
        this.textureFileName = textureFileName;
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

    public int getBaseHitpoints() {
        return baseHitpoints;
    }

    public void setBaseHitpoints(int baseHitpoints) {
        this.baseHitpoints = baseHitpoints;
    }

    public float getBaseAttackSpeed() {
        return baseAttackSpeed;
    }

    public void setBaseAttackSpeed(float baseAttackSpeed) {
        this.baseAttackSpeed = baseAttackSpeed;
    }

    public int getExperienceWhenKilled() {
        return experienceWhenKilled;
    }

    public void setExperienceWhenKilled(int experienceWhenKilled) {
        this.experienceWhenKilled = experienceWhenKilled;
    }

    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public void setScoreWhenKilled(int scoreWhenKilled) {
        this.scoreWhenKilled = scoreWhenKilled;
    }

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public String getMovementType() {
        return movementType;
    }

    public MovementType[] getMovementTypeEnums(){
        return EnumStringConverter.stringToEnumArray(MovementType.class, movementType);
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getTargetSelectionType() {
        return targetSelectionType;
    }

    public TargetSelectionType[] getTargetSelectionTypeEnums(){
        return EnumStringConverter.stringToEnumArray(TargetSelectionType.class, targetSelectionType);
    }

    public void setTargetSelectionType(String targetSelectionType) {
        this.targetSelectionType = targetSelectionType;
    }

    public String getAttackType() {
        return attackType;
    }

    public AttackType[] getAttackTypeEnums(){
        return EnumStringConverter.stringToEnumArray(AttackType.class, attackType);
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }
    //endregion

}