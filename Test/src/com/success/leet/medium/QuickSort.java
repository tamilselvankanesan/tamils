package com.success.leet.medium;

public class QuickSort {
  private int number;
  static int[] numbers = new int[]{10, 11, 8, 9, 1, 5, 12, 7, 4};
  public static void main(String[] args) {
    sort( 0, numbers.length-1);
    for(int i : numbers){
      System.out.print(" "+i);
    }
    System.out.println(" \n");
    new QuickSort().vv();
  }
  
  private static void sort(int left, int right){
    int i = left, j = right, middle = left+(right-left)/2;
   
    while(i <= j){
      
      while(numbers[i] < numbers[middle]){
        //loop until the higher number found in the left side
        i++;
      }
      while(numbers[j] > numbers[middle]){
        //loop until a small number found in the right side
        j--;
      }
      if(i<=j){
        //swap the high from left and low from right
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
        i++; //after swap go to next left element
        j--; // after swap go to next right element
      }
    }
    if(left < j){ //if still there are elements in the left side to sort , call the same function again (until i reaches the left side)
      sort(left, j); 
    }
    if(j > right){ //if still there are elements in the right side to sort , call the same function again
      sort(i, right);
    }
  }

/*public class QuickSort  {
  private int[] numbers;
  private int number;
  public static void main(String[] args) {
    new QuickSort().vv();
  }*/
  private void vv(){
    numbers = new int[]{10, 11, 8, 9, 1, 5, 12, 7, 4};
    sort(numbers);
    for(int i : numbers){
      System.out.print(" "+i);
    }
  }
  public void sort(int[] values) {
      // check for empty or null array
      if (values ==null || values.length==0){
          return;
      }
      this.numbers = values;
      number = values.length;
      quicksort(0, number - 1);
  }

  private void quicksort(int low, int high) {
      int i = low, j = high;
      // Get the pivot element from the middle of the list
      int pivot = numbers[low + (high-low)/2];

      // Divide into two lists
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
      if (low < j)
          quicksort(low, j);
      if (i < high)
          quicksort(i, high);
  }

  private void exchange(int i, int j) {
      int temp = numbers[i];
      numbers[i] = numbers[j];
      numbers[j] = temp;
  }
}
