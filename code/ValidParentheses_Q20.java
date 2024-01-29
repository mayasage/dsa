import java.util.*;

/**
 * <a href="https://leetcode.com/problems/valid-parentheses/description/">
 * <h1> 242. Valid Anagram </h1>
 * </a>
 *
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * </p>
 * <p>
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 * 3. Every close bracket has a corresponding open bracket of the same type.
 * </p>
 *
 * <p>
 * Example 1:
 * Input: s = "()"
 * Output: true
 * </p>
 * <p>
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 * </p>
 * <p>
 * Example 3:
 * Input: s = "(]"
 * Output: false
 * </p>
 */

public class ValidParentheses_Q20 {
  /**
   * <p>
   * Time Complexity: O(N)
   * </p>
   *
   * <p>
   * Space Complexity: O(N) for stack
   * </p>
   *
   * <p>
   * If you see, '(', '[', or '{', put reverse of it into stack.
   * When something else comes up, pop from stack and match the char.
   * Example:
   * "(())"
   * - Push ')'
   * - Push ')'
   * - Pop and match ')'
   * </p>
   *
   * @param s string containing different parentheses
   * @return True if validParentheses, False otherwise
   */
  public boolean stack_1(String s) {
    boolean result = true;

    char[] chars = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char c : chars) {
      if (c == '(') stack.add(')');
      else if (c == '[') stack.add(']');
      else if (c == '{') stack.add('}');
      else if (stack.isEmpty()) return false;
      else if (stack.pop() != c) return false;
    }
    if (!stack.isEmpty()) return false;

    return result;
  }

  /**
   * <p>
   * Time Complexity: O(N)
   * </p>
   *
   * <p>
   * Space Complexity: O(N) for stack
   * </p>
   *
   * <p>
   * Same as stack_1 except that you don't use built in stack, but a primitive
   * array and a pointer variable.
   * </p>
   *
   * @param s string containing different parentheses
   * @return True if validParentheses, False otherwise
   */
  public boolean stack_2(String s) {
    boolean result = true;

    int n = s.length();
    char[] stack = new char[n];

    int top = -1;
    for (int i = 0; i < n; i += 1) {
      char c = s.charAt(i);
      if (c == '(') stack[++top] = ')';
      else if (c == '{') stack[++top] = '}';
      else if (c == '[') stack[++top] = ']';
      else if (top < 0) return false;
      else if (c != stack[top--]) return false;
    }
    if (top >= 0) return false;

    return result;
  }

  public static void main(String[] args) {
    ValidParentheses_Q20 validParenthesesQ20 = new ValidParentheses_Q20();
    String s;
    s = "()}";
    s = "[";
    s = "[[(({}))]";
    s = "[(({}))]";
    s = "";
    s = "()";
    boolean result = validParenthesesQ20.stack_2(s);
    System.out.println(result);
  }
}
