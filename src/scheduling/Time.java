package scheduling;
/*
 * This class is used to do operations with integer time values
 */
public class Time 
{
	/*
	 * This calculates how many rotations a class has.
	 */
	public static int numOfRotationsNeeded(int start, int end, int rotation)
	{
		/*
		 * first, the time is changed reflect its base 100 equivalent.
		 * For example, 1015 is turned to 1025 because 15 is 1/4 of 60.
		 */
		int s = (start/100)*100+(start%100*5)/3;
		int e = (end/100)*100+(end%100*5)/3;
		/*
		 * This, in two parts, finds the number of hours first and then the number of minutes
		 * of the total class time.
		 */
		int total = ((e-s)/100)*60+(e-s)%100*6/10;
		/*
		 * this takes that total time and divides by the rotation time to give the number of 
		 * rotations needed.
		 */
		return total/rotation;
	}
	
	
}
