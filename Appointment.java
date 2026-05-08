
public class Appointment {

    private String id;
    private String patientId;
    private String doctorId;
    private String date;   // e.g: 2026-05-15
    private String time;   // e.g: 10:30
    private String status;

    public Appointment() {
    }

    public Appointment(
            String id,
            String patientId,
            String doctorId,
            String date,
            String time,
            String status
    ) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
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


    public static Appointment create(
            String patientId,
            String doctorId,
            String date,
            String time,
            String status
    ) {

        if (date == null || date.isEmpty()
                || time == null || time.isEmpty()) {
            throw new IllegalArgumentException(
                    "Date and time required"
            );
        }

        String id = FileManager.generateId("appointments");

        Appointment app = new Appointment(
                id,
                patientId,
                doctorId,
                date,
                time,
                status
        );

        FileManager.save("appointments", app.toCSV());

        return app;
    }

    public static Appointment fromCSV(String record) {

        String[] data = record.split(",");

        Appointment a = new Appointment(
                data[0], // id
                data[1], // patientId
                data[2], // doctorId
                data[3], // date
                data[4], // time
                data[5]// status

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
        //Prohibit cancelled appointment be marked as completed
        if (this.status.equalsIgnoreCase("Cancelled")
                && status.equalsIgnoreCase("Completed")) {
            System.out.println("Error: A cancelled appointment cannot be marked as completed.");
            return;
        }
        this.status = status;
    }

    //must be validated:
    // • Appointment date ,time cannot be empty. tt
    //• A patient cannot book an appointment without being assigned to a doctor. tt
    //• A doctor cannot have two appointments at the same date and time. tt
    //• A cancelled appointment cannot be marked as completed. tt
}
