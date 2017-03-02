package com.success.amazon;

import java.util.Arrays;

public class SwapWithoutTempVariable {
	public static void main(String args[]) {
        int[] data = { 5, 6 };
        swapUsingNumericOperators(data);
    }
 
    /**
     * @param data
     *            - sample size 2 integer array. This method will swap data[0]
     *            and data[1] without using extra variable using numeric
     *            operation.
     *
     *            Formula: a = b - a; b = b - a; a = a + b
     */
    private static void swapUsingNumericOperators(int[] data) {
        if (data == null || data.length > 2) {
            return;
        }
        System.out.println("\nIN PLACE SWAP USING NUMERIC OPERATOR");
        System.out.println("Input:" + Arrays.toString(data));
 
        data[0] = data[1] - data[0];
        data[1] = data[1] - data[0];
        data[0] = data[0] + data[1];
 
        System.out.println("Swapped:" + Arrays.toString(data));
 
    }
}
