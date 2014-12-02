package chocan;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MemberMaintainer {

	ArrayList memberList = new ArrayList<Member>();
	
	public MemberMaintainer() throws FileNotFoundException
	{
		//this method get all Members from file and write them into arrayList.
		FileReader inputStream = null;
		try{
			inputStream = new FileReader("Members");
			Scanner file = null;
			file = new Scanner(inputStream);
			
			String delims = "[ ]+";
			while(file.hasNext())
			{
				String info = file.nextLine();//read a line from file.
				String[] memberInfo = info.split(delims);
				Member p = new Member(memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3],memberInfo[4],Integer.parseInt(memberInfo[5]),Boolean.parseBoolean(memberInfo[6]));
				//put this newly found member to memberList.
				memberList.add(p);
			}
			 
		}
		catch(Exception e){
			System.out.println("File not found.");
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void deleteMemberFromFile(String memberInfo)
	{
		File inputFile = new File("Members");//open input file.
		File tempFile = new File("MembersTemp");//open a temp file.

		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedWriter writer = null;//initialize writer.
		try {
			writer = new BufferedWriter(new FileWriter(tempFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String lineToRemove = memberInfo;
		String currentLine;

		try {
			while((currentLine = reader.readLine()) != null) 
			{
			    
			    if(currentLine.equals(lineToRemove))
			    	continue;
			    else
			    	writer.write(currentLine+System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//delete file
		File f = new File("Members");
		boolean success1 = f.delete();
		
		boolean success2 = tempFile.renameTo(inputFile);
	}
	
	public boolean verifyMember(int ID)
	{
		boolean found=false;
		for(int i=0;i<memberList.size();i++)
		{
			if(((Member) memberList.get(i)).getMemberID()==ID)
			{//match given ID to those in arrayList.
				found=true;//remove the member.
				break;
			}
		}
		return found;
	}
	
	public void printMembers()
	{
		Iterator iterator = memberList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
	public void loginMember(int memberID)
	{				
		for(int i=0;i<memberList.size();i++)
		{
			if(((Member) memberList.get(i)).getMemberID()==memberID)
			{
				if(((Member) memberList.get(i)).isFeeDue()==false)
				{
					//get current date.
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					System.out.println("log in date and time: "+dateFormat.format(date)); //2014/12/01 15:59:48
					((Member) memberList.get(i)).addLoginRecord(date);//add log in date to member login records.
				}
					
				else
					System.out.println("Member suspended! Member has not paid membership fee!");
			}
		}
	}
	
	public Member getMember(int memberID)
	{
		Member p = null;
		for(int i=0;i<memberList.size();i++)
		{
			if(((Member) memberList.get(i)).getMemberID()==memberID)
			{//match given ID to those in arrayList.
				p = (Member) memberList.get(i);
				break;
			}
		}
		return p;
	}
}
