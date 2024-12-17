package se.maje.databas.project;

import se.maje.databas.project.DBconnection.JDBCUtil;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class WorkRoleDAOImpl implements WorkRoleDAO{

    public WorkRoleDAOImpl() {
    }

    Scanner scanner = new Scanner(System.in);
//    ProjectParameters parameters;

    @Override
    public void PreparedStatementInsert() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO WORK_ROLE (TITLE, DESCRIPTION, SALARY, CREATION_DATE) " +
                "VALUES (?, ?, ?, ?)";

        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            System.out.println("Ange ditt TITLE :");
            pstmt.setString(1, ProjectInputValidater.getValidatedStringInput()); // Title
            System.out.println("Ange ditt DESCRIPTION :");
            pstmt.setString(2, ProjectInputValidater.getValidatedStringInput()); // Description
            System.out.println("Ange ditt SALARY :");
            pstmt.setDouble(3, ProjectInputValidater.getValidatedDoubleInput()); //Salary
            System.out.println("Ange ditt CREATION_DATE :");
            pstmt.setDate(4, ProjectInputValidater.getValidatedDateInput()); // DATE


            int rowsAffected = pstmt.executeUpdate(); // Kör INSERT
            System.out.println(ProjectConstants.RED + "Rad(er) insatta: " + rowsAffected +
                    "\n----------------------------------------------------------" + ProjectConstants.RESET);

//            System.out.println(ProjectConstants.RED + " ROLE_ID   |" +
//                    "         TITLE          |" +
//                    "                      DESCRIPTION                     |" + System.out.println(ProjectConstants.RED + " ROLE_ID   |" +
//                    "         TITLE          |" +
//                    "                      DESCRIPTION                     |" +
//                    "    SALARY    |" +
//                    " CREATION_DATE|" +
//                    "\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET);
//                    "    SALARY    |" +
//                    " CREATION_DATE|" +
//                    "\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET);
//            System.out.println("");
            JDBCUtil.commit(conn);


        } catch (SQLException e) {
            System.err.println("Ett fel uppstod vid exekvering av INSERT: " + e.getMessage());
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public void PreparedStatementSelect() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM WORK_ROLE";

        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();            // Kör SELECT och få resultatet som ResultSet
            System.out.println(ProjectConstants.RED + " ROLE_ID   |" +
                    "         TITLE          |" +
                    "                      DESCRIPTION                     |" +
                    "    SALARY    |" +
                    " CREATION_DATE|" +
                    "\n --------------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET);

            // Iterera genom resultatet och skriv ut rader
            while (rs.next()) {
//                int id = rs.getInt("ID"); // Anta att PERSON har en kolumn 'ID'
                String roleId = rs.getString("ROLE_ID");
                String title = rs.getString("TITLE");
                String description = rs.getString("DESCRIPTION");
                double salary = rs.getDouble("SALARY");
                Date dob = rs.getDate("CREATION_DATE");
                if (description.length() > 52) {
                    description = description.substring(0, 48) + "...";
                }
                if (title.length() > 28) {
                    title = title.substring(0, 25) + "...";
                }

                System.out.printf("%-10s | %-22s | %-52s | %-12.2f | %-15s%n",
                        roleId, title, description, salary, dob.toString());
            }
            System.out.println(ProjectConstants.RED + "\n --------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET);
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










//    @Override
//    public void PreparedStatementSelect(EmployeeDAOImpl emp) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        final String RED = "\u001B[31m";          // Röd
//        final String RESET = "\u001B[0m";
////        emp.getUserInput()
//
//        String employeeEmail = emp.getUserInput(); // Hämta användarens input (antaget att emp har en scanner för att läsa input)
//
//        String sql = "SELECT * FROM WORK_ROLE R, EMPLOYEE E " +
//                "WHERE E.ROLE_ID = R.ROLE_ID " +
//                "AND E.EMPLOYEE_ID = ?"; // Parametriserad query
//
//
//        try {
//            conn = JDBCUtil.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, employeeEmail);
//            rs = pstmt.executeQuery();            // Kör SELECT och få resultatet som ResultSet
//            System.out.println(RED + " ROLE_ID   |" +
//                    "         TITLE          |" +
//                    "                      DESCRIPTION                     |" +
//                    "    SALARY    |"  +
//                    " CREATION_DATE|" +
//                    "\n -----------------------------------------------------------------------------------------------------------------------------------------------------------------------" + RESET);
//
//            // Iterera genom resultatet och skriv ut rader
//            while (rs.next()) {
////                int id = rs.getInt("ID"); // Anta att PERSON har en kolumn 'ID'
//                String roleId = rs.getString("ROLE_ID");
//                String title = rs.getString("TITLE");
//                String description = rs.getString("DESCRIPTION");
//                double salary = rs.getDouble("SALARY");
//                Date dob = rs.getDate("CREATION_DATE");
//                if (description.length() > 52) {
//                    description = description.substring(0, 48) + "...";
//                }
//                if (title.length() > 28) {
//                    title = title.substring(0, 25) + "...";
//                }
//
//                System.out.printf("%-10s | %-22s | %-52s | %-12.2f | %-15s%n",
//                        roleId, title, description, salary, dob.toString());
//            }
//            System.out.println(RED + "\n ------------------------------------------------------------------" + RESET);

    /// /            System.out.println("PERSON rader: " + rs);
//            JDBCUtil.commit(conn);
//        } catch (SQLException e) {
//            System.err.println("Ett fel uppstod vid exekvering av SELECT: " + e.getMessage());
//            JDBCUtil.rollback(conn);
//            e.printStackTrace();
//            throw e;
//        } finally {
//            JDBCUtil.closeStatement(pstmt);
//            JDBCUtil.closeConnection(conn);
//        }
//    }
//
    @Override
    public void PreparedStatementDelete() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM WORK_ROLE WHERE ROLE_ID = ?";

        System.out.println("Vilken Role_ID vill du bort?");

        int menuChoice = ProjectInputValidater.getValidatedIntegerInput();

        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,menuChoice);
            int rowsAffected = pstmt.executeUpdate(); // Kör DELETE
            System.out.println(ProjectConstants.RED +"Rader borta: " + rowsAffected + ProjectConstants.RESET);
            JDBCUtil.commit(conn);
            System.out.println(ProjectConstants.RED + "\n --------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET);
        } catch (SQLException e) {
            System.err.println("Ett fel uppstod vid exekvering av DELETE: " + e.getMessage());
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
    }

    @Override
    public void PreparedStatementUpdate() throws SQLException {

        Connection conn = null;
        PreparedStatement pstmt = null;
        List<Object> parameters = (List<Object>) ProjectProcess.updateAndDeleteWorkRoleColumn();// Hämta parametrar
        String columnName = null;
        Object value = null;
        int idValue = 0;

//        ProjectProcess.updateAndDeleteWorkRoleColumn();
        if (parameters.size() >= 3) {
            columnName = (String) parameters.get(0);  // Kolumnnamnet
            value = parameters.get(1);               // Värdet (kan vara String, Double, eller Date)
            idValue = (int) parameters.get(2);       // ROLE_ID
        } else {
            throw new IllegalArgumentException("Felaktigt antal parametrar! Förväntar minst: columnName, value, idValue.");
        }

        String sql = "UPDATE WORK_ROLE " +
                "SET " + columnName + " = ? " +
                "WHERE ROLE_ID = ?";
        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            // Dynamiskt sätt rätt parameter baserat på typen
            if (value instanceof String) {
                pstmt.setString(1, (String) value); // Om värdet är String
            } else if (value instanceof Double) {
                pstmt.setDouble(1, (Double) value); // Om värdet är Double
            } else if (value instanceof java.util.Date) {
                pstmt.setDate(1, new java.sql.Date(((java.util.Date) value).getTime())); // Om värdet är Date
            } else {
                throw new IllegalArgumentException("Ogiltig datatyp: " + value.getClass().getName());
            }
            pstmt.setInt(2, idValue); // Sätt ID för raden som ska uppdateras


            int rowsAffected = pstmt.executeUpdate(); // Kör UPDATE
            System.out.println(ProjectConstants.RED+"Rad(er) updaterade: " + rowsAffected +ProjectConstants.RESET);
            JDBCUtil.commit(conn);
            System.out.println(ProjectConstants.RED + "\n --------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET); System.out.println(ProjectConstants.RED + "\n --------------------------------------------------------------------------------------------------------------------" + ProjectConstants.RESET);
        } catch (SQLException e) {
            System.err.println("Ett fel uppstod vid exekvering av Update: " + e.getMessage());
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        } finally {
            JDBCUtil.closeStatement(pstmt);
            JDBCUtil.closeConnection(conn);
        }
    }


}
