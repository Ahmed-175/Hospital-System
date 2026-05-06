public abstract class User {

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

    public abstract void viewPersonalInfo();
    public abstract void viewAppointments();

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
}