import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.*;
import java.lang.*;


public class board //extends ExecutorService
{

int port;
ServerSocket server=null;
Socket client=null;
ExecutorService pool=null;
int xcount=0;
static Cipher cipher;
//SecretKey orginalKey;

public static void main(String[] args)
{
try
{
board serverob=new board(8200);
serverob.startElection();
	KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		//recieve key;
		SecretKey secretKey;
		cipher = Cipher.getInstance("AES");
}
catch(Exception e)
{
e.printStackTrace();
}
}

board(int port)
{
this.port=port;
pool=Executors.newFixedThreadPool(5);
}

public void startElection ()
{
try
{
server=new ServerSocket(8200);
System.out.println("ELection started "+"\n"+
 "Waiting for votes");
while(true)
{
client=server.accept();
//server.setSoTimeOut(5000);

InputStream is=client.getInputStream();
InputStreamReader isr=new InputStreamReader(is);
BufferedReader br=new BufferedReader(isr);
String secretKey=br.readLine(); // String Type Secret Key is reading ffrom the server;
String store=secretKey;
System.out.println("sec key is"+secretKey);
byte[] decodedKey=Base64.getDecoder().decode(store);
SecretKey originalKey=new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
xcount++;
System.out.println(secretKey);
ServerThread runnable=new ServerThread(client,xcount,originalKey,secretKey,this);
pool.execute(runnable);
}
}
catch(Exception e)
{
e.printStackTrace();
}

}
}

class ServerThread implements Runnable
{
board server=null;
Socket client=null;
BufferedReader br;
PrintStream ps;
Scanner sc=new Scanner(System.in);
int id;
String s;
static Cipher cipher;
SecretKey orginalKey;

String secretKey;

ServerThread(Socket client,int count,SecretKey orginalKey,String secretKey,board server)
{
try
{
this.client=client;
this.server=server;
this.id=count;
this.orginalKey=orginalKey;
this.secretKey=secretKey;
//Static Cipher cipher;
System.out.println("connected");
br=new BufferedReader(new InputStreamReader(client.getInputStream()));
ps=new PrintStream(client.getOutputStream());
System.out.println(this.secretKey);
}

catch(Exception e)
{
e.printStackTrace();
}
}

String authenticate(String id)
{
final String file="check.xls";
// checklist code;
StringTokenizer stz=new StringTokenizer(id,",");

String login=stz.nextToken();
String passwd=stz.nextToken();

Authenticate ob=new Authenticate(login,passwd);

String res=ob.checkthevalidity();
System.out.println(res);
return res;
}

public void display(String a,PrintWriter ps)
{
int col=0;

final int contestants=5;


if(a.equals("1"))
col=1;
else if(a.equals("2"))
col=2;
else 
col=3;

//display from contestants work book

}



public void receivevotes(String s)
{
final int posts=3;

StringTokenizer tk=new StringTokenizer(s,",");

String[] vote=new String[posts];
int i=0;
for(i=0;i<posts;i++)
{
String obk=tk.nextToken();	
vote[i]=obk;
}

// write the votes in a workbook


// write in a workbook that particular person is checked
}
public void run()
{
int x=1;
try
{
int flag=0;

//while(flag!=1)
//{
String credentials;
credentials=br.readLine(); 
Thread.sleep(1000);
//server.setSoTimeOut(5000);
System.out.println("Received "+s);

// CHECKING THE AUTHENTICATION



ps.println(authenticate(credentials));


//String dis=br.readLine(); // Details has to be sent
//displaylist(dis,ps);


// I have taken static data here as JXL API is not working for me

String s[][]=new String[3][3];
s[0][0]="Amith";
s[0][1]="Prakash";
s[0][2]="Supraja";
s[1][0]="Rajendra";
s[1][1]="Lakshmi reddy";
s[1][2]="Aarti nagpal";
s[2][0]="Razia sultana";
s[2][1]="Karthik";
s[2][2]="manohar";

String p[]=new String[3];
p[0]="President";
p[1]="Vice-President";
p[2]="Secretary";

ps.println(p[0]+"::"+s[0][0]+"\t"+s[0][1]+"\t"+s[0][2]);
ps.println(p[1]+"::"+s[1][0]+"\t"+s[1][1]+"\t"+s[1][2]);
ps.println(p[2]+"::"+s[2][0]+"\t"+s[2][1]+"\t"+s[2][2]);





//Thread.sleep(5000);
String votedlist=br.readLine();

//receivevotes(votedlist); // encrypted data will be receiveed here
System.out.println(votedlist);

System.out.println("SECRET KEY "+this.secretKey);

//String sharedagain=br.readLine();
//System.out.println(sharedagain);
///
byte[] decodedKey=Base64.getDecoder().decode(this.secretKey);
SecretKey or=new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
System.out.println("helllo: ");
//Thread.sleep(1000);
String decryptedText = decrypt(votedlist,or); // orginal key replace
//Thread.sleep(1000);
System.out.println("helllo: ");

//String secKey=Base64.getEncoder().encodeToString(originalKey.getEncoded());
System.out.println("sec key is"+secretKey);
System.out.println("Decrypted Text After Decryption: " + decryptedText);


//receivevotes(votedlist);
//Decryption has to be done and to be stored in a variable



ps.println("Thanks for voting ");
}
catch(Exception e)
{
e.printStackTrace();
}
}
	public  String decrypt(String encryptedText, SecretKey originalKey)
			throws Exception {
                Cipher ciph=(Cipher.getInstance("AES"));
                System.out.println(encryptedText);
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
               System.out.println("1");
		ciph.init(Cipher.DECRYPT_MODE, originalKey);  
                 System.out.println("2");
		byte[] decryptedByte = ciph.doFinal(encryptedTextByte);
                System.out.println("3");
		String decryptedText = new String(decryptedByte);
                System.out.println(decryptedText);
		return decryptedText;
	}
}
