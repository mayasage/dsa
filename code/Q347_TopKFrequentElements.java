/**
 * <a href="https://leetcode.com/problems/top-k-frequent-elements/description/">
 * <h1> 347. Top K Frequent Elements </h1>
 * </a>
 *
 * <p>
 * Given an integer array nums and an integer k, return the k most frequent
 * elements. You may return the answer in any order.
 * </p>
 *
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * </p>
 *
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * </p>
 *
 * <p>
 * Constraints:
 *   <ul>
 *     <li> 1 <= nums.length <= 105 </li>
 *     <li> -104 <= nums[i] <= 104 </li>
 *     <li>
 *       k is in the range [1, the number of unique elements in the array].
 *     </li>
 *     <li> It is guaranteed that the answer is unique. </li>
 *   </ul>
 * </p>
 */

import java.util.*;

public class Q347_TopKFrequentElements {
  /**
   * <p>
   * Time Complexity:
   * O(n) to build HashMap that hold frequency.
   * O(nlogn) to build PriorityQueue.
   * O(k) to fetch results from PriorityQueue.
   * Total: O(nlogn)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for HashMap
   * O(n) for PriorityQueue
   * Total: O(n)
   * </p>
   *
   * <p>
   * Build a HashMap.
   * Then build a PriorityQueue to sort the HashMap based on Values (Max Heap).
   * Poll k times from the PriorityQueue and put values into an int array.
   * </p>
   *
   * @param numbers Array of numbers
   * @param k       Most frequent count
   * @return Numbers with k or more frequency
   */
  public int[] time_oklogn_space_on(int[] numbers, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int number : numbers) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
    }

    PriorityQueue<Integer> pq =
      new PriorityQueue<>((a, b) -> countMap.get(b) - countMap.get(a));
    pq.addAll(countMap.keySet());

    int[] result = new int[k];
    for (int i = 0; i < k; i += 1) {
      Integer value = pq.poll();
      if (value == null) {
        break;
      }
      result[i] = value;
    }

    return result;
  }

  /**
   * <p>
   * Time Complexity:
   * O(n) to build HashMap that hold frequency.
   * O(kn) to build result array.
   * Total: O(kn)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for HashMap
   * Total: O(n)
   * </p>
   *
   * @param numbers Array of numbers
   * @param k       Most frequent count
   * @return Numbers with k or more frequency
   */
  public int[] time_okn_space_on(int[] numbers, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int number : numbers) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
    }

    int[] result = new int[k];
    for (int i = 0; i < k; i += 1) {
      Integer key = null;
      Integer val = null;

      for (int curKey : countMap.keySet()) {
        int curVal = countMap.get(curKey);
        if (key == null) {
          key = curKey;
          val = curVal;
          continue;
        }
        if (curVal > val) {
          key = curKey;
          val = curVal;
        }
      }

      result[i] = key;
      countMap.remove(key);
    }

    return result;
  }

  /**
   * <p>
   * Time Complexity:
   * O(n) to build HashMap that hold frequency.
   * O(nlogn) to sort HashMap based on Values.
   * O(k) to fetch results from from Sorted HashMap.
   * Total: O(nlogn)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for HashMap
   * Total: O(n)
   * </p>
   *
   * <p>
   * Build a HashMap.
   * Then build a PriorityQueue to sort the HashMap based on Values (Max Heap).
   * Poll k times from the PriorityQueue and put values into an int array.
   * </p>
   *
   * @param numbers Array of numbers
   * @param k       Most frequent count
   * @return Numbers with k or more frequency
   */
  public int[] time_onlogn_space_on(int[] numbers, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int number : numbers) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
    }

    // Sort HashMap based on Values
    List<Map.Entry<Integer, Integer>> entries =
      new ArrayList<>(countMap.entrySet());
    entries.sort(new Comparator<Map.Entry<Integer, Integer>>() {
      @Override
      public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
        return o2.getValue().compareTo(o1.getValue());
      }
    });

    List<Integer> result = new ArrayList<>();
    int c = 0;
    for (Map.Entry<Integer, Integer> entry : entries) {
      result.add(entry.getKey());
      c += 1;
      if (c == k) {
        break;
      }
    }

    return result.stream().mapToInt(i -> i).toArray();
  }

  /**
   * <p>
   * Time Complexity:
   * O(n) to build HashMap that hold frequency.
   * O(n) to build bucket.
   * O(k) to build result.
   * Total: O(n)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(n) for HashMap
   * O(n) for Bucket
   * O(k) for result
   * Total: O(n)
   * </p>
   *
   * <p>
   * Same as onlogn except, this replaces sorting with bucket sort based on
   * frequency (this work only because frequency is fixed).
   * </p>
   *
   * @param numbers Array of numbers
   * @param k       Most frequent count
   * @return Numbers with k or more frequency
   */
  public int[] time_on_space_on(int[] numbers, int k) {
    Map<Integer, Integer> countMap = new HashMap<>();
    for (int number : numbers) {
      countMap.put(number, countMap.getOrDefault(number, 0) + 1);
    }

    List<Integer>[] bucket = new ArrayList[numbers.length + 1];

    for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
      int number = entry.getKey();
      int frequency = entry.getValue();
      if (bucket[frequency] == null) {
        bucket[frequency] = new ArrayList<>();
      }
      bucket[frequency].add(number);
    }

    int[] result = new int[k];
    int c = 0;
    for (int i = numbers.length; i > 0; i -= 1) {
      if (bucket[i] == null) {
        continue;
      }
      for (int num : bucket[i]) {
        result[c++] = num;
        if (c == k) {
          return result;
        }
      }
    }

    return new int[]{};
  }

  public static void main(String[] args) {
    Q347_TopKFrequentElements q347TopKFrequentElements = new Q347_TopKFrequentElements();
    int[] numbers = {};
    int target = 2;
    int[] result = q347TopKFrequentElements.time_on_space_on(numbers, target);
    System.out.println(Arrays.toString(result));
  }
}
