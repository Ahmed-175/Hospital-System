
public class User {

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected String phone;
    protected String role;

    public User(String name, String username,
            String password, String phone, String role) {

        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public void viewPersonalInfo() {
    }

    ;
    public void viewAppointments() {
    }

    ;

    public String userToCSV() {
        return String.join(",",
                id,
                name,
                username,
                password,
                phone,
                role
        );
    }

    public static User fromCSV(String record) {

        String[] data = record.split(",");

        String id = data[0];
        String name = data[1];
        String username = data[2];
        String password = data[3];
        String phone = data[4];
        String role = data[5];

        User user = new User(name, username, password, phone, role);
        user.setId(id);

        return user;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
