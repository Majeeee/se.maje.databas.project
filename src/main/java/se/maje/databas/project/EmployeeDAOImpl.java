package se.maje.databas.project;

import se.maje.databas.project.DBconnection.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void createEmployeeObject(String epost) throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        final String RED = "\u001B[31m";          // Röd
        final String RESET = "\u001B[0m";

        String sql = "SELECT EMP.EMPLOYEE_ID ,EMP.NAME , EMP.EMAIL, WRK.TITLE " +
                     "FROM EMPLOYEE EMP , WORK_ROLE WRK " +
                     "WHERE EMP.ROLE_ID = WRK.ROLE_ID " +
                     "AND EMAIL = ?";

        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
//            // Bind användarens e-post till frågan
            pstmt.setString(1, epost);
            rs = pstmt.executeQuery();            // Kör SELECT och få resultatet som ResultSet
            System.out.println(RED + " EMPLOYEE_ID |" +
                    "         NAME         |" +
                    "           TITLE           |" +
                    "\n -------------------------------------------------------------------" + RESET);

            // Iterera genom resultatet och skriv ut rader
            while (rs.next()) {
                int employeeId = rs.getInt("EMPLOYEE_ID");
                String name = rs.getString("NAME");
//                epost = rs.getString("EMAIL");
                String title = rs.getString("TITLE");

//                    if (description.length() > 52) {
//                        description = description.substring(0, 48) + "...";
//                    }
//                    if (title.length() > 28) {
//                        title = title.substring(0, 25) + "...";
//                    }
                System.out.printf("%-12d | %-20s | %-25s |%n", employeeId, name, title);
//
            }
            System.out.println(RED + "\n ------------------------------------------------------------------" + RESET);
//            System.out.println("PERSON rader: " + rs);
            JDBCUtil.commit(conn);
        } catch (SQLException e) {
            System.err.println("Ett fel uppstod vid exekvering av SELECT: " + e.getMessage());
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
    }
}



