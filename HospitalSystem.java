
import java.util.List;
import java.util.Scanner;

public class HospitalSystem {

    public static void run() {
        Scanner input = new Scanner(System.in);
        System.out.println("===================================================");
        System.out.println("Welcome to Hospital Appointments Management System!");
        System.out.println("===================================================");
        System.out.println();

        int UserChoice = 0;
        do {
            System.out.println("1- Login as Admin");
            System.out.println("2- Login as Doctor");
            System.out.println("3- Login as Patient");
            System.out.println("4- Exit");
            System.out.print("What your choise (number between 1 and 4) : ");
            try {
                UserChoice = input.nextInt();
                input.nextLine();
                if (UserChoice < 1 || UserChoice > 4) {
                    System.out.println("please Enter between (1-4)");
                }
            } catch (Exception e) {
                System.out.println("please Enter only a number !!");
                input.nextLine();
            }
            switch (UserChoice) {

                case 1 ->
                    loginAdmin();
                case 2 ->
                    System.out.println("method login Doctor");
                case 3 ->
                    System.out.println("method login Patient");
                case 4 ->
                    System.out.println("method  saving data");
                default ->
                    System.out.println("");
            }

        } while (UserChoice != 4);
    }

    private static void loginAdmin() {
        Scanner input = new Scanner(System.in);

        System.out.println("##############");
        System.out.println("Login As Admin");
        System.out.println("##############");

        String username;
        String password;

        System.out.print("Enter username : ");
        username = input.next();

        System.out.print("Enter password : ");
        password = input.next();

        List<String> data = FileManager.findAll("users");

        boolean isLoggedIn = false;

        // A001,Ahmed,admin123,1234,01000000000,ADMIN
        for (String record : data) {

            String[] user = record.split(",");

            String dbUsername = user[2];
            String dbPassword = user[3];
            String role = user[5];

            if (dbUsername.equals(username)
                    && dbPassword.equals(password)
                    && role.equalsIgnoreCase("ADMIN")) {

                isLoggedIn = true;

                System.out.println("\nLogin Successful\n");

                // Call admin menu function
                // adminMenu();
                return;
            }
        }

        if (!isLoggedIn) {
            System.out.println("\nInvalid username or password\n");
        }

    }

    private  static void adminMenu(String name){
        
    }
}
