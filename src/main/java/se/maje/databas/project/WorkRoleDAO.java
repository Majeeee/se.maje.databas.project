package se.maje.databas.project;

import java.sql.SQLException;

public interface WorkRoleDAO {
    public void PreparedStatementSelect() throws SQLException;
    public void PreparedStatementInsert() throws SQLException;
    public void PreparedStatementUpdate() throws SQLException;
    public void PreparedStatementDelete() throws SQLException;
//    public void PreparedStatementSelectForRole(int roleId) throws SQLException;


}
