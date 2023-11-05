/**
 * @param {number} n
 * @return {number}
 */
const climbStairs = (n) => {
  let max = 1;

  let jumpOne = 1;
  let jumpTwo = 1;

  for (let i = n - 2; i >= 0; i -= 1) {
    max = jumpOne + jumpTwo;
    jumpTwo = jumpOne;
    jumpOne = max;
  }

  return max;
};

export default climbStairs;
