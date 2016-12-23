package be.howest.junglewars.gameobjects.enemy.movementTypes.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyMovementType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class ZigZagMovement implements IEnemyMovementType {
    Random rand = new Random();
    private Vector2 position = new Vector2(0,0);
    @Override
    public Vector2 returnMovement(Enemy enemy) {
        if (position.x == 0 && position.y == 0){
            position.x = (rand.nextInt(Gdx.graphics.getWidth()));
            position.y = (rand.nextInt(Gdx.graphics.getHeight()));
        }
        else{
            if(enemy.getBody().contains(position)){
                position.x += (randomWithRange(-50, 50));
                position.y += (randomWithRange(-50, 50));


            }
            int width = Gdx.graphics.getWidth() - 150;
            int height = Gdx.graphics.getHeight() - 150;
            if(position.x >= width || position.y >= height || position.x <= 150 || position.y <= 150){
                position.x = Gdx.graphics.getWidth()/2;
                position.y = Gdx.graphics.getHeight()/2;

            }

        }
        return position;


    }

    private int randomWithRange(int min, int max)
    {
        int range = Math.abs(max - min) + 1;
        return (int)(Math.random() * range) + (min <= max ? min : max);
    }
}
