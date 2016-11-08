package be.howest.junglewars.game;

import be.howest.junglewars.be.howest.junglewars.managers.*;
import be.howest.junglewars.states.PlayState;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g3d.particles.values.RectangleSpawnShapeValue;
import com.badlogic.gdx.math.Rectangle;
import com.sun.xml.internal.ws.api.ha.HaInfo;

public class JungleWarsGame extends ApplicationAdapter {

    public static int WIDTH;
    public static int HEIGHT;
    public static Rectangle screenBounds;

    private OrthographicCamera camera;

    private StateManager sm;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        screenBounds = new Rectangle(0, 0, WIDTH, HEIGHT);

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(WIDTH/2, HEIGHT/2);
        camera.update();

        sm = new StateManager();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sm.update(Gdx.graphics.getDeltaTime());

        sm.render();
    }

    @Override
    public void resize(int width, int height) {
        HEIGHT = height;
        WIDTH = width;
        screenBounds = new Rectangle(0, 0, WIDTH, HEIGHT);

        camera = new OrthographicCamera(WIDTH, HEIGHT);
        camera.translate(WIDTH/2, HEIGHT/2);
        camera.update();

        sm.update(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {

    }

}
