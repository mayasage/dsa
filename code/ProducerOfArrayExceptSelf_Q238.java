/**
 * <a href="https://leetcode.com/problems/product-of-array-except-self/description/">
 * <h1> 238. Product of Array Except Self </h1>
 * </a>
 *
 * <p>
 * Given an integer array nums, return an array answer such that answer[i] is
 * equal to the product of all the elements of nums except nums[i].
 * </p>
 *
 * <p>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit
 * integer.
 * </p>
 *
 * <p>
 * You must write an algorithm that runs in O(n) time and without using the
 * division operation.
 * </p>
 *
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * </p>
 *
 * <p>
 * Example 2:
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * </p>
 *
 * <p>
 * Constraints:
 *   <ul>
 *     <li> 2 <= nums.length <= 105 </li>
 *     <li> -30 <= nums[i] <= 30 </li>
 *     <li>
 *       The product of any prefix or suffix of nums is guaranteed to fit in a
 *       32-bit integer.
 *     </li>
 *   </ul>
 * </p>
 *
 * <p>
 *   Follow up: Can you solve the problem in O(1) extra space complexity? (The
 *   output array does not count as extra space for space complexity analysis.)
 * </p>
 */

import java.util.Arrays;

public class ProducerOfArrayExceptSelf_Q238 {
  /**
   * Time Complexity:
   * O(N) to build prefix, postfix arrays
   * O(N) to build result array
   * Total: O(N)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(N) to build a Prefix Array
   * O(N) to build a Postfix Array
   * </p>
   *
   * <p>
   * Prefix = (Left -> Right) Previous Prefix Value * Current Number
   * Example:
   * [1, 2, 3, 4]
   * [1, 1, 2, 6]
   * </p>
   *
   * <p>
   * Postfix = (Left <- Right) Next Postfix Value * Current Number
   * Example:
   * [1, 2, 3, 4]
   * [24, 12, 4, 1]
   * </p>
   *
   * <p>
   * result[i] = prefix[i] * postfix[i]
   * </p>
   *
   * @param numbers An array of integers
   * @return Array of product of all numbers except numbers[i]
   */
  public int[] time_on_space_on(int[] numbers) {
    int[] prefix = new int[numbers.length];
    int prev = 1;
    for (int i = 0; i < numbers.length; i += 1) {
      prefix[i] = prev;
      prev *= numbers[i];
    }

    int[] postfix = new int[numbers.length];
    int next = 1;
    for (int i = numbers.length - 1; i >= 0; i -= 1) {
      postfix[i] = next;
      next *= numbers[i];
    }

    int[] result = new int[numbers.length];
    for (int i = 0; i < result.length; i += 1) {
      result[i] = prefix[i] * postfix[i];
    }
    return result;
  }

  /**
   * Time Complexity:
   * O(N) to build prefix array
   * O(N) to build result array
   * Total: O(N)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(N) to build a result Array (this is ignored)
   * </p>
   *
   * <p>
   * Same as time_on_space_on, except you don't create extra arrays for
   * prefix and postfix... instead, you first put prefix, then postfix into the
   * result array.
   * </p>
   *
   * @param numbers An array of integers
   * @return Array of product of all numbers except numbers[i]
   */
  public int[] time_on_space_o1(int[] numbers) {
    int[] result = new int[numbers.length];

    int prefix = 1;
    for (int i = 0; i < numbers.length; i += 1) {
      result[i] = prefix;
      prefix *= numbers[i];
    }

    int postfix = 1;
    for (int i = numbers.length - 1; i >= 0; i -= 1) {
      result[i] *= postfix;
      postfix *= numbers[i];
    }

    return result;
  }

  public static void main(String[] args) {
    ProducerOfArrayExceptSelf_Q238 producerOfArrayExceptSelfQ238 = new ProducerOfArrayExceptSelf_Q238();
//    int[] numbers = {-1, 1, 0, -3, 3};
    int[] numbers = {1, 2, 3, 4};
    int[] result = producerOfArrayExceptSelfQ238.time_on_space_o1(numbers);
    System.out.println(Arrays.toString(result));
  }
}
