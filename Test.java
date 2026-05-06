
public class Test {

    public static void main(String[] args) {
        Doctor d1 = new Doctor(
                "Ahmed Farag",
                "ahmed175",
                "1234",
                "01023235534",
                "General Medicine",
                "Internal Medicine Department"
        );

        Doctor d2 = new Doctor(
                "Ali Hassan",
                "ali175",
                "1234",
                "01098765432",
                "Cardiology",
                "Heart Department"
        );

        Doctor d3 = new Doctor(
                "Sara Mohamed",
                "sara90",
                "1234",
                "01055544332",
                "Neurology",
                "Brain & Nerves Department"
        );


        Patient p1 = new Patient(
                "Ahmed Ali",
                "ahmedA",
                "1234",
                "01011112222",
                20,
                "Male",
                d1.getId()
        );

        Patient p2 = new Patient(
                "Mona Ahmed",
                "monaA",
                "1234",
                "01022223333",
                25,
                "Female",
                d2.getId()
        );

        Patient p3 = new Patient(
                "Khaled Samy",
                "khaledS",
                "1234",
                "01033334444",
                30,
                "Male",
                d1.getId()
        );


            Appointment a1 = new Appointment(
            "A001",
            "P001",
            "D001",
            "2026-05-15",
            "10:30",
            "CONFIRMED"
    );

      Appointment a2 = new Appointment(
            "A002",
            "P002",
            "D002",
            "2026-05-16",
            "11:00",
            "PENDING"
    );

    Appointment a3 = new Appointment(
            "A003",
            "P003",
            "D001",
            "2026-05-17",
            "09:30",
            "CANCELLED"
    );

    }


}
