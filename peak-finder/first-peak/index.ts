type result = { position: undefined | number; value: undefined | number }

const peakFinder = (arr: number[]): result => {
  let result: result = { position: undefined, value: undefined }

  for (let i = 0; i < arr.length; i += 1) {
    // Prepare
    const left = arr[i - 1]
    const right = arr[i + 1]
    const cur = arr[i]

    const hasLeft = typeof left === 'number'
    const hasRight = typeof right === 'number'

    const inMiddle = hasLeft && hasRight
    const atFirst = !inMiddle && hasRight
    const atLast = !inMiddle && hasLeft
    const isTheOnlyOne = !inMiddle && !atFirst && !atLast && arr.length === 1

    const gteLeft = () => cur >= left
    const gteRight = () => cur >= right
    const setResult = () => (result = { position: i, value: cur })

    // Attack
    if (inMiddle) {
      if (gteLeft() && gteRight()) {
        setResult()
        break
      }
    } else if (atFirst) {
      if (gteRight()) {
        setResult()
        break
      }
    } else if (atLast) {
      if (gteLeft()) {
        setResult()
        break
      }
    } else if (isTheOnlyOne) {
      setResult()
      break
    }
  }

  return result
}

export default peakFinder
