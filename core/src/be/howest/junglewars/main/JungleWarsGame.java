package be.howest.junglewars.main;

import be.howest.junglewars.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JungleWarsGame extends Game {
    private FPSLogger fpsLogger;

    private SpriteBatch batch;

    @Override
    public void create() {
        fpsLogger = new FPSLogger();
        batch = new SpriteBatch();

        // TODO: load settings here

        setScreen(new GameScreen(this, 1, 1)); // TODO: get level & difficulty from settings (enum?)
    }

    @Override
    public void render() {
        fpsLogger.log();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

}
