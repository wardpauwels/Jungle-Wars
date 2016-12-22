package be.howest.junglewars.net;

import be.howest.junglewars.GameData;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class JWClient {

    private final GameData data;
    public String name;
    public long id;
    public String remoteIP;
    private Client client;

    public JWClient(String name) {
        this.data = new GameData(this);
        this.name = name;

        client = new Client();
        client.start();

        Network.register(client);

        client.addListener(new Listener() {
            @Override
            public void connected(Connection connection) {
                handleConnect(connection);
            }

            @Override
            public void received(Connection connection, Object o) {
                handleReceive(connection.getID(), o);
            }

            @Override
            public void disconnected(Connection connection) {
                handleDisconnected(connection);
            }
        });
    }

    private void handleDisconnected(Connection connection) {
        data.onDisconnect();
    }

    private void handleReceive(int playerId, Object message) {
        if (message instanceof Network.PlayerJoinLeave) {
            Network.PlayerJoinLeave msg = (Network.PlayerJoinLeave) message;
            if (msg.hasJoined) {
                data.addPlayer(msg);
                System.out.println("Player has joined: " + data.getPlayerById(msg.playerId).getName());
            } else {
                System.out.println("Player has left: " + data.getPlayerById(msg.playerId).getName());
                data.removePlayer(msg);
            }
            for (long id : data.getPlayers().keySet()) {
                System.out.println(data.getPlayerById(id).getName() + " is online now.");
            }
        } else if (message instanceof Network.MovementState) {
            Network.MovementState msg = (Network.MovementState) message;
            data.playerMoved(msg);
        } else if (message instanceof Network.PlayerWasHit) {
            Network.PlayerWasHit msg = (Network.PlayerWasHit) message;
            data.onPlayerWasHit(msg);
        } else if (message instanceof Network.WaveEnd) {
            Network.WaveEnd msg = (Network.WaveEnd) message;
            data.onWwaveEnd(msg);
        } else if (message instanceof Network.WaveStart) {
            Network.WaveStart msg = (Network.WaveStart) message;
            data.onWaveEnd(msg);
        } else if (message instanceof Network.PlayerSpawned) {
            Network.PlayerSpawned msg = (Network.PlayerSpawned) message;
            data.onPlayerSpawned(msg);
        }
    }

    private void handleConnect(Connection connection) {
        id = connection.getID();
        remoteIP = connection.getRemoteAddressTCP().toString();
        Network.Login login = new Network.Login(name);
        client.sendTCP(login);
        client.updateReturnTripTime();
        data.onConnect(name);
    }

    public void connect(String host) {
        try {
            client.connect(5000, host, Network.portTCP, Network.portUDP);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JWClient::connect_catch - Can't connect to " + host);
        }
    }

    public void shutdown() {
        client.stop();
        client.close();
    }

    public GameData getData() {
        return data;
    }
}