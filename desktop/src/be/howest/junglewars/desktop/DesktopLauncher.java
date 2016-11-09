package be.howest.junglewars.desktop;

import be.howest.junglewars.game.*;
import com.badlogic.gdx.backends.lwjgl.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Jungle Wars: The Revenge of Harambe";
        config.width = 1200;
        config.height = 900;
        config.resizable = false;
<<<<<<< 552ae2107adaa47f901d65aea84562151788e1c4
        config.useGL30 = true;
=======
        config.useGL30 = false;
>>>>>>> shoot animation & shoot direction fix
        new LwjglApplication(new JungleWarsGame(), config);
    }
}
