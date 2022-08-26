package com.learning;

public class MergeSort {


    // Time complexity: O(n log n)
    // Space complexity: O(n)
    public static void mergeSort(int[] arr, int arrLength) {
        if (arrLength < 2) {
            return;
        }
        int mid = arrLength / 2;
        int[] leftArr = new int[mid];
        int[] rightArr = new int[arrLength - mid];

        for (int i = 0; i < mid; i++) {
            leftArr[i] = arr[i];
        }
        for (int i = mid; i < arrLength ; i++) {
            rightArr[i - mid] = arr[i];
        }

        mergeSort(leftArr, mid);
        mergeSort(rightArr, arrLength - mid);

        merge(arr, leftArr, rightArr, mid, arrLength - mid);
    }

    public static void merge(int[] arr, int[] leftArr, int[] rightArr, int left, int right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left && j < right) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        while (i < left) {
            arr[k++] = leftArr[i++];
        }
        while (j < right) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        int[] actual = { 5, 1, 6, 2, 3, 4 };
        mergeSort(actual, actual.length);
        for (int s : actual) {
            System.out.println(s);
        }
    }

}
