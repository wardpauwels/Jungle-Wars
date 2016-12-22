package be.howest.junglewars.net;

import be.howest.junglewars.*;
import be.howest.junglewars.gameobjects.*;
import com.esotericsoftware.kryonet.*;

import java.io.*;

public class JWServer {

    Server server;
    GameData data;

    public JWServer() throws IOException {
        server = new Server() {
            protected Connection newConnection() {
                return new JWConnection();
            }
        };

        this.data = new GameData(this);

        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object o) {
                handleReceive(connection, o);
            }

            @Override
            public void disconnected(Connection connection) {
                handleDisconnect(connection);
            }
        });

        server.bind(Network.portTCP, Network.portUDP);
        server.start();
    }

    public void update(float dt) {
        data.update(dt);
    }

    private void handleDisconnect(Connection connection) {
        JWConnection conn = (JWConnection) connection;
        if (conn.name != null) {
            Network.PlayerJoinLeave reply = new Network.PlayerJoinLeave(conn.getID(), conn.name, false);
            server.sendToAllExceptTCP(conn.getID(), reply);
            data.removePlayer(reply);
        }
    }

    private void handleReceive(Connection connection, Object message) {
        JWConnection conn = (JWConnection) connection;

        if (message instanceof Network.Login) {
            Network.Login msg = (Network.Login) message;

            if (conn.name != null) return;

            String name = msg.name;

            if (name == null) return;

            name = name.trim();
            if (name.length() == 0) return;
            conn.name = name;
            //TODO send data state?
            Network.PlayerJoinLeave reply = new Network.PlayerJoinLeave(conn.getID(), conn.name, true);
            server.sendToAllExceptTCP(conn.getID(), reply);
            data.addPlayer(reply);

            for (Connection c : server.getConnections()) {
                JWConnection jwc = (JWConnection) c;
                System.out.println("Player has joined / left: " + data.getPlayerById(jwc.getID()).getName());

                if (jwc.getID() != conn.getID()) {
                    Player connectedPlayer = data.getPlayerById(jwc.getID());
                    Network.PlayerJoinLeave connectedMsg = new Network.PlayerJoinLeave(jwc.getID(), connectedPlayer.getName(), true);
                    conn.sendTCP(connectedMsg);
                    conn.sendTCP(connectedPlayer.getMovementState());
                }
            }
        } else if (message instanceof Network.MovementState) {
            Network.MovementState msg = (Network.MovementState) message;
            msg.playerId = conn.getID();
            data.playerMoved(msg);
            server.sendToAllExceptUDP(conn.getID(), msg);
        } else if (message instanceof Network.PlayerShoot) {
            Network.PlayerShoot msg = (Network.PlayerShoot) message;
            msg.playerId = conn.getID();
            server.sendToAllExceptTCP(conn.getID(), msg);
        }
    }

    public void shutdown() {
        server.close();
        server.stop();
    }

    static class JWConnection extends Connection {
        public String name;
    }
}
