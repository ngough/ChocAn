package chocan;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	public UserInterface() throws FileNotFoundException
	{
		done=false;
		in = new Scanner(System.in);
		memberMaintainer = new MemberMaintainer();
		providerMaintainer = new ProviderMaintainer();
	}
	
	/*This method prints welcome page and determine employee type.*/
	public void printLoginPage()
	{
		System.out.println("Welcome to ChocAn!");
		System.out.println("\t1. Provider log in.");
		System.out.println("\t2. Manager log in.");
		System.out.println("\t3. Operator log in.");		
		employeeType = in.nextInt();//take user input and determine employee type.
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
				else
					System.out.println("Invalid provider!");
			}
			else if(employeeType==2)//manager login
			{
				
			}
			else//operator login
			{
				
			}
		}
		
	}
	
	public void executeCommand() throws IOException
	{
		while(!done)
		{
			if(employeeType==1)
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
						System.out.println("Mmeber does not exist.");
					}					
					break;
				case 2://print provider directory.
					providerMaintainer.checkProviderDirectory();
					break;
				case 3: //bill chocan for a service.
					//1.Member ID.
					System.out.println("Please enter member ID: ");
					int memberId = in.nextInt();
					if(memberMaintainer.verifyMember(memberId))//found this member.
					{
						if(memberMaintainer.getMember(memberId).isFeeDue())
						{
							System.out.println("Member has to pay membership fee first then get service!");
						}
						else
						{
							System.out.println("valid");
						}
					}
					//2.Date.
					System.out.println("Please enter the Date in the format MM-DD-YYYY");
					String s_date = in.nextLine();
					
					//3.Date & Time.
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					//String s_dtime = dateFormat.format(date); //2014/12/01 15:59:48
					
					//4.Service code.
					System.out.println("Do you need to check Provider Directory? y/n ");
					String c = in.nextLine();
					if(c.equals("y")||c.equals("Y"))
					{
						providerMaintainer.getProvider(id).checkProviderDirectory();
						System.out.println("\nPlease enter service code:");
					}
					else
					{
						System.out.println("Please enter service code:");
					}
					String s_code = in.nextLine();
					
					//5.Verify the service.
					while(true)
					{
						boolean s = providerMaintainer.getProvider(id).searchService(s_code);
						if(s==true)
							break;
					}
					
					//6.Make a service.
					Service service = Service.makeService(s_date,date, memberId, id, Integer.parseInt(s_code));
					
					break;
				case 4://log off.
					providerMaintainer.getProvider(id).writeProviderLoginRecords(id);//save the log_in record.
					System.out.println("Good bye!");
					System.exit(0);
				}
			}
		}
	}
	
	public void printProviderMenu()
	{
		System.out.println("");
		System.out.println("\t1. Check in a member.");
		System.out.println("\t2. Print Provider Directory.");
		System.out.println("\t3. Bill ChocAn for a service.");
		System.out.println("\t4. Log off.");//write all records to files.
		command = in.nextInt();
	}
	
	public void printManagerMenu()
	{
		
	}
	/**
	 * 
	 */
	public void printOperatorMenu()
	{
		
	}
	
}
