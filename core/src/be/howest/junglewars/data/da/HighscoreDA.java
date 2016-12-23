package be.howest.junglewars.data.da;

import be.howest.junglewars.data.entities.*;
import be.howest.junglewars.gameobjects.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class HighscoreDA {
    private static final String URL = "jdbc:mysql://188.166.66.6/junglewars?useSSL=false";
    private static final String UID = "ward";
    private static final String PWD = "pauwels";
    private static HighscoreDA instance;
    private Connection connection;

    private HighscoreDA() {
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

    public static HighscoreDA getInstance() {
        if (instance == null) {
            instance = new HighscoreDA();
        }
        return instance;
    }

    public void addHighscore(Player player) {
        if (getHighscore( player.getId() ) == null) {
            Date date = new Date( Calendar.getInstance().getTime().getTime() );
            try {
                String sql = "INSERT INTO Highscores(fbId, score, date) VALUES(?, ?, ?)";
                PreparedStatement prep = this.connection.prepareStatement( sql );
                prep.setLong( 1, player.getId() );
                prep.setInt( 2, player.getScore() );
                prep.setDate( 3, date );
                prep.executeUpdate();
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            if (getHighscore( player.getId() ).getScore() < player.getScore()) {
                Date date = new Date( Calendar.getInstance().getTime().getTime() );
                try {
                    String sql = "UPDATE Highscores SET score = ?, date = ? WHERE fbId = ?";
                    PreparedStatement prep = this.connection.prepareStatement( sql );
                    prep.setInt( 1, player.getScore() );
                    prep.setDate( 2, date );
                    prep.setLong( 3, player.getId() );
                    prep.executeUpdate();
                    prep.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<HighscoreEntity> getHighscores(int amount) {
        try {
            List<HighscoreEntity> highscores = new LinkedList<>();

            String sql = "select * from Highscores order by score desc limit ?";
            PreparedStatement prep = this.connection.prepareStatement( sql );
            prep.setInt( 1, amount );

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                highscores.add( new HighscoreEntity( rs.getInt( "score" ), rs.getDate( "date" ), PlayerDA.getInstance().getPlayer( rs.getLong( "fbId" ) ) ) );
            }
            prep.close();
            return highscores;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HighscoreEntity getHighscore(long id) {
        try {
            HighscoreEntity highscore = null;

            String sql = "select * from Highscores where fbId = ?";
            PreparedStatement prep = this.connection.prepareStatement( sql );
            prep.setLong( 1, id );

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                highscore = new HighscoreEntity( rs.getInt( "score" ), rs.getDate( "date" ), PlayerDA.getInstance().getPlayer( rs.getLong( "fbId" ) ) );
            }
            prep.close();
            return highscore;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
