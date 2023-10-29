import Queue from './index.ts';

describe('Queue', () => {
  test('empty dequeue', () => {
    const queue = new Queue();

    expect(queue).toMatchObject({
      queue: Object.create(null),
      startIndex: -1,
      endIndex: -1,
    });

    expect(queue.dequeue()).toBe(undefined);

    expect(queue).toMatchObject({
      queue: Object.create(null),
      startIndex: -1,
      endIndex: -1,
    });
  });

  test('enqueue', () => {
    const queue = new Queue();

    queue.enqueue('a');
    queue.enqueue(1);
    queue.enqueue([]);
    queue.enqueue({});

    expect(queue).toMatchObject({
      queue: {
        0: 'a',
        1: 1,
        2: [],
        3: {},
      },
      startIndex: 0,
      endIndex: 3,
    });
  });

  test('enqueue dequeue', () => {
    const queue = new Queue();

    queue.enqueue('a');
    queue.enqueue(1);
    queue.enqueue([]);
    queue.enqueue({});

    expect(queue).toMatchObject({
      queue: {
        0: 'a',
        1: 1,
        2: [],
        3: {},
      },
      startIndex: 0,
      endIndex: 3,
    });

    expect(queue.dequeue()).toBe('a');
    expect(queue.dequeue()).toBe(1);
    expect(queue.dequeue()).toEqual([]);
    expect(queue.dequeue()).toEqual({});

    expect(queue).toMatchObject({
      queue: {},
      startIndex: 4,
      endIndex: 3,
    });
  });

  test('constructor', () => {
    const queue = new Queue(['a', 1, [], {}]);

    expect(queue).toMatchObject({
      queue: {
        0: 'a',
        1: 1,
        2: [],
        3: {},
      },
      startIndex: 0,
      endIndex: 3,
    });
  });

  test('big queue', () => {
    const queue = new Queue();

    for (let i = 0; i < 1_000_000; i += 1) queue.enqueue(i);
    expect(queue.startIndex).toBe(0);
    expect(queue.endIndex).toBe(1_000_000 - 1);

    for (let i = 0; i < 500_000 - 1; i += 1) queue.dequeue();
    expect(queue.startIndex).toBe(500_000 - 1);
    expect(queue.endIndex).toBe(1_000_000 - 1);

    queue.dequeue();
    expect(queue.startIndex).toBe(0);
    expect(queue.endIndex).toBe(500_000 - 1);
  });
});
