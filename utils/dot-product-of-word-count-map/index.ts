/* eslint-disable operator-linebreak */
type wordCountMapType = {
  [key: string]: number;
};

const innerProduct = (
  wordCountMap1: wordCountMapType,
  wordCountMap2: wordCountMapType,
): number => {
  let sum = 0;

  Object.keys(wordCountMap1).forEach((word) => {
    if (wordCountMap2[word]) {
      sum += wordCountMap1[word] * wordCountMap2[word];
    }
  });

  return sum;
};

const dotProductOfWordCountMap = (
  wordCountMap1: wordCountMapType,
  wordCountMap2: wordCountMapType,
) => {
  const numerator = innerProduct(wordCountMap1, wordCountMap2);
  const denominator = Math.sqrt(
    innerProduct(wordCountMap1, wordCountMap1) *
      innerProduct(wordCountMap2, wordCountMap2),
  );
  return Math.acos(numerator / denominator);
};

export default dotProductOfWordCountMap;
