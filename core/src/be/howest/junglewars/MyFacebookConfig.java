package be.howest.junglewars;

import de.tomgrill.gdxfacebook.core.*;


public class MyFacebookConfig extends GDXFacebookConfig {

    public MyFacebookConfig() {
        APP_ID = "1294359183954877\n"; // required
        PREF_FILENAME = ".facebookSessionData"; // optional
        GRAPH_API_VERSION = "v2.6"; // optional, default is v2.6
    }
}
