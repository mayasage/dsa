/* eslint-disable consistent-return */
/* eslint-disable operator-linebreak */
/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * @param {TreeNode} root
 * @param {TreeNode} p
 * @param {TreeNode} q
 * @return {TreeNode}
 */
const lowestCommonAncestor = (root, p, q) => {
  if (p.val === root.val || q.val === root.val) return root;

  if (
    (p.val < root.val && q.val > root.val) ||
    (q.val < root.val && p.val > root.val)
  ) {
    return root;
  }

  if (p.val < root.val) return lowestCommonAncestor(root.left, p, q);
  if (p.val > root.val) return lowestCommonAncestor(root.right, p, q);
};

export default lowestCommonAncestor;
