package se.maje.databas.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.maje.databas.project.DBconnection.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class WorkRoleDAOImplTestSelect {

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        // Skapa en anslutning till in-memory-databasen
        connection = JDBCUtil.getConnection();

        // Lägg till en testpost i WORK_ROLE
        String insertSQL = "INSERT INTO WORK_ROLE (TITLE, DESCRIPTION, SALARY, CREATION_DATE) " +
                "VALUES ('Software Engineer', 'Responsible for software development', 70000.00, CURRENT_DATE)";
        connection.prepareStatement(insertSQL).executeUpdate();
    }

    @Test
    void preparedStatementSelect() throws SQLException {
        // SQL-fråga för att hämta en post med ROLE_ID = 1
        String selectSQL = "SELECT ROLE_ID, TITLE, DESCRIPTION, SALARY, CREATION_DATE FROM WORK_ROLE WHERE ROLE_ID = ?";
        int roleIdToSelect = 1;

        try (PreparedStatement selectStatement = connection.prepareStatement(selectSQL)) {
            selectStatement.setInt(1, roleIdToSelect);
            var resultSet = selectStatement.executeQuery();

            // Kontrollera att vi har fått en rad från resultatet
            assertTrue(resultSet.next(), "En rad bör returneras från SELECT-frågan");

            // Kontrollera att värdena för de valda kolumnerna stämmer
            assertEquals(roleIdToSelect, resultSet.getInt("ROLE_ID"), "ROLE_ID ska stämma");
            assertEquals("Software Engineer", resultSet.getString("TITLE"), "Titel ska stämma");
            assertEquals("Responsible for software development", resultSet.getString("DESCRIPTION"), "Beskrivning ska stämma");
            assertEquals(70000.00, resultSet.getDouble("SALARY"), "Lön ska stämma");
            assertNotNull(resultSet.getDate("CREATION_DATE"), "Skapelsedatum ska inte vara null");
        }
    }
}
