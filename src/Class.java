
public class Class 
{
	private String name, teacher;
	private int startTime, endTime, rotationTime, number;
	
	public Class(String n, String t, int s, int e, int r, int i)
	{
		name = n; 
		teacher = t;
		startTime = s;
		endTime = e;
		rotationTime = r;
		number = i;		
	}
	public String getName()
	{
		return name;
	}
	
	public String getTeacher()
	{
		return teacher;
	}
	
	public int getStart()
	{
		return startTime;
	}
	public int getEnd()
	{
		return endTime;
	}
	public int getRotation()
	{
		return rotationTime;
	}
	public int getNumber()
	{
		return number;
	}
}
