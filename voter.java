import java.net.*; 
import java.util.*; 
import java.io.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.lang.*;

public class voter
{
public static final int posts=3;
static Cipher cipher;

public static void main(String[] args) throws Exception
{
	
KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		cipher = Cipher.getInstance("AES");
		//System.out.println("key",+secretKey);
//Socket sk=new Socket("localhost",8200);
String host="localhost";
int port=8200;
InetAddress add=InetAddress.getByName(host);
Socket sk=new Socket(add,port);
BufferedReader br=new BufferedReader(new InputStreamReader(sk.getInputStream()));
PrintStream sout=new PrintStream(sk.getOutputStream());
String apost,bpost,cpost;
Scanner sc=new Scanner(System.in);
String loginid,paswd;


// KEY - SHARING
PrintStream os=new PrintStream(sk.getOutputStream());
//OutputStreamWriter osw=new OutputStreamWriter(os);
//BufferWriter bw=new BufferWriter(osw);
String secKey=Base64.getEncoder().encodeToString(secretKey.getEncoded());
System.out.println("sec key is"+secKey);
os.println(secKey);
os.flush();
System.out.println("Your vote secret key is sent successfully");


//while(true)
//{
System.out.println("Welcome to UOH SU ELECTION DAY");
System.out.println("Enter the Login details");
System.out.println("Enter the login id");
loginid=sc.nextLine();
System.out.println("Enter the password");

//sk.setSoTimeOut(5000);

paswd=sc.nextLine();
sout.println(loginid+","+paswd);
Thread.sleep(1000);
String s;
s=br.readLine();
System.out.println("received"+s);
if(s.equalsIgnoreCase("done"))
{
System.out.println("Following candidates are participating in the elections" );
int i=0;
String []vote=new String[3];

//String mesage=br.readLine();
//System.out.println(mesage);

for(i=0;i<posts;i++)
{
//sout.println(i);
String message=br.readLine();
System.out.println(message);

System.out.println("Enter the name");
String st; // choosing option
st=sc.nextLine();
vote[i]=st;
}

String []encvote=new String[3];

String enc=vote[0]+","+vote[1]+","+vote[2]; // this vote data has to be encrypted
String encryptedText=encrypt(enc, secretKey);
System.out.println("Encrypted Text After Encryption: " + encryptedText);
sout.println(encryptedText);
//sout.println(secretKey);
/*String encryptedText=encrypt(vote[1], secretKey);
System.out.println("Encrypted Text After Encryption: " + encryptedText);
String encryptedText=encrypt(vote[2], secretKey);
System.out.println("Encrypted Text After Encryption: " + encryptedText);*/
}
else
{
System.out.println("Credentials are invalid");
}

System.out.println("Thanks for voting");
Thread.sleep(5000);
sk.close();
br.close();
Thread.sleep(2000);
sout.close();
}
public static String encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}
}

