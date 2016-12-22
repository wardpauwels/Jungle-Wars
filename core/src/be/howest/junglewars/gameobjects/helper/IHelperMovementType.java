package be.howest.junglewars.gameobjects.helper;


import com.badlogic.gdx.math.Vector2;

public interface IHelperMovementType {
    Vector2 movementType(Helper helper, float dt);
}
