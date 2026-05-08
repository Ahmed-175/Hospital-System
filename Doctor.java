
import java.util.*;

public class Doctor extends User {

    private String specialization;
    private String department;
    private List<String> patients;
    private List<String> appointments;

    public Doctor(String name, String username,
            String password, String phone,
            String specialization, String department, boolean save) {

        super(name, username, password, phone, "DOCTOR");

        this.id = FileManager.generateId("doctors");
        this.specialization = specialization;
        this.department = department;
        this.patients = FileManager.findAll("patients");
        this.appointments = FileManager.findAll("appointments");
        if (save) {

            FileManager.save("doctors", this.toCSV());
            FileManager.save("users", this.userToCSV());
        }
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
                data[5], // department
                false
        );
        doctor.setId(data[0]);
        return doctor;
    }

    @Override
    public void viewPersonalInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Specialization: " + specialization);
        System.out.println("Department: " + department);
        System.out.println("Phone: " + phone);
    }

    @Override
    public void viewAppointments() {
        boolean found = false;

        for (String record : appointments) {
            String[] data = record.split(",");

            // A001,P001,D001,2026-05-15,10:30,confirmed
            if (data[2].equals(this.id)) {
                System.out.println("Appointment ID: " + data[0]);
                System.out.println("Patient ID: " + data[1]);
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

    public String getSpecialization() {
        return specialization;
    }

    public String getDepartment() {
        return department;
    }

    public void viewAssignedPatients() {
        boolean found = false;

        for (String record : patients) {
            String[] data = record.split(",");
            if (data[7].equals(this.id)) {
                System.out.println("ID: " + data[0]);
                System.out.println("Name: " + data[1]);
                System.out.println("Phone: " + data[4]);
                System.out.println("------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No assigned patients.");
        }
    }

    public void updateAppointmentStatus(String appointmentId, String newStatus) {
        String record = FileManager.findById("appointments", appointmentId);
        if (record != null) {
            Appointment app = Appointment.fromCSV(record);
            System.out.println("Status : " + app.getStatus());

            if (app.getDoctorId().equals(this.id)) {
                app.setStatus(newStatus);
                FileManager.update("appointments", appointmentId, app.toCSV());
                System.out.println("Status updated successfully.");
            } else {
                System.out.println("Error: You are not authorized to update this appointment ,you are not this appointment doctor");
            }
        } else {
            System.out.println("Error: Appointment ID not found.");
        }
    }
}
