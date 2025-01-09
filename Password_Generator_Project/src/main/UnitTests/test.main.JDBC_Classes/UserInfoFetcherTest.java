package test.main.JDBC_Classes;

import main.JDBC_Classes.UserInfoFetcher;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.*;

public class UserInfoFetcherTest {

    @Test
    public void testFetchUserInfo() throws Exception {
        // Mock database connection, statement, and result set
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Mock result set behavior
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("phone_number")).thenReturn("1234567890");
        when(mockResultSet.getString("dob")).thenReturn("2000-01-01");
        when(mockResultSet.getString("name")).thenReturn("John Doe");
        when(mockResultSet.getString("platform")).thenReturn("Platform");
        when(mockResultSet.getString("password")).thenReturn("password123");

        // Mock DriverManager
        try (var mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                               .thenReturn(mockConnection);

            // Call the method
            UserInfoFetcher.fetchUserInfo("1234567890", "Platform");

            // Verify interactions
            verify(mockPreparedStatement).setString(1, "1234567890");
            verify(mockPreparedStatement).setString(2, "Platform");
            verify(mockPreparedStatement, times(1)).executeQuery();
            verify(mockResultSet, times(1)).next();
        }
    }
}
