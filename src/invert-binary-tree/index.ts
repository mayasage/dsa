/* eslint-disable no-param-reassign */

import TreeNode from '../../utils/BinaryTree/TreeNode.ts';

/**
 * Definition for a binary tree node.
 * function TreeNode(val, leftChild, rightChild) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.leftChild = (leftChild===undefined ? null : leftChild)
 *     this.rightChild = (rightChild===undefined ? null : rightChild)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {TreeNode}
 */
const invertBinaryTree = (root: TreeNode): TreeNode => {
  if (!root) return root;

  invertBinaryTree(root.leftChild);
  invertBinaryTree(root.rightChild);

  const temp = root.leftChild;
  root.leftChild = root.rightChild;
  root.rightChild = temp;

  return root;
};

export default invertBinaryTree;
