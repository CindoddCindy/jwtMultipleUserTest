package jwtMultipleUserTest.demo.payload;

public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String customersPassword;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getCustomersPassword() {
        return customersPassword;
    }

    public void setCustomersPassword(String customersPassword) {
        this.customersPassword = customersPassword;
    }
}
