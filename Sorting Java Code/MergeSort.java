package mergesort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class MergeSort extends SortingAlgo {

    static long assignment = 0;
    static long comparison = 0;
    static long arithmetic = 0;

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public void merge(Comparable[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        assignment += 2;
        arithmetic += 3;//2 subtract, 1 addition

        /* Create temp arrays */
        Comparable[] L = new Comparable[n1];
        Comparable[] R = new Comparable[n2];

        assignment += 2;

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            comparison++;
            L[i] = arr[l + i];
            assignment += 2;//i and L[i]
            arithmetic += 2; //++i and l + i
        }

        for (int j = 0; j < n2; ++j) {
            comparison++;
            R[j] = arr[m + 1 + j];
            assignment += 2; //R[j] and j
            arithmetic += 3;
        }

        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        assignment += 3;

        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                assignment++;
                i++;
                arithmetic++;
                assignment++;
            } else {
                arr[k] = R[j];
                assignment++;
                j++;
                arithmetic++;
                assignment++;
            }
            k++;
            arithmetic++;
            assignment++;
            comparison += 3; //2 checks for while and 1 for if
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
            comparison++;
            arithmetic += 2;
            assignment += 3;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
            comparison++;
            arithmetic += 2;
            assignment += 3;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public void sort(Comparable[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
            assignment++;
            arithmetic += 3; //2 additon and 1 division
        }
        comparison++;
    }

    public void sort(Comparable[] list) {
        sort(list, 0, list.length - 1);
        arithmetic++;//minus
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many numbers do you want to sort?: ");
        int totnum = scanner.nextInt();
        Random rand = new Random();

        Integer[] generated = new Integer[totnum];
        for (int i = 0; i < generated.length; i++) {
            generated[i] = rand.nextInt(1000000) + 1;
        }

        int temp = 0;
        System.out.println("Do you want to sort it before executing the sorting algorithm?");
        System.out.println("1. Yes, in descending order");
        System.out.println("2. No");
        System.out.println("3. Yes, in ascending order");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                for (int i = 0; i < generated.length; i++) {
                    for (int j = i + 1; j < generated.length; j++) {
                        if (generated[i] < generated[j]) {
                            temp = generated[i];
                            generated[i] = generated[j];
                            generated[j] = temp;
                        }
                    }
                }
                break;

            case 2:
                break;
            case 3:
                for (int i = 0; i < generated.length; i++) {
                    for (int j = i + 1; j < generated.length; j++) {
                        if (generated[i] > generated[j]) {
                            temp = generated[i];
                            generated[i] = generated[j];
                            generated[j] = temp;
                        }
                    }
                }
                break;
        }

        Integer[] dataset = new Integer[totnum];
        for (int i = 0; i < generated.length; i++) {
            dataset[i] = generated[i];
        }

        MergeSort ms = new MergeSort();
        
        //Calculate the running time in millisecond
        long startTime = System.currentTimeMillis();
        long total = 0;
        for (int i = 0; i < 10000000; i++) {
            total += i;
        }

        ms.sort(generated);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        ms.print(generated);
        System.out.println();
        System.out.println("The numbers of comparison is " + comparison);
        System.out.println("The numbers of assignment is " + assignment);
        System.out.println("The numbers of arithmetic is " + arithmetic);
        System.out.println(elapsedTime + " milliseconds");
        PrintStream out = new PrintStream(new FileOutputStream("DataSet.txt"));
        for (int i = 0; i < generated.length; i++) {
            out.println(dataset[i]);
        }
        out.close();

    }
}
