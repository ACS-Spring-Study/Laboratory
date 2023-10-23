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
        boolean number = false;
        boolean upper = false;
        boolean notOnlyNumber = false;

        int numberCheck = 0;

        char[] passwordArray = password.toCharArray();

        for (int i = 0; i < length; i++) {
            if (Character.isUpperCase(passwordArray[i])) {
                numberCheck++;
            } else if (Character.isDigit(passwordArray[i])) {
                upper = true;
            }
        }

        if (upper && numberCheck >= 1 && length >= 8) {
            return PasswordStrength.STRONG;
        } else if (upper && numberCheck >= 1 || numberCheck >= 1 && length  >= 8 || length >= 8 && upper) {
            return PasswordStrength.NORMAL;
        } else{
            return PasswordStrength.WEAK;
        }
    }
}

// 암호의 '글자 수', '숫자 수', '대문자 수' 초기화한 변수들이 있다. 여기에 앞으로 마주할 암호의 정보를 저장하는 듯하다.
// char[] passwordChar = password.toCharArray(); 이 문장에 대해.
// String.toCharArray()는 문자열을 한 글자씩 쪼개서 char타입의 배열에 넣어주는 함수이다.
// 클래스의 내부 속성을 가져와서 배열에 한 글자씩 넣어주는 이유가 뭘까? 대문자랑 숫자 확인하려고

//for(int i=0;i<passwordChar.length;i++){ 저 문자형 배열에 넣은 문자의 수만큼 반복한다.
//    if(Character.isDigit(passwordChar[i])){ 맥락상 암호중에서 숫자인 부분을 확인.
//    digit++; 그럼 여기서 숫자 부분에 해당하는 변수에 1을 더해주고
//    }
//    if(Character.isUpperCase(pEasswordChar[i])){ 맥락상 여기서 대문자를 확인해주면
//    uppercase++; 여기서 대문자 부분에 해당하는 변수에 1을 더해주는거지
//    } 그래서 비밀번호 정할 때 조건들 10자 이상, 대문자 포함, 숫자 포함 같은 조건들을 넣어줄 수 있게 되는 것이고.
//
//    }

//암호의 강도. strong, normal, weak에 대해.
// 조건문 만들어서 글자 길이, 대문자, 숫자 조건을 모두 만족하면 해당 클래스의 속성인 strong을 반환해주고
// 두 조건을 만족하면 normal을 반환해주고
// 하나의 조건만 만족하면 weak를 반환해주는 것이지.