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
	int number = EquipmentCollection.number;
	String day;
	public ClassCollection(String day) 
	{
		try 
		{
			this.day = day;
			Scanner scan;
			BufferedReader reader = new BufferedReader(new FileReader(this.day+"classes.txt"));
			String line=null;
			while ((line = reader.readLine()) != null) 
			{
				scan = new Scanner(line);
				add(scan.next(), scan.next(), scan.nextInt(), scan.nextInt(), scan.nextInt());
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

	private void add(String name, String desc,int s, int e, int r)
	{
		classList.add(new Class(name, desc, s,e,r,++number));
	}
	public void addClass(String name, String desc, int s, int e, int r)
	{
		add(name, desc,s,e,r);
		changed = true;
	}
	public void removeClass()
	{
		changed = true;
	}
	public String classInfo()
	{
		return null;
	}
	
	public void save()
	{
		if(changed)
		{
			try
			{
				BufferedWriter out = new BufferedWriter(new FileWriter(day+"classes.txt"));
				int size = classList.size();
				for(int i =0;i<size;i++)
				{
					Class c = classList.remove(0);
					out.write(c.getName()+" "+c.getTeacher()+" "+c.getStart()+" "+c.getEnd()+" "+c.getRotation()+"\n");
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
