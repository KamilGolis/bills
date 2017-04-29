package pl.bills.forms;

import org.hibernate.validator.constraints.NotEmpty;
import pl.bills.enums.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by trot on 12.04.17.
 */
public class UserCreateForm {

    @NotEmpty
    private String email = "";

    @NotEmpty
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$", message = "Hasło musi zawierać min.8 znaków, jedną dużą i małą literę oraz cyfrę.")
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotEmpty
    private String safeCode = "";

    @NotNull
    private Role role = Role.USER;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSafeCode() {
        return safeCode;
    }

    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode;
    }
}
