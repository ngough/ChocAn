package chocan;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;


public class Service {
	
	private String date;
	private String serviceName;
	private Date tdate;
	private int memberID;
	private int providerID;
	private int serviceCode;
	
	public Service(String serviceName,String date, Date tdate,int memberID, int providerID, int serviceCode)
	{ 
		this.serviceName = serviceName;this.tdate=tdate;this.memberID=memberID;this.providerID=providerID;this.serviceCode=serviceCode;this.date=date;
	}

	public static Service makeService(String date,Date tdate, int memberID, int providerID, int serviceCode)
	{
		String serviceName="";
		
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
			while((currentLine = reader.readLine()) != null) 
			{			    
				String[] serviceInfo = currentLine.split(delims);
				if(serviceInfo[0].equals(serviceCode))
				{						
					serviceName = serviceInfo[0];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Service(serviceName,date,tdate,memberID, providerID,serviceCode);
	}
	public Date getTdate() {
		return tdate;
	}

	public void setTdate(Date tdate) {
		this.tdate = tdate;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	private String comment;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(Date tdate) {
		this.tdate = tdate;
	}
	public int getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(int serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
