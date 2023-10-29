import peakFinder from './index'
import tests from './test-cases'

const { load } = tests

Object.entries(load).forEach(([key, question]) => {
  console.time(key)

  const { question: q, answer: a } = question

  peakFinder(q)

  console.timeEnd(key)
})
