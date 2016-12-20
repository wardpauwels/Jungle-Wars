package be.howest.junglewars.gameobjects.enemy;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by jensthiel on 19/12/16.
 */
public interface IChooseTargetType {

    List<Vector2> chooseTargets(Enemy enemy);

}
