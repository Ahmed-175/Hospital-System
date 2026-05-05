public class Patient extends User{

    public int Age;
    public String Gender;
    public Doctor AssignedDoctor;

    public Patient(int user_ID, String User_Name, String User_Username,
                   int User_Password, int User_PhoneNumber, Appointment[] listAppointment,
                   int age, Doctor assignedDoctor, String gender)
    {
        super(user_ID, User_Name, User_Username,User_Password, User_PhoneNumber,listAppointment);
        this.Age = age;
        this.AssignedDoctor = assignedDoctor;
        this.Gender = gender;
    }

    @Override
    public void ViewPersonalInfo() {

    }
    @Override
    public void ViewAppointments() {

    }
    public void ViewAssignedDoctor(){

    }
    public void Book_AppointmentWithAssi_Doctor(){

    }
    public void Cancel_AppointmentWithAssi_Doctor(){

    }
}
