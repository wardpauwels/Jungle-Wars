package be.howest.junglewars.desktop;

import be.howest.junglewars.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl.*;
import com.badlogic.gdx.tools.texturepacker.*;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.*;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Settings settings = new Settings();
        settings.combineSubdirectories = true;
        settings.debug = false;
        settings.maxHeight = 2048;
        settings.maxWidth = 2048;
        TexturePacker.process(settings, "images", "atlas", "images");

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Jungle Wars: The Revenge of Harambe";
        config.width = 1600;
        config.height = 900;
        config.resizable = true;
        config.useGL30 = false;
        config.addIcon("images/player/harambe32.png", Files.FileType.Internal);
        new LwjglApplication(new JungleWars(), config);
    }
}
