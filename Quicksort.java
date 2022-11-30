import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Quicksort {
    private static int compara;
    private static int move;
    private static int[] data;
    private static int n;

    public Quicksort(int max) {
        data = new int[max];
        n = data.length;
        compara = 0;
        move = 0;
    }

    public int[] getData() {
        return data;
    }

    public int getN() {
        return n;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        move++;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        compara++;
        return (i + 1);
    }

    static void quickSort(int[] arr, int low, int high) throws IOException {
        Tempo tempo = new Tempo();

        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }

        Log.logQuickSort(Quicksort.data.length, tempo, compara, move);
    }

    static void printArray(int[] arr, int size) {
        for (int i = 0; i < size; i++)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(new File("C:\\in_1000000.txt"))) {
            Quicksort arr = new Quicksort(1000000);

            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] content = line.split("\n");

                for (int i = 0; i < content.length; i++) {
                    arr.getData()[i] = Integer.parseInt(content[i]);
                }

                quickSort(arr.getData(), 0, arr.getN() - 1);
            }

            System.out.println("Sorted array: ");
            printArray(arr.getData(), arr.getN());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// This code is contributed by Ayush Choudhary - Geeks for Geeks
