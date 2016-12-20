package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.GameData;
import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends GameObject {
    private static final float WIDTH = 70;
    private static final float HEIGHT = 80;
    public static final float BULLET_WIDTH = 15;
    public static final float BULLET_HEIGHT = 15;

    private static final String ATLAS_PREFIX = "enemy/";

    private EnemyActionType enemyAction;
    private ChooseTargetType chooseMovement;
    private ChooseTargetType chooseTarget;

    private String name;

    private int baseDamage;
    private int baseHitpoints;
    private int baseSpeed;

    private float actionTime;
    private float actionTimer;

    private int spawnProbability;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    private List<Vector2> targets;

    private IChooseTargetType chooseTargetType;
    private IChooseTargetType chooseMovementType;
    private IEnemyActionType enemyActionType;

    public Enemy(GameData data, EnemyEntity e) {
        this(
                data,
                e.getName(),
                e.getDefaultSpriteUrl(),
                e.getBaseDamage(),
                e.getBaseSpeed(),
                e.getBaseHitpoints(),
                e.getBaseAttackSpeed(),
                e.getExperienceWhenKilled(),
                e.getScoreWhenKilled(),
                e.getSpawnProbability(),
                ChooseTargetType.valueOf(e.getTargetSelectionType()),
                ChooseTargetType.valueOf(e.getMovementType()),
                EnemyActionType.valueOf(e.getAttackType())
        );
    }

    public Enemy(GameData data, String name, String defaultSpriteUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 float baseAttackSpeed, int experienceWhenKilled, int scoreWhenKilled, int spawnProbability, ChooseTargetType chooseTargetType, ChooseTargetType chooseMovementType, EnemyActionType actionType) {
        super(data, ATLAS_PREFIX + defaultSpriteUrl, WIDTH, HEIGHT,
                ThreadLocalRandom.current().nextInt(0 - Math.round(WIDTH), Gdx.graphics.getWidth() + Math.round(WIDTH)),
                ThreadLocalRandom.current().nextBoolean() ? 0 - HEIGHT : Gdx.graphics.getHeight() + HEIGHT); // TODO: spawns only top or bottom now

        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.spawnProbability = spawnProbability;

        // TODO: calculate stats based by game level and difficulty
        this.baseDamage = baseDamage;
        this.baseSpeed = baseSpeed;
        this.baseHitpoints = baseHitpoints;
        this.actionTime = baseAttackSpeed;
        this.actionTimer = 0;

        this.chooseTarget = chooseTargetType;
        this.chooseTargetType = chooseTargetType.getType();
        this.chooseMovement = chooseMovementType;
        this.chooseMovementType = chooseMovementType.getType();
        this.enemyAction = actionType;
        this.enemyActionType = actionType.getAction();
    }

    @Override
    public void update(float dt) {

        if (this.baseHitpoints <= 0) this.remove = true;

        targets = chooseMovementType.chooseTargets(this);
        for (Vector2 v : targets) {

            float radians = MathUtils.atan2(v.y - body.y, v.x - body.x);

            body.x += MathUtils.cos(radians) * baseSpeed * dt;
            body.y += MathUtils.sin(radians) * baseSpeed * dt;

            doEnemyAction(dt);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public void hitBy(Missile missile, Player player) {

        if (catchDamage(missile.getDamage()) <= 0) {
            player.addScore(scoreWhenKilled);
            player.addXp(experienceWhenKilled);
        }
        missile.remove = true;

    }

    public int catchDamage(int dmg) {
        this.baseHitpoints -= dmg;
        return baseHitpoints;
    }


    private void doEnemyAction(float dt) {
        actionTimer += dt;
        if (actionTimer > actionTime) {
            attack();
            actionTimer = 0;
        }
    }

    private void attack() {
        targets = chooseTargetType.chooseTargets(this);
        if (targets == null) return;
        for (Vector2 v : targets) {
            float destinationX = v.x;
            float destinationY = v.y;

            float spawnX = body.x + (body.width / 2);
            float spawnY = body.y + (body.height / 2);

            enemyActionType.attack(this, v, spawnX, spawnY);

        }
    }

    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public int getExperienceWhenKilled() {
        return experienceWhenKilled;
    }

    public int getSpawnProbability() {
        return spawnProbability;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }
}
