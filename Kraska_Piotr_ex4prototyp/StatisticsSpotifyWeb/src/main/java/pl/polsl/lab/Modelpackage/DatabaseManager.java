/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.Modelpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Piotr
 */
public class DatabaseManager {
     private final String JDBC_URL = "jdbc:derby://localhost:1527/SpotifyDB";
     private final String JDBC_USER = "Piotr";
     private final String JDBC_PASSWORD = "20Piotr02";
     
     
     private static void createTables(Connection connection) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                // Check if the tables exist
                if (!tableExists(statement, "Artist")) {
                    statement.executeUpdate("CREATE TABLE Artist (artist_id INT PRIMARY KEY, artist_name VARCHAR(255) NOT NULL)");
                }

                if (!tableExists(statement, "Song")) {
                    statement.executeUpdate("CREATE TABLE Song (song_id INT PRIMARY KEY, song_title VARCHAR(255) NOT NULL, artist_id INT, FOREIGN KEY (artist_id) REFERENCES Artist(artist_id))");
                }
            }
    }
     private static boolean tableExists(Statement statement, String tableName) throws SQLException {
    // Query to check if the table exists
    String query = "SELECT * FROM SYS.SYSTABLES WHERE TABLENAME = '" + tableName + "'";
    
    // Execute the query
    return statement.executeQuery(query).next();
}
     public void insertArtist(String artistName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            String sql = "INSERT INTO Artist (artist_name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, artistName);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSong(String songTitle, String artistName) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASSWORD)) {
            // Retrieve artist_id for the given artistName
            int artistId = getArtistId(connection, artistName);

            // Insert the song with the retrieved artist_id
            String sql = "INSERT INTO Song (song_title, artist_id) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, songTitle);
                statement.setInt(2, artistId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllArtists() {
            List<String> artists = new ArrayList<>();
            try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
                String sql = "SELECT * FROM Artist";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        artists.add(resultSet.getString("artist_name"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return artists;
        }
    public List<String> getAllSongs() {
        List<String> songs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            String sql = "SELECT * FROM Song";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    songs.add("Title: " + resultSet.getString("song_title") +
                            ", Artist: " + getArtistName(connection, resultSet.getInt("artist_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }
    private String getArtistName(Connection connection, int artistId) throws SQLException {
        String sql = "SELECT artist_name FROM Artist WHERE artist_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artistId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("artist_name");
            }
        }
        throw new SQLException("Artist not found for ID: " + artistId);
    }
    private int getArtistId(Connection connection, String artistName) throws SQLException {
        String sql = "SELECT artist_id FROM Artist WHERE artist_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artistName);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("artist_id");
            }
        }
        throw new SQLException("Artist not found: " + artistName);
    }
}
