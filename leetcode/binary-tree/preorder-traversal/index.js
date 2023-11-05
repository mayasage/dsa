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
 * @return {number[]}
 */
const preorderTraversal = (root) => {
  const arr = [];

  const recurse = (node) => {
    if (node === null) return;
    arr.push(node.val);
    recurse(node.left);
    recurse(node.right);
  };
  recurse(root);

  return arr;
};

export default preorderTraversal;
