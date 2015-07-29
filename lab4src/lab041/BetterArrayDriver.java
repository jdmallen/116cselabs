package lab041;

public class BetterArrayDriver {
	public static void main(String[] args) {
		// add elements to end of list
		BetterArray list = new BetterArray();
		int element1 = 200;
		for (int i = 0; i <= 9; i++) {
			list.insert(i, element1);
			element1 = element1 + 1;

		}

		System.out.print(
				"The numbers 200 thru 209 have been stored into the array,");
		System.out.println("a list of the number from elements 0 thru 9 are ");
		System.out.println(list);

		int element2 = 300;
		for (int i = 10; i <= 19; i++) {
			list.insert(i, element2);
			element2 = element2 + 1;
		}

		System.out.print(
				"The numbers 300 thru 319 have been stored into the array, the array has expanded to size 20 elements;");
		System.out.println(
				"a new list of the number from elements 0 thru 19 are ");
		System.out.println(list);

		list.insert(13, 999);
		System.out.println(
				"after inserting the number 999,a new list of number from elements 0 thru 19 are ");
		System.out.println(list);

		list.delete(15);
		System.out.println(
				"After deleteing elements 15, a new list of numbers from elements 0 thru 19 are ");
		System.out.println(list);

		BetterArray list2 = new BetterArray();
		int[] array = new int[1000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 1000000);
		}
		for (int i = 1; i < array.length; i++) {
			/**
			 * Insert list[i] into a sorted sublist list[0..i-1] so that
			 * list[0..i] is sorted.
			 */
			int currentElement = array[i];
			int k;
			for (k = i - 1; k >= 0 && array[k] > currentElement; k--) {
				array[k + 1] = array[k];

			}
			array[k + 1] = currentElement;
		}

		for (int i = 0; i < array.length; i++) {
			list2.insert(i, array[i]);
		}
		System.out.println(
				"sort the array after filling the randomly-generated integers");
		System.out.println(list2);

	}

}
