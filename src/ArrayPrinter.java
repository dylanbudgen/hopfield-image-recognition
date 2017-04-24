import java.util.ArrayList;

/**
 * ArrayPrinter is a class which prints arrays for debugging. 
 * 
 * ***REMOVED***
 * @version 1.0
 */

public class ArrayPrinter {

	/**
	 * Prints an array of numbers to console window.
	 *
	 * @param array
	 */
	public static void printArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
		System.out.println("");
	}


	/**
	 * Prints an ArrayList of numbers to console window.
	 *
	 * @param array
	 */
	public static void printArrayList(ArrayList<Integer> array) {
		for(int i = 0; i < array.size(); i++) {
			System.out.print(array.get(i));
			System.out.print(" ");
		}
		System.out.println("");
		
	}

}


