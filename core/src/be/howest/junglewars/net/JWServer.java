package be.howest.junglewars.net;

import be.howest.junglewars.gameobjects.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.HashSet;

public class JWServer {

    Server server;
    HashSet<Player> players = new HashSet<>();

    public JWServer() throws IOException {
        server = new Server() {
            protected Connection newConnection() {
                return new JWConnection();
            }
        };

        Network.register(server);

        server.addListener(new Listener() {
            public void received(Connection c, Object o) {
                JWConnection conn = (JWConnection) c;

            }
        });
    }

    static class JWConnection extends Connection {
        // TODO
    }

}
