package be.howest.junglewars.gameobjects.enemy;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public interface IChooseTargetType {

    List<Vector2> chooseTargets(Enemy enemy);

}
