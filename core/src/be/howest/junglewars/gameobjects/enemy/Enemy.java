package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.GameData;
import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

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

    private ArrayList<Missile> missiles;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    private AttackType[] attackTypes;
    private MovementType movementType;
    private TargetSelectionType targetSelection;

    public Enemy(GameData gameData, String name, String textureUrl, float width, float height,
                 int baseDamage, int baseSpeed, int baseHitpoints, float baseAttackSpeed,
                 int experienceWhenKilled, int scoreWhenKilled, int rarity,
                 MovementType[] movementType, TargetSelectionType[] targetSelection, AttackType[] attackTypes) {
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.rarity = rarity;
        this.attackTypes = attackTypes;
        this.movementType = movementType[0];
        this.targetSelection = targetSelection[0];

        missiles = new ArrayList<>();

//        calculateStats(gameScreen.getLevel(), gameScreen.getDifficulty(), baseDamage, baseHitpoints, baseSpeed, baseAttackSpeed);
        target = chooseTarget();


        init(gameData, width, height);
    }

    public Enemy(GameScreen game, EnemyEntity entity) {
        this(
                game,
                entity.getName(),
                entity.getTextureFileName(),
                entity.getBaseDamage(),
                entity.getBaseSpeed(),
                entity.getBaseHitpoints(),
                entity.getBaseAttackSpeed(),
                entity.getExperienceWhenKilled(),
                entity.getScoreWhenKilled(),
                entity.getRarity(),
                entity.getMovementTypeEnums(),
                entity.getTargetSelectionTypeEnums(),
                entity.getAttackTypeEnums()
        );
    }

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

//    @Override
//    protected void checkCollision() {
//
//    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/enemies.atlas");
    }

    @Override
    protected Sprite initDefaultSprite() {
        return null;
    }

    @Override
    protected Vector2 initSpawnPosition() {
        return null;
    }

    private void calculateStats(int level, Difficulty difficulty, int baseDamage, int baseHitpoints, int baseSpeed, float baseAttackSpeed) {
        // TODO: algorithm (see: EnemySpawner)
//        this.damage = level * difficulty * baseDamage;
//        this.hitpoints = level * difficulty * baseHitpoints;
//        this.speed = level * difficulty * baseSpeed;
//        this.attackSpeed = level * difficulty * baseAttackSpeed;
    }

    private Player chooseTarget() {
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

    public ArrayList<Missile> getMissiles() {
        return missiles;
    }
}
