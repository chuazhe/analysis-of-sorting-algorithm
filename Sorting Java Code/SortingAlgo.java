package insertionsort; //if for insertion sort, package insertionsort; if for merge sort, package mergesort;

public abstract class SortingAlgo {

    public abstract void sort(Comparable[] array);

    public void print(Comparable[] array) {

        int n = array.length;
        for (int i = 0; i < n; ++i) {
            System.out.println(array[i] + " ");
        }

        System.out.println();
    }

}
