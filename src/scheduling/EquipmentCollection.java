package scheduling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
/*
 * This is the class that has the equipmentFactory and that also stores the
 * equipment created. It also has the functionality to save the equipment in
 * a text file for further use
 * created by Matt Detrick
 * 11/10/14
 */

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

		//System.out.println(name+" added");
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
	public String[] getEquipmentList()
	{
		Object[] full = equipList.keySet().toArray();
		String[] empty = new String[full.length];
		for(int i =0;i<full.length;i++)
		{
			empty[i]=(String)full[i];
		}
		return empty;
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
