package be.howest.junglewars;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

enum AttackType {
    MELEE {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I'm a melee attacker");
        }
    },
    RANGED {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I'm a ranged attacker");
        }
    },
    SLOW_DOWN {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("My attacks slow you down");
        }
    },
    WALL_BUILDER {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I build a wall");
        }
    },
    KEY_SCRAMBLER {
        @Override
        public void attack(Enemy enemy) {
            System.out.println("I scramble your keybinds");
        }
    };

    public abstract void attack(Enemy enemy);
}

enum MovementType {
    STRAIGHT {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I run straight at you");
        }
    },
    ZIG_ZAG {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I'm hard to hit because I zigzag to you");
        }
    },
    CIRCLE_AROUND {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I'm just circling around");
        }
    },
    RANDOM {
        @Override
        public void move(Enemy enemy) {
            System.out.println("I'm crazy and run all over the map");
        }
    };

    public abstract void move(Enemy enemy);
}

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

    private AttackType[] attackTypes;
    private MovementType[] movementTypes;

    public Enemy(String name, int width, int height, int scoreWhenKilled, int experienceWhenKilled,
                 String textureUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 int rarity, int gameLevel, int gameDifficulty, AttackType[] attackTypes, MovementType[] movementTypes) {
        super(width, height, textureUrl);
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;
        this.rarity = rarity;
        this.attackTypes = attackTypes;
        this.movementTypes = movementTypes;

        calculateStats(gameLevel, gameDifficulty, baseDamage, baseHitpoints, baseSpeed);

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

    private void calculateStats(int level, int difficulty, int baseDamage, int baseHitpoints, int baseSpeed) {
        // TODO: algorithm
        this.damage = level * difficulty * baseDamage;
        this.hitpoints = level * difficulty * baseHitpoints;
        this.speed = level * difficulty * baseSpeed;
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
        for (MovementType movementType : movementTypes) {
            movementType.move(this);
        }
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