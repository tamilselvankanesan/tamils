package com.success.leet.medium;

public class QuickSort {
	static int[] numbers = new int[] { 10, 11, 8, 9, 1, 5, 12, 7, 4, 25 };

	public static void main(String[] args) {
		new QuickSort().sort();
	}

	void sort(int left, int right) {
		// Divide into two lists
		int i = left, j = right;
		// Get the pivot element from the middle of the list
		int pivot = numbers[left + (right - left) / 2];
		while (i <= j) {
			// If the current value from the left list is smaller than the pivot
			// element then get the next element from the left list
			while (numbers[i] < pivot) {
				i++;
			}
			// If the current value from the right list is larger than the pivot
			// element then get the next element from the right list
			while (numbers[j] > pivot) {
				j--;
			}
			// If we have found a value in the left list which is larger than
			// the pivot element and if we have found a value in the right list
			// which is smaller than the pivot element then we exchange the
			// values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (left < j) {
			sort(left, j);
		}

		if (right > i) {
			sort(i, right);
		}
	}

	private void sort() {
		numbers = new int[] { 10, 11, 8, 9, 1, 5, 12, 7, 4, 25, 3};
		sort(0, numbers.length - 1);
		for (int i : numbers) {
			System.out.print(" " + i);
		}
	}

	void print() {
		for (int i : numbers) {
			System.out.print(" " + i);
		}
		System.out.println("");
	}

	private void exchange(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}
