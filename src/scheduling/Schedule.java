package scheduling;
import javax.swing.JOptionPane;

public class Schedule {
   /*
    * Since we have two ways to solve the problem, this allows you to choose which stratgey you want
    */
    String[] equipList;
    Class[] classes;

    /*
     * The constructor needs the list of classes that need to be scheduled and also the equipment list
     * from EquipmentCollection.
     */
    
    public Schedule(String[] equipList, Class[] classes)
    {
    	this.equipList = equipList;
        this.classes = classes;
    	int choice =Integer.parseInt(JOptionPane.showInputDialog("Type 1 for Dancing links or 2 for Recursion"));
    	if(choice == 1)
    	{
    		//TODO add dancing links implementation
    	}
    	else if(choice == 2)
    	{
    		InputAdapter inputer = new RecursiveInput();
    		inputer.changeInput(equipList, classes);
    		//no output adapter needed, the solution output is already in the right form
    		new Outputter(inputer.schedule(), equipList, classes );
    	}
    }
    /*
    public Schedule(String[] equipList, Class[] classes) {
        
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
        //scheduleClasses();
        //printSchedule();
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
    }*/
	/*
	 * Simple bubblesort to sort the classes by their start date, earliest first.
	 */
}
