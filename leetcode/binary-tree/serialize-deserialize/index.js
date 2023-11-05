/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
const serialize = (root) => {
  const arr = [];

  const build = (node) => {
    if (node === null) {
      arr.push('n');
      return;
    }

    arr.push(node.val);
    build(node.left);
    build(node.right);
  };

  build(root);
  return arr.join(',');
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
const deserialize = (data) => {
  const arr = data.split(',');

  let i = 0;
  const build = () => {
    const val = arr[i];
    i += 1;

    if (val === 'n') return null;

    const node = {
      val,
      left: build(),
      right: build(),
    };

    return node;
  };

  return build();
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */

export default { serialize, deserialize };
