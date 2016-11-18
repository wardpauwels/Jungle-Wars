package be.howest.junglewars.data.entities;

public class EnemyEntity {
    private int id;
    private String name;
    private String textureFileName;
    private int baseDamage;
    private int baseSpeed;
    private int baseHitpoints;
    private float baseAttackSpeed;
    private int experienceWhenKilled;
    private int scoreWhenKilled;
    private int rarity;
    private String targetSelection;
    private String attackTypes;
    private String movementType;

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

    public String getTargetSelection() {
        return targetSelection;
    }

    public void setTargetSelection(String targetSelection) {
        this.targetSelection = targetSelection;
    }

    public String getAttackTypes() {
        return attackTypes;
    }

    public void setAttackTypes(String attackTypes) {
        this.attackTypes = attackTypes;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnemyEntity that = (EnemyEntity) o;

        if (id != that.id) return false;
        if (baseDamage != that.baseDamage) return false;
        if (baseSpeed != that.baseSpeed) return false;
        if (baseHitpoints != that.baseHitpoints) return false;
        if (Double.compare(that.baseAttackSpeed, baseAttackSpeed) != 0) return false;
        if (experienceWhenKilled != that.experienceWhenKilled) return false;
        if (scoreWhenKilled != that.scoreWhenKilled) return false;
        if (rarity != that.rarity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (textureFileName != null ? !textureFileName.equals(that.textureFileName) : that.textureFileName != null)
            return false;
        if (targetSelection != null ? !targetSelection.equals(that.targetSelection) : that.targetSelection != null)
            return false;
        if (attackTypes != null ? !attackTypes.equals(that.attackTypes) : that.attackTypes != null) return false;
        if (movementType != null ? !movementType.equals(that.movementType) : that.movementType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (textureFileName != null ? textureFileName.hashCode() : 0);
        result = 31 * result + baseDamage;
        result = 31 * result + baseSpeed;
        result = 31 * result + baseHitpoints;
        temp = Double.doubleToLongBits(baseAttackSpeed);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + experienceWhenKilled;
        result = 31 * result + scoreWhenKilled;
        result = 31 * result + rarity;
        result = 31 * result + (targetSelection != null ? targetSelection.hashCode() : 0);
        result = 31 * result + (attackTypes != null ? attackTypes.hashCode() : 0);
        result = 31 * result + (movementType != null ? movementType.hashCode() : 0);
        return result;
    }
}
