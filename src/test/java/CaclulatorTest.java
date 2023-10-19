import Calc.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CaclulatorTest {
  public Calculator myCalc = new Calculator();

  @Test
  void addTest() {
    Assertions.assertEquals(10, myCalc.add(5, 5));
  }

  @Test
  void subTest() {
    Assertions.assertEquals(0, myCalc.sub(5, 5));
  }

  @Test
  void mulTest() {
    Assertions.assertEquals(25, myCalc.add(5, 5));
  }

  @Test
  void divTest() {
    Assertions.assertEquals(1, myCalc.add(5, 5));
  }

  @Test
  void modTest() {
    Assertions.assertEquals(0, myCalc.add(5, 5));
  }
}
