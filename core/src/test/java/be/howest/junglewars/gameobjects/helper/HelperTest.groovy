package be.howest.junglewars.gameobjects.helper

import be.howest.junglewars.Difficulty
import be.howest.junglewars.JungleWars
import be.howest.junglewars.gameobjects.Missile
import be.howest.junglewars.gameobjects.MissileType
import be.howest.junglewars.gameobjects.Player
import be.howest.junglewars.screens.GameScreen
import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.headless.HeadlessApplication
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import org.junit.Before
import org.junit.Test

import static com.badlogic.gdx.Gdx.*
import static com.badlogic.gdx.graphics.GL20.*

class HelperTest extends GroovyTestCase {
    def GameScreen gamescreen;
    def JungleWars jw;
    def Helper helper;
    def Player player;
    def SpriteBatch batch;
    def ArrayList<Missile> missiles = new ArrayList<Missile>()


    private static Application application;

    @Before
    void setUp() {

        jw = new JungleWars(){
            @Override
            void create(){
                batch = new SpriteBatch()
            }
        }


        gamescreen = new GameScreen(jw, 1, Difficulty.EASY);
        player = new Player(gamescreen, "naam", 2, "sprite");
        helper = new Helper(gamescreen, "name", player, "sprite", HelperMovementType.COINCOLLECTING_HELPER, HelperActionType.COLLECTING_HELPER );
    }

    @Test
    void testHitBy() {
        ArrayList<Missile> missles = new ArrayList<>();
        missles.add(new Missile(gamescreen, 5, 5, 5, 5, 5,5,"Sprite",5,5,5,5,MissileType.STANDARD));
        missles.add(new Missile(gamescreen, 5, 5, 5, 5, 5,5,"Sprite",5,5,5,5,MissileType.STANDARD));
        helper.hitBy(missles.get(0));

        assertEquals(missiles.size(), 2, 1)
    }

    void testGetOwner() {

    }

    void testSetProtecting() {

    }

    void testCheckLevelUp() {

    }
}
