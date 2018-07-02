package radixsort;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class RadixSort {

    public static void main(String[] args) throws FileNotFoundException {

        sort();
    }

    public static void sort() throws FileNotFoundException {

        long assignment = 0;
        long comparison = 0;
        long arithmetic = 0;

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

        List<Integer>[] array1 = new List[10]; //Create two arrays which store list
        List<Integer>[] array2 = new List[10];
        int max = 0; //For finding the maximum number

        for (int i = 0; i < array1.length; i++) {
            List<Integer> list1 = new ArrayList<>(); //Initializing ArrayList inside of each element in the array
            array1[i] = list1;
        }
        for (int i = 0; i < array2.length; i++) {
            List<Integer> list2 = new ArrayList<>(); //Initializing ArrayList inside of each element in the array
            array2[i] = list2;
        }

        //Calculate the running time in millisecond
        long startTime = System.currentTimeMillis();
        long total = 0;
        for (int i = 0; i < 10000000; i++) {
            total += i;
        }

        //First sort iteration will start right after numbers are inputted by users, maximum number will be found
        for (int i = 0; i < totnum; i++) {
            comparison++;
            int number = generated[i];
            int getmod = number % 10;
            assignment += 2;
            arithmetic++;
            array1[getmod].add(number);
            if (number > max) {
                max = number;
                assignment++;
            }
            comparison++;
            arithmetic++;
            assignment++;
        }

        //Setting another variables as pointers to those arrays
        List<Integer>[] source = array1;
        List<Integer>[] destination = array2;

        String strmax = Integer.toString(max);

        int modulo = 10; //For getting the last digit of the number based on the sort iteration
        int divid = 10;
        assignment += 5;

        for (int i = 1; i <= strmax.length() - 1; i++) {
            comparison++; //comparison of for loop
            arithmetic++; //strmax.length()-1

            for (int j = 0; j < source.length; j++) {
                comparison++;
                while (!source[j].isEmpty()) {
                    comparison++;
                    int getnum = source[j].get(0);
                    assignment++;
                    int dividnum = getnum / divid;
                    assignment++;
                    arithmetic++;
                    int lastnum = dividnum % modulo; //Getting the digit which is needed for comparison
                    assignment++;
                    arithmetic++;
                    source[j].remove(0); //Remove the number from source then insert it to destination with proper place
                    destination[lastnum].add(getnum);
                }
                arithmetic++;//j++
                assignment++;//j++

            }

            divid *= 10;
            arithmetic++;
            assignment++;

            //Setting source and destination after each sort iteration
            if (i % 2 == 0) {
                source = array1;
                destination = array2;
            } else {
                source = array2;
                destination = array1;
            }
            assignment += 2;
            comparison++;
            arithmetic++; //i++
            assignment++; //i++
        }

        //Setting the destination after the sort has been completed
        if ((strmax.length() - 1) % 2 == 0) {
            source = array2;
            destination = array1;
        } else {
            source = array1;
            destination = array2;
        }
        comparison++;
        assignment += 2;
        arithmetic += 2;//minus and modulus

        //sort end
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        for (int i = 0; i < destination.length; i++) {
            while (!destination[i].isEmpty()) {
                System.out.println(destination[i].get(0));
                destination[i].remove(0);
            }
        }

        System.out.println();
        System.out.println("The numbers of comparison is " + comparison);
        System.out.println("The numbers of assignment is " + assignment);
        System.out.println("The numbers of arithmetic is " + arithmetic);
        System.out.println(elapsedTime + " milliseconds");

        PrintStream out = new PrintStream(new FileOutputStream("DataSet.txt"));
        for (int i = 0; i < generated.length; i++) {
            out.println(generated[i]);
        }
        out.close();

    }

}
