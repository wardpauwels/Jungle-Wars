package be.howest.junglewars.main;

import be.howest.junglewars.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JungleWarsGame extends Game {
    private FPSLogger fpsLogger; // TODO: development only!

    private SpriteBatch batch;

    @Override
    public void create() {
        fpsLogger = new FPSLogger(); // TODO: development only!
        batch = new SpriteBatch();

        // TODO: load settings here

        setScreen(new GameScreen(this, 1)); // TODO: get level from settings
    }

    @Override
    public void render() {
        fpsLogger.log(); // TODO: development only!
        super.render();
    }

    public SpriteBatch getBatch(){
        return batch;
    }

}
