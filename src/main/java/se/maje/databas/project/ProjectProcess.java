package se.maje.databas.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class ProjectProcess {

    public static Scanner scanner = new Scanner(System.in);
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    WorkRoleDAOImpl workRoleDAO = new WorkRoleDAOImpl();


    public void printWelcometMenu() throws Exception {

// Loopen kommer att köras så länge 'running' är true
        System.out.println("Mina sidor: Logga in genom att ange din e-postadress och ditt lösenord\n" );

        boolean loggedIn = false;
        String choiceEpost = null;

    while(!loggedIn) {
        System.out.println("Användare :");
        choiceEpost = ProjectInputValidater.getValidatedStringInput();
        System.out.println("Lössenord");
        String choicePassword = ProjectInputValidater.getValidatedStringInput();
        loggedIn = new ProjectLoginService().loginUser(choiceEpost, choicePassword);

        if (!loggedIn) {
            System.out.println("Felaktigt användarnamn eller lösenord. Försök igen.");
        } else {
            System.out.println(ProjectConstants.RED +"Inloggning lyckades! Välkommen, " + choiceEpost + "." +ProjectConstants.RESET);
        }

    }

        while (ProjectConstants.running) {
            System.out.println("Vilken del vill du gå, och ange att giltiga värden är nedanstående\n" +
                    "1. Skapa ny arbetsroll\n" +
                    "2. Ta bort en arbetsroll\n" +
                    "3. Visa alla arbetsroller\n" +
                    "4. Uppdatera en arbetsroll\n" +
                    "5. visa en arbetsroll\n" +
                    "6. logga in och se din (den anställdes) roll\n" +
                    "7. avsluta\n" +
                    "Enter valet: ");
            int choice = ProjectInputValidater.getValidatedIntegerInput();
            projectchoice(choice,choiceEpost);


        }
    }


    public boolean projectchoice(int menuChoice, String choiceEpost) throws Exception {

        switch (menuChoice) {
            case 1:
               workRoleDAO.PreparedStatementInsert();
                break;
            case 2:
                workRoleDAO.PreparedStatementDelete();
                break;
            case 3:
                workRoleDAO.PreparedStatementSelect();
                break;
            case 4:
                workRoleDAO.PreparedStatementUpdate();
                break;
            case 5:

                break;
            case 6:
               employeeDAO.createEmployeeObject(choiceEpost);
                break;
            case 7:
                System.out.println("Avslutar programmet. Tack för att du använde Mina sidor!");
                ProjectConstants.running = false;
                break;
        }

        return true;
    }

    public static Object updateAndDeleteWorkRoleColumn() {
        List<Object> updatedValues = new ArrayList<>();
        String columnName = null;
        String stringValue = null;
        double doubleValue = 0.0;
        Date dateValue = null;
        int IdValue = 0;

//         Visa meny med alternativ
        System.out.println("Vilken kolumn vill du uppdatera?" +
                "\n 1.TITLE" +
                "\n 2.DESCRIPTION" +
                "\n 3.SALARY" +
                "\n 4.CREATION_DATE" +
                "\n 5.EXIT");

        // Hämta användarens val
        int menuChoice = ProjectInputValidater.getValidatedIntegerInput();

        // Bearbeta användarens val
        switch (menuChoice) {
            case 1:
                // Uppdatera TITLE
                columnName = "TITLE";
                System.out.println("Ange ditt TITLE :");
                stringValue = ProjectInputValidater.getValidatedStringInput();
                System.out.println("Ange ditt WORK_ID :");
                IdValue = ProjectInputValidater.getValidatedIntegerInput();

                updatedValues.add("TITLE");
                updatedValues.add(stringValue);
                updatedValues.add( IdValue);
                break;

            case 2:
                // Uppdatera DESCRIPTION
                columnName = "DESCRIPTION";
                System.out.println("Ange ditt DESCRIPTION :");
                stringValue = ProjectInputValidater.getValidatedStringInput();
                System.out.println("Ange ditt WORK_ID :");
                IdValue = ProjectInputValidater.getValidatedIntegerInput();

                updatedValues.add("DESCRIPTION");
                updatedValues.add(stringValue);
                updatedValues.add( IdValue);
                break;

            case 3:
                // Uppdatera SALARY
                columnName = "SALARY";
                System.out.println("Ange ditt SALARY :");
                doubleValue = ProjectInputValidater.getValidatedDoubleInput();
                System.out.println("Ange ditt WORK_ID :");
                IdValue = ProjectInputValidater.getValidatedIntegerInput();

                updatedValues.add("SALARY");
                updatedValues.add(doubleValue);
                updatedValues.add( IdValue);
                break;

            case 4:
                // Uppdatera CREATION_DATE
                columnName = "CREATION_DATE";
                System.out.println("Ange ditt CREATION_DATE (format: yyyy-MM-dd):");
                dateValue = ProjectInputValidater.getValidatedDateInput();
                System.out.println("Ange ditt WORK_ID :");
                IdValue = ProjectInputValidater.getValidatedIntegerInput();

                updatedValues.add("CREATION_DATE");
                updatedValues.add(dateValue);
                updatedValues.add( IdValue);
                break;

            case 5:
                // Avsluta metoden
                return updatedValues;

            default:
                System.out.println("Ogiltigt val, försök igen.");
                return updatedValues; // Return om ogiltigt val
        }

        return  updatedValues;
    }























//    public String getUserTitle(employeeDAO) {
//
//        return this.employeeDAO.;
//    }

//    public void start() throws SQLException {
//        printWelcometMenu();
//        while (ProjectConstants.running) {
//
//            //        processInput(getUserInput());
////        if (HouseConstatants.residentCurrentLocation.equals(HouseConstatants.burglarCurrentLocation))
////            return;
//        }
//    }


    String processInput(String epost) {



        return "0";


    }

}


