package test.main.JDBC_Classes;

import main.JDBC_Classes.PasswordUpdater;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static org.mockito.Mockito.*;

public class PasswordUpdaterTest {

    @Test
    public void testUpdatePassword() throws Exception {
        // Mock database connection and statement
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Mock DriverManager
        try (var mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                               .thenReturn(mockConnection);

            // Call the method
            PasswordUpdater.updatePassword("1234567890", "Platform", "newPassword123");

            // Verify interactions
            verify(mockPreparedStatement).setString(1, "newPassword123");
            verify(mockPreparedStatement).setString(2, "1234567890");
            verify(mockPreparedStatement).setString(3, "Platform");
            verify(mockPreparedStatement, times(1)).executeUpdate();
        }
    }
}
