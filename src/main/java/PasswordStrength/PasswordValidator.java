package PasswordStrength;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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


        int length = password.length();
        boolean upper = false;
        boolean numberCheck = false;

        char[] passwordArray = password.toCharArray();

        // 비밀번호에 숫자 포함 여부와 대문자 포함 여부를 확인.
        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(passwordArray[i])) {
                numberCheck = true;
            } else if (Character.isDigit(passwordArray[i])) {
                upper = true;
            }
        }


        // 세 조건이 만족되면 strong, 두 조건이 만족되면 normal, 하나 이하의 조건이 만족되었을 경우 weak
        if (upper && numberCheck && length >= 8) {
            return PasswordStrength.STRONG;
        } else if (upper && numberCheck || numberCheck && length  >= 8 || length >= 8 && upper) {
            return PasswordStrength.NORMAL;
        } else{
            return PasswordStrength.WEAK;
        }
    }
}