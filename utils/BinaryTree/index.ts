import TreeNode from './TreeNode.ts';
import Queue from '../Queue/index.ts';

type EnqueueFormat = [TreeNode, 'leftChild' | 'rightChild'];

const createNode = (value: unknown): TreeNode => ({
  value,
  leftChild: null,
  rightChild: null,
});

class BinaryTree {
  root: TreeNode = null;

  queue = new Queue<EnqueueFormat>();

  constructor(values: unknown[] = []) {
    for (let i = 0; i < values.length; i += 1) this.add(values[i]);
  }

  /**
   *  1. Create new Node
   *  2. Attach new Node
   *  3. Enqueue left & right of new Node
   *
   *  @param value unknown
   */
  add(value: unknown) {
    const newNode = createNode(value); // 1

    const next = this.queue.dequeue(); // 2
    if (next === undefined) {
      this.root = newNode;
    } else {
      const [parentNode, direction] = next;
      if (parentNode === null) throw new Error("parentNode can't be null");
      parentNode[direction] = newNode;
    }

    this.queue.enqueue([newNode, 'leftChild']); // 3
    this.queue.enqueue([newNode, 'rightChild']);
  }
}

export default BinaryTree;
