public class Doctor extends User{

    public String Specialization;
    public String Department;
    public Patient[] ListPatient;

    public Doctor(int user_ID,String User_Name,String User_Username,int User_Password,
                  int User_PhoneNumber,Appointment[] listAppointment,String specialization,
                  String department,Patient[] listPatient)
    {
        super(user_ID, User_Name, User_Username, User_Password, User_PhoneNumber,listAppointment);
        this.Department = department;
        this.ListPatient = listPatient;
        this.Specialization = specialization;
    }

    @Override
    public void ViewPersonalInfo(){

    }
    @Override
    public void ViewAppointments(){

    }
    public void ViewAssignedPatients(){

    }
    public void UpdateAppointments_Status(){
        //Confirmed,Completed.Cancelled
    }
}
