/**
 * Given a signed 32-bit integer x, return x with its digits reversed.
 * If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 */
public class Main {

  /**
   * In our environment the integer is 32-bit so providing bigger value the Integer.MAX_VALUE automatically overflows and the same is true for
   * Integer.MIN_VALUE, so an attempt to pass value bigger/smaller than that would result in compile error.
   *
   * The first step is to come up with an approach to reverse a number, fortunately we have one here <link>How to reverse a number</link>
   * The second step is to make sure we're not exceeding the limits during that process. Following our intuitive thoughts, we could create one more variable
   * to hold the <b>previous</b> reversed value.
   * When it's positive, we only need to encounter a case where previous > reversed or alternatively for negative value: previous < reversed to conclude that
   * there was overflow.
   *
   * Even though the method looks quite simple and solid, it doesn't handle all the possible inputs, and it fails when x = 1534236469, which results to multiple
   * overflows and at the end the value is still bigger than the previous one. (mentioning the first encountered failing case)
   *
   * Time Complexity: O(n) where n is the amount of digits in x
   * Space Complexity: O(1)
   */
  public static int reverseV1(int x) {
    int reversed = 0;
    int previous;

    while (x != 0) {
      previous = reversed;
      reversed = (reversed * 10) + (x % 10);

      if (previous > 0 && (previous > reversed) || (previous < 0 && (previous < reversed))) {
        return 0;
      }

      x /= 10;
    }

    return reversed;
  }

  /**
   * To come up with a solution that has reliable check if the value will overflow during the next step of <b>(reversed * 10) + (x % 10)</b>, we could
   * first establish if there's enough space to hold it, if not -> return 0 (limit exceeded).
   *
   * In summary, before calculating the new reversed value, we check if we could add at least 10 of the old/current one without overflow.
   * Also, initially we made the wrong assumption that we should also check if there's an overflow after it's multiplied by 10 <b>and the carry is added</b> which was a wrong one.
   *
   * Such case could occur only when we're near lower or upper limit. The highest possible value that fits 10x without overflow in the positive side is Integer.MAX_VALUE / 10,
   * which produces <b>214748364</b> which has 9 digits. If there was 1 more digit, it must've been 214748364[0-7] as Integer.MAX_VALUE % 214748364 = 7
   * but in order to achieve such reverse value, the input must've been either:
   *
   * - 0463847412 -> invalid
   * - 1463847412 -> in the only possible case the carry will be 1
   * - 2463847412 -> limit exceeded
   * - 3463847412 -> limit exceeded
   * - 4463847412 -> limit exceeded
   * - 5463847412 -> limit exceeded
   * - 6463847412 -> limit exceeded
   * - 7463847412 -> limit exceeded
   *
   * For all other cases it will fail in the check: Integer.[MIN-MAX]_VALUE / reversed < 10) or succeed
   * because the carry won't make any difference as the remainder will be > 9.
   *
   * Time Complexity: O(n) where n is the amount of digits in x
   * Space Complexity: O(1)
   */
  public static int reverseV2(int x) {
    int reversed = 0;

    while (x != 0) {
      int carry = (x % 10);

      // the negative case should be smaller than -1 as Integer.MIN_INTEGER / -1 always results to smaller than 10
      if (
        (reversed > 0 && Integer.MAX_VALUE / reversed < 10) || (reversed < -1 && Integer.MIN_VALUE / reversed < 10)) {
        return 0;
      }

      reversed = (reversed * 10) + carry;

      x /= 10;
    }

    return reversed;
  }
}
