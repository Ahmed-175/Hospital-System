
import java.util.List;
import java.util.Scanner;

public class HospitalSystem {

    private static final Scanner input = new Scanner(System.in);

    public static void run() {

        System.out.println("==================================================");
        System.out.println(" Welcome to Hospital Management System ");
        System.out.println("==================================================");
        
        int userChoice = 0;

        do {

            System.out.println("\n=============== Main Menu ===============");
            System.out.println("1- Login as Admin");
            System.out.println("2- Login as Doctor");
            System.out.println("3- Login as Patient");
            System.out.println("4- Exit");

            System.out.print("Enter your choice (1-4): ");

            try {

                userChoice = input.nextInt();
                input.nextLine();

                switch (userChoice) {

                    case 1 ->
                        loginAdmin();

                    case 2 ->
                        loginDoctor();

                    case 3 ->
                        loginPatient();
                    case 4 ->
                        System.out.println("Exiting System...");

                    default ->
                        System.out.println("Please enter a number between 1 and 4.");
                }

            } catch (Exception e) {

                System.out.println("Invalid input! Please enter numbers only.");
                input.nextLine();
            }

        } while (userChoice != 4);
    }

    private static void loginAdmin() {

        System.out.println("\n============== Admin Login ==============");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        List<String> users = FileManager.findAll("users");

        for (String record : users) {

            // A001,Ahmed,admin123,1234,01000000000,ADMIN
            String[] userData = record.split(",");

            String dbUsername = userData[2];
            String dbPassword = userData[3];
            String role = userData[5];

            if (dbUsername.equals(username)
                    && dbPassword.equals(password)
                    && role.equalsIgnoreCase("ADMIN")) {

                System.out.println("\nLogin Successful.");

                adminMenu(userData[1]);
                return;
            }
        }

        System.out.println("\nInvalid username or password.");
    }

