import PasswordStrength.PasswordStrength;
import PasswordStrength.PasswordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {
    PasswordValidator pv;

    @BeforeEach
    @DisplayName("테스트 메서드가 실행되기 전에 반드시 호출되는 메서드")
    void setUp(){
        pv = new PasswordValidator("abcd");
    }

    @Test
    @DisplayName("비밀번호 유효성 검사 객체가 Null이 아님을 증명하는 테스트")
    void 유효성_검사_객체_Not_null_테스트(){
        pv = new PasswordValidator("abcd");
        Assertions.assertNotNull(pv);
    }

    @Test
    @DisplayName("비밀번호 유효성 검사 세 가지를 모두 통과하는 경우에 대한 테스트")
    void 유효성_검사_3개_통과(){
        pv.setPassword("abcDE012");
        Assertions.assertEquals(PasswordStrength.STRONG, pv.check());
    }

    @Test
    @DisplayName("비밀번호 유효성 검사 중에 두 가지를 통과하는 경우에 대한 테스트")
    void 유효성_검사_2개_통과(){
        // 1번 조건과 2번 조건을 만족
        pv.setPassword("abcde012");
        Assertions.assertEquals(PasswordStrength.NORMAL, pv.check());

        // 1번 조건과 3번 조건을 만족
        pv.setPassword("abcdEabC");
        Assertions.assertEquals(PasswordStrength.NORMAL, pv.check());

        // 2번 조건과 3번 조건을 만족
        pv.setPassword("ABCDE012");
        Assertions.assertEquals(PasswordStrength.NORMAL, pv.check());
    }

    @Test
    @DisplayName("비밀번호 유효성 검사 중에 한 가지를 통과하는 경우에 대한 테스트")
    void 유효성_검사_1개_통과(){
        // 1번 조건만 만족
        pv.setPassword("abcdefgh");
        Assertions.assertEquals(PasswordStrength.WEAK, pv.check());

        // 2번 조건만 만족
        pv.setPassword("12345678");
        Assertions.assertEquals(PasswordStrength.WEAK, pv.check());

        // 3번 조건만 만족
        pv.setPassword("ABCDEFGH");
        Assertions.assertEquals(PasswordStrength.WEAK, pv.check());
    }
}
