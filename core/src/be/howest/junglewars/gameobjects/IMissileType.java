package be.howest.junglewars.gameobjects;

import be.howest.junglewars.GameData;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

public interface IMissileType {
    void doEffect(GameData g, Player p);

}
