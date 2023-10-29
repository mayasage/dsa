# The Peak

An Array has a peak.

An element is a peak if it's greater than all the elements on the left & right.

## Algorithm

1. Go to position n/2:

    ```js
    const cur = array[n / 2]
    const left = array[n / 2 - 1]
    const right = array[n / 2 + 1]

    const hasLeft = typeof left === 'number'
    const hasRight = typeof right === 'number'
    ```

    - If both left & right exist:

      ```js
      if (hasLeft && hasRight) {
        if (cur < left) {} // peak is to the left
        if (cur < right) {} // peak is to the right
        else {} // I am peak
      }
      ```

    - If only left exist:

    ```js
    if (hasLeft) {
      if (cur < left) {} // peak is to the left
      else {} // I am peak
    }
    ```

    - If only right exist:

    ```js
    if (hasRight) {
      if (cur < right) {} // peak is to the right
      else {} // I am peak
    }
    ```

2. Recurse the above till peak is found.

This is "Divide & Conquer".
