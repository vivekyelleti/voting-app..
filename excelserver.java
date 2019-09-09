
import java.io.*;
import java.net.*; 
import java.lang.*; 
import java.util.*; 
import jxl.*;
import jxl.write.*;

public class excelserver  
{

	private ServerSocket socket;

	public static String login,paswd;

	int count[]={1,0,0,0,0,0};

	excelserver(ServerSocket s)
	{
		try
		{
			System.out.println("The server is waiting for client requests ");
			socket=s;
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int checklist(String msg)
	{
// CHECKING IN THE EXCEL SHEET 
		 final String location="check.xls";
		 final int maxlines=6;
		 final int fields=6;
 		String s=msg;
 		StringTokenizer st=new StringTokenizer(s,",");
 		System.out.println("First token "+s);
 		Workbook list=null;
 		st.nextToken();
	try
	{
		list=Workbook.getWorkbook(new File(location));
		Sheet sheet=list.getSheet(0);
		System.out.println("opened");
		int i=0;
		int j=0;
		int id=maxlines;
		String nameid=st.nextToken();
		System.out.println("verfiying\t"+nameid);
		for(i=1;i<maxlines;i++)
		{
			Cell cell=sheet.getCell(0,i);
			String x=cell.getContents();
			System.out.println(cell.getContents());
			if(nameid.equalsIgnoreCase(x))
			{
			id=i;
			i=maxlines;
			}

		}

		if(id==maxlines)
		{
			System.out.println("Person authentication failed");
			return 0;
		}

	System.out.println("finded at\t"+id);
	Cell cell=sheet.getCell(6,id);
	System.out.println(cell.getContents());
	if(cell.getContents().equals("V"))
	{
		System.out.println("Already verified");
		return 2;
	}

//int []count={1,0,0,0,0,1};

	for(j=1;j<5;j++)
	{
		cell=sheet.getCell(j,id);
		System.out.println(cell.getContents());
		String ab=st.nextToken();
		System.out.println(ab);
		if(ab.equals(cell.getContents()))
		{
			count[j]=1;
			System.out.println("VERIFIED");
		}
	}

	String []name={"name","registration number","email adress","department","gender","DOB"};
	int ct=0;
	for(j=0;j<5;j++)
	{
	if(count[j]==0)
		System.out.println("Identity verification failed :: \t"+name[j]);
	else
	ct=ct+1;
	}


	System.out.println("value at "+ct);
	if(ct==5)
	{
		System.out.println("Student Veerification is done ");
		System.out.println("Dont share the details");
		cell=sheet.getCell(1,id);
		login=cell.getContents();
		System.out.println("Login ID :: "+cell.getContents());
		cell=sheet.getCell(5,id);
		paswd=cell.getContents();
		System.out.println("DOB ::"+cell.getContents());

// update the value from NV to V
//list.save();
//Workbook wkk=Workbook.getWorkbook(new File(location));
//System.out.println("af");
//WritableWorkbook wr=Workbook.createWorkbook(new File("check.xls"),list);
//list.close();
//WritableSheet wsh=wr.getSheet(0);

//WritableCell wc=wsh.getWritableCell(6,id);

//Label l=new Label(6,id,"V");
//WritableCell wc=(WritableCell)l;
//wsh.addCell(wc);
//wr.write();
//WritableCell c=sheet.getWritableCell(6,id);
//System.out.println(c.getContents());
//c.setValue("V",0);
/*if(cell.getType()==CellType.LABEL)
{
Label l=(Label)cell;
l.setString("V");
}*/

		return 1;
	}
	else
	{
		System.out.println("Re enter the valid details");
		return 0;
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return 1;
	}

public static void main(String[] args)
{
	try
	{
		ServerSocket s=new ServerSocket(6675);
		System.out.println("Server is ready \n");

		while(true)
		{
		System.out.println("accpeted");
		Socket st=s.accept();
		excelserver es=new excelserver(s);

		DataInputStream di=new DataInputStream(st.getInputStream());
//BufferedReader di=new BufferedReader(s.getInputStream());
		String msg=di.readUTF();
		int k=0;
		k=es.checklist(msg);
		System.out.println(msg);
//OutputStreamWriter dou=new OutputStreamWriter(st.getOutputStream());
//st.getOutputStream(new BufferedReader(k));
//BufferedWriter bw=new BufferedWriter(dou);
		DataOutputStream dou=new DataOutputStream(st.getOutputStream());
		System.out.println(k);
		if(k==1)
		dou.writeUTF("ok"+","+login+","+paswd);
		else if(k==0)
		dou.writeUTF("notok"+","+"*"+","+"+");
		else
		dou.writeUTF("already verfied"+","+"*"+","+"+");

//bw.flush()


//System.out.println(bw.readLine());
		System.out.println("Process  of the Registration was completed ");
		}
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
	}

	}

}
