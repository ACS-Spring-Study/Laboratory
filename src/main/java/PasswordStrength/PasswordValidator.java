package PasswordStrength;

public class PasswordValidator {
  private String password;

  public PasswordValidator(String password) {
    this.password = password;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public PasswordStrength check() {
    return PasswordStrength.WEAK;
  }
}
