package be.howest.junglewars.gameobjects.helper.actionType.impl;

import be.howest.junglewars.gameobjects.Missile;
import be.howest.junglewars.gameobjects.MissileType;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.helper.IHelperActionType;
import com.badlogic.gdx.Gdx;

import java.util.List;

import static be.howest.junglewars.gameobjects.enemy.Enemy.BULLET_HEIGHT;
import static be.howest.junglewars.gameobjects.enemy.Enemy.BULLET_WIDTH;

public class ShootingHelper implements IHelperActionType {


    private float shootTime = 2;
    private float shootTimer= 0 ;

    @Override
    public void helperAction(Helper helper) {
        shootTimer += Gdx.graphics.getDeltaTime();
        if (shootTimer > shootTime) {
            shoot(helper);
            shootTimer = 0;
        }
    }

    @Override
    public void helperUpgrade(Helper helper) { // Schiet langzamerhand sneller
        shootTime *= 0.97f;
        System.out.println(shootTime);
    }

    private void shoot(Helper helper){
        Enemy target = chooseTarget(helper);
        if (target == null) return;

        float destinationX = target.getBody().x + (target.getBody().width / 2);
        float destinationY = target.getBody().y + (target.getBody().height / 2);

        float spawnX = helper.getBody().x + (helper.getBody().width / 2);
        float spawnY = helper.getBody().y + (helper.getBody().height / 2);

        helper.getOwner().getMissiles().add(new Missile(helper.game, BULLET_WIDTH, BULLET_HEIGHT, spawnX, spawnY, destinationX, destinationY, "helper-bullet", 15, 800, 30, 1.5f, MissileType.STANDARD));

    }

    public Enemy chooseTarget(Helper helper) {
        return helper.getNearest(helper.game.getData().getEnemies());
    }



}
