import peakFinder from './index.ts';
import tests from './test-cases.ts';

const { coverage } = tests;

describe('coverage tests', () => {
  Object.values(coverage).forEach(({ question, answer }) => {
    test(`check question: ${question}`, () => {
      expect(peakFinder(question).value).toBe(answer);
    });
  });
});
