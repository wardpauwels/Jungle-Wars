package be.howest.junglewars;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Enemy extends GameObject {
    private String name;
    private Sprite sprite;

    private Player target;
    private Vector2 dPosition;

    private int damage;
    private int hitpoints;
    private int speed;

    private int rarity;

    private int scoreWhenKilled;
    private int experienceWhenKilled;


    public enum AttackType {
        ENEMY_MELEE_ATTACK {

        };

        interface EnemyAttackBehaviour {
            void attack();
        }
    }

    public enum MovementType {

    }

    public Enemy(AttackType attackType) {
        super(0, 0);
    }

    public Enemy(String name, int width, int height, int scoreWhenKilled, int experienceWhenKilled,
                 String imgUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 int rarity, int gameLevel, int gameDifficulty) {
        super(width, height);
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;

        generateStats(gameLevel, gameDifficulty, baseDamage, baseHitpoints, baseSpeed);

        Texture texture = new Texture(imgUrl);
        sprite = new Sprite(texture, width, height);
        sprite.setOriginCenter();
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

    @Override
    protected Vector2 generateSpawnPosition(float width, float height) {
        float x = MathUtils.random(0, Gdx.graphics.getWidth());
        float y = MathUtils.random(0, Gdx.graphics.getHeight());
        int side = MathUtils.random(0, 3);

        switch (side) {
            case 0:
                y = 0 - width;
                break;
            case 1:
                y = Gdx.graphics.getHeight();
                break;
            case 2:
                x = 0 - height;
                break;
            case 3:
                x = Gdx.graphics.getWidth();
                break;
        }

        return new Vector2(x, y);
    }

    private void generateStats(int level, int diff, int damage, int hitpoints, int speed) {
        // TODO: use algorithm
        this.damage = damage;
        this.hitpoints = hitpoints;
        this.speed = speed;
    }

    public Player chooseTarget(ArrayList<Player> players) {
        Player playerToAttack = players.get(0);
        for (int i = 1; i < players.size(); i++) {
            if (Math.sqrt(Math.pow(position.x - players.get(i).getPosition().x, 2) + Math.pow(position.y - players.get(i).getPosition().y, 2))
                    > Math.sqrt(Math.pow(position.x - players.get(i - 1).getPosition().x, 2) + Math.pow(position.y - players.get(i - 1).getPosition().y, 2))) {
                playerToAttack = players.get(i);
            }
        }
        return playerToAttack;
    }

    public void move() {

    }

    public void attack() {
        Player p = new Player("test");
    }

    //region getters/setters
    public int getScoreWhenKilled() {
        return scoreWhenKilled;
    }

    public int getExperienceWhenKilled() {
        return experienceWhenKilled;
    }

    public int getRarity() {
        return rarity;
    }
    //endregion
}
