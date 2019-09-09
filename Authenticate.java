import java.io.*;
import jxl.*;

public class Authenticate
{
String login;
String paswd;
//final static String filename="../electionday/check.xls";
Authenticate(String login,String paswd)
{
this.login=login;
this.paswd=paswd;
}
String checkthevalidity()
{
String filename="../registration/check.xls";
System.out.println(login+"\t"+paswd);
Workbook wb=null;
Sheet s=null;
Cell cell=null;
String did=null;
int id=0;
try
{
wb=Workbook.getWorkbook(new File(filename));
s=wb.getSheet(0);
int i=0;
int flag=0;
System.out.println("started searching ...");
for(i=0;i<5;i++)
{
cell=s.getCell(1,i);
String ls=cell.getContents();
if(this.login.equals(ls))
{
System.out.println("finded at"+i);
flag=1;
id=i;
}
}
if(flag==0)
{
System.out.println("not able to findout");
did="not-done";
//return did;
}
else
{
cell=s.getCell(5,id); // dob cell
String lst=cell.getContents();
System.out.println(lst);
if(this.paswd.equals(lst))
{
did="done";
System.out.println("paswd is also validated");
//return did;
}
else
{
System.out.println("passwd isn not validated");
did="not-done";
}
}
}
catch(Exception e)
{
e.printStackTrace();
}
//String did="done";
return did;
}



}
