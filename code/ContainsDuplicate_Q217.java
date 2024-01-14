/**
 *  <a href="https://leetcode.com/problems/contains-duplicate/description/">
 *    <h1> 217. Contains Duplicate </h1>
 *  </a>
 *
 *  <p>
 *    Given an integer array numbers, return true if any value appears at least
 *    twice in the array, and return false if every element is distinct.
 *  </p>
 */

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class ContainsDuplicate_Q217 {
  /**
   *  O(N^2) Time Complexity to Double loops
   *
   *  @param numbers An array of integers
   *  @return True if duplicate exists, False otherwise
   */
  public boolean time_on2(int[] numbers) {
    for (int i = 0; i < numbers.length; i += 1) {
      int num = numbers[i];
      for (int j = i + 1; j < numbers.length; j += 1) {
        int n = numbers[j];
        if (num == n) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   *  O(NLogN) Time Complexity to Sort numbers
   *
   *  @param numbers An array of integers
   *  @return True if duplicate exists, False otherwise
   */
  public boolean time_onlogn(int[] numbers) {
    Arrays.sort(numbers);

    for (int i = 0; i < numbers.length - 1; i += 1) {
      int cur = numbers[i];
      int nxt = numbers[i + 1];

      if (cur == nxt) {
        return true;
      }
    }

    return false;
  }

  /**
   *  O(N) Space complexity to create a HashMap
   *  O(N) Time complexity to loop through the array of numbers
   *
   *  @param numbers An array of integers
   *  @return True if duplicate exists, False otherwise
   */
  public boolean time_on_space_on_hashmap(int[] numbers) {
    Map<Integer, Integer> countMap = new HashMap<>();

    for (int num : numbers) {
      if (countMap.containsKey(num)) {
        return true;
      }
      countMap.put(num, 1);
    }

    return false;
  }

  /**
   *  O(N) Space complexity to create a Set
   *  O(N) Time complexity to loop through the array of numbers
   *  A Set should be faster than a HashMap.
   *
   *  @param numbers An array of integers
   *  @return True if duplicate exists, False otherwise
   */
  public boolean time_on_space_on_set(int[] numbers) {
    Set<Integer> seenSet = new HashSet<>();

    for (int num : numbers) {
      if (!seenSet.add(num)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    ContainsDuplicate_Q217 containsDuplicateQ217 = new ContainsDuplicate_Q217();
    int[] numbers = {-1, 2, 0, -7, 10, 0};
    boolean result = containsDuplicateQ217.time_on_space_on_set(numbers);
    System.out.println(result);
  }
}
