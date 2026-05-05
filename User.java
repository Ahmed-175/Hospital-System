public abstract class User {
    public int User_ID;
    public String User_Name;
    public String User_Username;
    public int User_Password;
    public int User_PhoneNumber;
    public Appointment[] ListAppointment;

    public User(int user_ID,String User_Name,String User_Username,
                int User_Password,int User_PhoneNumber,Appointment[] listAppointment) {
        this.User_ID = user_ID;
        this.User_Name = User_Name;
        this.User_Username=User_Username;
        this.User_Password=User_Password;
        this.User_PhoneNumber=User_PhoneNumber;
        this.ListAppointment = listAppointment;
    }
    public abstract void ViewPersonalInfo();
    public abstract void ViewAppointments();
}
