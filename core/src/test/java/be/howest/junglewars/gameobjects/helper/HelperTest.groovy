package be.howest.junglewars.gameobjects.helper

import be.howest.junglewars.Difficulty
import be.howest.junglewars.JungleWars
import be.howest.junglewars.data.entities.PlayerEntity
import be.howest.junglewars.gameobjects.Missile
import be.howest.junglewars.gameobjects.MissileType
import be.howest.junglewars.gameobjects.Player
import be.howest.junglewars.screens.GameScreen
import com.badlogic.gdx.Game

import org.junit.Test; // for @Test
import org.junit.Before; // for @Before

class HelperTest extends GroovyTestCase {
    def GameScreen g;
    def JungleWars jw;
    def Helper h;
    def Player p;

    @Before
    public void setUp(){
        jw = new JungleWars();
        g = new GameScreen(jw, 1, Difficulty.EASY);
        p = new Player(g, "naam", 2, "sprite");
        h = new Helper(g, "name", p, "sprite", HelperMovementType.COINCOLLECTING_HELPER, HelperActionType.COLLECTING_HELPER );
    }

    @Test
    void testHitBy() {
        ArrayList<Missile> missiles = new ArrayList<>();
        missiles.add(new Missile(g, 5, 5, 5, 5, 5,5,"Sprite",5,5,5,5,MissileType.STANDARD));
        missiles.add(new Missile(g, 5, 5, 5, 5, 5,5,"Sprite",5,5,5,5,MissileType.STANDARD));
        h.hitBy(missiles.get(0));

        assertEquals(missiles.size(), 2, 1)
    }

    void testGetOwner() {

    }

    void testSetProtecting() {

    }

    void testCheckLevelUp() {

    }
}
