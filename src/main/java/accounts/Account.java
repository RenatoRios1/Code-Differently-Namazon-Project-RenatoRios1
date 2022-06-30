package accounts;

public abstract class Account {
    private static Long counter = Long.valueOf(0);
    private Long id;
    private  String firstName;
    private String lastName;
    private String email;
    private String password;

    public Account(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        id = counter++;
    }

    public Boolean validatePassword(String unvalidatedPassword){
        return password.equals(unvalidatedPassword);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", firstName, lastName, email);
    }
}
