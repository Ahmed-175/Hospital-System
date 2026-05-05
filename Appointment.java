public class Appointment {

    public int Appointment_ID;
    public int Patient_ID;
    public int Doctor_ID;
    public String Date;//ex:2026-05-15
    public String Time;//ex:10:30
    public String Status;

    public Appointment(int appointment_ID, String date, int doctor_ID, int patient_ID, String status, String time) {
        this.Appointment_ID = appointment_ID;
        this.Date = date;
        this.Doctor_ID = doctor_ID;
        this.Patient_ID = patient_ID;
        this.Status = status;
        this.Time = time;
    }

    //must be validated:
        //• A patient cannot book an appointment without being assigned to a doctor.
        //• A doctor cannot have two appointments at the same date and time.
        // • Appointment date cannot be empty.
        //• Appointment time cannot be empty.
        //• A cancelled appointment cannot be marked as completed.

}
