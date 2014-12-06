package scheduling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/*
 * This class is the factory for the classes and also stores all of the classes for 
 * that day in memory. There is also the functionality to store those classes in a text file for later
 * use
 * created by Matt Detrick
 * 11/10/14
 */

public class ClassCollection 
{
	private HashMap<String, Class> classList = new HashMap<String, Class>(0);
	//private List<Class> classList = new ArrayList<Class>();
	boolean changed = false;
	String day;
	public ClassCollection(String day) 
	{
		try 
		{
			this.day = day;
			Scanner scan;
			BufferedReader reader = new BufferedReader(new FileReader(this.day+"classes.txt"));
			String line=null;
			String name, teacher;
			int start, end, rotation;
			String[] equip;
			while ((line = reader.readLine()) != null) 
			{
				String[] values = line.split("_");
				add(values[0], values[1],Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]), Arrays.copyOfRange(values, 5, values.length));
				
			}
			reader.close();
			
		}
		catch (FileNotFoundException e)
		{
			try {
				new FileWriter(day+"classes.txt");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add(String name, String teacher,int s, int e, int r, String[] equip)
	{
		classList.put(name, new Class(name, teacher, s,e,r, equip));
	}
	/*
	 * Time is viewed as a normal time number from a digital watch minus the colon. 
	 * The rotation should be passed in minutes
	 */
	public void addClass(String name, String desc, int s, int e, int r, String[] equip)
	{
		
		classList.put(name, new Class(name, desc, s,e,r, equip));
		
		changed = true;
	}
	public Class getClass(int i)
	{
		return classList.get(i);
	}
	public void removeClass(Class c)
	{
		classList.remove(c.getName());
		changed = true;
	}
	public String classInfo()
	{
		return null;
	}
	
	public Class[] getClasses()
	{
		Collection<Class> c = classList.values();
		Class[] c1 = new Class[0];
		return c.toArray(c1);
	}
	
	public void save()
	{
		if(changed)
		{
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter(day+"classes.txt"));
				Iterator<String> keys = classList.keySet().iterator();
				while(keys.hasNext())
				{
					Class c = classList.get(keys.next());
					out.write(validate(c.getName())+"_"+validate(c.getTeacher())+"_"+c.getStart()+"_"+c.getEnd()+"_"+c.getRotation()+"_");
					String[] e = c.getEquipment();
					for(int i=0;i<e.length;i++)
					{
						out.write(e[i]+"_");
					}
					out.write("\n");
				}
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Class changes saved");

		}
	}
	
	private String validate(String v)
	{
		return v.replaceAll("_", " ");
	}

}
