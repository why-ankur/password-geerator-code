package main.JDBC_Classes;
import java.sql.*;

public class PasswordUpdater {

    // Database connection details (Modify according to your setup)
    public static String url = "jdbc:mysql://127.0.0.1:3306/user_db1";  
    public static String dbUser = "root";
    public static String dbPassword = "mysql@2004";
    // Function to update the password
    public static void updatePassword(String phoneNumber, String platform, String updatedPassword) {
        // SQL query to update the password based on phone number and platform
        String sql = "UPDATE users_info SET password = ? WHERE phone_number = ? AND platform = ?";
        // Establish database connection and execute the update
        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, updatedPassword); // Set the new password
            preparedStatement.setString(2, phoneNumber); // Set the phone number
            preparedStatement.setString(3, platform); // Set the platform

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();
            
            // If rows are affected, the update was successful
            if (rowsAffected > 0)
            {
                System.out.println("Password updated successfully!");
            };

        } catch (SQLException e) {
            // Handle SQL exceptions (e.g., connection issues, syntax errors)
            e.printStackTrace();
        }
    }

}
