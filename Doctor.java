
import java.util.*;

public class Doctor extends User {

    private String specialization;
    private String department;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public Doctor(String name, String username,
                  String password, String phone,
                  String specialization, String department) {

        super(name, username, password, phone, "DOCTOR");

        this.id = FileManager.generateId("doctors");
        this.specialization = specialization;
        this.department = department;
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();

        FileManager.save("doctors", this.toCSV());
        FileManager.save("users", this.userToCSV());
    }

    // Convert Doctor to CSV
    public String toCSV() {
        return String.join(",",
                id,
                name,
                username,
                password,
                specialization,
                department,
                phone
        );
    }

    @Override
    public void viewPersonalInfo() {
        System.out.println("=== Doctor Info ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Specialization: " + specialization);
        System.out.println("Department: " + department);
        System.out.println("Phone: " + phone);
    }

    @Override
    public void viewAppointments() {
        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }
}