package scheduling;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
public class Outputter 
{
	HSSFWorkbook wb ;  
    HSSFSheet sheet1;
    String[] equipList;
    Class[] classes;
    int[][] schedule;
	public Outputter(int[][] schedule, String[] equipList, Class[] classes)
	{
		this.schedule = schedule;
		this.equipList=equipList;
		this.classes=classes;
		wb = new HSSFWorkbook();  
	    sheet1 = wb.createSheet("sheet 1");
	    addEquipNames();
	    addTimes();
	    addClasses();
	    writeToFile();
	}
	private void addEquipNames()
	{
		HSSFRow row = sheet1.createRow((short)0);
	    for(int i=0;i<equipList.length;i++)
	    {
		    HSSFCell cell = row.createCell(i+1);
		    cell.setCellValue(equipList[i]);
	    }
	}
	private void addTimes()
	{
		HSSFRow row;
		HSSFCell cell;
		int time = classes[0].getStart();
		for(int i=0;i<schedule.length;i++)
		{
			row = sheet1.createRow((short)i+1);
			cell = row.createCell(0);
			cell.setCellValue(time);
			time = Time.addFifteen(time);
			
		}
	}
	private void addClasses()
	{
		HSSFCell cell;
		for(int i =0;i<schedule.length;i++)
		{
			for(int j =0;j<schedule[i].length;j++)
			{
				if(schedule[i][j]==-1)
				{
					//do nothing, no class is scheduled
				}
				else
				{
					Class class1 = classes[schedule[i][j]];
					cell = sheet1.getRow(i+1).createCell(j+1);
					cell.setCellValue(class1.getName());
					if(class1.getRotation()==30)
					{
						//This means that the class has 30 min rotations so add the class at the next time slot also
						cell = sheet1.getRow(i+2).createCell(j+1);
						cell.setCellValue(class1.getName());
					}
				}
			}
		}
	}
	private void writeToFile()
	{
		 FileOutputStream fileOut=null;
		try 
		{
			fileOut = new FileOutputStream("schedule.xls");
			wb.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
