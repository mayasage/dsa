import BinaryTree from './index.ts';

describe('binary tree', () => {
  test('add with constructor', () => {
    const binaryTree = new BinaryTree(['a', 1, null, [], {}]);

    expect(binaryTree.root).not.toBe(null);
    if (binaryTree.root === null) throw new Error('root is null');
    if (!Object.hasOwn(binaryTree.root, 'value')) {
      throw new Error('root is null');
    }

    expect(binaryTree.root.value).toBe('a');

    if (binaryTree.root.leftChild === null) {
      throw new Error('root.left is null');
    }
    expect(binaryTree.root.leftChild.value).toBe(1);

    if (binaryTree.root.rightChild === null) {
      throw new Error('root.right is null');
    }
    expect(binaryTree.root.rightChild.value).toBe(null);

    if (binaryTree.root.leftChild.leftChild === null) {
      throw new Error('root.leftChild.leftChild is null');
    }
    expect(binaryTree.root.leftChild.leftChild.value).toEqual([]);

    if (binaryTree.root.leftChild.rightChild === null) {
      throw new Error('root.leftChild.rightChild is null');
    }
    expect(binaryTree.root.leftChild.rightChild.value).toEqual({});
  });
});
