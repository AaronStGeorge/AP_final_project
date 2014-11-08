package colorGraphing;

public class Edge 
{
	private String class1, equipment;
	private int time=-1;
	
	public Edge(String class1, String equipment)
	{
		this.class1 = class1;
		this.equipment = equipment;
	}
	
	public void setTime(int t)
	{
		time = t;
	}
	
	public String classEnd()
	{
		return class1;
	}
	
	public String equipmentEnd()
	{
		return equipment;
	}

}
