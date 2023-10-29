import stringToWords from './index.ts';

type answer = {
  words: string[];
  incompleteWord: string;
};

type tests = {
  [key: string]: { input: string; output: answer }[];
};

const testStrings: tests = {
  'Test-1': [
    { input: 'xyz\n', output: { words: ['xyz'], incompleteWord: 'xyz' } },
    {
      input: 'abc lmn\n',
      output: {
        words: ['xyzabc', 'abc', 'lmn'],
        incompleteWord: 'lmn',
      },
    },
  ],
  'Test-2': [
    { input: 'xyz\n', output: { words: ['xyz'], incompleteWord: 'xyz' } },
    {
      input: 'lmn abc\n',
      output: {
        words: ['xyzlmn', 'lmn', 'abc'],
        incompleteWord: 'abc',
      },
    },
  ],
  'Test-3': [
    { input: 'xyz\n', output: { words: ['xyz'], incompleteWord: 'xyz' } },
    {
      input: ' lmn abc\n',
      output: {
        words: ['lmn', 'abc'],
        incompleteWord: 'abc',
      },
    },
  ],
  'Test-4': [
    { input: 'xyz \n', output: { words: ['xyz'], incompleteWord: '' } },
  ],
  'Test-5': [
    { input: 'xyz\n', output: { words: ['xyz'], incompleteWord: 'xyz' } },
  ],
  'Test-6': [
    { input: 'xyz \n', output: { words: ['xyz'], incompleteWord: '' } },
    {
      input: ' lmn abc \n',
      output: {
        words: ['lmn', 'abc'],
        incompleteWord: '',
      },
    },
  ],
};

describe('test strings', () => {
  Object.entries(testStrings).forEach(([testName, testSet]) => {
    const previousAnswer: answer = { words: [], incompleteWord: '' };

    test(testName, () => {
      testSet.forEach((question) => {
        const res = stringToWords(
          question.input,
          previousAnswer.incompleteWord,
        );

        previousAnswer.words = res.words;
        previousAnswer.incompleteWord = res.incompleteWord;

        expect(previousAnswer).toStrictEqual(question.output);
      });
    });
  });
});
