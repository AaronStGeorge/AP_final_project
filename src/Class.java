
public class Class 
{
	private String name, teacher;
	private int startTime, endTime, rotationTime;
	String[] equipment;
	
	public Class(String n, String t, int s, int e, int r, String[] equip)
	{
		name = n; 
		teacher = t;
		startTime = s;
		endTime = e;
		rotationTime = r;		
		equipment = equip;
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
	
	public String[] getEquipment()
	{
		return equipment;
	}

}
