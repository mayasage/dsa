/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} preorder
 * @param {number[]} inorder
 * @return {TreeNode}
 */
const buildTree = (preorder, inorder) => {
  let pi = 0;

  const imap = {};
  for (let i = 0; i < inorder.length; i += 1) {
    imap[inorder[i]] = i;
  }

  const build = (ii, ij) => {
    if (ij < ii) return null;

    const node = {
      val: preorder[pi],
      left: null,
      right: null,
    };
    pi += 1;

    const mid = imap[node.val];

    node.left = build(ii, mid - 1);
    node.right = build(mid + 1, ij);

    return node;
  };

  return build(0, inorder.length - 1);
};

export default buildTree;
