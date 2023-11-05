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
 * @return {boolean}
 */
const isValidBST = (root) => {
  const isValid = (node, min, max) => {
    if (node === null) return true;
    if (node.val <= min || node.val >= max) return false;
    return (
      isValid(node.left, min, Math.min(max, node.val)) &&
      isValid(node.right, Math.max(min, node.val), max)
    );
  };

  return isValid(root, -Infinity, +Infinity);
};

export default isValidBST;
