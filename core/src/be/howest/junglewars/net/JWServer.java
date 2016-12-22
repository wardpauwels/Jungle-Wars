package be.howest.junglewars.net;

import be.howest.junglewars.GameData;
import be.howest.junglewars.gameobjects.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashSet;

public class JWServer {

    Server server;
    GameData data;
    HashSet<Player> players = new HashSet<>();

    public JWServer() throws IOException {
        server = new Server() {
            protected Connection newConnection() {
                return new JWConnection();
            }
        };

        data = new GameData();

        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                JWConnection conn = (JWConnection) connection;
                ArrayList<String> players = new ArrayList<>();
                for(Connection c : server.getConnections()){
                    players.add(conn.name);
                }
                server.sendToAllTCP(new Network.PlayerJoinLobby(conn.getID(), conn.name, players));
            }

            public void received(Connection c, Object o) {

            }
        });

        server.bind(Network.portTCP, Network.portUDP);
        server.start();
    }

    static class JWConnection extends Connection {
        public String name;
    }

    public void sendMessage(Object msg){
        server.sendToAllTCP(msg);
    }

}
