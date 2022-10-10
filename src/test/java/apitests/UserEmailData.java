package apitests;

public class UserEmailData {

    private String email;
    private String password;

    public UserEmailData(String email, String password) {
       this.email = email;
       this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
