/**
 * <a href="https://leetcode.com/problems/valid-anagram/description/">
 * <h1> 242. Valid Anagram </h1>
 * </a>
 *
 * <p>
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 * </p>
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a
 * different word or phrase, typically using all the original letters exactly
 * once.
 * </p>
 */

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class ValidAnagram_Q242 {
  /**
   *  O(s = t) Space complexity to create a HashMap
   *  O(s = t) Time complexity to loop through s and t
   *  O(s = t) Time complexity to loop through HashMap
   *
   *  <p>
   *    Each character in s increases the corresponding charCount in hashMap.
   *    Each character in t decreases the corresponding charCount in hashMap.
   *    Finally, every value in HashMap must be zero.
   *  </p>
   *
   *  <p>
   *    This works with unicode.
   *  </p>
   *
   *  @param s firstString
   *  @param t secondString
   *  @return True if anagram, False otherwise
   */
  public boolean time_on_space_on(String s, String t) {
    Map<Character, Integer> charCount = new HashMap<>();

    char[] firstString = s.toCharArray();
    char[] secondString = t.toCharArray();

    if (firstString.length != secondString.length) {
      return false;
    }

    for (int i = 0; i < firstString.length; i += 1) {
      char firstChar = firstString[i];
      Integer firstCount = charCount.get(firstChar);
      if (firstCount == null) {
        firstCount = 0;
      }
      charCount.put(firstChar, firstCount + 1);

      char secondChar = secondString[i];
      Integer secondCount = charCount.get(secondChar);
      if (secondCount == null) {
        secondCount = 0;
      }
      charCount.put(secondChar, secondCount - 1);
    }

    for (int count : charCount.values()) {
      if (count > 0) {
        return false;
      }
    }

    return true;
  }

  /**
   *  O(1) Space complexity to create an Array
   *  O(s = t) Time complexity to loop through s and t
   *  O(1) Time complexity to loop through the Array
   *
   *  <p>
   *    An Array of length 26 represents the 26 english alphabets, lower-cased.
   *    Each character in s increases the corresponding charCount in Array.
   *    Each character in t decreases the corresponding charCount in Array.
   *    Finally, every value in HashMap must be zero.
   *  </p>
   *
   *  @param s firstString
   *  @param t secondString
   *  @return True if anagram, False otherwise
   */
  public boolean time_on_space_on_alpha_lower(String s, String t) {
    int[] countMap = new int[26];

    char[] firstString = s.toCharArray();
    char[] secondString = t.toCharArray();

    if (firstString.length != secondString.length) {
      return false;
    }

    for (int i = 0; i < firstString.length; i += 1) {
      char firstChar = firstString[i];
      int firstCharPos = firstChar - 'a';
      int firstCharCount = countMap[firstCharPos];
      countMap[firstCharPos] = firstCharCount + 1;

      char secondChar = secondString[i];
      int secondCharPos = secondChar - 'a';
      int secondCharCount = countMap[secondCharPos];
      countMap[secondCharPos] = secondCharCount - 1;
    }

    for (int count : countMap) {
      if (count != 0) {
        return false;
      }
    }

    return true;
  }

  /**
   *  O(s(log(s))) Time complexity to sort s
   *  O(t(log(t))) Time complexity to sort t
   *  O(s = t) Time complexity to loop through s and t
   *
   *  <p>
   *    This works with unicode.
   *  </p>
   *
   *  @param s firstString
   *  @param t secondString
   *  @return True if anagram, False otherwise
   */
  public boolean time_on_space_on_3(String s, String t) {
    char[] firstString = s.toCharArray();
    char[] secondString = t.toCharArray();
    Arrays.sort(firstString);
    Arrays.sort(secondString);

    if (firstString.length != secondString.length) {
      return false;
    }

    for (int i = 0; i < firstString.length; i += 1) {
      char firstChar = firstString[i];
      char secondChar = secondString[i];

      if (firstChar != secondChar) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ValidAnagram_Q242 validAnagramQ242 = new ValidAnagram_Q242();
    String s = "eh";
    String t = "he";
    boolean result = validAnagramQ242.time_on_space_on_3(s, t);
    System.out.println(result);
  }
}
