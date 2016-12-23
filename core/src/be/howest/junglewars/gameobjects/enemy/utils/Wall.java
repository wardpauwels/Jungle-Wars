package be.howest.junglewars.gameobjects.enemy.utils;

import be.howest.junglewars.*;
import com.badlogic.gdx.math.*;

import java.util.*;

/**
 * Created by jensthiel on 20/12/16.
 */
public class Wall {
    private Vector2 start;
    private ArrayList<Brick> wall;
    private int length;
    private Vector2 end;
    private int curveX;
    private int curveY;
    private GameData data;

    public Wall(Vector2 start, int length, int curveX, int curveY, GameData data) {
        this.data = data;
        this.start = start;
        this.length = length;
        this.end = end;
        this.curveX = curveX;
        this.curveY = curveY;
        wall = new ArrayList<>();


    }


    public void DrawWall(){
        for(int i = 0; i < length; i = i+1){
            Brick b = new Brick(20, 10, start.x + curveX * i, start.y + curveY * i, data);
            wall.add(b);


        }
    }


    public void DrawWallHorz(){
        for(int i = 0; i < length; i = i + 5){
            Brick b = new Brick(10, 5, start.x + i, start.y, data);
            wall.add(b);


        }
    }

    public void DrawWallDia(){
        for(int i = 0; i < length; i = i + 5){
            Brick b = new Brick(10, 5, start.x + i, start.y + i, data);
            wall.add(b);


        }
    }
    public ArrayList<Brick> returnWall(){
        return wall;
    }
}
