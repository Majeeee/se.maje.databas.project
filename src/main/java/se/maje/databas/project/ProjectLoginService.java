package se.maje.databas.project;

import se.maje.databas.project.DBconnection.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


    public class ProjectLoginService {

        public ProjectLoginService() {
        }

        public static boolean loginUser (String username, String password) throws SQLException {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            String sql = "SELECT e.EMPLOYEE_ID, e.EMAIL ,e.PASSWORD FROM EMPLOYEE e " +
                    "WHERE EMAIL = ? AND PASSWORD = ?";
            boolean loginSuccessful = false;

            try {
                conn = JDBCUtil.getConnection(); // Hämta databasanslutning
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    // Hämtar det hashade lösenordet från databasen
                    String storedPasswordHash = rs.getString("PASSWORD");

                    // Verifierar det inskrivna lösenordet mot det hashade lösenordet
                    if (password.equals(storedPasswordHash)) {
                        loginSuccessful = true; // Inloggningen lyckades
//                        System.out.println("Inloggning lyckades för användare: " + username);
                    } else {
                        System.out.println("Felaktigt lösenord för användare: " + username);
                    }
                } else {
                    System.out.println("Ingen användare hittades med e-post: " + username);
                }
            } catch (SQLException e) {
                System.err.println("Ett fel uppstod vid exekvering av SELECT: " + e.getMessage());
                e.printStackTrace();
                throw e; // Vidarebefordra undantaget
            } finally {
                JDBCUtil.closeResultSet(rs);
                JDBCUtil.closeStatement(pstmt);
                JDBCUtil.closeConnection(conn);
            }

            return loginSuccessful;
        }
    }