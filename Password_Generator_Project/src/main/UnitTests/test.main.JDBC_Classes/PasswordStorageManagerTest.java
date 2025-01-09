package test.main.JDBC_Classes;

import main.JDBC_Classes.password_storage_manager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static org.mockito.Mockito.*;

public class PasswordStorageManagerTest {

    @Test
    public void testStoreUserData() throws Exception {
        // Mock database connection and statement
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Mock DriverManager to return the mock connection
        try (var mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                               .thenReturn(mockConnection);

            // Call the method to test
            password_storage_manager.storeUserData("1234567890", "2000-01-01", "John Doe", "Platform");

            // Verify interactions
            verify(mockPreparedStatement).setString(1, "1234567890");
            verify(mockPreparedStatement).setString(2, "2000-01-01");
            verify(mockPreparedStatement).setString(3, "John Doe");
            verify(mockPreparedStatement).setString(4, "Platform");
            verify(mockPreparedStatement, times(1)).executeUpdate();
        }
    }
}
