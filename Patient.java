
import java.util.List;
import java.util.Scanner;

public class Patient extends User {

    private int age;
    private String gender;
    private String doctorId;

    public Patient(String id, String name, String username,
            String password, String phone,
            int age, String gender, String doctorId) {

        super(name, username, password, phone, "PATIENT");
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.doctorId = doctorId;
    }

    public void create() {

        this.id = FileManager.generateId("patients");
        String patient = this.toCSV();
        FileManager.save("patients", patient);
    }

    public static Patient fromCSV(String record) {
        String[] data = record.split(",");
        Patient patient = new Patient(
                data[0], // id
                data[1], // name
                data[2], // username
                data[3], // password
                data[4], // phone
                Integer.parseInt(data[5]), // age
                data[6],
                data[7] // doctorId
        );

        patient.setId(data[0]);

        return patient;
    }

    public String toCSV() {
        return String.join(",",
                id,
                name,
                username,
                password,
                phone,
                String.valueOf(age),
                gender,
                doctorId
        );
    }

    @Override
    public void viewPersonalInfo() {
        System.out.println("=== Patient Info ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }

    @Override
    public void viewAppointments() {

        System.out.println("=== My Appointments ===");

        List<String> appointments = FileManager.findAll("appointments");
        boolean found = false;

        for (String record : appointments) {
            String[] data = record.split(",");

            if (data[1].equals(this.id)) {
                System.out.println("Appointment ID: " + data[0]);
                System.out.println("Doctor ID: " + data[2]);
                System.out.println("Date: " + data[3]);
                System.out.println("Time: " + data[4]);
                System.out.println("Status: " + data[5]);
                System.out.println("------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found.");
        }
    }

    public void viewAssignedDoctor() {

        System.out.println("=== Assigned Doctor ===");
        if (doctorId == null || doctorId.isEmpty()) {
            System.out.println("No doctor assigned.");
            return;
        }
        String doctor = FileManager.findById("doctors", doctorId);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }
        String[] data = doctor.split(",");

        System.out.println("ID: " + data[0]);
        System.out.println("Name: " + data[1]);
        System.out.println("Specialization: " + data[4]);
        System.out.println("Department: " + data[5]);
        System.out.println("Phone: " + data[6]);
    }

    public void bookAppointmentWithDoctor() {
        if (doctorId == null || doctorId.isEmpty() || doctorId.equals("null")) {
            System.out.println("Error: You cannot book an appointment without being assigned to a doctor.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter date (yyyy-mm-dd): ");
        String date = input.nextLine();
        System.out.print("Enter time (hh:mm): ");
        String time = input.nextLine();

        Appointment.create(this.id, date, time, "PENDING");
        System.out.println("successful");
    }

    public void cancelAppointmentWithDoctor() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter appointment ID: ");
        String appId = input.nextLine();

        String record = FileManager.findById("appointments", appId);
        if (record != null) {
            Appointment app = Appointment.fromCSV(record);

            if (app.getPatientId().equals(this.id)) {
                app.setStatus("Cancelled");
                FileManager.update("appointments", appId, app.toCSV());
                System.out.println("Appointment cancelled successfully.");
            } else {
                System.out.println("Error: This appointment does not belong to you.");
            }
        } else {
            System.out.println("Error: Appointment ID not found.");
        }
    }

    public String getDoctorId() {
        return doctorId;
    }
}
