package scheduling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ClassCollection 
{
	private List<Class> classList = new ArrayList<Class>();
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
				scan = new Scanner(line);
				name = scan.next();
				teacher = scan.next();
				start = scan.nextInt();//time is viewed as a normal time number from a digital watch minus the colon
				end = scan.nextInt();//time is viewed as a normal time number from a digital watch minus the colon
				rotation =scan.nextInt();//this should be in minutes
				equip = new String[Time.timeOnEquipment(start, end, rotation)];
				for(int i =0;i<equip.length;i++)
				{
					equip[i]=scan.next();
				}
				System.out.println(name+" at "+start+" added");
				add(name, teacher, start, end, rotation, equip);
			}
			reader.close();
			
		}
		catch (FileNotFoundException e)
		{
			try {
				new FileWriter(day+"classes.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void add(String name, String desc,int s, int e, int r, String[] equip)
	{
		classList.add(new Class(name, desc, s,e,r, equip));
	}
	/*
	 * Time is viewed as a normal time number from a digital watch minus the colon. The rotation should be passed in minutes
	 */
	public void addClass(String name, String desc, int s, int e, int r, String[] equip)
	{
		boolean added = false;
		int i =0;
		while(!added)
		{
			if(i==classList.size())
			{
				added=true;
				classList.add(new Class(name, desc, s,e,r, equip));
			}
			else if(s>classList.get(i).getStart())
				i++;
			else
			{
				added=true;
				classList.add(i, new Class(name, desc, s,e,r, equip));
			}
		}
		
		changed = true;
	}
	public Class getClass(int i)
	{
		return classList.get(i);
	}
	public void removeClass()
	{
		changed = true;
	}
	public String classInfo()
	{
		return null;
	}
	
	public Class[] getClasses()
	{
		Class[] classes = new Class[classList.size()];
		for(int i =0;i<classList.size();i++)
		{
			classes[i]=classList.get(i);
		}
		return classes;
	}
	
	public void save()
	{
		if(changed)
		{
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter(day+"classes.txt"));
				int size = classList.size();
				String[] equip;
				for(int i =0;i<size;i++)
				{
					Class c = classList.remove(0);
					out.write(c.getName()+" "+c.getTeacher()+" "+c.getStart()+" "+c.getEnd()+" "+c.getRotation()+" ");
					equip=c.getEquipment();
					for(int j =0;j<equip.length;j++)
					{
						out.write(equip[j]+" ");
					}
					out.write("\n");
				}
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Class changes saved");

		}
	}

}
