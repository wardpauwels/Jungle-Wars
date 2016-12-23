package be.howest.junglewars.data.da;

import be.howest.junglewars.data.entities.EnemyEntity;
import be.howest.junglewars.data.entities.HelperEntity;
import be.howest.junglewars.data.entities.PowerEntity;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by jensthiel on 23/12/16.
 */
public class JungleWarsDA {
    private static final String URL = "jdbc:mysql://188.166.66.6/JungleWars?useSSL=false";
    private static final String UID = "ward";
    private static final String PWD = "pauwels";
    private static JungleWarsDA instance;
    private Connection connection;


    private JungleWarsDA() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.connection = DriverManager.getConnection(URL, UID, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JungleWarsDA getInstance() {
        if (instance == null) {
            instance = new JungleWarsDA();
        }

        return instance;
    }

    public ArrayList<EnemyEntity> getEnemies() {
        try {
            ArrayList<EnemyEntity> enemies = new ArrayList<>();
            String sql = "select * from enemies";
            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.executeQuery();

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                EnemyEntity enemy = null;
                enemy = new EnemyEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sprite"),
                        rs.getString("alt_sprite"),
                        rs.getInt("width"),
                        rs.getInt("height"),
                        rs.getInt("dmg"),
                        rs.getInt("speed"),
                        rs.getInt("hp"),
                        rs.getFloat("attackspeed"),
                        rs.getInt("killed_xp"),
                        rs.getInt("killed_score"),
                        rs.getInt("spawn_chance"),
                        rs.getString("movement_type"),
                        rs.getString("target_type"),
                        rs.getString("action_type"),
                        rs.getInt("spawnChance")
                );
                enemies.add(enemy);
            }
            prep.close();
            return enemies;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<PowerEntity> getPowers()

    {
        try {
            ArrayList<PowerEntity> powers = new ArrayList<>();
            String sql = "select * from powers";
            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.executeQuery();

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                PowerEntity power = null;
                power = new PowerEntity(rs.getInt("id"), rs.getString("name"), rs.getString("sprite"), rs.getInt("lifetime"),rs.getInt("activetime"),rs.getString("powertype"),rs.getInt("percentage"));
                powers.add(power);
            }
            prep.close();
            return powers;

        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        return null;



    }



    public ArrayList<HelperEntity> getHelpers()

    {
        try {
            ArrayList<HelperEntity> helpers = new ArrayList<>();
            String sql = "select * from helpers";
            PreparedStatement prep = this.connection.prepareStatement(sql);
            prep.executeQuery();

            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                HelperEntity helper = null;
                helper = new HelperEntity(rs.getInt("id"), rs.getString("name"), rs.getString("sprite"), rs.getString("movement_type"),rs.getString("action_type"));
                helpers.add(helper);
            }
            prep.close();
            return helpers;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
