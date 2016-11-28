package be.howest.junglewars.gameobjects.helper.actions;

import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.util.Distance;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class ShootingAction implements HelperAction {

    private boolean canShoot;
    private float shootTime;
    private float shootTimer;

    private Player owner;

    private ArrayList<Missile> missiles;

    public ShootingAction(Player owner) {
        this.owner = owner;

        canShoot = false;
        shootTime = 1.5f;
        shootTimer = 0;

        missiles = new ArrayList<>();
    }

    @Override
    public void doAction() {
        for (int i = 0; i < missiles.size(); i++) {
            if (missiles.get(i).shouldRemove()) missiles.remove(i);
        }

        // TODO: herhaling shoot and timer, ...
        if (shootTimer > shootTime) {
            canShoot = true;
            shootTime = 0;
        } else {
            shootTimer += Gdx.graphics.getDeltaTime();
        }

        if (canShoot) {
            // FIXME: ClassCastException
            Enemy target = (Enemy) Distance.getClosest(owner.getHelper(), (Enemy[]) owner.game.getEnemies().toArray());
            // TODO: iew
            if (target != null) {
                shoot(target.bounds.x, target.bounds.y);
            }
        }
    }

    private void shoot(float destinationX, float destinationY) {
        canShoot = false;
        shootTimer = 0;

        missiles.add(new Missile(
                owner.game, owner, 10, 10, owner.getHelper().position.x, owner.getHelper().position.y, destinationX, destinationY, "helper-bullet", 10, 50, 30, 2)
        );
    }

}
