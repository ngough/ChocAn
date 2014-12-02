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


public class Provider extends Person {

	private int providerID;
	private ArrayList serviceList = new ArrayList<Service>();
	
	public void writeServiceToFile(Service service) throws IOException
	{
		File servicesFile = new File(name+"_ServicesFile");//open a temp file.
		FileWriter writer = new FileWriter(servicesFile,true);//initialize writer.
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		writer.append("Service info:"+"\n********************");
		Date dtime = service.getTdate();
		writer.append(dateFormat.format(dtime));
		
		String date = service.getDate();
		writer.append(date);
		
//		String serviceName = service.getServiceName();
//		writer.append(serviceName);
		
		int providerID = service.getProviderID();
		writer.append(String.valueOf(providerID));
		
		int memberID = service.getMemberID();
		writer.append(String.valueOf(memberID));
		
		int serviceCode = service.getServiceCode();
		writer.append(String.valueOf(serviceCode));
		
		writer.close();
	}
	
	public Provider(String n, String s, String c, String st, String z,int id)
	{
		super(n,s,c,st,z);
		providerID=id;
	}

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
					System.out.println("The service corresponding to service code: "+serviceCode+"is "+serviceInfo[1]);
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
	
	public void writeProviderLoginRecords(int id) throws IOException
	{
		File logFile = new File(name+"_LoginRecords");//open a temp file.
		FileWriter writer = new FileWriter(logFile,true);//initialize writer.
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Iterator iterator = loginRecords.iterator();
		while(iterator.hasNext())
		{
			Date log = (Date) iterator.next();
			String s_log = dateFormat.format(log);
			System.out.println(s_log);
			writer.append(s_log+System.getProperty("line.separator"));
		}
		writer.close();
	}

	/*This method reads provider directory file and print it out. */
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
	public ArrayList getServiceList() {
		return serviceList;
	}
	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}
	
}
