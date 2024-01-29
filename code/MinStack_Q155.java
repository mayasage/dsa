/**
 * <a href="https://leetcode.com/problems/min-stack/description/">
 * <h1> 155. Min Stack </h1>
 * </a>
 *
 * <p>
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * </p>
 * <p>
 * Implement the MinStack class:
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 * </p>
 * <p>
 * You must implement a solution with O(1) time complexity for each function.
 * </p>
 *
 * <p>
 * Constraints:
 * - -2^31 <= val <= 2^31 - 1
 * - Methods pop, top and getMin operations will always be called on non-empty
 * stacks.
 * - At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
 * </p>
 *
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * </p>
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * </p>
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * </p>
 * </p>
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <p>
 * Time Complexity: O(1) for all.
 * </p>
 *
 * <p>
 * Space Complexity: O(N) for stack and mins.
 * </p>
 *
 * <p>
 * Working: The most tricky part is the getMin() operation, which needs to
 * get the minimum element of the stack in O(1) time.
 * To do this, all we do is maintain another array that keeps track of the
 * min element till each index.
 * </p>
 * <p>
 * Example:
 * stack = [-2, 0, -1]
 * mins = [-2, -2, -2]
 * </p>
 */
public class MinStack_Q155 {
  private List<Integer> stack;
  private List<Integer> mins;
  private int top;

  public MinStack_Q155() {
    this.stack = new ArrayList<>();
    this.mins = new ArrayList<>();
    this.top = -1;
  }

  public void push(int val) {
    this.stack.add(val);
    this.top += 1;
    int top = this.top;
    this.mins.add(top == 0 ? val : Math.min(val, this.mins.get(top - 1)));
  }

  public void pop() {
    this.stack.remove(top);
    this.mins.remove(top);
    this.top -= 1;
  }

  public int top() {
    return this.stack.get(this.top);
  }

  public int getMin() {
    return this.mins.get(this.top);
  }

  public static void main(String[] args) {
    MinStack_Q155 minStack = new MinStack_Q155();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    System.out.println(minStack.getMin()); // return -3
    minStack.pop();
    System.out.println(minStack.top());    // return 0
    System.out.println(minStack.getMin()); // return -2
  }
}

/**
 * <p>
 * Time Complexity: O(1) for all.
 * </p>
 *
 * <p>
 * Space Complexity: O(N) for stack and mins.
 * </p>
 *
 * <p>
 * Working: The most tricky part is the getMin() operation, which needs to
 * get the minimum element of the stack in O(1) time.
 * To do this, all we do is maintain another array that keeps track of the
 * min element till each index.
 * </p>
 * <p>
 * Example:
 * stack = [-2, 0, -1]
 * mins = [-2, -2, -2]
 * </p>
 */
class MinStack_Q155_2 {
  Stack<Integer> stack = new Stack<>();
  int min = Integer.MAX_VALUE;

  public void push(int val) {
    if (val <= min) {
      stack.push(min);
      min = val;
    }
    stack.push(val);
  }

  public void pop() {
    if (stack.pop() == min) {
      min = stack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return min;
  }
}