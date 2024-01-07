/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.Modelpackage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
 * @version 2.0
 */
public class DatabaseManager {
     private final String JDBC_URL = "jdbc:derby://localhost:1527/SpotifyDB";
     private final String JDBC_USER = "Piotr";
     private final String JDBC_PASSWORD = "20Piotr02";
     private static DatabaseManager instance;
     
     
     
    public DatabaseManager() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            createTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    public String getJdbcUrl() {
        return JDBC_URL;
    }

    public String getJdbcUser() {
        return JDBC_USER;
    }

    public String getJdbcPassword() {
        return JDBC_PASSWORD;
    }
    
        public List<ModelStatisticsSpotify> getAllStatistics()throws SQLException {
        List<ModelStatisticsSpotify> statisticsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql; 
            sql = """
                  SELECT
                      Artist.artist_name AS artist_name,
                      Artist.artist_lastname as artist_lastname,
                      Song.song_title AS song_title,
                      Song.playsCount AS playsCount,
                      Song.songTime AS songTime,
                      Song.continent AS continent
                  FROM
                      Artist
                  JOIN
                      Song ON Artist.artist_id = Song.artist_id""";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    ModelStatisticsSpotify statistics = new ModelStatisticsSpotify();

                    statistics.setAuthorName(resultSet.getString("artist_name"));
                    statistics.setAuthorLastName(resultSet.getString("artist_lastname"));
                    statistics.setTitle(resultSet.getString("song_title"));
                    statistics.setContinent(resultSet.getString("continent"));
                    statistics.setPlaysCount(resultSet.getInt("playsCount"));
                    statistics.setSongTime(resultSet.getDouble("songTime"));

                    // Jeśli masz kolumnę w tabeli przechowującą historię obliczeń
                    // statistics.setCalculationHistory(resultSet.getString("calculationHistory"));

                    statisticsList.add(statistics);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Problem with get all statistics database exist...");
        }

        return statisticsList;
    }
    
    private static void createTables(Connection connection) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                // Check if the tables exist
                if (!tableExists(connection, "ARTIST")) {
                    statement.executeUpdate("CREATE TABLE Artist (artist_id INT PRIMARY KEY, artist_name VARCHAR(255) NOT NULL, artist_lastname VARCHAR(255) NOT NULL)");
                }

                if (!tableExists(connection, "SONG")) {
                    statement.executeUpdate( """
                    CREATE TABLE Song (
                        song_id INT PRIMARY KEY,
                        song_title VARCHAR(255) NOT NULL,
                        artist_id INT,
                        playsCount INT,
                        continent VARCHAR(255),
                        songTime DOUBLE,
                        FOREIGN KEY (artist_id) REFERENCES Artist(artist_id)
                    )
                    """);
                }
            }catch (SQLException e) {
            throw new SQLException("Problem with create database exist...");
        }
    }
    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, "PIOTR", tableName.toUpperCase(), null);
        return resultSet.next();
    }
    public List<String> getAllStatisticsAsString() throws SQLException{
        List<String> statisticsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = """
                SELECT
                    Artist.artist_name AS artist_name,
                    Artist.artist_lastname AS artist_lastname,
                    Song.song_title AS song_title,
                    Song.playsCount AS playsCount,
                    Song.songTime AS songTime,
                    Song.continent AS continent
                FROM
                    Artist
                JOIN
                    Song ON Artist.artist_id = Song.artist_id""";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String statistics = String.format(
                        "Artist: %s %s, Title: %s, Plays Count: %d, Song Time: %.2f, Continent: %s \n",
                        resultSet.getString("artist_name"),
                        resultSet.getString("artist_lastname"),
                        resultSet.getString("song_title"),
                        resultSet.getInt("playsCount"),
                        resultSet.getDouble("songTime"),
                        resultSet.getString("continent")
                    );

                    statisticsList.add(statistics);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Problem with read database exist...");
        }

        return statisticsList;
    }
    public void insertArtist(int id,String name, String lastname)throws SQLException {
      try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
          String sql = "INSERT INTO Artist (artist_id, artist_name, artist_lastname) VALUES (?, ?, ?)";
          try (PreparedStatement statement = connection.prepareStatement(sql)) {
              statement.setInt(1, id);
              statement.setString(2, name);
              statement.setString(3, lastname);
              statement.executeUpdate();
          }
      } catch (SQLException e) {
          throw new SQLException("Error while inserting artist into the database.", e);
      }
  }


    public void insertSong(int songId, String songTitle, int artistId, int playsCount, String continent, double songTime) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO Song (song_id, song_title, artist_id, playsCount, continent, songTime) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, songId);
                statement.setString(2, songTitle);
                statement.setInt(3, artistId);
                statement.setInt(4, playsCount);
                statement.setString(5, continent);
                statement.setDouble(6, songTime);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error while inserting song into the database.", e);
        }
    }
    public boolean isIndexExists(int index) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT 1 FROM Artist WHERE artist_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, index);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Zwróć true, jeśli istnieje rekord o danym indeksie
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // W przypadku błędu zwróć false
        }
    }
    public void deleteArtist(int artistId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "DELETE FROM Artist WHERE artist_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, artistId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error while deleting artist from the database.", e);
        }
    }
    public void deleteSong(int songId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "DELETE FROM Song WHERE song_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, songId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error while deleting artist from the database.", e);
        }
    }
    public void updateSong(int songId, String newTitle, int newPlaysCount, String newContinent, double newSongTime) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "UPDATE Song SET song_title = ?, playsCount = ?, continent = ?, songTime = ? WHERE song_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newTitle);
                statement.setInt(2, newPlaysCount);
                statement.setString(3, newContinent);
                statement.setDouble(4, newSongTime);
                statement.setInt(5, songId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error while updating song in the database.", e);
        }
    }
    public void updateArtist(int artistId, String newName, String newLastName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "UPDATE Artist SET artist_name = ?, artist_lastname = ? WHERE artist_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newName);
                statement.setString(2, newLastName);
                statement.setInt(3, artistId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLException("Error while updating artist in the database.", e);
        }
    }




}
