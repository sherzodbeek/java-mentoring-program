package com.learning.benchmark;

import com.learning.BinarySearch;
import com.learning.InsertionSort;
import com.learning.MergeSort;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class BenchmarkTests {

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public int testBinarySearchIterative() {
        int[] arr= {1, 2, 5, 7, 9, 10, 12, 15, 18, 22};
        return BinarySearch.binarySearchIterative(arr, 10);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public int testBinarySearchRecursive() {
        int[] arr = {1, 2, 5, 7, 9, 10, 12, 15, 18, 22};
        return BinarySearch.binarySearchRecursive(arr, 0, arr.length - 1, 10);
    }


    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void testMergeSort() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        MergeSort.mergeSort(actual, actual.length);
    }

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void testInsertionSort() {
        int[] actual = {5, 1, 6, 2, 3, 4};
        InsertionSort.sort(actual);
    }
}
