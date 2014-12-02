package scheduling;

import java.util.Arrays;

/**
 * Created by aaron on 12/1/14.
 */
public class RecursiveSolution {

    int[][][] possibilities;
    int[][] schedule;
    Class[] classes;

    public RecursiveSolution( int[][][] possibilities, Class[] classes, int time, int equipListSize)
    {
    	this.possibilities = possibilities;
    	this.classes = classes;
    	schedule = new int[time][equipListSize];
        for(int[] row: schedule)
        {
            Arrays.fill(row,-1);
        }
        scheduleClasses();
        printSchedule();
    }
    public int[][] getSchedule()
    {
    	return schedule;
    }
    private boolean scheduleClasses() {
        if (scheduleClasses(0)) {
            System.out.println("Scheduling was a success!");
            return true;
        } else {
            System.out.println("Scheduling was a failure!");
            return false;
        }
    }

    private boolean scheduleClasses(int pos) {
        if (pastEndOfGrid(pos)) return true;

        int[] ij = position(pos);
        int i = ij[0]; // row - time
        int j = ij[1]; // column - class

        // if nothing is trying to be scheduled skip
        if (possibilities[i][j].length == 0)
            return scheduleClasses(pos + 1);

        // recursive backtracking
        for (int k : possibilities[i][j]) {
            // i = time, j = class, k = equipment
            if (schedule[i][k] == -1) {
                // check if cell above exists
                if (i > 0) {
                    // if there is nothing scheduled above or the class above has 15 min rotation
                    if (schedule[i - 1][k] == -1 || classes[schedule[i - 1][k]].getRotation() == 15) {

                        schedule[i][k] = j;
                        if (gridValid(schedule)) {
                            if (scheduleClasses(pos + 1)) {
                                return true;
                            }
                        }
                    }
                } else {
                    schedule[i][k] = j;
                    if (gridValid(schedule)) {
                        if (scheduleClasses(pos + 1)) {
                            return true;
                        }
                    }

                }
                schedule[i][k] = -1;
            }
        }

        //all possibilities exhausted, solution does not exist
        return false;
    }

    // returns row and column in possibilities matrix from flat matrix index pos
    private int[] position(int pos) {
        int i = pos / possibilities[0].length;
        int j = pos % possibilities[0].length;
        return new int[]{i, j};
    }

    private boolean pastEndOfGrid(int pos) {
        try {
            int[] ij = position(pos);
            int i = ij[0];
            int j = ij[1];
            int[] _ = possibilities[i][j];
            return false;
        } catch (ArrayIndexOutOfBoundsException a) {
            return true;
        }
    }


    // checks for duplicates except for -1. You can repeat -1 as much as you want.
    private static boolean checkForDuplicates(int[] row) {
        for (int i = 0; i < row.length; i++) {
            int sum = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == row[i] && row[i] != -1) {
                    sum++;
                }
            }
            if (sum > 1) {
                return false;
            }
        }
        return true;
    }


    

    private static boolean gridValid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (!checkForDuplicates(grid[i])) {
                return false;
            }
        }
        int[] testArray = new int[grid.length];
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                testArray[i] = grid[i][j];
            }
            if (!checkForDuplicates(testArray)) {
                return false;
            }
        }
        return true;
    }
    private void printSchedule() {
        System.out.println("===================== Schedule");
        for (int i = 0 ; i < schedule.length; i++){
            System.out.print("[");
            for (int j = 0; j < schedule[0].length; j++) {
                System.out.print(String.format("[%-2s]",schedule[i][j]));
            }
            System.out.println("]");
        }
    }
}
