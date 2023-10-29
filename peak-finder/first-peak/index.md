# First Peak

An Array has any number of peaks.

An element at position i is a peak, iff:

- If both [i - 1] & [i + 1] exist:

    ```js
    array[i] >= array[i - 1] &&
    array[i] >= array[i + 1]
    ```

- If [i - 1] doesn't exist:

    ```js
    array[i] >= array [i + 1]
    ```

- If [i + 1] doesn't exist:

    ```js
    array[i] >= array[i - 1]
    ```

- If neither [i + 1], nor [i - 1] exist:

    ```js
    array[i]
    ```
