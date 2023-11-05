/* eslint-disable operator-linebreak */
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {number}
 */
const maxPathSum = (root) => {
  if (root === null) return 0;
  let max = root.val;

  const find = (node) => {
    if (node === null) return 0;

    const leftMax = find(node.left);
    const rightMax = find(node.right);
    const pickedMaxPath = node.val + Math.max(leftMax, rightMax, 0);
    const becameMaxPath =
      node.val + Math.max(leftMax, 0) + Math.max(rightMax, 0);

    max = Math.max(max, becameMaxPath);
    return pickedMaxPath;
  };

  find(root);
  return max;
};

export default maxPathSum;
