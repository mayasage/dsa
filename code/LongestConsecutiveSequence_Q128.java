/**
 * <a href="https://leetcode.com/problems/longest-consecutive-sequence/">
 * <h1> 128. Longest Consecutive Sequence </h1>
 * </a>
 *
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest
 * consecutive elements sequence.
 * </p>
 *
 * <p>
 * You must write an algorithm that runs in O(n) time.
 * </p>
 *
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4].
 * Therefore its length is 4.
 * </p>
 *
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 * </p>
 *
 * <p>
 * Constraints:
 *   <ul>
 *     <li> 0 <= nums.length <= 105 </li>
 *     <li> -109 <= nums[i] <= 109 </li>
 *   </ul>
 * </p>
 */

import java.util.*;

public class LongestConsecutiveSequence_Q128 {
  /**
   * <p>
   * Time Complexity:
   * O(n) to build MaxHeap.
   * O(n) to loop through MaxHeap to find result.
   * Total: O(n)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for MaxHeap
   * Total: O(n)
   * </p>
   *
   * @param nums Array of numbers
   * @return Max length of consecutive elements
   */
  public int maxHeap(int[] nums) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> a - b);

    for (int num : nums) {
      maxHeap.add(num);
    }

    int maxLen = 0;
    int count = 0;
    Integer prev = null;
    while (!maxHeap.isEmpty()) {
      int cur = maxHeap.poll();

      if (prev == null) {
        count = 1;
      } else if (prev == cur - 1) {
        count += 1;
      } else if (prev == cur) {
        // skip
      } else {
        maxLen = Math.max(count, maxLen);
        count = 1;
      }

      prev = cur;
    }

    if (count > maxLen) {
      maxLen = count;
    }

    return maxLen;
  }

  /**
   * <p>
   * Time Complexity:
   * O(n) to build HashSet.
   * O(n2) to loop through HashSet to find result.
   * Total: O(n2)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for HashSet
   * Total: O(n)
   * </p>
   *
   * <p>
   * Working:
   * Creates a HashSet.
   * This automatically remove duplicates.
   * Now, iterate through the HashSet and for each element, assume that it is
   * the first element at the start a "sequence".
   * Then try to find next elements in the sequence, while incrementing count.
   * The time complexity is O(n^2) because:
   * 1 2 3 4
   * Every element is assumed to be the start of a sequence, and therefore, we
   * try to calculate sequence count starting from every element.
   * 1-4 => 4
   * 2-4 => 3
   * 3-4 => 2
   * 4-4 => 1
   * Yes, this is not necessary, and we can avoid this by just ignoring 3, 2, 1
   * if the previous value exist, i.e., it is NOT the start of a sequence.
   * </p>
   *
   * @param nums Array of numbers
   * @return Max length of consecutive elements
   */
  public int hashSet_on2(int[] nums) {
    Set<Integer> hashSet = new HashSet<>();
    for (int num : nums) {
      hashSet.add(num);
    }

    int maxLen = 0;
    for (int num : hashSet) {
      int count = 1;
      int x = num;
      while (hashSet.contains(x + 1)) {
        count += 1;
        x += 1;
      }
      maxLen = Math.max(count, maxLen);
    }

    return maxLen;
  }

  /**
   * <p>
   * Time Complexity:
   * O(n) to build HashSet.
   * O(n) to loop through HashSet to find result.
   * Total: O(n)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for HashSet
   * Total: O(n)
   * </p>
   *
   * <p>
   * Same as hashSet_on2 except it doesn't scan if their is a previous element.
   * Due to this on2 becomes on.
   * Example:
   * 1 2 3 4
   * Won't look at 2, 3, 4 because the previous element was already scanned.
   * </p>
   *
   * @param nums Array of numbers
   * @return Max length of consecutive elements
   */
  public int hashSet_on(int[] nums) {
    Set<Integer> hashSet = new HashSet<>();
    for (int num : nums) {
      hashSet.add(num);
    }

    int maxLen = 0;
    for (int num : hashSet) {
      if (hashSet.contains(num - 1)) {
        continue;
      }
      int count = 1;
      int x = num;
      while (hashSet.contains(x + 1)) {
        count += 1;
        x += 1;
      }
      maxLen = Math.max(count, maxLen);
    }

    return maxLen;
  }

  /**
   * <p>
   * Time Complexity:
   * O(nlogn) to sort numbers.
   * O(n) to calculate maxLen.
   * Total: O(nlogn)
   * </p>
   *
   * <p>
   * Space Complexity: O(1)
   * </p>
   *
   * @param nums Array of numbers
   * @return Max length of consecutive elements
   */
  public int sort(int[] nums) {
    Arrays.sort(nums);

    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return 1;
    }

    int maxLen = 1;
    int count = 1;
    for (int i = 1; i < nums.length; i += 1) {
      int cur = nums[i];
      int prev = nums[i - 1];

      if (prev == cur) {
        // skip
      } else if (prev == cur - 1) {
        count += 1;
      } else {
        maxLen = Math.max(maxLen, count);
        count = 1;
      }
    }
    maxLen = Math.max(maxLen, count);

    return maxLen;
  }

  /**
   * <p>
   * Time Complexity:
   * O(n) to find max and min.
   * O(max - min + 1) to loop through bits to calculate maxLen.
   * Total: O(max - min + 1)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(max - min + 1)
   * Total: O(max - min + 1)
   * </p>
   *
   * <p>
   * This can work if max and min are not too far from each other.
   * </p>
   *
   * @param nums Array of numbers
   * @return Max length of consecutive elements
   */
  public int bits(int[] nums) {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int num : nums) {
      max = Math.max(max, num);
      min = Math.min(min, num);
    }

    int maxLen = 0;

    byte[] bits = new byte[max - min + 1];
    for (int num : nums) {
      bits[num - min] = 1;
    }

    int count = 0;
    for (byte bit : bits) {
      if (bit == 1) {
        count += 1;
      } else {
        maxLen = Math.max(maxLen, count);
        count = 0;
      }
    }
    maxLen = Math.max(maxLen, count);

    return maxLen;
  }

  public static void main(String[] args) {
    LongestConsecutiveSequence_Q128 longestConsecutiveSequenceQ128 = new LongestConsecutiveSequence_Q128();
//    int[] numbers = {100, 4, 200, 1, 3, 2};
//    int[] numbers = {1,2,0,1};
//    int[] numbers = {1,2,10,12,13,14,15,20, 30,31,31,32,33,34,35,36};
//    int[] numbers = {};
    int[] numbers = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
    int result = longestConsecutiveSequenceQ128.bits(numbers);
    System.out.println(result);
  }
}
