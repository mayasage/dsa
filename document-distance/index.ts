import fileToWordCountMap, {
  response,
} from '../utils/file-to-word-count-map/index.ts';
import dotProductOfWordCountMap from '../utils/dot-product-of-word-count-map/index.ts';

const documentDistance = async (
  doc1AbsPath: string,
  doc2AbsPath: string,
): Promise<number> => {
  const doc1Map: response = await fileToWordCountMap(doc1AbsPath);
  const doc2Map: response = await fileToWordCountMap(doc2AbsPath);
  const distance = dotProductOfWordCountMap(doc1Map, doc2Map);
  return distance;
};

export default documentDistance;
