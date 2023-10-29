type result = {
  row: number | undefined
  column: number | undefined
  value: number | undefined
}

type findMaxElementInColumnReturns = {
  column: number
  value: number | undefined
  row: number | undefined
}

const findMaxElementInColumn = (
  array: number[][],
  column: number,
): findMaxElementInColumnReturns => {
  const o: findMaxElementInColumnReturns = {
    column,
    value: undefined,
    row: undefined,
  }

  const setO = (value: number, row: number): void => {
    o.value = value
    o.row = row
  }

  for (let i = 0; i < array.length; i += 1) {
    const row = i
    const col = column
    const el = array[row][col]

    const isExist = typeof el === 'number'

    if (isExist && (typeof o.value !== 'number' || el > o.value)) setO(el, row)
  }

  return o
}

type findArgs = {
  array: number[][]
  row?: number
  col?: number
}

type findReturns = {
  row: number
  col: number
  value: number
}

const find = ({
  array,
  row = Math.floor(array.length / 2),
  col = 0,
}: findArgs): findReturns => {
  const maxInColumn = findMaxElementInColumn(array, col)
  if (typeof maxInColumn.row !== 'number') throw new Error('no row')
  if (typeof maxInColumn.value !== 'number') throw new Error('no value')

  row = maxInColumn.row
  const cur = maxInColumn.value
  const left = array[row][col - 1]
  const right = array[row][col + 1]

  const hasLeft = typeof left === 'number'
  const hasRight = typeof right === 'number'

  if (hasLeft && left > cur) {
    col -= 1
    return find({ row, col, array })
  }

  if (hasRight && right > cur) {
    col += 1
    return find({ row, col, array })
  }

  return { row, col, value: array[row][col] }
}

const peakFinder = (array: number[][]): result => {
  let res: result = {
    row: undefined,
    column: undefined,
    value: undefined,
  }

  let o = find({ array })

  res.row = o.row
  res.column = o.col
  res.value = o.value

  return res
}

export default peakFinder
