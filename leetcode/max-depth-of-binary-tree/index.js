function createQueue() {
  const queue = Object.create(null);
  let startIndex = -1;
  let endIndex = -1;
  let length = 0;

  const enqueue = (x) => {
    endIndex += 1;
    queue[endIndex] = x;
    length += 1;

    if (startIndex < 0) startIndex += 1;
  };

  const dequeue = () => {
    const val = queue[startIndex];
    if (!val) return undefined;

    delete queue[startIndex];
    startIndex += 1;
    length -= 1;

    return val;
  };

  const getLength = () => length;

  return {
    queue,
    getLength,
    enqueue,
    dequeue,
  };
}

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
const maxDepth = function maxDepth(root) {
  if (!root) return 0;

  let count = 0;

  const queue = createQueue();
  queue.enqueue(root);

  while (queue.getLength() !== 0) {
    count += 1;
    const n = queue.getLength();

    for (let i = 0; i < n; i += 1) {
      const node = queue.dequeue();

      if (node.left !== null) queue.enqueue(node.left);
      if (node.right !== null) queue.enqueue(node.right);
    }
  }

  return count;
};

export default maxDepth;
