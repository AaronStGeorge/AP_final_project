package colorGraphing;
import java.util.ArrayList;

import Scheduling.Class;

public class classVertex 
{
	private Class class1;
	private ArrayList<Edge> edges;
	public classVertex(Class c)
	{
		class1 = c;
		edges = new ArrayList<Edge>();
		fillList();
	}
	
	private void fillList()
	{
		String[] equipment = class1.getEquipment();
		for(int i =0;i<equipment.length;i++)
		{
			edges.add(new Edge(class1.getName(), equipment[i]));
		}
	}
}
