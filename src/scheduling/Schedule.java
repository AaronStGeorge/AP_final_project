package scheduling;

import java.util.HashMap;
import java.util.List;

public class Schedule 
{
	/*
	 * This creates the4-dimensional array but is dynamic
	 */
	HashMap<Integer, List<String>> classList;
	HashMap<Integer, HashMap<Integer, List<String>>> equipment;
	HashMap<Integer,HashMap<Integer, HashMap<Integer, List<String>>>> times;
	
	public Schedule(Class[] classes)
	{
		printClasses(sortClasses(classes));
	}
	public Schedule(int equipment, Class[] classes)
	{
		classes = sortClasses(classes);
		int numOfTimeSlots = Time.numOfRotationsNeeded(classes[0].getStart(), classes[classes.length-1].getEnd(), 15);
		/*
		 * times holds all of the 15 min time slices that need to be considered
		 */
		times =new HashMap<Integer,HashMap<Integer, HashMap<Integer, List<String>>>>(numOfTimeSlots);
	}
	private void printClasses(Class[] c)
	{
		for(int i =0;i<c.length;i++)
		{
			System.out.println(c[i].getName());
		}
	}
	/*
	 * Simple bubblesort to sort the classes by their start date, earliest first.
	 */
	private Class[] sortClasses(Class[] classes)
	{
		int c, d, n=classes.length;
		Class swap;
		for (c = 0; c < ( n - 1 ); c++)
		{
		      for (d = 0; d < n - c - 1; d++) 
		      {
		        if (classes[d].getStart() > classes[d+1].getStart()) /* For descending order use < */
		        {
		          swap       = classes[d];
		          classes[d]   = classes[d+1];
		          classes[d+1] = swap;
		        }
		      }
		}
		return classes;
	}
		

}
