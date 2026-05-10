
import java.util.*;

public class Admin extends User {
    //must do:
    //• Add a new doctor. tt
    //• Register a new patient. tt
    //• Assign a patient to a doctor. tt
    //• Create an appointment. tt
    //• View all appointments. tt
    //• View all doctors and patients. tt
    //• Search for a patient by ID. tt
    //• Search for a doctor by ID. tt
    //• Save and load data from files. tt
    //and
    //The admin should be able to generate simple reports about the hospital system.
    //• The reports should include:
    //• Total number of doctors and patients          tt
    //• Total number of appointments (each status).   tt
    //• Doctor with the highest number of appointments (Top 3 Doctors). tt

    public Admin(String name, String username, String password, String phone) {
        super(name, username, password, phone, "ADMIN");
        this.id = "ADMIN123";
    }

    public static void addDoctor(String name, String username, String password,
            String phone, String specialization, String department) {

        List<String> users = FileManager.findAll("users");
        for (String u : users) {
            String[] data = u.split(",");
            if (data[2].equals(username)) {
                System.out.println("Error: Username already exists.");
                return;
            }
        }
        new Doctor(name, username, password, phone, specialization, department , true);
        System.out.println("Doctor added and saved successfully.");
    }

    public static void registerPatient(String name, String username, String password,
            String phone, int age, String gender) {
        List<String> users = FileManager.findAll("users");
        for (String u : users) {
            String[] data = u.split(",");
            if (data[2].equals(username)) {
                System.out.println("Error: Username already exists.");
                return;
            }
        }
        Patient p =  new Patient("",name, username, password, phone, age, gender, "null");
        p.create();
        System.out.println("Patient registered and saved successfully.");
    }

    public static void assignPatientToDoctor(String patientId, String doctorId) {

        String patientData = FileManager.findById("patients", patientId);
        String doctorData = FileManager.findById("doctors", doctorId);

        if (patientData != null && doctorData != null) {
            String[] parts = patientData.split(",");
            parts[7] = doctorId;

            FileManager.update("patients", patientId, String.join(",", parts));
            System.out.println("Success: Patient " + patientId + " assigned to Dr. " + doctorId);
        } else {
            System.out.println("Error: Patient or Doctor ID not found.");
        }
    }

    public static void createAppointment(String patientId, String date, String time) {
       Appointment.create(patientId, date, time, "Confirmed" );
    }

    public static void viewAllDoctors() {
        List<String> doctors = FileManager.findAll("doctors");

        System.out.println("=== All Hospital Doctors ===");
        for (String dstr : doctors) {
            String[] data = dstr.split(",");
            System.out.println(" Name:" + data[1] + " Id:" + data[0]);
        }
    }

    public static void viewAllPatients() {
        System.out.println("=== All Hospital Patients ===");
        List<String> patients = FileManager.findAll("patients");

        for (String pstr : patients) {
            String[] data = pstr.split(",");
            System.out.println(" Name:" + data[1] + " Id:" + data[0]);
        }
    }

    public static void viewAllAppointments() {

        List<String> appointments = FileManager.findAll("appointments");

        if (appointments.isEmpty()) {
            System.out.println("\nNo appointments found.");
            return;
        }

        System.out.println("\n============== All Hospital Appointments ==============");

        for (String record : appointments) {

            String[] appointmentData = record.split(",");

            String appointmentId = appointmentData[0];
            String patientId = appointmentData[1];
            String doctorId = appointmentData[2];
            String date = appointmentData[3];
            String time = appointmentData[4];
            String status = appointmentData[5];

            String patientRecord = FileManager.findById("patients", patientId);
            String doctorRecord = FileManager.findById("doctors", doctorId);

            String patientName = "Unknown Patient";
            String doctorName = "Unknown Doctor";

            if (patientRecord != null) {
                String[] patientData = patientRecord.split(",");
                patientName = patientData[1];
            }

            if (doctorRecord != null) {
                String[] doctorData = doctorRecord.split(",");
                doctorName = doctorData[1];
            }

            System.out.println("--------------------------------------------------");
            System.out.println("Appointment ID : " + appointmentId);
            System.out.println("Doctor         : Dr. " + doctorName);
            System.out.println("Patient        : " + patientName);
            System.out.println("Date           : " + date);
            System.out.println("Time           : " + time);
            System.out.println("Status         : " + status);
        }

        System.out.println("-------------------------------------------------------");
    }

