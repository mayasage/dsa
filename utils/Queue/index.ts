class Queue {
  queue: { [key: string]: unknown } = Object.create(null);

  startIndex = -1;

  endIndex = -1;

  constructor(values: unknown[] = []) {
    for (let i = 0; i < values.length; i += 1) this.enqueue(values[i]);
  }

  enqueue(val: unknown) {
    this.endIndex += 1;
    this.queue[this.endIndex] = val;

    if (this.startIndex < 0) this.startIndex = 0;
  }

  dequeue(): unknown {
    const val = this.queue[this.startIndex];
    if (val === undefined) return undefined;

    delete this.queue[this.startIndex];
    this.startIndex += 1;
    this.adjust();

    return val;
  }

  peek(): unknown {
    return this.queue[this.startIndex];
  }

  isEmpty(): boolean {
    return this.queue[this.startIndex] === undefined;
  }

  adjust() {
    const ZeroToStartIndex = this.startIndex - 0;
    const startIndexToEndIndex = this.endIndex - this.startIndex;

    if (
      // eslint-disable-next-line operator-linebreak
      this.endIndex > 1_00_000 &&
      ZeroToStartIndex > startIndexToEndIndex
    ) {
      const values = Object.values(this.queue);

      this.queue = Object.create(null);
      for (let i = 0; i < values.length; i += 1) {
        this.queue[i] = values[i];
      }

      if (values.length > 0) {
        this.startIndex = 0;
        this.endIndex = values.length - 1;
      } else {
        this.startIndex = -1;
        this.endIndex = -1;
      }
    }
  }
}

export default Queue;
