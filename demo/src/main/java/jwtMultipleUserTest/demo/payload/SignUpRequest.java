package jwtMultipleUserTest.demo.payload;

import java.util.Set;

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

    private Set<String> role;

    public SignUpRequest(String nameCustomers, String emailCustomers, String phoneCustomers, String passwordCustomers, Set<String> role) {
        this.nameCustomers = nameCustomers;
        this.emailCustomers = emailCustomers;
        this.phoneCustomers = phoneCustomers;
        this.passwordCustomers = passwordCustomers;
        this.role = role;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

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
