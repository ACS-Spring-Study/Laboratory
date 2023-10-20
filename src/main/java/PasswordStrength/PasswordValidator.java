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
    /*
			To do : 테스트 메서드를 모두 통과하도록 수정
		 */

		// 암호의 글자 수, 숫자 수, 대문자 수
		int length = password.length();
		int digit = 0;
		int uppercase = 0;
		char[] passwordChar = password.toCharArray();

		for (int i = 0; i < length; i++) {
			if (Character.isDigit(passwordChar[i])) {
				digit++;
			}
			if (Character.isUpperCase(passwordChar[i])) {
				uppercase++;
			}

		}
		// 암호의 강도
		// 세가지 기준을 만족하면 Strong
		if (length >= 8 && digit > 0 && uppercase > 0) {
			return PasswordStrength.STRONG;
		}
		// 두가지 기준을 만족하면 Normal
		else if (digit > 0 && uppercase > 0) {
			return PasswordStrength.NORMAL;
		} else if (length >= 8 && digit > 0) {
			return PasswordStrength.NORMAL;
		} else if (length >= 8 && uppercase > 0) {
			return PasswordStrength.NORMAL;
		}
		// 한가지 기준을 만족하면 WEAK
		return PasswordStrength.WEAK;
	}
}
