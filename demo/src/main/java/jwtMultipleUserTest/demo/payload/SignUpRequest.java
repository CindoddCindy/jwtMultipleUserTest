package jwtMultipleUserTest.demo.payload;

public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    private String nameCustomers;

    @NotBlank
    @Size(max = 40)
    @Email
    private String emailCustomers;



    @NotBlank
    @Size(min = 1, max = 20)
    private String phoneCustomers;


    @NotBlank
    @Size(min = 3, max = 50)
    private String passwordCustomers;


    public String getNameCustomers() {
        return nameCustomers;
    }

    public void setNameCustomers(String nameCustomers) {
        this.nameCustomers = nameCustomers;
    }

    public String getEmailCustomers() {
        return emailCustomers;
    }

    public void setEmailCustomers(String emailCustomers) {
        this.emailCustomers = emailCustomers;
    }

    public String getPhoneCustomers() {
        return phoneCustomers;
    }

    public void setPhoneCustomers(String phoneCustomers) {
        this.phoneCustomers = phoneCustomers;
    }

    public String getPasswordCustomers() {
        return passwordCustomers;
    }

    public void setPasswordCustomers(String passwordCustomers) {
        this.passwordCustomers = passwordCustomers;
    }
}
