package main.JDBC_Classes;

import java.sql.*;

public class UserInfoFetcher {

    // Database connection details (Modify these according to your setup)
    public static String url = "jdbc:mysql://127.0.0.1:3306/user_db1";  
    public static String dbUser = "root";
    public static String dbPassword = "mysql@2004";
    // Function to fetch user information based on phone number
    public static void fetchUserInfo(String phoneNumber , String platforms) {
        // SQL query to select user details based on phone number
        String sql = "SELECT id, phone_number, dob, name, platform, password FROM users_info WHERE phone_number = ? AND platform = ?";
        
        // Establish database connection and execute the select query
        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set the phone number parameter for the query
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, platforms);

            // Execute the query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // If a user is found, display their information
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String phone = resultSet.getString("phone_number");
                String dob = resultSet.getString("dob");
                String name = resultSet.getString("name");
                String platform = resultSet.getString("platform");
                String password = resultSet.getString("password");

                // Display the user information
                System.out.println("User Information:");
                System.out.println("ID: " + id);
                System.out.println("Phone Number: " + phone);
                System.out.println("Date of Birth: " + dob);
                System.out.println("Name: " + name);
                System.out.println("Platform: " + platform);
                System.out.println("Password: " + password);
            } else {
                System.out.println("No user found with phone number: " + phoneNumber);
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., connection issues, query errors)
            e.printStackTrace();
        }
    }
}

