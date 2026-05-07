import  java.util.List;

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


        //make sure date time not empty
        if(date == null || date.trim().isEmpty() || time == null || time.trim().isEmpty()){
            //akeno exception
            System.out.println("Error: Appointment date and time cannot be empty.");
            //stop matkamelsh
            return;
        }

        //make sure patient has doctor,, if doesn't has doctor or has another doctor return
        String patientData = FileManager.findById("patients", patientId);
        if(patientData != null){
            String[] pParts = patientData.split(",");
            String assignedDocId = pParts[7]; // last thing in paient.txt is doc id
            if(assignedDocId.equals("null") || !assignedDocId.equals(doctorId)){
                System.out.println("Error: Patient cannot book with this doctor (Not assigned)");
                return;
            }
        }

        // make sure doctor cannot have two appointments at the same date and time.
        List<String> allAppointments = FileManager.findAll("appointments");
        for (String record : allAppointments) {
            String[] aParts = record.split(",");
            //if same doc and same date and same time and not cancelled
            if (aParts[2].equals(doctorId) && aParts[3].equals(date) && aParts[4].equals(time) && !aParts[5].equalsIgnoreCase("Cancelled")) {
                System.out.println("Error: Doctor already has an appointment at this time.");
                //don't continue
                return;
            }
        }

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

    public void setStatus(String status){
        //Prohibit cancelled appointment be marked as completed
        if(this.status.equalsIgnoreCase("Cancelled") &&
                status.equalsIgnoreCase("Completed")){
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
