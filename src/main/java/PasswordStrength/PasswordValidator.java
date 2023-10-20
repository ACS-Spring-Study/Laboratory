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

    boolean lengthCase = false;
    boolean numCase = false;
    boolean UpperCase = false;
    int caseCnt = 0;

    // 암호는 여덟 글자 이상이어야 한다.
    int passwordLength = password.length();

    if (passwordLength >= 8) {
      lengthCase = true;
      caseCnt++;
    }

    // 암호는 0부터 9사이의 숫자를 포함한다.
    if (password.matches(".*[0-9].*")){
      numCase = true;
      caseCnt++;
    }

    // 암호는 대문자를 포함한다.
    if (password.matches(".*[A-Z].*")){
      UpperCase = true;
      caseCnt++;
    }

    if (caseCnt == 1)
        return PasswordStrength.WEAK;
    else if (caseCnt == 2)
        return PasswordStrength.NORMAL;
    else if (caseCnt == 3)
        return PasswordStrength.STRONG;
    else
      return PasswordStrength.WEAK;
  }
}
