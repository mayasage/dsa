import path from 'path';
import fileToWords from './index.ts';
import * as test1Ans from './data/ch5-replication-answer.json';

const tests = {
  'Test-1': {
    input: path.join(__dirname, './ch5-replication.txt'),
    output: test1Ans,
  },
};

Object.entries(tests).forEach(([testName, question]) => {
  console.time(testName);
  fileToWords(question.input);
  console.timeEnd(testName);
});
