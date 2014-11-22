package scheduling;

import java.util.Arrays;

public class test
{

	public static void main(String args[])
	{

		ClassCollection c = new ClassCollection("monday");
		String[] e  ={"bars","floor","rings","tramp"};
		String[] e1 ={"floor","rings"};
		String[] e2 ={"bars","tramp"};

		c.addClass("mmv", "asdfu",  900, 1000, 15 ,e);
		c.addClass("mm",  "asdf",   915, 1015, 30 ,e1);
		c.addClass("im",  "asdfx",  915, 1015, 15 ,e);
		c.addClass("imx", "asdfx", 1000, 1100, 30 ,e2);

		EquipmentCollection eq = new EquipmentCollection();
		eq.addEquipment("bars", "asdf");
		eq.addEquipment("floor", "asdf");
		eq.addEquipment("rings", "asdf");
		eq.addEquipment("tramp", "asdf");

		int test = 2;

		if(test == 1)
		{			
			
			Class[] classes = c.getClasses();
			for(int i =0;i<4;i++)
			{
				System.out.println(Time.numOfRotationsNeeded(classes[i].getStart(), classes[i].getEnd(), 15));
			}
			
			
		}
		
		else if(test == 2)
		{
			Schedule s = new Schedule(eq.getEquipmentList(), c.getClasses());
			//System.out.println(Arrays.toString(s.position(4)));
			//System.out.println(s.pastEndOfGrid(20));
		}

		
	}

}
