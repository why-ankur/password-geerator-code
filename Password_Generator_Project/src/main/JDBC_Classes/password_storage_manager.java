package main.JDBC_Classes;
import java.sql.*;
import java.util.Random;
import java.util.UUID;
public class password_storage_manager {
    // Function to store user data with a generated password
    public static void storeUserData(String phoneNumber, String dob, String name, String platform) {
        // Generate a random password
        String password = Function_calling(name,dob);
        // JDBC setup
        String url = "jdbc:mysql://127.0.0.1:3306/user_db1";  
        String dbUser = "root";
        String dbPassword = "mysql@2004";
        // SQL query to insert data into the users_info table
        String query = "INSERT INTO users_info (phone_number, dob, name, platform, password) VALUES (?, ?, ?, ?, ?)";

        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection(url, dbUser, dbPassword);

            // Prepare statement for SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters in the query
            preparedStatement.setString(1, phoneNumber);
            preparedStatement.setString(2, dob);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, platform);
            preparedStatement.setString(5, password);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the data was inserted successfully
            if (rowsAffected > 0) {
                System.out.println("User data inserted successfully!");
            } else {
                System.out.println("Failed to insert user data.");
            }

            // Close the resources
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Function to generate a strong password
    public static String generatePassword(String name, String dob, String[] randomWords) {
        // Combine name and date of birth as the base string
        StringBuilder baseString = new StringBuilder(name + dob);    
        // Randomly choose a number of words to insert and random digits
        baseString = insertRandomWords(baseString, randomWords, 3); // Insert 3 random words
        baseString = insertRandomDigits(baseString, 3); // Insert 3 random digits    
        // Ensure password is not longer than 20 characters
        if (baseString.length() > 20) {
            baseString.setLength(20);
        }    
        // Return the generated password
        return baseString.toString();
    }    
    // Helper function to generate a random alphanumeric string (digit) of a given length
    public static String generateRandomString(int length, String characters) {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();    
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }    
        return randomString.toString();
    }    
    // Helper function to insert random words into the base string at random positions
    public static StringBuilder insertRandomWords(StringBuilder baseString, String[] randomWords, int insertCount) {
        Random random = new Random();    
        for (int i = 0; i < insertCount; i++) {
            // Randomly choose a word from the list
            String randomWord = randomWords[random.nextInt(randomWords.length)];    
            // Randomly choose a position in the base string to insert the word
            int position = random.nextInt(baseString.length() + 1); // +1 for inserting at the end    
            // Insert the random word at the chosen position
            baseString.insert(position, randomWord);
        }    
        return baseString;
    }    
    // Helper function to insert random digits into the base string at random positions
    public static StringBuilder insertRandomDigits(StringBuilder baseString, int insertCount) {
        Random random = new Random();
        String digits = "0123456789";    
        for (int i = 0; i < insertCount; i++) {
            // Randomly choose a digit
            char randomDigit = digits.charAt(random.nextInt(digits.length()));    
            // Randomly choose a position in the base string to insert the digit
            int position = random.nextInt(baseString.length() + 1);    
            // Insert the random digit at the chosen position
            baseString.insert(position, randomDigit);
        }    
        return baseString;
    }    
    // Main method to test password generation
    public static String Function_calling(String names , String dobs) {
        String name = names;
        String dob = dobs; // Date of Birth in ddmmyyyy format
        String[] randomWords = {"apple", "banana", "cherry", "grape", "orange"};    
        // Generate a strong password
        String password = generatePassword(name, dob, randomWords);    
        // Print the generated password
        return password;
    }
}
