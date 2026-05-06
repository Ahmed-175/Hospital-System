
public class Patient extends User {

    private int age;
    private String gender;
    private String doctorId;

    public Patient(String name, String username,
            String password, String phone,
            int age, String gender, String doctorId) {

        super(name, username, password, phone, "PATIENT");
        this.id = FileManager.generateId("patients");

        this.age = age;
        this.gender = gender;
        this.doctorId = doctorId;
        FileManager.save("patients", this.toCSV());
        FileManager.save("users", this.userToCSV());

    }

    public static Patient fromCSV(String record) {

        String[] data = record.split(",");
        Patient patient = new Patient(
                data[1], // name
                data[2], // username
                data[3], // password
                data[4], // phone
                Integer.parseInt(data[5]), // age
                data[6], // gender
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
    }

    public void viewAssignedDoctor() {
    }

    public void bookAppointmentWithDoctor() {
    }

    public void cancelAppointmentWithDoctor() {
    }

    public String getDoctorId() {
        return doctorId;
    }
}
