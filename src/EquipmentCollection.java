import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EquipmentCollection
{
	private List<Equipment> equipList = new ArrayList<Equipment>();
	boolean changed = false;
	
	public EquipmentCollection() 
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader("equipment.txt"));
			String line=null;
			while ((line = reader.readLine()) != null) 
			{
			    System.out.println(line);
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

	public void addEquipment(Equipment e)
	{
		equipList.add(e);
		changed = true;
	}
	public void removeEquipment()
	{
		changed = true;
	}
	public String equipmentInfo()
	{
		return null;
	}
	
	public void save()
	{
		if(changed)
		{
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("equipment.txt"));
				for(int i =0;i<equipList.size();i++)
				{
					Equipment e = equipList.remove(i);
					out.write(e.name+" "+e.description+"\n");
				}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("changes saved");

		}
	}
}
