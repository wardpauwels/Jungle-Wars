package be.howest.junglewars.net;

import be.howest.junglewars.GameData;
import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class JWClient {

    private Client client;
    private GameData data;
    public int id;
    public String name;
    public String remoteIP;

    public JWClient(String name) {
        this.name = name;
    }

    public void connectLocal() {
        System.out.println("connected to localhost");
        connect("localhost");
    }

    public void connect(String ip) {
        try {
            client.connect(5000, ip, Network.portTCP, Network.portUDP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
