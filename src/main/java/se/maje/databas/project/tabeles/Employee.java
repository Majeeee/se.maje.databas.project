package se.maje.databas.project.tabeles;

public class Employee {
    private int employeeId;        // Auto-genererat primärnyckel
    private String name;           // Namn
    private String email;          // E-post
    private String password;       // Lösenord
    private int roleId;            // Främmande nyckel till work_role

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    // Konstruktorer
    public Employee(int employeeId, String name, String email, String password, int roleId) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
    }
}