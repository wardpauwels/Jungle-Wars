package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyActionType;
import be.howest.junglewars.gameobjects.enemy.utils.Wall;

import com.badlogic.gdx.math.Vector2;


public class TrumpAction implements IEnemyActionType {


    @Override
    public void attack(Enemy enemy, Vector2 v, float spawnX, float spawnY) {
        int length = randomWithRange(20,150);
        Vector2 start = new Vector2(v.x,v.y);
        int curveX = randomWithRange(-10,10);
        int curveY = randomWithRange(-10,10);
        Wall wall = new Wall(enemy.game,start,length,curveX,curveY);
        int vertOrHorz = randomWithRange(1,2);
        if (vertOrHorz==1){
            wall.DrawWallVert();

        }else{
            wall.DrawWallHorz();
        }
        enemy.game.getData().getWalls().add(wall);
        enemy.game.sounds.get(randomWithRange(0,enemy.game.sounds.size()-1)).play();


    }

    private int randomWithRange(int min, int max)
    {
        int range = Math.abs(max - min) + 1;
        return (int)(Math.random() * range) + (min <= max ? min : max);
    }
}
