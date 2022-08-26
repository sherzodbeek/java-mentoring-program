package com.learning;

import com.sun.source.tree.BinaryTree;

public class InsertionSort {

    // Time complexity: O(N^2)
    // Space complexity: O(1)
   public static void sort(int[] arr) {
       int lengthArr = arr.length;
       for (int i = 1; i < lengthArr; i++) {
           int key = arr[i];
           int j = i - 1;

           while (j >= 0 && arr[j] > key) {
               arr[j + 1] = arr[j];
               j = j -1;
           }
           arr[j + 1] = key;
       }
   }

    public static void main(String[] args) {

        int[] arr = { 12, 11, 13, 5, 6 };
        sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
