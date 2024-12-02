import main.JDBC_Classes.*;
public class tester {
    public static void main(String[] args) {
        String phoneNumber = "1234567890";
        String Platform = "Instagram";
        String new_password = "ABCDE";
        PasswordUpdater.updatePassword(phoneNumber, Platform, new_password);
    }
}
