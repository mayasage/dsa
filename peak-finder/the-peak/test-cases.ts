type question = { [key: string]: { question: number[]; answer: number } }

const generateQuestion = (n: number) => {
  const a: number[] = []
  for (let i = 1; i <= n; i += 1) a.push(i)
  return { question: a, answer: n }
}

const load: question = {
  'Question-1': generateQuestion(100000000),
  'Question-2': generateQuestion(100000000),
}

const coverage: question = {
  'Question-1': { question: [1, 2, 3], answer: 3 },
  'Question-2': { question: [-1, -2, -3], answer: -1 },
  'Question-3': { question: [-1], answer: -1 },
  'Question-4': { question: [0], answer: 0 },
}

const tests = {
  load,
  coverage,
}

export default tests
