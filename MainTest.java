import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {

  @Test
  public void test() {
    Assertions.assertEquals(0, Main.reverseV1(Integer.MAX_VALUE));
    Assertions.assertEquals(0, Main.reverseV2(Integer.MAX_VALUE));

    Assertions.assertEquals(0, Main.reverseV1(Integer.MIN_VALUE));
    Assertions.assertEquals(0, Main.reverseV2(Integer.MIN_VALUE));

    Assertions.assertEquals(321, Main.reverseV1(123));
    Assertions.assertEquals(321, Main.reverseV2(123));

    Assertions.assertEquals(-321, Main.reverseV1(-123));
    Assertions.assertEquals(-321, Main.reverseV2(-123));

    Assertions.assertNotEquals(0, Main.reverseV1(1534236469)); // failing case
    Assertions.assertEquals(0, Main.reverseV2(1534236469));

    Assertions.assertEquals(21, Main.reverseV1(120));
    Assertions.assertEquals(21, Main.reverseV2(120));

    Assertions.assertEquals(987654321, Main.reverseV1(123456789));
    Assertions.assertEquals(987654321, Main.reverseV2(123456789));

    Assertions.assertEquals(0, Main.reverseV1(0));
    Assertions.assertEquals(0, Main.reverseV2(0));

    Assertions.assertEquals(-109, Main.reverseV1(-901000));
    Assertions.assertEquals(-109, Main.reverseV2(-901000));
  }
}
