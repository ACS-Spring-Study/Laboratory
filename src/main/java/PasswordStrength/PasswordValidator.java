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
    // 만족 조건 횟수
    int strengthCnt = 0;

    // 문자열 길이
    int PasswordLength = password.length();
    // 숫자 포함 확인용 변수
    int checkDigit = 0;
    // 대문자 포함 확인용 변수
    int checkUpper = 0;

    // 문자열 길이 8이상인지 확인
    if (PasswordLength >= 8) {
      strengthCnt += 1;
    }

    // 문자열에 숫자가 포함되어있는지 확인
    if (password.matches(".*[0-9].*")){
      strengthCnt += 1;
    }

    // 문자열에 대문자가 포함되엉씨는지 확인
    if (password.matches(".*[A-Z].*")) {
      strengthCnt += 1;
    }

    if (strengthCnt == 3){
      return PasswordStrength.STRONG; // 모든 조건 만족일 경우
    } else if (strengthCnt == 2) {
      return PasswordStrength.NORMAL; // 2개 조건 만족일 경우
    } else {
      return PasswordStrength.WEAK; // 1개 이하 조건 만족일 경우
    }
  }
}
