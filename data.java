import java.io.*;
import java.util.*;
import jxl.*;

/* 

step 1 : Storing contestants names
step 2 : Accessing the election day voting list
step 3 : Displaying the output in the HTML page

*/

class data
{
/*int posts=3;
private int mem=4;*/

	String[][] names;

	public void load(String filename,int a,int b)
	{
		final String lo=filename;
		final int posts=a;
		final int mem=b; 
//String[][] names;
		names=new String [posts][mem];

		Workbook wk=null;
		try
		{
			wk=Workbook.getWorkbook(new File(lo));
			Sheet s=wk.getSheet(0);
			for(int i=0;i<posts;i++)
			{
			for(int j=0;j<mem;j++)
			{
				Cell cell=s.getCell(i,j+1);
				String st=new String(cell.getContents());
				names[i][j]=st;
//System.out.println(names[i][j]);
			}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}
}
