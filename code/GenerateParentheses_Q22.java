/**
 * <a href="https://leetcode.com/problems/generate-parentheses/">
 * <h1> 22. Generate Parentheses </h1>
 * </a>
 *
 * <p>
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * </p>
 *
 * <p>
 * Constraints:
 * - 1 <= n <= 8
 * </p>
 *
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * </p>
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 * </p>
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses_Q22 {
  /**
   * <p>
   * Time Complexity:
   * There can be a maximum of 2^n recursive calls (1 for each character).
   * So it's a GP like this:
   * 2^0 + 2^1 + 2^2 + ... + 2^2n
   * Then Total Calls = Sum of GP = a(r ^ (n + 1) - 1) / (r - 1)
   * = 2 ^ (2n + 1) - 1
   * But, there's another O(n) for converting stack to a String.
   * Total = O(N * 2^2N)
   * </p>
   *
   * <p>
   * Space Complexity:
   * For each recursive function call, we do 2 more recursive calls.
   * This implies that stack_recursion depth is 2n.
   * The stack we use to save intermediate results is another n.
   * Total = O(N)
   * </p>
   *
   * <p>
   * Working:
   * - Try to push '(' first
   * - Then try to push ')'
   * - Exit condition: both '(' & ')' are exhausted
   * - We maintain a stack instead of a StringBuilder, because we need to use
   * pop() to remove the last push parentheses, after its work is done, so
   * that the next combination can be created, while keeping the rest of the
   * parentheses.
   * Example: [((()))] -> pop last parentheses while keeping the rest.
   * Example:
   * n = 3
   * rec() -> stack push "(" * 3
   * rec() -> stack push ")" * 3
   * rec() -> openCount == closeCount == 3 => res.add(stack) -> return
   * Prev rec() -> stack.pop() * 3 => [ ((( ]
   * Prev rec() -> stack.pop() => [ (( ]
   * rec() -> stack push ")" => [ (() ]
   * rec() -> stack push "(" => [ (()( ]
   * ... and so on.
   * </p>
   *
   * @param n          number of parentheses pairs
   * @param openCount  open parentheses used
   * @param closeCount closed parentheses used
   * @param result     the final result array of strings
   * @param stack      used to build a combination of parentheses
   */
  private void stack_recursion(int n, int openCount, int closeCount,
                               List<String> result, Stack<Character> stack) {
    if (openCount == closeCount && openCount == n) {
      result.add(
        stack
          .stream()
          .map(Object::toString)
          .collect(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append
          )
          .toString()
      );
      return;
    }

    if (openCount < n || openCount == 0) {
      stack.push('(');
      stack_recursion(n, openCount + 1, closeCount, result, stack);
      stack.pop();
    }

    if (closeCount < openCount) {
      stack.push(')');
      stack_recursion(n, openCount, closeCount + 1, result, stack);
      stack.pop();
    }
  }

  public List<String> stack(int n) {
    List<String> result = new ArrayList<>();
    stack_recursion(n, 0, 0, result, new Stack<Character>());
    return result;
  }

  /**
   * <p>
   * Time Complexity:
   * There can be a maximum of 2^n recursive calls (1 for each character).
   * So it's a GP like this:
   * 2^0 + 2^1 + 2^2 + ... + 2^2n
   * Then Total Calls = Sum of GP = a(r ^ (n + 1) - 1) / (r - 1)
   * = 2 ^ (2n + 1) - 1
   * But, there's another O(n) for converting stack to a String.
   * Total = O(N * 2^2N)
   * </p>
   *
   * <p>
   * Space Complexity:
   * For each recursive function call, we do 2 more recursive calls.
   * This implies that stack_recursion depth is 2n.
   * The string we use to save intermediate results is another n.
   * Total = O(N)
   * </p>
   *
   * <p>
   * Same as string_recursion.
   * Just changed expression.
   * </p>
   *
   * @param n          number of parentheses pairs
   * @param openCount  open parentheses used
   * @param closeCount closed parentheses used
   * @param result     the final result array of strings
   * @param str        used to build a combination of parentheses
   */
  private void string_recursion(int n, int openCount, int closeCount,
                                List<String> result, String str) {
    if (str.length() == n * 2) {
      result.add(str);
      return;
    }

    if (openCount < n) {
      string_recursion(
        n,
        openCount + 1,
        closeCount,
        result,
        str + "("
      );
    }

    if (closeCount < openCount) {
      string_recursion(
        n,
        openCount,
        closeCount + 1,
        result,
        str + ")"
      );
    }
  }

  public List<String> string(int n) {
    List<String> result = new ArrayList<>();
    string_recursion(n, 0, 0, result, "");
    return result;
  }

  /**
   * <p>
   * Time Complexity:
   * There can be a maximum of 2^n recursive calls (1 for each character).
   * So it's a GP like this:
   * 2^0 + 2^1 + 2^2 + ... + 2^2n
   * Then Total Calls = Sum of GP = a(r ^ (n + 1) - 1) / (r - 1)
   * = 2 ^ (2n + 1) - 1
   * But, there's another O(n) for converting StringBuilder to a String.
   * Total = O(N * 2^2N)
   * </p>
   *
   * <p>
   * Space Complexity:
   * For each recursive function call, we do 2 more recursive calls.
   * This implies that stack_recursion depth is 2n.
   * The StringBuilder we use to save intermediate results is another n.
   * Total = O(N)
   * </p>
   *
   * <p>
   * Uses StringBuilder instead of stack.
   * O(1) for removing last element.
   * Stack was not required after all.
   * </p>
   *
   * @param n          number of parentheses pairs
   * @param openCount  open parentheses used
   * @param closeCount closed parentheses used
   * @param result     the final result array of strings
   * @param sb         used to build a combination of parentheses
   */
  private void string_builder_recursion(int n, int openCount, int closeCount,
                                        List<String> result, StringBuilder sb) {
    if (sb.length() == n * 2) {
      result.add(sb.toString());
      return;
    }

    if (openCount < n) {
      string_builder_recursion(
        n,
        openCount + 1,
        closeCount,
        result,
        sb.append("(")
      );
      sb.deleteCharAt(sb.length() - 1);
    }

    if (closeCount < openCount) {
      string_builder_recursion(
        n,
        openCount,
        closeCount + 1,
        result,
        sb.append(")")
      );
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  public List<String> string_builder(int n) {
    List<String> result = new ArrayList<>();
    string_builder_recursion(n, 0, 0, result, new StringBuilder());
    return result;
  }

  public static void main(String[] args) {
    GenerateParentheses_Q22 generateParenthesesQ22 = new GenerateParentheses_Q22();
    System.out.println(generateParenthesesQ22.stack(1).size());
    System.out.println(generateParenthesesQ22.stack(2).size());
    System.out.println(generateParenthesesQ22.stack(3).size());
    System.out.println(generateParenthesesQ22.stack(4).size());
    System.out.println(generateParenthesesQ22.stack(5).size());
    System.out.println();
    System.out.println(generateParenthesesQ22.string(1).size());
    System.out.println(generateParenthesesQ22.string(2).size());
    System.out.println(generateParenthesesQ22.string(3).size());
    System.out.println(generateParenthesesQ22.string(4).size());
    System.out.println(generateParenthesesQ22.string(5).size());
    System.out.println();
    System.out.println(generateParenthesesQ22.string_builder(1).size());
    System.out.println(generateParenthesesQ22.string_builder(2).size());
    System.out.println(generateParenthesesQ22.string_builder(3).size());
    System.out.println(generateParenthesesQ22.string_builder(4).size());
    System.out.println(generateParenthesesQ22.string_builder(5).size());
  }
}
