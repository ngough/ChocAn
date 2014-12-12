package chocan;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

/**
 * 
 * @author lingxi
 *  1.memberMaintainer Class is responsible for storing and manipulating members.
 * 	2.When a new MemberMaintaner class object gets instantiated, it read from a file which stores members' information,
 * 	  creates member objects, and dump all members into an arrayList called memberList.
 *  3.This class can then process those members in the list and write them back to file after all the changes been done.
 */
public class MemberMaintainer {

	/**
	 * providerList stores all member objects created from "Member" file.
	 */
	ArrayList<Member> memberList = new ArrayList<Member>();
	
	/**
	 * Constructor. It reads "Members" file, creates Member objects according to file contents, 
	 * and store them in memberList arrayList
	 * @throws FileNotFoundException
	 */
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
			System.out.println("Error closing inputStream.");
			e.printStackTrace();
		}
	}
	
	/**
	 * deleteMemberFromFile() method will delete a member from "Members" file. The method takes memberInfo which is
	 * a string and tries to search it in "Members" file. If it's found, it removes that line from the file.
	 * @param memberInfo a string contains member's name, address, id, feeDue. The format is the same as that in "Members" file.
	 */
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
	
	/**
	 * VerifyMember() method will search the member in the memberList by matching an ID number. 
	 * @param ID is the ID for the member which will be searched.
	 * @return true if the member is found and false if it's not in the list or file.
	 */
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
	
	/**
	 * printMembers() will prints out all the members and their information.
	 */
	public void printMembers()
	{
		Iterator<Member> iterator = memberList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
	/**
	 * loginMember() method logs in a member by taking that member's ID, finding that member in memberList, 
	 * and writing current date+time into that member's loginRecords
	 * @param memberID is the ID number for the member who wants to log in the system.
	 */
	public void loginMember(int memberID) throws IOException
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
					((Member) memberList.get(i)).writeMemberLoginRecords(date);
				}
					
				else
					System.out.println("Member suspended! Member has not paid membership fee!");
			}
		}
	}
	
	/**
	 * This method takes an ID and search through memberList. It will return the member with the given ID.
	 * @param memberID is the ID for the member that we want to get.
	 * @return member of given ID.
	 */
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
	
	public void printMemberReports() {
		MemberReportGenerator memberReportGenerator = new MemberReportGenerator();
		
		for(int i = 0; i < memberList.size(); i++) {
			memberReportGenerator.printMemberReport((Member)memberList.get(i));
		} //End for loop.
		
		return;
	} //End printMemberReports() method.
	
	public void addMember(Member m) {
		memberList.add(m);
	} //End addMember(Member) method.
	
	public void readServicesFromFile(ProviderMaintainer providerMaintainer) {
		//Read services into members' service lists.
		File serviceFile;
		Scanner scanner = null;
		String currentLine;
		String lineSegment;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date;
		Date tDate;
		int providerNumber;
		int memberNumber;
		String memberName;
		int serviceCode;
		String comment;
		
		try {
			for(int i = 0; i < memberList.size(); i++) {
				serviceFile = new File(memberList.get(i).getName()+"_ServicesFile");
				if(!serviceFile.exists()) {
					//No services for this member.
					continue;
				} //End if.
				scanner = new Scanner(serviceFile);
				
				//old
				while(scanner.hasNext()) {
					currentLine = scanner.nextLine();
					if(currentLine.contains("********************")) {
						if(scanner.hasNext()) {
							currentLine = scanner.nextLine();
							if(currentLine.contains("Current date and time:")) {
								//get current date when service was entered.
								lineSegment = currentLine.substring(22);
								tDate = dateFormat.parse(lineSegment);
								//Get the date service was received.
								currentLine = scanner.nextLine();
								date = currentLine.substring(27);
								//Get the provider number.
								currentLine = scanner.nextLine();
								providerNumber = Integer.parseInt(currentLine.substring(17));
								//Get the member number.
								currentLine = scanner.nextLine();
								memberNumber = Integer.parseInt(currentLine.substring(15));
								//Get the member name.
								currentLine = scanner.nextLine();
								memberName = currentLine.substring(13);
								//Get the service code.
								currentLine = scanner.nextLine();
								serviceCode = Integer.parseInt(currentLine.substring(14));
								//Get the comments.
								currentLine = scanner.nextLine();
								comment = currentLine.substring(10);
								
								//Create the Service object and add it to this provider's list.
								memberList.get(i).getServiceList().add(Service.makeService(date, tDate, memberNumber, memberName, providerNumber, providerMaintainer.getProvider(providerNumber).getName(), serviceCode, comment));
								
								//TODO remove this test stuff.
								Service s = Service.makeService(date, tDate, memberNumber, memberName, providerNumber, providerMaintainer.getProvider(providerNumber).getName(), serviceCode, comment);
								System.out.println(s.toString() + "member service");
							} //End if.
							else {
								continue;
							} //End else.
						} //End if.
					} //End if.
				} //End while loop.
			} //End for.
			
			scanner.close();
			System.out.println("Member service lists have been initialized.");
		} //End try.
		catch(Exception e) {
			System.out.println("Error reading services into members' service lists.");
			e.printStackTrace();
		} //End catch.
		
		return;
	} //End readServicesFromFile(ProviderMaintainer) method.
	
	public void writeDataFiles() {
		//TODO finish this
		
		
		
		return;
	} //End writeDataFiles() method.
	
} //End MemberMaintainer class.
