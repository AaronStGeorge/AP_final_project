package scheduling;

import java.util.ArrayList;
import java.util.Arrays;

public class Schedule {
    /*
     * This creates the4-dimensional array but is dynamic
     */
    /*
	 *HashMap<Integer, List<String>> equipment;
	 *HashMap<Integer,HashMap<Integer, List<String>>> times;
	*/
    int[][][] possibilities;
    int[][] schedule;
    String[] equipList;
    Class[] classes;
    private int maxLength;

    /*
     * The constructor needs the list of classes that need to be scheduled and also the equipment list
     * from EquipmentCollection.
     */
    public Schedule(String[] equipList, Class[] classes) {
        this.equipList = equipList;
        this.classes = classes;
        sortClassesEnd();
        int end = classes[classes.length - 1].getEnd();
        sortClassesStart();
        int start = classes[0].getStart();
        int time = Time.numOfRotationsNeeded(start, end, 15);
        possibilities = new int[time][classes.length][];
        schedule = new int[time][equipList.length];
        for(int[] row: schedule){
            Arrays.fill(row,-1);
        }
        addClasses();
        printPossibilities();
        scheduleClasses();
        printSchedule();
    }

    // returns row and column in possibilities matrix from flat matrix index pos
    public int[] position(int pos) {
        int i = pos / possibilities[0].length;
        int j = pos % possibilities[0].length;
        return new int[]{i, j};
    }

    public boolean pastEndOfGrid(int pos) {
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

    public static boolean gridValid(int[][] grid) {
        for (int i = 0; i < grid.length; i ++){
            if (!checkForDuplicates(grid[i])){
                return false;
            }
        }
        int[] testArray = new int[grid.length];
        for (int j = 0; j < grid[0].length; j ++){
            for (int i = 0; i < grid.length; i ++){
               testArray[i] = grid[i][j];
            }
            if (!checkForDuplicates(testArray)){
                return false;
            }
        }
        return true;
    }

    // checks for duplicates except for -1. You can repeat -1 as much as you want.
    public static boolean checkForDuplicates(int[] row){
        for (int i =0; i < row.length; i++){
            int sum = 0;
            for (int j = 0; j < row.length; j++){
                if (row[j] == row[i] && row[i] != -1) {
                    sum++;
                }
            }
            if (sum > 1){
                return false;
            }
        }
        return true;
    }

    public boolean scheduleClasses(){
        if(scheduleClasses(0)){
            System.out.println("Scheduling was a success!");
            return true;
        }else {
            System.out.println("Scheduling was a failure!");
            return false;
        }
    }

    private boolean scheduleClasses(int pos) {
        if(pastEndOfGrid(pos)) return true;

        int[] ij = position(pos);
        int i = ij[0]; // row - time
        int j = ij[1]; // column - class

        // if nothing is trying to be scheduled skip
        if(possibilities[i][j].length == 0)
            return scheduleClasses(pos + 1);

        // recursive backtracking
        for( int k: possibilities[i][j]){
            // i = time, j = class, k = equipment
           if(schedule[i][k] == -1){
               // check if cell above exists
               if(i > 0){
                   // if there is nothing scheduled above or the class above has 15 min rotation
                   if(schedule[i-1][k] == -1 || classes[schedule[i-1][k]].getRotation() == 15){

                       schedule[i][k] = j;
                       if(gridValid(schedule)) {
                           if (scheduleClasses(pos + 1)) {
                               return true;
                           }
                       }
                   }
               }

               else{
                   schedule[i][k] = j;
                   if(gridValid(schedule)) {
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

    private void initializeEquipLists() {
        for (int i = 0; i < possibilities.length; i++) {
            for (int j = 0; j < possibilities[i].length; j++) {

                possibilities[i][j] = new int[0];
            }
        }
    }

    private int[] findEquipIndexes(Class c) {
        String[] equipment = c.getEquipment();
        int[] indexes = new int[equipment.length];
        for (int i = 0; i < equipment.length; i++) {
            int j = 0;
            while (equipment[i] != equipList[j])
                j++;
            indexes[i] = j;
        }
        return indexes;
    }

    private void sortClassesStart() {
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

    private void sortClassesEnd() {
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

    private void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void printArray(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    private void printClasses(Class[] c) {
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i].getName());
        }
    }
	/*
	 * Simple bubblesort to sort the classes by their start date, earliest first.
	 */
}
