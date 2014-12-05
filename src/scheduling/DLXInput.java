package scheduling;

import DLX.dancingLinks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class DLXInput extends InputAdapter {

	byte[][] matrix;
	Object[] columnNames;
	int numSecondaryColumn;

	@Override
	public void changeInput(String[] equipList, Class[] classes) {
		this.classes = classes;
		this.equipList = equipList;

		// Note getTime will sort classes by start time this is a necessary side effect.
		// Outputter will not work without this.
		calcTimeAndStart();

		// create boolean array for every possible equip time. This is used to create column objects later
		// index 0 corresponds to equipList[0] at time 0, index 2 corresponds to equipList[0] at time 1 and so on.
		boolean[] allPossibleEquipTimes = new boolean[equipList.length * time];

		// this will hold index array of equipment in equipList for each class so that we don't have to calculate it
		// again
		ArrayList<int[]> EquipList = new ArrayList<>();

		// Find entries in allPossibleEquipTimes that will be used used.
		for (Class c : classes) {
			int[] equipForClass = findEquipIndexes(c);
			EquipList.add(equipForClass);

			int currentTime = c.getStart();
			while (currentTime < c.getEnd()) {
				int classTimeIndex = Time.numOfRotationsNeeded(start, currentTime, 15);
				for (int eq : equipForClass) {
					allPossibleEquipTimes[time * eq + classTimeIndex] = true;
				}
				currentTime = Time.addFifteen(currentTime);
			}
		}

		// indexes into classes. primaryColumns[i] + classes.length = index into class array. This is done so that
		// columnNames will be a sorted list. We can then use binary search later.
		int[] primaryColumns = IntStream
				.range(0, classes.length)
				.map(i -> -1 * (classes.length - i))
				.toArray();

		// indexes where true occurs occurs in allPossibleEquipTimes these will be our secondary column listHeaders
		int[] secondaryColumns = IntStream
				.range(0, allPossibleEquipTimes.length)
				.filter(i -> allPossibleEquipTimes[i] == true)
				.map(i -> i)
				.toArray();

		numSecondaryColumn = secondaryColumns.length;

		// read primary and secondary columns into Object[]
		columnNames = new Object[primaryColumns.length + secondaryColumns.length];
		for (int i = 0; i < columnNames.length; i++) {
			if (i < primaryColumns.length) {
				columnNames[i] = primaryColumns[i];
			} else {
				columnNames[i] = secondaryColumns[i - primaryColumns.length];
			}
		}

		// generate rows
		ArrayList<byte[]> rows = new ArrayList<>();
		for (int i = 0; i < classes.length; i++) {
			int[] e = EquipList.get(i); // indexes of equipment in epuipList needed by class[i]
			genRows(e, 0, rows, columnNames, classes[i], i);
		}

		matrix = new byte[rows.size()][columnNames.length];

		// read rows from ArrayList to byte[][]
		for (int i = 0; i < matrix.length; i++) {
			matrix[i] = rows.get(i);
		}
	}

	/**
	 * Generates rows needed for DLX. Each row will contain a marker to tell us which class this represents
	 * and one mapping of this class to equipment.
	 *
	 * @param equipIndexes - indexes into equipList of the pieces class c needs to use;
	 * @param k            - int used for permutation
	 * @param rows         - each new row generated will
	 * @param columnNames  - all available
	 * @param c            - Class object
	 * @param indexClass   - index of c in classes
	 */
	private void genRows(int[] equipIndexes, int k, ArrayList<byte[]> rows, Object[] columnNames, Class c, int indexClass) {
		// Compute all permutations of equipIndexes. This corresponds to all avaialbe arangements of a class onto its
		// needed equipment.
		for (int i = k; i < equipIndexes.length; i++) {
			swap(equipIndexes, i, k); // swap first element with k
			genRows(equipIndexes, k + 1, rows, columnNames, c, indexClass); // permute remaining indexes
			swap(equipIndexes, k, i); // undo swap so next swap can occur
		}
		if (k == equipIndexes.length - 1) { // end of a branch i.e. nothing left in list to permute.
			byte[] temp = new byte[columnNames.length];
			// binary search to find class marker in columnNames. Add maker to same position in row.
			int r = rank(-1 * (classes.length - indexClass), columnNames);
			temp[r] = 1;

			int startTimeIndex = Time.numOfRotationsNeeded(start, c.getStart(), 15);

			for (int i = 0; i < equipIndexes.length; i++) {
				// 15 min rotations
				if (c.getRotation() == 15) {
					r = rank(time * equipIndexes[i] + startTimeIndex + i, columnNames);
					temp[r] = 1;
				} else { // 30 min rotations
					r = rank(time * equipIndexes[i] + startTimeIndex + i * 2, columnNames);
					temp[r] = 1;
					r = rank(time * equipIndexes[i] + startTimeIndex + i * 2 + 1, columnNames);
					temp[r] = 1;
				}
			}
			rows.add(temp);
		}
	}

	private void swap(int[] arr, int i, int k) {
		int temp = arr[i];
		arr[i] = arr[k];
		arr[k] = temp;
	}

	/**
	 * Searches for the integer key in the sorted Object array a[].
	 * From: http://algs4.cs.princeton.edu/11model/BinarySearch.java.html Licence: GPLv3
	 *
	 * @param key the search key
	 * @param a   the array of integers, must be sorted in ascending order
	 * @return index of key in array a[] if present; -1 if not present
	 */
	public static int rank(int key, Object[] a) {
		int lo = 0;
		int hi = a.length - 1;
		while (lo <= hi) {
			// Key is in a[lo..hi] or not present.
			int mid = lo + (hi - lo) / 2;
			if (key < (int) a[mid]) hi = mid - 1;
			else if (key > (int) a[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
	}


	@Override
	public int[][] schedule() {

		int[][] schedule = new int[time][equipList.length];

		for (int[] row : schedule) {
			Arrays.fill(row, -1);
		}

		ArrayList<Object[]> sol = dancingLinks.firstSolution(matrix, columnNames, numSecondaryColumn);
		for (Object[] o : sol) {
			int classIndex = (int) o[0] + classes.length;

			if (classes[classIndex].getRotation() == 30) {
				for (int i = 1; i < o.length; i++) {
					int k = (int) o[i];
					int row = k % time; // time
					int col = k / time; // equip

					//if(false);
					if (row > 0 && schedule[row - 1][col] == classIndex) ; // do nothing
					else schedule[row][col] = classIndex;
				}
			} else
				for (int i = 1; i < o.length; i++) {
					int k = (int) o[i];
					schedule[k % time][k / time] = classIndex;
				}
		}

		//printSchedule(schedule);

		return schedule;
	}

	private void printSchedule(int[][] schedule) {
		System.out.println("===================== Schedule");

		for (int i = 0; i < schedule.length; i++) {
			System.out.print("[");
			for (int j = 0; j < schedule[0].length; j++) {
				System.out.print(String.format(" %2s ", schedule[i][j]));
			}
			System.out.println("]");
		}
	}

	public static void main(String[] args) {
		String[] allEquip = new String[]{"a", "b", "c", "d"};
		String[] e1 = new String[]{"a", "b", "c", "d"};
		String[] e2 = new String[]{"a", "b"};
		String[] e3 = new String[]{"c", "d"};
		Class c1 = new Class("c1", "t1", 900, 1000, 15, e1);
		Class c2 = new Class("c2", "t1", 900, 1000, 15, e1);
		Class c3 = new Class("c3", "t1", 900, 1000, 30, e2);
		Class c4 = new Class("c4", "t1", 900, 1000, 30, e3);
		Class[] classes1 = new Class[]{c1, c2, c3, c4};
		DLXInput dlxInput = new DLXInput();
		dlxInput.changeInput(allEquip, classes1);
		dlxInput.schedule();

		ArrayList<Object[]> sol = dancingLinks.firstSolution(dlxInput.matrix, dlxInput.columnNames, dlxInput.numSecondaryColumn);

		for (Object[] o : sol) {
			System.out.println("class " + dlxInput.classes[(int) o[0] + dlxInput.classes.length].getName());
			for (int i = 1; i < o.length; i++) {
				int s = dlxInput.start;
				int k = 0;
				while (k < (int) o[i] % dlxInput.time) {
					s = Time.addFifteen(s);
					k++;
				}
				System.out.println("on " + dlxInput.equipList[((int) o[i]) / dlxInput.time] + " at " + s);
			}
		}
	}
}
