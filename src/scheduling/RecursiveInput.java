package scheduling;
/*
 * This class wraps the recursiveSolution and formats the input from the GUI into 
 * what the recursiveSolution needs.
 * created by Matt Detrick
 * 12/2/14
 * 
 */
public class RecursiveInput extends InputAdapter
{
	int[][][] possibilities;
    int equipListSize;

	@Override
	public void changeInput(String[] equipList, Class[] classes) {
		this.classes = classes;
		this.equipList = equipList;
		equipListSize = equipList.length;

        /*
         * this calculates how many 15 min slots are needed based on the start
         * and end times found above
         */
        calcTimeAndStart();

        possibilities = new int[time][classes.length][];
        /*
         * This adds the classes to the three-dimensional array
         */
        addClasses();
	}


    @Override
	public int[][] schedule() {
		/*
		 * Here is where the RecursiveSolution is called and the solution returned
		 */
		RecursiveSolution solver = new RecursiveSolution(possibilities, classes, time, equipListSize);
		return solver.getSchedule();
	}
    /*
     * This adds in the classes. Each class is represented as a column and each
     * possibilities[a][b] holds the list of equipment that that class wishes to use for all
     * of the time slots of that class
     */
    private void addClasses() {
        initializeEquipLists();
        int startTime = classes[0].getStart();
        int[] indexes = null;
        int time, start;
        for (int i = 0; i < classes.length; i++) {
            indexes = findEquipIndexes(classes[i]);
            time = Time.numOfRotationsNeeded(classes[i].getStart(), classes[i].getEnd(), 15);
            start = Time.numOfRotationsNeeded(startTime, classes[i].getStart(), 15);
            for (int j = 0; j < time; j++) {
                if (classes[i].getRotation() == 30 && j % 2 == 1) {
                    //do nothing because it has a 30 min rotation
                } else
                    possibilities[(start + j)][i] = indexes;
            }
        }
    }
    /*
     * This simply initializes the three dimensional array to have arrays of all 0's
     */
    private void initializeEquipLists() {
        for (int i = 0; i < possibilities.length; i++) {
            for (int j = 0; j < possibilities[i].length; j++) {

                possibilities[i][j] = new int[0];
            }
        }
    }


    /*
     * This is used to print the possibilities matrix for testing/debugging
     */
    /*
    private void printPossibilities() {
        System.out.println("===================== Possibilities");
        int maxSize = 0;
        // find max size of equipment list
        for (int i = 0; i < possibilities.length; i++) {
            for (int j = 0; j < possibilities[i].length; j++) {
                if (possibilities[i][j].length > maxSize) {
                    maxSize = possibilities[i][j].length;
                }
            }
        }

        for (int i = 0; i < possibilities.length; i++) {
            System.out.print("[");
            for (int j = 0; j < possibilities[i].length; j++) {
                System.out.print("[");
                int[] indexes = possibilities[i][j];
                int count = 0;
                for (int k : indexes) {
                    System.out.print(k + " ");
                    count++;
                }
                while (count < maxSize) {
                    System.out.print("  ");
                    count++;
                }
                System.out.print("]");
            }
            System.out.println("]");
        }
    }*/

}
