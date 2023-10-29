import { eachLine } from 'line-reader';
import { promisify } from 'util';
import stringToWords, {
  response as stringToWordsResponse,
} from '../string-to-word-count-map/index.ts';

const eachLineP = promisify(eachLine);

export type response = {
  [key: string]: number;
};

const fileToWordCountMap = async (
  absoluteFilePath: string,
): Promise<response> => {
  const res: response = {};
  const previousResponse: stringToWordsResponse = {
    words: [],
    incompleteWord: '',
  };

  // @ts-ignore
  await eachLineP(absoluteFilePath, (line) => {
    const result = stringToWords(`${line}\n`, previousResponse.incompleteWord);

    previousResponse.words = result.words;
    previousResponse.incompleteWord = result.incompleteWord;

    result.words.forEach((word) => {
      const lowerCasedWord = word.toLowerCase();
      res[lowerCasedWord] = res[lowerCasedWord] ? res[lowerCasedWord] + 1 : 1;
    });
  });

  return res;
};

export default fileToWordCountMap;
