/**
 * <a href="https://leetcode.com/problems/valid-sudoku/description/">
 * <h1> 36. Valid Sudoku </h1>
 * </a>
 *
 * <p>
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * </p>
 *
 * <ol>
 * <li>Each row must contain the digits 1-9 without repetition.</li>
 * <li>Each column must contain the digits 1-9 without repetition.</li>
 * <li>
 *   Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
 *   without repetition.
 * </li>
 * </ol>
 *
 * <p>
 * Note:
 * <ul>
 *   <li>
 *     A Sudoku board (partially filled) could be valid but is not necessarily
 *     solvable.
 *   </li>
 *   <li>
 *     Only the filled cells need to be validated according to the mentioned
 *     rules.
 *   </li>
 * </ul>
 * </p>
 *
 * <p>
 * Constraints:
 *   <ul>
 *     <li> board.length == 9 </li>
 *     <li> board[i].length == 9 </li>
 *     <li>board[i][j] is a digit 1-9 or '.'.</li>
 *   </ul>
 * </p>
 *
 * <p>
 *   Follow up: Can you solve the problem in O(1) extra space complexity? (The
 *   output array does not count as extra space for space complexity analysis.)
 * </p>
 */

import java.util.*;

public class ValidSudoku_Q36 {
  /**
   * Time Complexity:
   * O(9^2) to test every char in the matrix
   * Total: O(9^2)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(N) for hashmap
   * </p>
   *
   * <p>
   * Loop through every char in the matrix.
   * The goal is to build a mapCount.
   * </p>
   *
   * @param board A 9x9 Matrix
   * @return True if valid sudoku, False otherwise
   */
  public boolean time_on2_space_on(char[][] board) {
    Map<Integer, Set<Character>> cols = new HashMap<>();
    Map<String, Set<Character>> boxes = new HashMap<>();

    for (int r = 0; r < 9; r += 1) {
      Set<Character> rowSet = new HashSet<>();

      for (int c = 0; c < 9; c += 1) {
        char v = board[r][c];
        if (v == '.') {
          continue;
        }

        Set<Character> colSet = cols.get(c);
        if (colSet == null) {
          Set<Character> newHashSet = new HashSet<>();
          cols.put(c, newHashSet);
          colSet = newHashSet;
        }

        String boxKey = r / 3 + "," + c / 3;
        Set<Character> boxSet = boxes.get(boxKey);
        if (boxSet == null) {
          Set<Character> newHashSet = new HashSet<>();
          boxes.put(boxKey, newHashSet);
          boxSet = newHashSet;
        }

        if (rowSet.contains(v) || colSet.contains(v) || boxSet.contains(v)) {
          return false;
        }

        rowSet.add(v);
        colSet.add(v);
        boxSet.add(v);
      }
    }

    return true;
  }

  /**
   * Time Complexity:
   * O(9^2) to test every char in the matrix
   * Total: O(9^2)
   * </p>
   *
   * <p>
   * Space Complexity:
   * O(9*3) for hashmap
   * </p>
   *
   * <p>
   * (i = 0) => check 1st row, 1st col and 1st box using i-j acrobatics
   * (i = 8) => you would be checking the last row, col & box
   * </p>
   *
   * @param board A 9x9 Matrix
   * @return True if valid sudoku, False otherwise
   */
  public boolean time_on2_space_o1(char[][] board) {
    for (int i = 0; i < 9; i += 1) {
      Set<Character> rowSet = new HashSet<>();
      Set<Character> colSet = new HashSet<>();
      Set<Character> boxSet = new HashSet<>();

      for (int j = 0; j < 9; j += 1) {
        char rowChar = board[i][j];
        if (rowChar != '.' && rowSet.contains(rowChar)) {
          return false;
        }
        if (rowChar != '.') {
          rowSet.add(rowChar);
        }

        char colChar = board[j][i];
        if (colChar != '.' && colSet.contains(colChar)) {
          return false;
        }
        if (colChar != '.') {
          colSet.add(colChar);
        }

        char boxChar = board[(i / 3) * 3 + j / 3][(i % 3) * 3 + j % 3];
        if (boxChar != '.' && boxSet.contains(boxChar)) {
          return false;
        }
        if (boxChar != '.') {
          boxSet.add(boxChar);
        }
      }
    }

    return true;
  }

  /**
   * Time Complexity:
   * O(9^3) to test every char in the matrix
   * Total: O(9^3)
   * </p>
   *
   * <p>
   * Space Complexity: O(1)
   * </p>
   *
   * <p>
   * Select every element in the matrix and check if it's present anywhere in
   * corresponding row, col or box.
   * This is brute force.
   * </p>
   *
   * <p>
   * Checking Process:
   * 0,5 => row: 5-8; col: 1-8, box: 3-5
   * </p>
   *
   * <p>
   * Box Checking Process:
   * Iterates through every char in the appropriate box.
   *    0 1 2 3 4 5 6 7 8
   *    1
   *    2
   *    3
   *    4
   *    5
   *    6
   *    7
   *    8
   * </p>
   *
   * <p>
   * Row:
   *  When row = 0:
   *    i / 3 * 3 => 0 / 3 * 3 = 0 (start of row)
   *    to
   *    i / 3 * 3 + 2 => 0 + 2 = 2 (end of row)
   *  When row = 5:
   *    5 / 3 * 3 = 3 (start of row)
   *    5 / 3 * 3 + 2 = 5 (end of row)
   * Exactly same for column.
   * If i == k && j == l, then we are at cur, so we continue
   * </p>
   *
   * @param board A 9x9 Matrix
   * @return True if valid sudoku, False otherwise
   */
  public boolean time_on3(char[][] board) {
    for (int row = 0; row < 9; row += 1) {
      for (int col = 0; col < 9; col += 1) {
        char cur = board[row][col];

        if (cur == '.') {
          continue;
        }

        for (int k = col + 1; k < 9; k += 1) {
          if (board[row][k] == cur) {
            return false;
          }
        }

        for (int k = row + 1; k < 9; k += 1) {
          if (board[k][col] == cur) {
            return false;
          }
        }

        for (int k = row / 3 * 3; k < row / 3 * 3 + 3; k++) {
          for (int l = col / 3 * 3; l < col / 3 * 3 + 3; l++) {
            if (row == k && col == l) {
              continue;
            }
            if (board[k][l] == cur) {
              return false;
            }
          }
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    ValidSudoku_Q36 validSudokuQ36 = new ValidSudoku_Q36();
    char[][] board =
      new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

//    char[][] board = new char[][]
//      {{'8', '3', '.', '.', '7', '.', '.', '.', '.'}
//        , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
//        , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
//        , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
//        , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
//        , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
//        , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
//        , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
//        , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

//    char[][] board = new char[][]{
//      {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
//      {'.', '4', '.', '3', '.', '.', '.', '.', '.'}, {
//      '.', '.', '.', '.', '.', '3', '.', '.', '1'}, {
//      '8', '.', '.', '.', '.', '.', '.', '2', '.'}, {
//      '.', '.', '2', '.', '7', '.', '.', '.', '.'}, {
//      '.', '1', '5', '.', '.', '.', '.', '.', '.'}, {
//      '.', '.', '.', '.', '.', '2', '.', '.', '.'}, {
//      '.', '2', '.', '9', '.', '.', '.', '.', '.'}, {
//      '.', '.', '4', '.', '.', '.', '.', '.', '.'}};

    boolean result = validSudokuQ36.time_on3(board);
    System.out.println(result);
  }
}
