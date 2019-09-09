import java.io.*;
import java.util.*;
import jxl.*;
import java.lang.*;
//import contestant.java;

public class results 
{

public static void main(String[] args)
{
//final String conlocation="contestant.xls";
	final int maxlines=20;
	final int fields=10;
	final int posts=3;
	Workbook work=null;
	int i=0,j=0,k=0;
	try
	{

		data contestant=new data();
		contestant.load("contestant.xls",3,4);


		for(i=0;i<3;i++)
		{
		for(j=0;j<4;j++)
			System.out.print(contestant.names[i][j]+"\t");
		System.out.println("");
		}
// read and store the results

		data result=new data();
		result.load("polling.xls",4,20);

/*for(i=0;i<4;i++)
{
for(j=0;j<20;j++)
System.out.print(result.names[i][j]+"\t");
System.out.println("");
}*/
		int [][]op=new int[3][4];

//computing the result

		for(i=0;i<3;i++)
		for(j=0;j<4;j++)
		op[i][j]=0;


		int a=0,b=0; //OP related iteratives

		System.out.println("output related activity beings");


		for(i=0;i<3;i++) //POSTS
		{
		for(k=0;k<20;k++) //VOTERS
		{
			System.out.print(result.names[i][k]+"---");
			for(j=0;j<4;j++) //CANDIDATES
			{
//String s=contestant.names[i][b];
			System.out.println(contestant.names[i][j]);
			if(contestant.names[i][j].equals(result.names[i][k]))
			{
				System.out.print("matched"+i+b+"\t");
				op[i][j]=op[i][j]+1;
				System.out.println(op[i][j]);
				j=4;
			}
			}
		}
		}
		
		System.out.println("hello");

		int[] max=new int[3];

		for(i=0;i<3;i++)
		{
			for(j=0;j<4;j++)
			{
				System.out.print(op[i][j]+"\t");
			}
			System.out.println("");
		}

	for(i=0;i<3;i++)
	{
//Integer []tt=new Integer(op[i]);
	List<Integer> list=new ArrayList<Integer>();
	for(j=0;j<4;j++)
		list.add(op[i][j]);
	max[i]=list.indexOf(Collections.max(list));
	}

	for(i=0;i<3;i++)
	System.out.println(max[i]);
/*String[] win=new String[3];

for(i=0;i<3;i++)
{
win[i]=names[i][max[i]];
}*/

//HTML CODE

	System.out.println("WInners of the Election ");
	System.out.println("POST\tName\tVotes\tStatus");

	i=0;
	j=0;

	for(i=0;i<3;i++)
	{
		for(j=0;j<4;j++)
		{
			System.out.print("P"+i+"\t"+contestant.names[i][j]+"\t"+op[i][j]+"\t");
			if(max[i]==j)
			System.out.println("Winner");
			else
			System.out.println("------");
		}
	}

	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}
}
