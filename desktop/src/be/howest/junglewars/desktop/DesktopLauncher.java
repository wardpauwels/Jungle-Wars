package be.howest.junglewars.desktop;

import be.howest.junglewars.game.*;
import com.badlogic.gdx.backends.lwjgl.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Jungle Wars: The Revenge of Harambe";
        config.width = 1920;
        config.height = 1080;
        config.resizable = true;
        config.useGL30 = true;
        new LwjglApplication(new JungleWarsGame(), config);
    }
}
