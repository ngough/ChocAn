package chocan;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
 *  1.Provider class is responsible for storing information for one provider. 
 *  2.It extends Person class.
 */
public class Provider extends Person {

	/**
	 * providerID is for storing ID for a provider.
	 */
	private int providerID;
	/**
	 * serviceList is for storing all the services provided by this provider.
	 */
	private ArrayList<Service> serviceList = new ArrayList<Service>();
	
	/**
	 * Constructor.
	 * @param n provider name.
	 * @param s provider street.
	 * @param c provider city.
	 * @param st provider state.
	 * @param z provider zipCode.
	 * @param id provider ID.
	 */
	public Provider(String n, String s, String c, String st, String z,int id)
	{
		super(n,s,c,st,z);
		providerID=id;
	}
	/**
	 * writeServiceToFile() method writes a service information into service file belonging to this provider. 
	 * The file name is provider name + "ServicesFile", for example, "Kevin_ServicesFile".
	 * @param service a Service object. It contains information such as serviceName, serviceCode, memberID, providerID, serviceDate and time.
	 * @throws IOException
	 */
	public void writeServiceToFile(Service service) throws IOException
	{
		serviceList.add(service); //Add service to list first.
		
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
		
		int serviceCode = service.getServiceCode();
		writer.append("Service code: "+String.valueOf(serviceCode)+System.getProperty("line.separator"));
		
		String comment = service.getComment();
		writer.append("Comments: "+comment+System.getProperty("line.separator"));
		
		writer.append("********************"+System.getProperty("line.separator"));
		writer.close();
	}

	/**
	 * SearchService() method searches a specific service in "Provider_Directory" which contains serviceCode and corresponding serviceName.
	 * @param serviceCode is serviceCode for a service.
	 * @return true if the serviceName corresponding to that serviceCode is found. Otherwise returns false.
	 */
	public boolean searchService(String serviceCode)
	{
		boolean valid = false;
		File directoryFile = new File("Provider_Directory");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(directoryFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String currentLine;
		String delims = "[ ]+";

		try {//read and parse on line.
			boolean found = false;
			while((currentLine = reader.readLine()) != null) 
			{			    
				String[] serviceInfo = currentLine.split(delims);
				if(serviceInfo[0].equals(serviceCode))
				{	
					found = true;
					System.out.println("The service corresponding to service code "+serviceCode+" is "+serviceInfo[1]);
					valid = true;
					break;
				}
			}
			if(found == false)
			{
				System.out.println("No service found, Please re-enter service code: ");
				valid = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return valid;
	}
	
	/**
	 * writeProviderLoginRecords() method writes a login date and time into file.
	 * The file name is provider's name + "_LoginRecords", for example, Kevin_LoginRecords.
	 * The file will contain date and time for each login session in each line. The format is yyyy/MM/dd HH:mm:ss.
	 */
	public void writeProviderLoginRecords() throws IOException
	{
		File logFile = new File(name+"_LoginRecords");//open a temp file.
		FileWriter writer = new FileWriter(logFile,true);//initialize writer.
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Iterator<Date> iterator = loginRecords.iterator();
		while(iterator.hasNext())
		{
			Date log = (Date) iterator.next();
			String s_log = dateFormat.format(log);
			System.out.println(s_log);
			writer.append(s_log+System.getProperty("line.separator"));
		}
		writer.close();
	}

	/**
	 * checkProviderDirectory() method will print out all the service code and its corresponding service name on the console.
	 */
	public void checkProviderDirectory()
	{
		File inputFile = new File("Provider_Directory");//open input file.
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String currentLine;//hold current line.
		
		try {
			while((currentLine = reader.readLine()) != null) 
			{
				System.out.println(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * toString() method returns a string of provider information.
	 */
	public String toString()
	{
		String s = name+" "+street+" "+city+" "+state+" "+zip+" "+providerID;
		return s;
	}
	/*Setters and getters*/
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	public ArrayList<Service> getServiceList() {
		return serviceList;
	}
	public void setServiceList(ArrayList<Service> serviceList) {
		this.serviceList = serviceList;
	}
	
}
