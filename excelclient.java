import java.io.*; import java.net.*; import java.util.*;


class excelclient{

public static void main(String[] args)
{
	try
	{
	Socket s=new Socket("localhost",6675);
	System.out.println("WELCOME TO THE UOH SU ELECTIONS "
                  +"\n Fill the following details \n"
		  +"\nNote: kindly provide the valid information\n");

	String id,name,email,branch,gender,dob;

	s.setSoTimeout(2000);

	Scanner sc=new Scanner(System.in);
	System.out.print("Enter the Student iD :: ");
	id=sc.nextLine();
	System.out.print("Enter the Name of the student ::");
	name=sc.nextLine();
	System.out.print("Enter the Email address :: ");
	email=sc.nextLine();
	System.out.println("Enter the Department ::");
	branch=sc.nextLine();
	System.out.println("Enter the Gender Male (M) / Female (F) ::");
	gender=sc.nextLine();
	System.out.println("Enter the date of birth in dd-mm-yy");
	dob=sc.nextLine();

	s.setSoTimeout(1000);

	DataOutputStream dou=new DataOutputStream(s.getOutputStream());

	dou.writeUTF("Data"+","+name+","+id+","+email+","+branch+","+gender+","+dob);

	System.out.println("waitng for response ...");


//InputStreamReader di=new InputStreamReader(s.getInputStream());
//di.readUTF();
//System.out.println(di);
//BufferedReader br=new BufferedReader(di);
//System.out.println(br);
//String res=br.readLine();

	DataInputStream di=new DataInputStream(s.getInputStream());
	s.setSoTimeout(1000);
	String rs=di.readUTF();
	System.out.println(rs);
	StringTokenizer res=new StringTokenizer(rs,",");
	System.out.println("aefae");
	String result=res.nextToken();
	String login=res.nextToken();
	String paswd=res.nextToken();
	System.out.println("Received "+ result);
	if(result.equals("ok"))
	{
		System.out.println("Registered Succesfully");
		System.out.println("Login Credentials :: ");
		System.out.println(login);
		System.out.println(paswd);
		System.out.println("Note : Dont share the credentials with anyone "+"\nProtecting your RIGHT TO VOTE"+"\n is YOUR RESPONSIBILTY");
	}
	if(result.equals("notok"))
	System.out.println("Invaalid Details");
	s.close();

	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	}

}
