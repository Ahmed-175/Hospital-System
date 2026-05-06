
public class Appointment {

    private String id;
    private String patientId;
    private String doctorId;
    private String date;   // e.g: 2026-05-15
    private String time;   // e.g: 10:30
    private String status;

    public Appointment(String id, String patientId,
            String doctorId, String date,
            String time, String status) {

        this.id = FileManager.generateId("appointments");
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
        FileManager.save("appointments", this.toCSV());
        System.out.println("Create : " + this.id);

    }

    public String toCSV() {
        return String.join(",",
                id,
                patientId,
                doctorId,
                date,
                time,
                status
        );
    }

    public static Appointment fromCSV(String record) {

        String[] data = record.split(",");

        Appointment a = new Appointment(
                data[0], // id
                data[1], // patientId
                data[2], // doctorId
                data[3], // date
                data[4], // time
                data[5] // status
        );

        return a;
    }

    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //must be validated:
    //• A patient cannot book an appointment without being assigned to a doctor.
    //• A doctor cannot have two appointments at the same date and time.
    // • Appointment date cannot be empty.
    //• Appointment time cannot be empty.
    //• A cancelled appointment cannot be marked as completed.
}
