package be.howest.junglewars.spawners;

import be.howest.junglewars.Difficulty;
import be.howest.junglewars.data.dao.EnemyDao;
import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.gameobjects.enemy.Enemy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EnemySpawner {

    private Set<EnemyEntity> availableEnemies;

    private int level;
    private Difficulty difficulty;

    private float multiplier;

    public EnemySpawner(int level, Difficulty difficulty) {
        this.level = level;
        this.difficulty = difficulty;

        calcMultiplier();

        availableEnemies = new HashSet<>();
        for (EnemyEntity entity : EnemyDao.getAllEnemies()) {
            availableEnemies.add(entity);
        }


    }

    private void calcMultiplier() {
        multiplier = 1;

        switch (difficulty) {
            case EASY:
                multiplier *= 1;
                break;
            case MEDIUM:
                multiplier *= 1.2;
                break;
            case HARD:
                multiplier *= 1.5;
                break;
            case EXTREME:
                multiplier *= 2;
                break;
            case UNSURVIVABLE:
                multiplier *= 5;
                break;
        }

        multiplier *= (level * .1f);

    }

//    private Enemy generateEnemy(EnemyEntity entity) {
//        return new Enemy(
//                entity.getName(),
//                entity.getTextureFileName(),
//                Math.round(entity.getBaseDamage() * multiplier),
//                Math.round(entity.getBaseSpeed() * multiplier),
//                Math.round(entity.getBaseHitpoints() * multiplier),
//                Math.round(entity.getBaseAttackSpeed() * multiplier),
//                entity.getExperienceWhenKilled(),
//                entity.getScoreWhenKilled(),
//                entity.getRarity(),
//                entity.getMovementTypeEnums(),
//                entity.getTargetSelectionTypeEnums(),
//                entity.getAttackTypeEnums()
//        );
//    }

    public Map<Enemy, Integer> generateEnemies() {
        HashMap<Enemy, Integer> enemies = new HashMap<>();
        for (EnemyEntity entity : availableEnemies) {
//            enemies.put(generateEnemy(entity), 1);
        }

        return enemies;
    }

}


//        amountEnemies = startingEnemies + (startingEnemies * (multiplierEnemies * level));
//                if (enemies.size() == 0) {
//                for (int i = 0; i < amountEnemies; i++) {
//        enemies.add(new EnemyOld(players));
//        enemies.get(i).update(dt);
//        }
//        level++;
//        }