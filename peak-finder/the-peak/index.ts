type result = {
  position: number | undefined
  value: number | undefined
}

const peakFinder = (arr: number[]) => {
  let res: result = { position: undefined, value: undefined }

  const find = ({ start = 0, end = arr.length }): result => {
    const mid = Math.floor((start + end) / 2)

    const cur = arr[mid]
    const left = arr[mid - 1]
    const right = arr[mid + 1]

    const hasLeft = typeof left === 'number'
    const hasRight = typeof right === 'number'

    const ltLeft = () => cur < left
    const ltRight = () => cur < right
    const setResult = () => (res = { position: mid, value: cur })

    if (hasLeft && hasRight) {
      if (ltLeft()) return find({ start, end: mid })
      if (ltRight()) return find({ start: mid, end })
      return setResult()
    }

    if (hasLeft) {
      if (ltLeft()) return find({ start, end: mid })
      return setResult()
    }

    if (hasRight) {
      if (ltRight()) return find({ start: mid, end })
      return setResult()
    }

    return setResult()
  }

  find({})

  return res
}

export default peakFinder
