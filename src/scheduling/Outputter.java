package scheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;



/*
 * These are used to output the schedule as a .xls file
 */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.util.HSSFColor;
/*
 * This class outputs the solution to a .xls file
 * created by Matt Detrick
 * 12/2/14
 */
public class Outputter 
{
	HSSFWorkbook wb ;  
    HSSFSheet sheet1;
    String[] equipList;
    Class[] classes;
    int[][] schedule;
    HSSFCellStyle[] colors;
	public Outputter(int[][] schedule, String[] equipList, Class[] classes)
	{
		this.schedule = schedule;
		this.equipList=equipList;
		this.classes=classes;
		wb = new HSSFWorkbook();  
	    sheet1 = wb.createSheet("sheet 1");
	    setColors();
	    addEquipNames();
	    addTimes();
	    addClasses();
	    writeToFile();
	}
	/*
	 * This adds the equipment names to the top of their respective columns
	 */
	private void addEquipNames()
	{
		HSSFRow row = sheet1.createRow((short)0);
	    for(int i=0;i<equipList.length;i++)
	    {
		    HSSFCell cell = row.createCell(i+1);
		    cell.setCellValue(equipList[i]);
	    }
	}
	/*
	 * This adds the times to the start of each row
	 */
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
	/*
	 * This basically copies the input schedule to its equivalent representation in excel
	 */
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
					int classNum = schedule[i][j];
					Class class1 = classes[classNum];
					cell = sheet1.getRow(i+1).createCell(j+1);
					cell.setCellValue(class1.getName());
					cell.setCellStyle(colors[classNum%5]);
					if(class1.getRotation()==30)
					{
						//This means that the class has 30 min rotations so add the class at the next time slot also
						cell = sheet1.getRow(i+2).createCell(j+1);
						cell.setCellValue(class1.getName());
						cell.setCellStyle(colors[classNum%5]);
					}
				}
			}
		}
	}
	/*
	 * Populates an array of colors that can be used to help distinguish between the classes
	 */
	private void setColors()
	{
		int i =4;
		colors = new HSSFCellStyle[i+1];
		while(i>=0)
		{
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(HSSFCellStyle.FINE_DOTS);
			if(i==1)
				style.setFillForegroundColor(new HSSFColor.LIGHT_BLUE().getIndex());
			else if(i==2)
				style.setFillForegroundColor(new HSSFColor.LIGHT_GREEN().getIndex());
			else if(i==3)
				style.setFillForegroundColor(new HSSFColor.LIGHT_ORANGE().getIndex());
			else if(i==4)
				style.setFillForegroundColor(new HSSFColor.LIGHT_TURQUOISE().getIndex());
			else if(i==0)
				style.setFillForegroundColor(new HSSFColor.LIGHT_YELLOW().getIndex());
					    
		    colors[i]=style;
		    i--;
		}
	}
	/*
	 * This simply writes the workbook to the actual .xls file using an outputStream
	 */
	private void writeToFile()
	{
		 FileOutputStream fileOut=null;
		try 
		{
			/*
			 * Below is a small gui piece to allow the user to select a save location and name
			 */
			
			JFileChooser saveFile = new JFileChooser();
			saveFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int selection =saveFile.showSaveDialog(null);
			if (selection == JFileChooser.APPROVE_OPTION)
			{
			   File file = saveFile.getSelectedFile();
			   fileOut = new FileOutputStream(file.getPath()+".xls");
			   wb.write(fileOut);
				fileOut.close();
			}
			
			
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
