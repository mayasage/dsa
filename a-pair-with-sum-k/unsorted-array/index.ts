// [ 10, 16, 1, 0, 0, 9, 7, 39 ]

const buildMap = (nums: number[]) => {
  const map: { [key: string]: { count: number; index: number[] } } = {};
  nums.forEach((n, i) => {
    if (map[n]) {
      map[n].count += 1;
      map[n].index.push(i);
    } else {
      map[n] = {
        count: 1,
        index: [i],
      };
    }
  });

  return map;
};

const getAPair = (nums: number[], sum: number) => {
  const map = buildMap(nums);

  for (let i = 0; i < nums.length; i += 1) {
    const part1 = nums[i];
    const part2 = sum - part1;
    if (map[part2]) return { [part1]: map[part1], [part2]: map[part2] };
  }

  return {};
};

export default getAPair;
