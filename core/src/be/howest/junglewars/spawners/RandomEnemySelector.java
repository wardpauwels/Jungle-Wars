package be.howest.junglewars.spawners;

import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.gameobjects.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomEnemySelector {
    List<Enemy> entities = new ArrayList<>();
    Random rand = new Random();
    int totalSum = 0;

    public RandomEnemySelector() {
        for(Enemy e : entities) {
            totalSum = totalSum + e.;
        }
    }

    public Enemy getRandom() {

        int index = rand.nextInt(totalSum);
        int sum = 0;
        int i=0;
        while(sum < index ) {
            sum = sum + objects.get(i++).relativeProb;
        }
        return new Enemy(entities.get(Math.max(0,i-1)));
    }
}