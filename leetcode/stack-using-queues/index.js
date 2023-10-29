const MyQueue = function MyQueue() {
  this.queue = Object.create(null);
  this.startIndex = -1;
  this.endIndex = -1;
};

/**
 * @param {number} x
 * @return {void}
 */
MyQueue.prototype.push = function push(x) {
  this.endIndex += 1;
  this.queue[this.endIndex] = x;

  if (this.startIndex < 0) this.startIndex = 0;
};

/**
 * @return {number}
 */
MyQueue.prototype.pop = function pop() {
  const val = this.queue[this.startIndex];
  if (val === undefined) return null;

  delete this.queue[this.startIndex];
  this.startIndex += 1;

  return val;
};

/**
 * @return {number}
 */
MyQueue.prototype.peek = function peek() {
  const val = this.queue[this.startIndex];
  return val === undefined ? null : val;
};

/**
 * @return {boolean}
 */
MyQueue.prototype.empty = function empty() {
  return this.queue[this.startIndex] === undefined;
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
