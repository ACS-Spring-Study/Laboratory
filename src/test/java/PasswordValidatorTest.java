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
}
