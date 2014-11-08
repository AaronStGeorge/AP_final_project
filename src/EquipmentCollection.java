import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class EquipmentCollection
{
	private HashMap<String, Equipment> equipList = new HashMap<String, Equipment>();
	boolean changed = false;
	public EquipmentCollection() 
	{
		try 
		{
			Scanner scan; 
			BufferedReader reader = new BufferedReader(new FileReader("equipment.txt"));
			String line=null;
			while ((line = reader.readLine()) != null) 
			{
				scan = new Scanner(line);
				add(scan.next(), scan.next());
			}
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			try {
				new FileWriter("equipment.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(String name, String desc)
	{

		System.out.println(name+" added");
		equipList.put(name, new Equipment(name, desc));
	}
	
	public void addEquipment(String name, String desc)
	{
		add(name, desc);
		changed = true;
	}
	public void removeEquipment()
	{
		//TODO
		changed = true;
	}
	public String equipmentInfo()
	{
		//TODO
		return null;
	}
	public Object[] getEquipmentList()
	{
		return equipList.keySet().toArray();
	}
	public int size()
	{
		return equipList.size();
	}
	public void save()
	{
		if(changed)
		{
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("equipment.txt"));
				Iterator<String> keys = equipList.keySet().iterator();
				while(keys.hasNext())
				{
					Equipment e = equipList.get(keys.next());
					out.write(e.getName()+" "+e.getDesc()+"\n");
				}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Equipment changes saved");

		}
	}
}
