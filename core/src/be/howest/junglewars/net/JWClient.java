package be.howest.junglewars.net;

import be.howest.junglewars.GameData;
import com.esotericsoftware.kryonet.Client;

public class JWClient {

    private Client client;
    private GameData data;
    public int id;
    public String name;
    public String remoteIP;

    public JWClient(String name) {
        this.name = name;
    }

}
