/*
 * This class is used to do operations with integer time values
 */
public class Time 
{
	/*
	 * This calculates how many rotations a class has.
	 */
	public static int timeOnEquipment(int start, int end, int rotation)
	{
		int total = ((end-start)/60)*60;
		return total/rotation;
	}
}
