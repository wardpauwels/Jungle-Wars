package be.howest.junglewars.gameobjects;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jensthiel on 19/12/16.
 */
public interface IMissileType {
    void doEffect(GameScreen g,Player p);

}
