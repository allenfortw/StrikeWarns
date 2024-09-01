package org.allenpixel.strikewarns;

import java.sql.*;

public class StrikeDatabase {
    private final String url = "jdbc:sqlite:plugins/StrikeWarns/Database.db";
    private Connection connection;

    public StrikeDatabase(StrikeWarns plugin) {
        try {
            connection = DriverManager.getConnection(url);
            String createTableSQL = "CREATE TABLE IF NOT EXISTS players (player_name TEXT PRIMARY KEY, strike INTEGER)";
            connection.createStatement().executeUpdate(createTableSQL);
        } catch (SQLException e) {
            plugin.getLogger().severe("无法连接到数据库：" + e.getMessage());
        }
    }

    public int addStrike(String player, int count) {
        try {
            String query = "INSERT INTO players(player_name, strike) VALUES(?, ?) ON CONFLICT(player_name) DO UPDATE SET strike=strike + ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, player);
            pstmt.setInt(2, count);
            pstmt.setInt(3, count);
            pstmt.executeUpdate();

            return getStrike(player);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void setStrike(String player, int count) {
        try {
            String query = "INSERT INTO players(player_name, strike) VALUES(?, ?) ON CONFLICT(player_name) DO UPDATE SET strike=?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, player);
            pstmt.setInt(2, count);
            pstmt.setInt(3, count);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getStrike(String player) {
        try {
            String query = "SELECT strike FROM players WHERE player_name = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, player);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("strike");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
