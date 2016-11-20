package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {
    private static final float ENEMY_WIDTH = 70;
    private static final float ENEMY_HEIGHT = 80;

    private String name;
    private Sprite sprite;

    private Vector2 dPosition;

    private int damage;
    private int hitpoints;
    private int speed;
    private float attackSpeed; // shoot per x seconds (e.g. "0.25" shoots 4 times a second)

    private int rarity;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    private AttackType[] attackTypes;
    private MovementType movementType;
    private TargetSelectionType targetSelection;
    private Player target;

    public Enemy(String name, String textureUrl,
                 int baseDamage, int baseSpeed, int baseHitpoints, float baseAttackSpeed,
                 int experienceWhenKilled, int scoreWhenKilled, int rarity,
                 MovementType movementType, TargetSelectionType targetSelection, AttackType[] attackTypes,
                 int gameLevel, int gameDifficulty) {
        super(ENEMY_WIDTH, ENEMY_HEIGHT, textureUrl);
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.rarity = rarity;
        this.attackTypes = attackTypes;
        this.movementType = movementType;
        this.targetSelection = targetSelection;

        calculateStats(gameLevel, gameDifficulty, baseDamage, baseHitpoints, baseSpeed, baseAttackSpeed);

    }

//    public Enemy(EnemyEntity entity, int gameLevel, int gameDifficulty) {
//        this(
//                entity.getName(),
//                entity.getTextureFileName(),
//                entity.getBaseDamage(),
//                entity.getBaseSpeed(),
//                entity.getBaseHitpoints(),
//                entity.getBaseAttackSpeed(),
//                entity.getExperienceWhenKilled(),
//                entity.getScoreWhenKilled(),
//                entity.getRarity(),
//                entity.getMovementType(),
//                entity.getTargetSelection(),
//                entity.getAttackTypes(),
//                gameLevel,
//                gameDifficulty);
//    }

    @Override
    public void update(float dt) {
        float radians = MathUtils.atan2(dPosition.y - position.y, dPosition.x - position.x);
        dPosition.set(
                MathUtils.cos(radians) * speed,
                MathUtils.sin(radians) * speed
        );
        position.add(
                dPosition.x * dt,
                dPosition.y * dt
        );
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    @Override
    protected void setAnimationFrames() {
        // TODO
    }

    @Override
    protected Vector2 generateSpawnPosition() {
        float x = MathUtils.random(0, Gdx.graphics.getWidth());
        float y = MathUtils.random(0, Gdx.graphics.getHeight());
        int side = MathUtils.random(0, 3);

        switch (side) {
            case 0:
                y = 0 - bounds.getWidth();
                break;
            case 1:
                y = Gdx.graphics.getHeight();
                break;
            case 2:
                x = 0 - bounds.getHeight();
                break;
            case 3:
                x = Gdx.graphics.getWidth();
                break;
        }

        return new Vector2(x, y);
    }

    private void calculateStats(int level, int difficulty, int baseDamage, int baseHitpoints, int baseSpeed, float baseAttackSpeed) {
        // TODO: algorithm
        this.damage = level * difficulty * baseDamage;
        this.hitpoints = level * difficulty * baseHitpoints;
        this.speed = level * difficulty * baseSpeed;
        this.attackSpeed = level * difficulty * baseAttackSpeed;
    }

    public Player chooseTarget() {
        return targetSelection.selectTarget(this);
    }

    public void move() {
        movementType.move(this);
    }

    public void attack() {
        for (AttackType attackType : attackTypes) {
            attackType.attack(this);
        }
    }

    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public int getExperienceWhenKilled() {
        return experienceWhenKilled;
    }

    public int getRarity() {
        return rarity;
    }
}