    private static void adminMenu(String adminName) {

        System.out.println("\n==================================================");
        System.out.println(" Welcome " + adminName + " To Admin Dashboard ");
        System.out.println("==================================================");

        int userChoice = 0;

        do {

            System.out.println("\n================ Admin Menu ================");
            System.out.println("1- Add Doctor");
            System.out.println("2- Register Patient");
            System.out.println("3- Assign Patient To Doctor");
            System.out.println("4- Create Appointment");
            System.out.println("5- View All Doctors");
            System.out.println("6- View All Patients");
            System.out.println("7- View All Appointments");
            System.out.println("8- Search Patient By ID");
            System.out.println("9- Search Doctor By ID");
            System.out.println("10- Generate Reports");
            System.out.println("11- Logout");

            System.out.print("Enter your choice (1-11): ");

            try {

                userChoice = input.nextInt();
                input.nextLine();

                switch (userChoice) {

                    case 1 -> {

                        System.out.println("\n============== Add Doctor ==============");

                        System.out.print("Enter doctor name: ");
                        String doctorName = input.nextLine();

                        System.out.print("Enter username: ");
                        String username = input.nextLine();

                        System.out.print("Enter password: ");
                        String password = input.nextLine();

                        System.out.print("Enter phone: ");
                        String phone = input.nextLine();

                        System.out.print("Enter specialization: ");
                        String specialization = input.nextLine();

                        System.out.print("Enter department: ");
                        String department = input.nextLine();

                        Admin.addDoctor(
                                doctorName,
                                username,
                                password,
                                phone,
                                specialization,
                                department
                        );
                    }

                    case 2 -> {

                        System.out.println("\n============ Register Patient ============");

                        System.out.print("Enter patient name: ");
                        String patientName = input.nextLine();

                        System.out.print("Enter username: ");
                        String username = input.nextLine();

                        System.out.print("Enter password: ");
                        String password = input.nextLine();

                        System.out.print("Enter phone: ");
                        String phone = input.nextLine();

                        System.out.print("Enter age: ");
                        int age = input.nextInt();
                        input.nextLine();

                        System.out.print("Enter gender: ");
                        String gender = input.nextLine();

                        Admin.registerPatient(
                                patientName,
                                username,
                                password,
                                phone,
                                age,
                                gender
                        );
                    }

                    case 3 -> {

                        System.out.println("\n======== Assign Patient To Doctor ========");

                        System.out.print("Enter patient ID: ");
                        String patientId = input.nextLine();

                        System.out.print("Enter doctor ID: ");
                        String doctorId = input.nextLine();

                        Admin.assignPatientToDoctor(patientId, doctorId);
                    }

                    case 4 -> {

                        System.out.println("\n=========== Create Appointment ===========");

                        System.out.print("Enter patient ID: ");
                        String patientId = input.nextLine();


                        System.out.print("Enter appointment date: ");
                        String date = input.nextLine();

                        System.out.print("Enter appointment time: ");
                        String time = input.nextLine();

                        Admin.createAppointment(
                                patientId,
                                date,
                                time
                        );

                        System.out.println("Appointment created successfully.");
                    }

                    case 5 -> {

                        Admin.viewAllDoctors();
                    }

                    case 6 -> {

                        Admin.viewAllPatients();
                    }

                    case 7 -> {
                        Admin.viewAllAppointments();
                    }

                    case 8 -> {

                        System.out.println("\n=========== Search Patient ===========");

                        System.out.print("Enter patient ID: ");
                        String patientId = input.nextLine();

                        Admin.searchPatientById(patientId);
                    }

                    case 9 -> {

                        System.out.println("\n=========== Search Doctor ===========");

                        System.out.print("Enter doctor ID: ");
                        String doctorId = input.nextLine();

                        Admin.searchDoctorById(doctorId);
                    }

                    case 10 -> {

                        System.out.println("\n=========== Hospital Reports ===========");

                        Admin.generateReports();
                    }

                    case 11 -> {
                        System.out.println("\nLogged out successfully.");
                        break;
                    }

                    default -> {

                        if (userChoice < 1 || userChoice > 11) {
                            System.out.println("Please enter a number between 1 and 11.");
                        }
                    }
                }

            } catch (Exception e) {

                System.out.println("Invalid input! Please enter numbers only.");
                input.nextLine();
            }

        } while (userChoice != 11);
    }

    private static void loginDoctor() {

        System.out.println("\n============== Doctor Login ==============");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        List<String> users = FileManager.findAll("users");

        for (String record : users) {

            // D001,Ahmed,ahmed123,1234,01000000000,DOCTOR
            String[] userData = record.split(",");

            String dbUsername = userData[2];
            String dbPassword = userData[3];
            String role = userData[5];

            if (dbUsername.equals(username)
                    && dbPassword.equals(password)
                    && role.equalsIgnoreCase("DOCTOR")) {

                String doctorRecord = FileManager.findById("doctors", userData[0]);

                if (doctorRecord != null) {

                    Doctor doctor = Doctor.fromCSV(doctorRecord);

                    System.out.println("\nLogin Successful.");

                    doctorMenu(doctor);

                    return;
                }
            }
        }

        System.out.println("\nInvalid username or password.");
    }

