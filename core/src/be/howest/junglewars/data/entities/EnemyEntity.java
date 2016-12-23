package be.howest.junglewars.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "enemies", schema = "junglewars")
public class EnemyEntity {

    private int spawnChance;
    //region Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sprite")
    private String textureFileName;

    @Column(name = "alt_sprite")
    private String altTextureFileName;

    @Column(name = "width")
    private float width;

    @Column(name = "height")
    private float height;

    @Column(name = "dmg")
    private int baseDamage;

    @Column(name = "speed")
    private int baseSpeed;

    @Column(name = "hp")
    private int baseHitpoints;

    @Column(name = "base_attack_speed")
    private float baseAttackSpeed;

    @Column(name = "killed_xp")
    private int experienceWhenKilled;

    @Column(name = "killed_score")
    private int scoreWhenKilled;

    @Column(name = "spawn_chance")
    private int rarity;

    @Column(name = "movement_type")
    private String movementType;

    @Column(name = "target_type")
    private String targetSelectionType;

    @Column(name = "action_type")
    private String attackType;
    private int spawnProbabitlity;
    private int spawnProbability;
    //endregion

    public EnemyEntity(){

    }

    public EnemyEntity(int id, String name, String textureFileName, String altTextureFileName, float width, float height, int baseDamage, int baseSpeed, int baseHitpoints, float baseAttackSpeed, int experienceWhenKilled, int scoreWhenKilled, int rarity, String movementType, String targetSelectionType, String attackType, int spawnChance) {
        this.name = name;
        this.id = id;
        this.textureFileName = textureFileName;
        this.altTextureFileName = altTextureFileName;
        this.width = width;
        this.height = height;
        this.baseDamage = baseDamage;
        this.baseSpeed = baseSpeed;
        this.baseHitpoints = baseHitpoints;
        this.baseAttackSpeed = baseAttackSpeed;
        this.experienceWhenKilled = experienceWhenKilled;
        this.scoreWhenKilled = scoreWhenKilled;
        this.rarity = rarity;
        this.movementType = movementType;
        this.targetSelectionType = targetSelectionType;
        this.attackType = attackType;
        this.spawnChance = spawnChance;
    }

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

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getTargetSelectionType() {
        return targetSelectionType;
    }

    public void setTargetSelectionType(String targetSelectionType) {
        this.targetSelectionType = targetSelectionType;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getAltTextureFileName() {
        return altTextureFileName;
    }

    public void setAltTextureFileName(String altTextureFileName) {
        this.altTextureFileName = altTextureFileName;
    }

    public int getSpawnChance() {
        return 0;
    }

    public void setSpawnProbabitlity(int spawnProbabitlity) {
        this.spawnProbabitlity = spawnProbabitlity;
    }

    public void setSpawnProbability(int spawnProbability) {
        this.spawnProbability = spawnProbability;
    }

    public int getSpawnProbability() {
        return spawnProbability;
    }
    //endregion


}