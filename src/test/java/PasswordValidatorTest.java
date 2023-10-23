import PasswordStrength.PasswordStrength; //package를 통해서 strength와 validator를 가져왔음.
import PasswordStrength.PasswordValidator;
import org.junit.jupiter.api.Assertions; // 인수 두 개가 같은지 확인하기 위한 함수? 클래스?
import org.junit.jupiter.api.BeforeEach; // 이것은 본 테스트 이전에 수행되는 함수를 가리킨다.
import org.junit.jupiter.api.DisplayName; // 테스트 클래스, 테스트 메서드에 이름을 지정하는 것.
import org.junit.jupiter.api.Test; // ?
// passwordvalidator을 적절하게 수정해서 여기서 검사하여 원하는 결과가 나올 경우
// git에 올리면 스터디 목표 완료.
// 태준이꺼 봐버렸기 때문에 알고리즘 푸는 느낌으로 처음부터 스스로 만들어보자. 자바 연습겸.

public class PasswordValidatorTest {
  PasswordValidator pv;

  @BeforeEach
  @DisplayName("테스트 메서드가 실행되기 전에 반드시 호출되는 메서드")
  void setUp() {
    pv = new PasswordValidator("abcd");
  }

  @Test
  @DisplayName("비밀번호 유효성 검사 객체가 Null이 아님을 증명하는 테스트")
  void 유효성_검사_객체_Not_null_테스트() {
    pv = new PasswordValidator("abcd");
    Assertions.assertNotNull(pv);
  }

  @Test
  @DisplayName("비밀번호 유효성 검사 세 가지를 모두 통과하는 경우에 대한 테스트")
  void 유효성_검사_3개_통과() {
    pv.setPassword("abcDE012");
    Assertions.assertEquals(PasswordStrength.STRONG, pv.check());
  }

  @Test
  @DisplayName("비밀번호 유효성 검사 중에 두 가지를 통과하는 경우에 대한 테스트")
  void 유효성_검사_2개_통과() {
    // 문자열의 길이가 8이고, 숫자가 포함된 비밀번호의 강도는 NORMAL이다.
    pv.setPassword("abcde012");
    Assertions.assertEquals(PasswordStrength.NORMAL, pv.check());

    // 문자열의 길이가 8이고, 숫자가 포함된 비밀번호의 강도는 NORMAL이다.
    pv.setPassword("abcdEabC");
    Assertions.assertEquals(PasswordStrength.NORMAL, pv.check());

    // 문자열의 길이가 7이고, 숫자와 대문자가 포함되는 비밀번호의 강도는 NORMAL이다.
    pv.setPassword("ABCDE01");
    Assertions.assertEquals(PasswordStrength.NORMAL, pv.check());
  }

  @Test
  @DisplayName("비밀번호 유효성 검사 중에 한 가지를 통과하는 경우에 대한 테스트")
  void 유효성_검사_1개_통과() {
    // 문자열의 길이가 8인 비밀번호의 강도는 WEAK이다.
    pv.setPassword("abcdefgh");
    Assertions.assertEquals(PasswordStrength.WEAK, pv.check());

    // 문자열의 길이가 7이고, 숫자로만 이루어진 비밀번호의 강도는 WEAK이다.
    pv.setPassword("1234567");
    Assertions.assertEquals(PasswordStrength.WEAK, pv.check());

    // 문자열의 길이가 7이고, 영어 대문자로만 이루어진 비밀번호의 강도는 WEAK이다.
    pv.setPassword("ABCDEFG");
    Assertions.assertEquals(PasswordStrength.WEAK, pv.check());
  }
}