    private static void doctorMenu(Doctor doctor) {

        int userChoice = 0;

        do {

            System.out.println("\n==================================================");
            System.out.println(" Welcome Dr. " + doctor.name);
            System.out.println("==================================================");

            System.out.println("1- View My Profile");
            System.out.println("2- View Assigned Patients");
            System.out.println("3- View My Appointments");
            System.out.println("4- Update Appointment Status");
            System.out.println("5- Logout");

            System.out.print("Enter your choice (1-5): ");

            try {

                userChoice = input.nextInt();
                input.nextLine();
                switch (userChoice) {
                    case 1 -> {
                        System.out.println("\n============= My Profile =============");
                        doctor.viewPersonalInfo();
                    }
                    case 2 -> {
                        System.out.println("\n========== Assigned Patients ==========");
                        doctor.viewAssignedPatients();
                    }
                    case 3 -> {
                        System.out.println("\n=========== My Appointments ===========");
                        doctor.viewAppointments();
                    }
                    case 4 -> {

                        System.out.println("\n======= Update Appointment Status =======");

                        System.out.print("Enter appointment ID: ");
                        String appointmentId = input.nextLine();

                        System.out.println("\nChoose Appointment Status:");
                        System.out.println("1- CONFIRMED");
                        System.out.println("2- DONE");
                        System.out.println("3- CANCELLED");

                        System.out.print("Enter your choice (1-3): ");

                        int statusChoice = input.nextInt();
                        input.nextLine();

                        String newStatus = "CONFIRMED";

                        switch (statusChoice) {

                            case 1 ->
                                newStatus = "CONFIRMED";

                            case 2 ->
                                newStatus = "DONE";

                            case 3 ->
                                newStatus = "CANCELLED";

                            default -> {

                                System.out.println("Invalid status choice.");
                                break;
                            }
                        }

                        doctor.updateAppointmentStatus(
                                appointmentId,
                                newStatus
                        );
                    }
                    case 5 -> {

                        System.out.println("\nLogged out successfully.");
                    }

                    default -> {

                        if (userChoice < 1 || userChoice > 5) {
                            System.out.println("Please enter a number between 1 and 5.");
                        }
                    }
                }

            } catch (Exception e) {

                System.out.println("Invalid input! Please enter numbers only.");
                input.nextLine();
            }

        } while (userChoice != 5);
    }

    private static void loginPatient() {

        System.out.println("\n============== Patient Login ==============");

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        List<String> users = FileManager.findAll("users");

        for (String record : users) {

            // P001,Ahmed Ali,ahmedA,1234,01011112222,PATIENT
            String[] userData = record.split(",");

            String dbUsername = userData[2];
            String dbPassword = userData[3];
            String role = userData[5];

            if (dbUsername.equals(username)
                    && dbPassword.equals(password)
                    && role.equalsIgnoreCase("Patient")) {

                System.out.println("\nLogin Successful.");

                patientMenu(userData[0]);
                return;
            }
        }

        System.out.println("\nInvalid username or password.");
    }

    private static void patientMenu(String id) {
        String record =  FileManager.findById("patients", id);
        Patient patient = Patient.fromCSV(record);


        System.out.println("\n==================================================");
        System.out.println(" Welcome " + patient.name + " To Patient Dashboard ");
        System.out.println("==================================================");

        int userChoice = 0;

        do {

            System.out.println("\n================ Patient Menu ================");
            System.out.println("1- View Personal Info");
            System.out.println("2- View Appointments");
            System.out.println("3- View Assigned Doctor");
            System.out.println("4- Book Appointment With Doctor");
            System.out.println("5- Cancel Appointment With Doctor");
            System.out.println("6- Logout");

            System.out.print("Enter your choice (1-6): ");

            try {

                userChoice = input.nextInt();
                input.nextLine();

                switch (userChoice) {

                    case 1 -> {
                        patient.viewPersonalInfo();
                    }

                    case 2 -> {
                        patient.viewAppointments();
                    }

                    case 3 -> {
                        patient.viewAssignedDoctor();
                    }

                    case 4 -> {
                        patient.bookAppointmentWithDoctor();
                    }

                    case 5 -> {
                        patient.cancelAppointmentWithDoctor();
                    }

                    case 6 -> {
                        System.out.println("\nLogged out successfully.");
                        break;
                    }

                    default -> {

                        if (userChoice < 1 || userChoice > 6) {
                            System.out.println("Please enter a number between 1 and 6.");
                        }
                    }
                }

            } catch (Exception e) {

                System.out.println("Invalid input! Please enter numbers only.");
                input.nextLine();
            }

        } while (userChoice != 6);
    }
}
