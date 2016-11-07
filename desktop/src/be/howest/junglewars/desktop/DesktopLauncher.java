package be.howest.junglewars.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import be.howest.junglewars.JungleWarsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jungle Wars: The Revenge of Harambe";
//		config.fullscreen = true;
		config.width = 1200;
		config.height = 900;
		config.resizable = false;
		config.useGL30 = true;
		new LwjglApplication(new JungleWarsGame(), config);
	}
}
