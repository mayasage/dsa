const q = () => {
  const queue = Object.create(null);
  let startIndex = -1;
  let endIndex = -1;
  let length = 0;

  const enqueue = (val) => {
    endIndex += 1;
    length += 1;
    queue[endIndex] = val;
    if (startIndex < 0) startIndex = 0;
  };

  const dequeue = () => {
    if (length === 0) return undefined;
    length -= 1;
    const val = queue[startIndex];
    delete queue[startIndex];
    startIndex += 1;
    return val;
  };

  const getLength = () => length;

  return {
    enqueue,
    dequeue,
    getLength,
  };
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
 * @return {number[][]}
 */
const levelOrder = (root) => {
  const arr = [];
  if (!root) return arr;

  const queue = q();
  queue.enqueue(root);

  while (queue.getLength() > 0) {
    const n = queue.getLength();
    const a = [];

    for (let i = 0; i < n; i += 1) {
      const node = queue.dequeue();
      a.push(node.val);
      if (node.left) queue.enqueue(node.left);
      if (node.right) queue.enqueue(node.right);
    }

    arr.push(a);
  }

  return arr;
};

export default levelOrder;
