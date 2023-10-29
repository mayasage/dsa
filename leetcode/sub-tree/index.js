const isSameTree = (p, q) => {
  if (!p && !q) return true;
  if ((p && !q) || (q && !p)) return false;
  if (p.val !== q.val) return false;

  return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
};

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
 * @param {TreeNode} subRoot
 * @return {boolean}
 */
const isSubtree = (root, subRoot) => {
  if (isSameTree(root, subRoot)) return true;
  if (!root) return false;
  return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
};

export default isSubtree;
