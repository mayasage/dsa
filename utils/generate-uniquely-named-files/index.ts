import { nanoid } from 'nanoid';
import { stat, writeFile } from 'fs/promises';
import { join } from 'path';

const DEFAULT_TEST_DUMP_PATH = join(__dirname, '../../test-dump');

type input = {
  absDirPath?: string;
  fileData: string[];
};

type output = {
  fileName: string;
  filePath: string;
}[];

const ensureDirExists = async (absDirPath: string): Promise<void> => {
  const stats = await stat(absDirPath);
  if (!stats.isDirectory()) throw new Error(`Invalid dir path: ${absDirPath}`);
};

const generateUniquelyNamedFiles = async ({
  absDirPath = DEFAULT_TEST_DUMP_PATH,
  fileData,
}: input): Promise<output> => {
  ensureDirExists(absDirPath);

  const promises: Promise<void>[] = [];
  const fileMeta: output = [];

  fileData.forEach((data) => {
    const fileName = nanoid();
    const filePath = join(absDirPath, fileName);
    fileMeta.push({ fileName, filePath });
    promises.push(writeFile(filePath, data));
  });

  await Promise.all(promises);

  return fileMeta;
};

export default generateUniquelyNamedFiles;
