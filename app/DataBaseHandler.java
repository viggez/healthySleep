package app;

import java.sql.*;

public class DataBaseHandler {
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/sleep_environment_db";
    private final String username = "root";
    private final String password = "healthySleep!";

    public DataBaseHandler(Connection connection) {
        this.connection = connection;
        createTableIfNotExists();
    }
    public Connection getConnection() {
        return connection;
    }

    public void saveData(int totalCaffeine, int totalScreenTime, String review) {
        String query = "INSERT INTO sleep_data (total_caffeine, total_screen_time, sleep_review) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, totalCaffeine);
            statement.setInt(2, totalScreenTime);
            statement.setString(3, review);
            statement.executeUpdate();
            System.out.println("Data saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int getSavedCaffeine() {
        int savedCaffeine = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT total_caffeine FROM sleep_data ORDER BY id DESC LIMIT 1");
            if (resultSet.next()) {
                savedCaffeine = resultSet.getInt("total_caffeine");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedCaffeine;
    }

    public int getSavedScreenTime() {
        int savedScreenTime = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT total_screen_time FROM sleep_data ORDER BY id DESC LIMIT 1");
            if (resultSet.next()) {
                savedScreenTime = resultSet.getInt("total_screen_time");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedScreenTime;
    }

    public void createTableIfNotExists() {
        String query = "CREATE TABLE IF NOT EXISTS sleep_data (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "total_caffeine INT NOT NULL," +
                "total_screen_time INT NOT NULL," +
                "sleep_review VARCHAR(255) NOT NULL" +
                ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
