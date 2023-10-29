import peakFinder from './index'
import tests from './test-cases'

const { coverage } = tests

describe('coverage tests', () =>
  Object.values(coverage).forEach(({ question, answer }) =>
    test('check question: ' + question, () =>
      expect(peakFinder(question).value).toBe(answer),
    ),
  ))
