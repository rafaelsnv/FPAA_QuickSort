import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

public class QuickSortInsertionSort {
	private static long[] data;
	private static int compara;
	private static int move;
	private int len;
	private static int M;

	public QuickSortInsertionSort(int max) {
		data = new long[max];
		len = 0;
		M = 10;
		compara = 0;
		move = 0;
	}

	public void insert(long value) {
		data[len] = value;
		len++;
	}

	public void display() {
		System.out.print("Data: ");

		for (int j = 0; j < len; j++) {
			System.out.print(data[j] + " ");
		}

		System.out.println("");
	}

	public void quickSort() throws IOException {
		Tempo tempo = new Tempo();
		recQuickSort(0, len - 1);
		Log.logQuickSortInsertion(data.length, M, tempo, compara, move);
	}

	public void recQuickSort(int left, int right) throws IOException {
		int size = right - left + 1;

		if (size < M) {
			insertionSort(left, right);
		} else {
			long median = medianOf3(left, right);
			int partition = partitionIt(left, right, median);
			recQuickSort(left, partition - 1);
			recQuickSort(partition + 1, right);
		}
	}

	public long medianOf3(int left, int right) {
		int center = (left + right) / 2;
		if (data[left] > data[center]) {
			swap(left, center);
		}

		if (data[left] > data[right]) {
			swap(left, right);
		}

		if (data[center] > data[right]) {
			swap(center, right);
		}

		swap(center, right - 1);
		compara++;
		return data[right - 1];
	}

	public void swap(int d1, int d2) {
		long temp = data[d1];
		data[d1] = data[d2];
		data[d2] = temp;
		move++;
	}

	public int partitionIt(int left, int right, long pivot) throws IOException {
		int leftPtr = left;
		int rightPtr = right - 1;

		while (true) {
			compara++;
			while (data[++leftPtr] < pivot)
				;
			while (data[--rightPtr] > pivot)
				;
			if (leftPtr >= rightPtr)
				break;
			else
				swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right - 1);
		return leftPtr;
	}

	public void insertionSort(int left, int right) {
		int in, out;

		for (out = left + 1; out <= right; out++) {
			long temp = data[out];
			in = out;
			while (in > left && data[in - 1] >= temp) {
				data[in] = data[in - 1];
				--in;
				move++;
			}
			data[in] = temp;
			compara++;
		}
	}

	public static void main(String[] args) throws IOException {

		try (Scanner input = new Scanner(new File("C:\\in_100000.txt"))) {
			QuickSortInsertionSort arr = new QuickSortInsertionSort(100000);

			while (input.hasNextLine()) {
				String line = input.nextLine();

				Long[] content = Stream.of(line.split("\n")).map(Long::parseLong).toArray(Long[]::new);

				for (int i = 0; i < content.length; i++) {
					arr.insert(content[i]);
				}
			}

			arr.display();
			arr.quickSort();
			arr.display();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}