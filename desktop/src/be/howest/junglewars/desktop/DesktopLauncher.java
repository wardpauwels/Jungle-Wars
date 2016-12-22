package be.howest.junglewars.desktop;

import be.howest.junglewars.JungleWars;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

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
        config.width = 1500;
        config.height = 900;
        config.resizable = true;
        config.useGL30 = false;
        config.addIcon("images/player/harambe32.png", Files.FileType.Internal);
        new LwjglApplication(new JungleWars(), config);
    }
}
