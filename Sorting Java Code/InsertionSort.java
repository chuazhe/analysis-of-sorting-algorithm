package insertionsort;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class InsertionSort extends SortingAlgo {

    static long comparison = 0;
    static long assignment = 0;
    static long arithmetic = 0;

    public void sort(Comparable[] list) {

        if (list == null) {
            return;
        }
        comparison++;

        int n = list.length;
        assignment++;

        for (int i = 1; i < n; ++i) {
            comparison++;
            Comparable key = list[i];
            assignment++;
            int j = i - 1;
            assignment++;
            arithmetic++; //subtraction

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && list[j].compareTo(key) > 0) {
                comparison += 2;
                list[j + 1] = list[j];
                j = j - 1;
                assignment += 2;
                arithmetic += 2; //j+1,j-1
            }

            arithmetic++;//++i in for loop
            assignment++;//assignment of i
            list[j + 1] = key;
            arithmetic++;//j+1
            assignment++;

        }
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
        System.out.println("1. Yes, in descending order"); // Worst Case
        System.out.println("2. No"); //Average Case
        System.out.println("3. Yes, in ascending order"); //Best Case
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

        InsertionSort is = new InsertionSort();

        //Calculate the running time in millisecond
        long startTime = System.currentTimeMillis();
        long total = 0;
        for (int i = 0; i < 10000000; i++) {
            total += i;
        }
        
        is.sort(generated);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        is.print(generated);
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
