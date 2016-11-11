package be.howest.junglewars.desktop;

import be.howest.junglewars.game.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.*;

import java.awt.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Jungle Wars: The Revenge of Harambe";

//        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
//        config.width = (int) screensize.getWidth();
//        config.height = (int) screensize.getHeight();
//        config.fullscreen = true;
        config.width = 1600;
        config.height = 900;

        config.resizable = false;

        config.useGL30 = false;

        new LwjglApplication(new JungleWarsGame(), config);
    }
}
