package PasswordStrength;

public class PasswordValidator {
    private String password;

    public PasswordValidator(String password){
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String setPassword(String password) { this.password = password; }

    public PasswordStrength check(){
        return PasswordStrength.WEAK;
    }
}
