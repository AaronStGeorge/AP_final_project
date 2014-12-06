package scheduling;

/*
 * This is the interface for the input adapters.
 * It is created as an abstract class so that it can have some shared methods
 * that all adapters can use.
 * created by Matt Detrick
 * 12/2/14
 */
public abstract class InputAdapter
{

	String[] equipList;
	Class[] classes;
	int start;
	int time;

	public abstract void changeInput(String[] equipList, Class[] classes);

	public abstract int[][] schedule();

	/*
	 * this calculates how many 15 min slots are needed based on the start
     * and end times found above
     */
	protected void calcTimeAndStart() {
		/*
		 * The sorting is used to find the earliest start time and latest endtime of
		 * the classes
		 */
		sortClassesEnd();
		int end = classes[classes.length - 1].getEnd();
		sortClassesStart();
		start = classes[0].getStart();
        /*
         * this calculates how many 15 min slots are needed based on the start
         * and end times found above
         */
		time = Time.numOfRotationsNeeded(start, end, 15);
	}

	/*
	 * uses a simple bubble sort to sort the classes as the list will
	 * be small
	 */
	protected void sortClassesStart() {
		int c, d, n = classes.length;
		Class swap;
		for (c = 0; c < (n - 1); c++) {
			for (d = 0; d < n - c - 1; d++) {
				if (classes[d].getStart() > classes[d + 1].getStart()) {
					swap = classes[d];
					classes[d] = classes[d + 1];
					classes[d + 1] = swap;
				}
			}
		}
	}

	/*
	 * uses a simple bubble sort to sort the classes as the list will
	 * be small
	 */
	protected void sortClassesEnd() {
		int c, d, n = classes.length;
		Class swap;
		for (c = 0; c < (n - 1); c++) {
			for (d = 0; d < n - c - 1; d++) {
				if (classes[d].getEnd() > classes[d + 1].getEnd()) {
					swap = classes[d];
					classes[d] = classes[d + 1];
					classes[d + 1] = swap;
				}
			}
		}
	}

	/*
     * This helper method find the indexes that correspond to the equipment that the class
     * needs in the equipList array
     */
	protected int[] findEquipIndexes(Class c) {
		String[] equipment = c.getEquipment();
		int[] indexes = new int[equipment.length];
		for (int i = 0; i < equipment.length; i++) {
			int j = 0;
			while (!equipment[i].equals(equipList[j]))
				j++;
			indexes[i] = j;
		}
		return indexes;
	}
}
