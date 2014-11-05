
public class Equipment 
{
	private String name;
	private String description;
	private int number;
	
	public Equipment(String n, String d, int i)
	{
		name = n;
		description = d;
		number = i;
	}
	
	public int getNumber()
	{
		return number;
	}
	public String getName()
	{
		return name;
	}
	public String getDesc()
	{
		return description;
	}
}
