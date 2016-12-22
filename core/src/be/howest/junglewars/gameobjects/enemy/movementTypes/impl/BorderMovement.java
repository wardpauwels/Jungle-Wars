package be.howest.junglewars.gameobjects.enemy.movementTypes.impl;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyMovementType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.ObjectMap;
import com.sun.deploy.config.VerboseDefaultConfig;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jensthiel on 20/12/16.
 */
public class BorderMovement implements IEnemyMovementType {
    private ArrayList<Rectangle> list = new ArrayList<>();
    private int currentDestination = 0;

    @Override
    public Vector2 returnMovement(Enemy enemy) {
        if (list.size() == 0){
            initArrayList();
            setClosest(enemy);
        }
        else{

        }
        if(enemy.getBody().contains(list.get(currentDestination)))
        {

            currentDestination += 1;
            if(currentDestination==list.size()){
                currentDestination = 0;

            }
        }

        return new Vector2(list.get(currentDestination).getX(),list.get(currentDestination).getY());

    }
    public void initArrayList() {
        int marge = 100;
        list.add(new Rectangle(marge, marge,50,50));
        list.add(new Rectangle(Gdx.graphics.getWidth() - marge, marge,50,50));
        list.add(new Rectangle(Gdx.graphics.getWidth() - marge, Gdx.graphics.getHeight() - marge,50,50));
        list.add(new Rectangle(marge, Gdx.graphics.getHeight()-marge,50,50));
    }

    public void setClosest(Enemy enemy){
        float closest = 100000000;
        int winner = 0;
        for(Rectangle r : list){
            float distance =new Vector2(enemy.getBody().getX(),enemy.getBody().getY()).dst(new Vector2(r.x,r.y));
            if (closest > distance) {
                closest = distance;
                winner = list.indexOf(r);
            }
        }
        currentDestination = winner;
    }





}