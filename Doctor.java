
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
        // this.patients;
        // this.appointments;Z

        FileManager.save("doctors", this.toCSV());
        FileManager.save("users", this.userToCSV());
    }

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

    public static Doctor fromCSV(String record) {
        String[] data = record.split(",");
        Doctor doctor = new Doctor(
                data[1], // name
                data[2], // username
                data[3], // password
                data[6], // phone
                data[4], // specialization
                data[5] // department
        );
        doctor.setId(data[0]);
        return doctor;
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

    public String getSpecialization() {
        return specialization;
    }

    public String getDepartment() {
        return department;
    }
}
