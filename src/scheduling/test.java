package scheduling;

public class test 
{
	public static void main(String args[])
	{
		ClassCollection c = new ClassCollection("monday");
		String[] e ={"bars","floor","rings","tramp"};
		c.addClass("mmv", "asdfu", 1415, 1615, 15,e );
		c.addClass("mm", "asdf", 1300, 1400, 30,e );
		c.addClass("im", "asdfx", 1330, 1500, 15,e );
		c.addClass("imx", "asdfx", 1015, 1545, 30,e );
		c.addClass("imm", "dfgk", 1000, 1630, 15, e);
			
		EquipmentCollection eq = new EquipmentCollection();
		eq.addEquipment("rings", "asdf");
		eq.addEquipment("bars", "asdf");
		eq.addEquipment("floor", "asdf");
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
		}

		
	}

}
