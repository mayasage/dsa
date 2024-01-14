/**
 *  <a href="https://leetcode.com/problems/two-sum/description/">
 *    <h1> 1. Two Sum </h1>
 *  </a>
 *
 *  <p>
 *    Given an array of integers nums and an integer target, return indices of
 *    the two numbers such that they add up to target.
 *  </p>
 *  <p>
 *    You may assume that each input would have exactly one solution, and you
 *    may not use the same element twice.
 *  </p>
 *  <p>
 *    You can return the answer in any order.
 *  </p>
 *
 *  <p>
 *    Constraints:
 *    <ul>
 *      <li> 2 <= nums.length <= 104 </li>
 *      <li> -109 <= nums[i] <= 109 </li>
 *      <li> -109 <= target <= 109 </li>
 *      <li> Only one valid answer exists. </li>
 *    </ul>
 *  </p>
 */

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class TwoSum_Q1 {
  /**
   *  Time Complexity: O(N^2) to brute force
   *
   *  @param numbers Array of numbers
   *  @param target  Target sum
   *  @return Returns 2 indices in an array
   */
  public int[] time_on2(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i += 1) {
      int number = numbers[i];
      for (int j = i + 1; j < numbers.length; j += 1) {
        int cur = numbers[j];
        if (target == (number + cur)) return new int[]{i, j};
      }
    }
    return new int[]{};
  }

  /**
   *  Time Complexity: O(N) to loop
   *  Space Complexity: O(N) to build a HashMap
   *
   *  <p>
   *    Go from left to right and try to find if the complementary value exists
   *    in the Map.
   *    If it exists, then we're done.
   *    If not, then then put the current number into the Map, so that if a
   *    complementary number is reached, the current number will be found in
   *    Map.
   *  </p>
   *  <p>
   *    This works only because exactly 2 numbers have to be added to the
   *    target value.
   *  </p>
   *
   *  @param numbers Array of numbers
   *  @param target  Target sum
   *  @return Returns 2 indices in an array
   */
  public int[] time_on_space_on(int[] numbers, int target) {
    Map<Integer, Integer> indexMap = new HashMap<>();

    for (int i = 0; i < numbers.length; i += 1) {
      int currentVal = numbers[i];
      int complementVal = target - currentVal;
      Integer complementIndex = indexMap.get(complementVal);
      if (complementIndex != null) {
        return new int[]{i, complementIndex};
      }
      indexMap.put(currentVal, i);
    }

    return new int[]{};
  }

  public static void main(String[] args) {
    TwoSum_Q1 twoSumQ1 = new TwoSum_Q1();
    int[] numbers = {2, 7, 11, 15};
    int target = 9;
    int[] result = twoSumQ1.time_on_space_on(numbers, target);
    System.out.println(Arrays.toString(result));
  }
}
