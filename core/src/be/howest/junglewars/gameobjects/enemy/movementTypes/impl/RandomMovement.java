package be.howest.junglewars.gameobjects.enemy.movementTypes.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyMovementType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;


public class RandomMovement implements IEnemyMovementType{

    Random rand = new Random();
    private Vector2 position = new Vector2(0,0);
    @Override
    public Vector2 returnMovement(Enemy enemy) {
        if (position.x == 0 && position.y == 0){
            position.x = (rand.nextInt(Gdx.graphics.getWidth()-150));
            position.y = (rand.nextInt(Gdx.graphics.getHeight()-150));
        }
        else{
            if(enemy.getBody().contains(position)){
                position.x = (rand.nextInt(Gdx.graphics.getWidth()-150));
                position.y = (rand.nextInt(Gdx.graphics.getHeight()-150));


            }

        }
        return position;


    }



}
