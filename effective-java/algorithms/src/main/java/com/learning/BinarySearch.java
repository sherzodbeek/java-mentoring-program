package com.learning;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 7, 9, 10, 12, 15, 18, 22};

        System.out.println("Binary Search Iterative");
        int result = binarySearchIterative(arr, 7);
        if (result == -1) {
            System.out.println("Element not found!");
        } else {
            System.out.println("Element found at index: " + result);
        }


        System.out.println("Binary Search Recursive");
        int result2 = binarySearchRecursive(arr, 0, arr.length - 1, 10);
        if (result2 == -1) {
            System.out.println("Element not found!");
        } else {
            System.out.println("Element found at index: " + result2);
        }
    }


    public static int binarySearchIterative(int[] arr, int number) {
        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == number) {
                return mid;
            }

            if (arr[mid] < number) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int left, int right, int number) {
        // Time Complexity: O(log n)
        // Space Complexity: O(1)
        if (right >= left && left <= arr.length - 1) {
            int mid = left + (right - left) / 2;
            // element present in middle
            if (arr[mid] == number) {
                return mid;
            }
            // element smaller than middle element of array, element can be present left part of array
            if (arr[mid] > number) {
                return binarySearchRecursive(arr, left, mid - 1, number);
            }

            // element bigger than middle element of array, element can be present right part of array
            return binarySearchRecursive(arr, mid + 1, right, number);
        }
        return -1;
    }
}
