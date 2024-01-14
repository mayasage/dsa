/**
 * <a href="https://leetcode.com/problems/group-anagrams/description/">
 * <h1> 49. Group Anagrams </h1>
 * </a>
 *
 * <p>
 * Given an array of strings strs, group the anagrams together. You can
 * return the answer in any order.
 * </p>
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 * </p>
 *
 * <p>
 * Constraints:
 *   <ul>
 *     <li> 1 <= strs.length <= 104 </li>
 *     <li> 0 <= strs[i].length <= 100 </li>
 *     <li> strs[i] consists of lowercase English letters. </li>
 *   </ul>
 * </p>
 */

import java.util.*;

public class GroupAnagrams_Q49 {
  /**
   * <p>
   * Time Complexity:
   * O(N) to Loop through strs
   * O(Ns) to convert each str to charArray
   * O(Nslogs) to sort charArray
   * O(Ns) to convert sorted charArray to str
   * O(1) for map operations
   * O(N) to build result from Map
   * Total: O(Nslogs)
   * </p>
   *
   * <p>
   * Space Complexity: O(N) to build a HashMap
   * </p>
   *
   * <p>
   * <p>
   * The goal is to build a structure like this:
   * {
   * "aet": ["eat", "tea", "ate"],
   * "ant": ["tan", "nat"],
   * "abt": ["bat"]
   * }
   * This is a HashMap where key is the sorted word.
   * </p>
   *
   * <p>
   * Sort every word in strs to get key, then put the word into that key.
   * </p>
   *
   * <p>
   * Finally, convert that structure into result:
   * [[eat, tea, ate], [tan, nat], [bat]]
   * </p>
   * </p>
   *
   * @param strs An array of Strings
   * @return Example: [[eat, tea, ate], [tan, nat], [bat]]
   */
  public List<List<String>> time_onslogs(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String word : strs) {
      char[] c = word.toCharArray();
      Arrays.sort(c);
      String sortedWord = new String(c);
      map.putIfAbsent(sortedWord, new ArrayList<>());
      map.get(sortedWord).add(word);
    }

    return new ArrayList<>(map.values());
  }

  /**
   * <p>
   * Time Complexity:
   * O(N) to Loop through strs
   * O(Ns) to convert each str to charArray
   * O(Ns) to sort charArray
   * O(Ns) to convert sorted charArray to str
   * O(1) for map operations
   * O(N) to build result from Map
   * Total: O(Ns)
   * </p>
   *
   * <p>
   * Space Complexity: O(N) to build a HashMap
   * </p>
   *
   * <p>
   * This algorithm is exactly similar to time_onslogs, except that this
   * replaces the sorting algorithm with a counting algorithm.
   * And this only works because the words are limited to lower-case
   * english alphabets.
   * </p>
   * <p>
   * New Sorting Algorithm is as follows:
   * Instead of sorting using the traditional sorting method, we'll instead
   * count the frequency of each character in the word.
   * Then we'll go from left to right and get the sorted string.
   * </p>
   *
   * @param strs An array of Strings
   * @return Example: [[eat, tea, ate], [tan, nat], [bat]]
   */
  public List<List<String>> time_ons(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for (String word : strs) {
      char[] arr = new char[26];
      for (char c : word.toCharArray()) {
        arr[c - 'a'] += 1;
      }
      String sortedWord = String.valueOf(arr);

      map.putIfAbsent(sortedWord, new ArrayList<>());
      map.get(sortedWord).add(word);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    GroupAnagrams_Q49 groupAnagramsQ49 = new GroupAnagrams_Q49();
    String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
    List<List<String>> result = groupAnagramsQ49.time_ons(strs);
    System.out.println(result.toString());
  }
}
