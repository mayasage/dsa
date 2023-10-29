export type response = {
  words: string[];
  incompleteWord: string;
};

const regex = {
  whitespace: /\s/,
};

const isWhitespace = (char: string) => regex.whitespace.test(char);

const isLastWordIncomplete = (string: string): boolean => {
  if (!string.endsWith('\n')) return false;
  const secondLastChar: string = string.at(-2) ?? '';
  return !regex.whitespace.exec(secondLastChar);
};

const isThereADelimiterBeforeFirstWord = (string: string): boolean => {
  const firstChar = string.at(0) ?? '';
  return isWhitespace(firstChar);
};

const stringToWords = (
  string: string,
  previousIncompleteWord: string = '',
): response => {
  const res: response = {
    words: [],
    incompleteWord: '',
  };

  const prevIncompleteWord = isThereADelimiterBeforeFirstWord(string)
    ? ''
    : previousIncompleteWord;

  let currentWord = '';
  let lastPushedWord = '';
  let isFirstWord = true;

  for (let i = 0; i < string.length; i += 1) {
    const char = string[i];

    if (isWhitespace(char)) {
      const completeWord = currentWord;
      currentWord = '';

      if (completeWord) {
        if (isFirstWord) {
          isFirstWord = false;
          if (prevIncompleteWord) {
            res.words.push(previousIncompleteWord + completeWord);
          }
        }

        res.words.push(completeWord);
        lastPushedWord = completeWord;
      }
    } else {
      currentWord += char;
    }
  }

  if (isLastWordIncomplete(string)) res.incompleteWord = lastPushedWord;

  return res;
};

export default stringToWords;
