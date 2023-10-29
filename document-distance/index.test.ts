import generateUniquelyNamedFiles from '../utils/generate-uniquely-named-files/index.ts';
import documentDistance from './index.ts';

const testSuite: {
  [key: string]: {
    fileData: [string, string];
    output: number;
  };
} = {
  'Test-1': {
    fileData: ['xyz\nlmn abc', 'xyz\nlmn abc'],
    output: 0,
  },
};

describe('test document distance', () => {
  Object.entries(testSuite).forEach(([testName, testData]) => {
    test(testName, async () => {
      const [doc1Path, doc2Path] = (
        await generateUniquelyNamedFiles({
          fileData: testData.fileData,
        })
      ).map((filesMeta) => filesMeta.filePath);

      expect(await documentDistance(doc1Path, doc2Path)).toBe(0);
    });
  });

  test('not zero', async () => {
    const [doc1Path, doc2Path] = (
      await generateUniquelyNamedFiles({
        fileData: ['xyz\nlmn abc', 'xyz\n lmn abc'],
      })
    ).map((filesMeta) => filesMeta.filePath);

    expect(await documentDistance(doc1Path, doc2Path)).not.toBe(0);
  });
});
