import fileToWords from './index.ts';
import generateUniquelyNamedFiles from '../generate-uniquely-named-files/index.ts';

const testSuite: {
  [key: string]: {
    data: string;
    output: { [key: string]: number };
  };
} = {
  'Test-1': {
    data: 'xyz\nabc lmn',
    output: {
      xyz: 1,
      abc: 1,
      xyzabc: 1,
      lmn: 1,
    },
  },
  'Test-2': {
    data: 'xyz\n abc lmn',
    output: {
      xyz: 1,
      abc: 1,
      lmn: 1,
    },
  },
};

Object.entries(testSuite).forEach(([testName, testData]) => {
  test(testName, async () => {
    const fileMeta = await generateUniquelyNamedFiles({
      fileData: [testData.data],
    });
    const res = await fileToWords(fileMeta[0].filePath);
    expect(res).toMatchObject(testData.output);
  });
});
