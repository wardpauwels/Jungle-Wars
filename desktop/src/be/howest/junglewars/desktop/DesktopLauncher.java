package be.howest.junglewars.desktop;

import be.howest.junglewars.JungleWarsGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {

        AtlasPacker.packAll();

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Jungle Wars: The Revenge of Harambe";
        config.width = 1600;
        config.height = 900;

        config.resizable = false;

        config.useGL30 = false;

        new LwjglApplication(new JungleWarsGame(), config);
    }
}
