/** @type {import('ts-jest').JestConfigWithTsJest} */

module.exports = {
  // cacheDirectory: '/temp/jest-cache/',
  preset: 'ts-jest',
  testEnvironment: 'node',
  transform: {
    '\\.(j|t)sx?$': 'ts-jest',
    // '\\.jsx?$': 'babel-jest',
    // '\\.tsx?$': 'ts-jest',
  },
  transformIgnorePatterns: ['<rootDir>/node_modules/(?!nanoid/)'],
  collectCoverage: true,
  coverageReporters: ['text-summary', 'html'],
  coverageDirectory: '<rootDir>/temp/jest-cache/coverage',
  // transformIgnorePatterns: ['/node_modules/(?!(nanoid)/)'],
  // transformIgnorePatterns: ['<rootDir>/node_modules/(?!nanoid)'],
  // transform: {
  //   'node_modules/variables/.+\\.(j|t)sx?$': 'ts-jest',
  // },
  // transformIgnorePatterns: ['node_modules/(?!variables/.*)'],
  // transform: {
  //   '^.+\\.ts?$': 'ts-jest',
  //   '^.+\\.(js|jsx)$': 'babel-jest',
  // },
  // testMatch: ['<rootDir>/peak-finder/first-peak/**/*.test.ts']
};
