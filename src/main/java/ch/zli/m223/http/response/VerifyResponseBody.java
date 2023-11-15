package ch.zli.m223.http.response;

public class VerifyResponseBody {
    private String email;
    private String username;
    private String lastName;
    private String firstName;

    public VerifyResponseBody(String email, String username, String lastName, String firstName) {
        this.email = email;
        this.username = username;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
