package chocan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserInterface {

	int command;
	boolean done;
	int employeeType;//1=provider,2=manager,3=operator.
	int id;
	Scanner in = null;
	MemberMaintainer memberMaintainer = null;
	ProviderMaintainer providerMaintainer =null;
	ManagerMaintainer managerMaintainer = null;
	
	public UserInterface() throws IOException
	{
		done=false;
		in = new Scanner(System.in);
		memberMaintainer = new MemberMaintainer();
		providerMaintainer = new ProviderMaintainer();
		providerMaintainer.readServicesFromFile();
		memberMaintainer.readServicesFromFile(providerMaintainer);
		managerMaintainer = new ManagerMaintainer();
		
	} //End UserInterface() constructor.
	
	/*This method prints welcome page and determine employee type.*/
	public void printLoginPage()
	{
		System.out.println("Welcome to ChocAn!");
		System.out.println("\t1. Provider log in.");
		System.out.println("\t2. Manager log in.");
		System.out.println("\t3. Operator log in.");	
		System.out.println("\t4. Exit.");
		employeeType = in.nextInt();//take user input and determine employee type.
		if(employeeType==4)
		{
			System.out.println("Good bye!");
			System.exit(0);
		}
			
	}
	
	public void login() throws FileNotFoundException
	{
		while(true)
		{
			if(employeeType==1)//provider login
			{
				
				System.out.println("Please enter your Provider ID: ");
				id = in.nextInt();//get user input.
				boolean validProvider = providerMaintainer.verifyProvider(id);//verify this id.
				if(validProvider)//if found this provider, we log him/her in.
				{
					providerMaintainer.loginProvider(id);
					break;
				}
				else {
					System.out.println("Invalid provider!");
				} //End else.
			} //End else if.
			else if(employeeType==2)//manager login
			{
				System.out.println("Please enter your Manager ID: ");
				id = in.nextInt();//get user input.
				boolean validManager = managerMaintainer.verifyManager(id);//verify this id.
				if(validManager)//if found this manager, we log him/her in.
				{
					managerMaintainer.loginManager(id);
					break;
				} //End if.
				else {
					System.out.println("Invalid manager!");
				} //End else.
			} //End else if.
			else//operator login
			{
				
			} //End else.
		} //End while loop.
		
	} //End login() method.
	
	public void executeCommand() throws IOException
	{
		while(!done)
		{
			if(employeeType==1) //Provider
			{
				printProviderMenu();
				switch(command)
				{
				case 1: //check in member.						
					System.out.println("Please enter Member ID: ");
					int m_id = in.nextInt();//get member id.
					boolean found = memberMaintainer.verifyMember(m_id);
					if(found)
					{				
						memberMaintainer.loginMember(m_id);
					}
					else
					{
						System.out.println("Member does not exist.");
					}					
					break;
				case 2://print provider directory.
					providerMaintainer.checkProviderDirectory();
					break;
				case 3: //bill chocan for a service.
					//1.Member ID.
					System.out.println("Please enter member ID: ");
					int memberId = in.nextInt();
					String memberName = "";
					if(memberMaintainer.verifyMember(memberId))//found this member.
					{
						memberName = memberMaintainer.getMember(memberId).getName();
						if(memberMaintainer.getMember(memberId).isFeeDue())
						{
							System.out.println("Member has to pay membership fee first then get service!");
							break;
						}
						else
						{
							System.out.println("valid");
						}
					}
					//2.Date.
					System.out.println("Please enter the Date in the format MM-DD-YYYY");
					String s_date="";
					Scanner keyboard = new Scanner(System.in);
					s_date += keyboard.nextLine();
					
					//3.Date & Time.
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					//String s_dtime = dateFormat.format(date); //2014/12/01 15:59:48
					
					//4.Service code.
					System.out.println("Do you need to check Provider Directory? y/n ");
					String c = keyboard.nextLine();
					if(c.equals("y")||c.equals("Y"))
					{
						providerMaintainer.getProvider(id).checkProviderDirectory();
						System.out.println("\nPlease enter service code:");
					}
					else
					{
						System.out.println("Please enter service code:");
					}
					String s_code = keyboard.nextLine();
					
					//5.Verify the service.
					while(true)
					{
						boolean s = providerMaintainer.getProvider(id).searchService(s_code);
						System.out.println("confirm the service y/n");
						String confirmed = keyboard.nextLine();
						if(s==true && confirmed.equals("y"))
							break;
						else if(confirmed.equals("n")){
							System.out.println("Re-enter the service code: ");
							s_code = keyboard.nextLine();
						}
						else if(s==false){
							System.out.println("Re-enter the service code: ");
							s_code = keyboard.nextLine();
						}
					}
					
					//6. add comment.
					String comment="";
					System.out.println("Add comment: y/n");
					c = keyboard.nextLine();
					if(c.equals("y")||c.equals("Y"))
					{
						comment += keyboard.nextLine();
					}
					else
					{
						//no comment. Does nothing.
					}
					
					//6.Make a service.
					Service service = Service.makeService(s_date,date, memberId, memberName, id, providerMaintainer.getProvider(id).getName(), Integer.parseInt(s_code),comment);
					providerMaintainer.getProvider(id).writeServiceToFile(service);
					memberMaintainer.getMember(memberId).writeServiceToFile(service);
					break;
				case 4://log off.
					providerMaintainer.getProvider(id).writeProviderLoginRecords();//save the log_in record.
					writeDataFiles();
					System.out.println("Good bye!");
					System.exit(0);
				} //End switch.
			} //End if.
			else if(employeeType == 2) { //Manager
				printManagerMenu();
				switch(command) {
				case 1: //Run reports.
					managerMaintainer.printReports(memberMaintainer, providerMaintainer);
					break;
				case 2: //Log off.
					managerMaintainer.getManager(id).writeManagerLoginRecords();
					writeDataFiles();
					System.out.println("Good bye!");
					System.exit(0);
				} //End switch.
				
			} //End else if.
			else if(employeeType == 3) { //Operator
				
				//TODO Write operator code!
				
			} //End else if.
			else { //Invalid input
				System.out.println("Invalid input for user type! Terminating.");
				writeDataFiles();
				System.exit(0);
			} //End else.
		} //End while loop.
	} //End executeCommand() method.
	
	/**
	 * 
	 */
	public void printProviderMenu()
	{
		System.out.println("");
		System.out.println("\t1. Check in a member.");
		System.out.println("\t2. Print Provider Directory.");
		System.out.println("\t3. Bill ChocAn for a service.");
		System.out.println("\t4. Log off.");//write all records to files.
		command = in.nextInt();
	} //End printProviderMenu() method.
	
	/**
	 * 
	 */
	public void printManagerMenu()
	{
		System.out.println("");
		System.out.println("\t1. Run reports.");
		System.out.println("\t2. Log off.");
		command = in.nextInt();
	} //End printManagerMenu() method.
	
	/**
	 * 
	 */
	public void printOperatorMenu()
	{
		
		//TODO Add operator menu choices!
		
	} //End printOperatorMenu() method.
	
	/**
	 * 
	 */
	public void writeDataFiles() {
		providerMaintainer.writeDataFiles();
		memberMaintainer.writeDataFiles();
		managerMaintainer.writeDataFiles();
		return;
	} //End writeDataFiles() method.
	
} //End UserInterface class.
