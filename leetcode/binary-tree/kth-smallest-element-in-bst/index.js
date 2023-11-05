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
 * @param {number} k
 * @return {number}
 */
const kthSmallest = (root, k) => {
  const arr = [];

  const recurse = (node) => {
    if (node === null) return;
    if (arr.length === k) return;
    recurse(node.left);
    arr.push(node.val);
    recurse(node.right);
  };
  recurse(root);

  return arr[k - 1];
};

export default kthSmallest;
