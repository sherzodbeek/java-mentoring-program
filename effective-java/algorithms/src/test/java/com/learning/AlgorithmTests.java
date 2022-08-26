package com.learning;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlgorithmTests {

    static int[] numbers = {1, 2, 5, 7, 9, 10, 12, 15, 18, 22};

    @Test
    void shouldReturnElementIndexWithBinarySearchIterative() {
        int result = BinarySearch.binarySearchIterative(numbers, 5);

        Assertions.assertEquals(2, result);
    }

    @Test
    void shouldReturnMinusOneIfElementNotFoundWithBinarySearchIterative() {
        int result = BinarySearch.binarySearchIterative(numbers, 8);

        Assertions.assertEquals(-1, result);
    }

    @Test
    void shouldReturnElementIndexWithBinarySearchRecursive() {
        int result = BinarySearch.binarySearchRecursive(numbers, 0, numbers.length-1, 7);

        Assertions.assertEquals(3, result);
    }

    @Test
    void shouldReturnMinusOneIfElementNotFoundWithBinarySearchRecursive() {
        int result = BinarySearch.binarySearchRecursive(numbers, 0, numbers.length-1, 11);

        Assertions.assertEquals(-1, result);
    }

    @Test
    void shouldSortArrayWithInsertionSort() {
        int[] actual = {8, 5, 6, 1};
        int[] expected = {1, 5, 6, 8};

        InsertionSort.sort(actual);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSortArrayWithMergeSort() {
        int[] actual = {8, 5, 6, 1};
        int[] expected = {1, 5, 6, 8};

        MergeSort.mergeSort(actual, actual.length);

        Assertions.assertArrayEquals(expected, actual);
    }
}
