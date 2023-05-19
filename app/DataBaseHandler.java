package app;
import java.sql.*;

public class DataBaseHandler {

    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/sleep_environment_db";
    private final String username = "root";
    private final String password = "healthySleep!";

    public DataBaseHandler() {
        try {
            // Establish the database connection
            connection = DriverManager.getConnection(url, username, password);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveData(int totalCaffeine, int totalScreenTime) {
        String query = "INSERT INTO sleep_data (total_caffeine, total_screen_time) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, totalCaffeine);
            statement.setInt(2, totalScreenTime);

            // Execute the query to insert the data
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void createTableIfNotExists() {
        String query = "CREATE TABLE IF NOT EXISTS sleep_data (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "caffeine INT NOT NULL," +
                "screen_time INT NOT NULL" +
                ")";

        try (Statement statement = connection.createStatement()) {
            // Execute the query to create the table if it doesn't exist
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