    public static void viewAllDocPat() {
        List<String> doctors = FileManager.findAll("doctors");
        List<String> patients = FileManager.findAll("patients");

        System.out.println("=== All Hospital Doctors ===");
        for (String dstr : doctors) {
            String[] data = dstr.split(",");
            System.out.println(" Name:" + data[1] + " Id:" + data[0]);
        }
        System.out.println("=== All Hospital Patients ===");
        for (String pstr : patients) {
            String[] data = pstr.split(",");
            System.out.println(" Name:" + data[1] + " Id:" + data[0]);
        }
    }

    public static  void searchPatientById(String id) {
        String data = FileManager.findById("patients", id);
        if (data != null) {
            Patient p = Patient.fromCSV(data);
            p.viewPersonalInfo();
        } else {
            System.out.println("Patient not found.");
        }
    }

    public static  void searchDoctorById(String id) {
        String data = FileManager.findById("doctors", id);
        if (data != null) {
            Doctor d = Doctor.fromCSV(data);
            d.viewPersonalInfo();
        } else {
            System.out.println("Doctor not found.");
        }
    }

    public static  void generateReports() {
        List<String> doctors = FileManager.findAll("doctors");
        List<String> patients = FileManager.findAll("patients");
        List<String> apps = FileManager.findAll("appointments");

        System.out.println("\n--- Hospital Report ---");
        System.out.println("Total Doctors  : " + doctors.size());
        System.out.println("Total Patients : " + patients.size());

        int appCONFIRMED = 0;
        int appPENDING = 0;
        int appCANCELLED = 0;

        for (String astr : apps) {
            String[] data = astr.split(",");
            if (data[5].equalsIgnoreCase("CONFIRMED")) {
                appCONFIRMED++;
            } else if (data[5].equalsIgnoreCase("PENDING")) {
                appPENDING++;
            } else {
                appCANCELLED++;
            }
        }
        System.out.println("Total Appointment CONFIRMED: " + appCONFIRMED);
        System.out.println("Total Appointment PENDING: " + appPENDING);
        System.out.println("Total Appointment CANCELLED: " + appCANCELLED);

        String[] names = new String[doctors.size()];
        int[] counts = new int[doctors.size()];
        for (int i = 0; i < doctors.size(); i++) {
            String[] dData = doctors.get(i).split(",");
            names[i] = dData[1]; // name
            String dId = dData[0]; // id

            int c = 0;
            for (String app : apps) {
                if (app.split(",")[2].equals(dId)) {// app.split(",") hyasem elsattr ly parts ,,[2] tany goz2 fy el parts el hwa el doc id
                    c++;
                }
            }
            counts[i] = c;//kda 2 arrays mwazyyn ly ba3d,wa7da llesm wy el2 ll count id fy el appoin ya3ny 3andoh kam appoin
        }
        System.out.println("============Top 3 Doctors==========");

        for (int iteration = 0; iteration < 3; iteration++) {//max method
            int maxVal = -1;
            int maxIndex = -1;

            // get max value
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > maxVal) {//lw el rakam ely fy elcounts array akbar 7ootoh fy maxval
                    maxVal = counts[i];
                    maxIndex = i;//aked 2oly mkan el mac value fy elarray
                }
            }
            if (maxIndex != -1) {//make sure that count arr isnt empty
                System.out.println((iteration + 1) + ". Dr. " + names[maxIndex] + " (" + maxVal + " appointments)");
                counts[maxIndex] = -1;//make the max value ==-1 to get the next max value in the array
            }
        }
    }
}
