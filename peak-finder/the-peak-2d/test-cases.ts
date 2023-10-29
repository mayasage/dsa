type question = { [key: string]: { question: number[][]; answer: number } }

const coverage: question = {
  'Question-1': {
    question: [
      [10, 8, 10, 10],
      [14, 13, 12, 11],
      [15, 9, 11, 21],
      [16, 17, 19, 20],
    ],
    answer: 21,
  },
  'Question-2': {
    question: [
      [10, 10],
      [12, 11],
      [11, 21],
      [19, 20],
    ],
    answer: 21,
  },
  'Question-3': { question: [[10], [11], [21], [20]], answer: 21 },
}

const tests = {
  coverage,
}

export default tests
