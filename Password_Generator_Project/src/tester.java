import main.JDBC_Classes.*;
public class tester {
    public static void main(String[] args) {
        String phoneNumber = "1234567890";
        String Platform = "Drive";
        String name = "Prince Gupta";
        String dob = "12/04/2024";
        String new_password = "Parth Sachudeva";
        PasswordUpdater.updatePassword(phoneNumber, Platform, new_password);
    }
}
