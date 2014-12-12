package chocan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @author lingxi
 *  1.Member class is responsible for storing information for one member. 
 *  2.It extends Person class.
 */
public class Member extends Person{
	/**
	 * memberID is an ID number for member.
	 */
	private int memberID;
	/**
	 * serviceList holds service objects received by this member.
	 */
	private ArrayList<Service> serviceList = new ArrayList<Service>();
	/**
	 * feeDue is a boolean value. True means this member owns fee to ChocAn; False means the member has paid his/her membership fee.
	 */
	private boolean feeDue;
	
	/**
	 * Constructor.
	 * @param name member name.
	 * @param street member street.
	 * @param city member city.
	 * @param state member state.
	 * @param zip member zip.
	 * @param memberID member ID.
	 * @param feeDue member's payment status.
	 */
	public Member(String name,String street,String city,String state,String zip,int memberID,boolean feeDue)
	{
		super(name,street,city,state,zip);
		this.memberID=memberID;
		this.feeDue=feeDue;
		
	}
	
	/**
	 * writeMemberLoginRecords() method writes a login date and time into file.
	 * The file name is member's name + "_LoginRecords", for example, Kevin_LoginRecords.
	 * The filr will contain date and time for each login session in each line. The format is yyyy/MM/dd HH:mm:ss
	 * @param date
	 * @throws IOException
	 */
	public void writeMemberLoginRecords(Date date) throws IOException
	{
		File logFile = new File(name+"_LoginRecords");//open a temp file.
		FileWriter writer = new FileWriter(logFile,true);//initialize writer.
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			String s_log = dateFormat.format(date);
			System.out.println(s_log);
			writer.append(s_log+System.getProperty("line.separator"));
		
		writer.close();
	}
	
	/**
	 * writeServiceToFile() method writes a service information into service file belonging to this member. 
	 * The file name is member name + "ServicesFile", for example, "Kevin_ServicesFile".
	 * @param service a Service object. It contains information such as serviceName, serviceCode, memberID, providerID, serviceDate and time.
	 * @throws IOException
	 */
	public void writeServiceToFile(Service service) throws IOException
	{
		serviceList.add(service); //Add service to list first
		
		File servicesFile = new File(name+"_ServicesFile");//open a temp file.
		FileWriter writer = new FileWriter(servicesFile,true);//initialize writer.
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		writer.append("Service info:"+System.getProperty("line.separator"));
		writer.append("\n********************"+System.getProperty("line.separator"));
		Date dtime = service.getTdate();
		writer.append("Current date and time: "+dateFormat.format(dtime)+System.getProperty("line.separator"));
		
		String date = service.getDate();
		writer.append("Date service was provided: "+date+System.getProperty("line.separator"));
		
//		String serviceName = service.getServiceName();
//		writer.append(serviceName);
		
		int providerID = service.getProviderID();
		writer.append("Provider number: "+String.valueOf(providerID)+System.getProperty("line.separator"));
		
		int memberID = service.getMemberID();
		writer.append("Member number: "+String.valueOf(memberID)+System.getProperty("line.separator"));
		
		String memberName = this.getName();
		writer.append("Member name: "+memberName+System.getProperty("line.separator"));
		
		int serviceCode = service.getServiceCode();
		writer.append("Service code: "+String.valueOf(serviceCode)+System.getProperty("line.separator"));
		
		String comment = service.getComment();
		writer.append("Comments: "+comment+System.getProperty("line.separator"));
		
		//double fee = service.getFee();
		//writer.append("Fee: "+String.valueOf(fee)+System.getProperty("line.separator"));
		
		writer.append("********************"+System.getProperty("line.separator"));
		writer.close();
	}

	/*getters and setters.*/
	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public boolean isFeeDue() {
		return feeDue;
	}

	public void setFeeDue(boolean feeDue) {
		this.feeDue = feeDue;
	}
	
	public ArrayList<Service> getServiceList() {
		return serviceList;
	}
	
}
