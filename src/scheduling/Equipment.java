package scheduling;
/*
 * This is the equipment object
 * created by Matt Detrick
 * 11/10/14
 */
public class Equipment 
{
	private String name;
	private String description;
	
	public Equipment(String n, String d)
	{
		name = n;
		description = d;
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
