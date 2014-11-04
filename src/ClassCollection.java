import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ClassCollection 
{
	private List<Class> classList = new ArrayList<Class>();
	boolean changed = false;
	
	public ClassCollection() 
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader("classes.txt"));
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
				new FileWriter("classes.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addClass(Class c)
	{
		classList.add(c);
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
				BufferedWriter out = new BufferedWriter(new FileWriter("classes.txt"));
				for(int i =0;i<classList.size();i++)
				{
					Class c = classList.remove(i);
					out.write(c.name+" "+c.teacher+"\n");
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
