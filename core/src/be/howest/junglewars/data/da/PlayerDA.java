package be.howest.junglewars.data.da;

import be.howest.junglewars.data.entities.*;

import java.sql.*;

/**
 * Created by Ward on 20/12/2016.
 */
public class PlayerDA {
    private static final String URL = "jdbc:mysql://188.166.66.6/junglewars?useSSL=false";
    private static final String UID = "ward";
    private static final String PWD = "pauwels";
    private static PlayerDA instance;
    private Connection connection;

    private PlayerDA() {
        try {
            Class.forName( "com.mysql.cj.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection( URL, UID, PWD );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static PlayerDA getInstance() {
        if (instance == null) {
            instance = new PlayerDA();
        }

        return instance;
    }

    public void addPlayer(PlayerEntity player) {
        if (!playerExists( player.getId() )) {
            try {
                String sql = "insert into FBPlayer(fbId, fbName) values(?, ?)";
                PreparedStatement prep = this.connection.prepareStatement( sql );
                prep.setLong( 1, player.getId() );
                prep.setString( 2, player.getName() );
                prep.executeUpdate();
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PlayerEntity getPlayer(long id) {
        try {
            String sql = "select * from FBPlayer where fbId = ?";
            PreparedStatement prep = this.connection.prepareStatement( sql );
            prep.setLong( 1, id );
            prep.executeQuery();

            ResultSet rs = prep.executeQuery();
            PlayerEntity player = null;
            if (rs.next()) {
                player = new PlayerEntity( rs.getString( "fbName" ), rs.getLong( "fbId" ) );
            }
            prep.close();
            return player;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean playerExists(long id) {
        ResultSet rs = null;
        boolean player = false;
        try {
            String sql = "select * from FBPlayer where fbId = ?";
            PreparedStatement prep = this.connection.prepareStatement( sql );
            prep.setLong( 1, id );

            rs = prep.executeQuery();
            player = rs.next();

            rs.close();
            prep.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }
}
