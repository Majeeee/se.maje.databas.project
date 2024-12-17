package se.maje.databas.project.tabeles;

public class WorkRole {
    private int roleId;           // Auto-genererat primärnyckel
    private String title;         // Titel för arbetsrollen
    private String description;   // Beskrivning av arbetsrollen
    private double salary;        // Lön
    private String creationDate;  // Skapelsedatum

    public WorkRole(int roleId, String title, String description, double salary, String creationDate) {
        this.roleId = roleId;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "WorkRole{" +
                "roleId=" + roleId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }

    public String getCreationDate() {
        return creationDate;
    }

    public double getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public int getRoleId() {
        return roleId;
    }
}