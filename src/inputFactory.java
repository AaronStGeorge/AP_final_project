
public class inputFactory 
{

	public Equipment addEquipment(String name, String desc)
	{
		return new Equipment(name, desc);
	}
	
	public Class addClass(String name, String teacher)
	{
		return new Class(name, teacher);
	}
}
