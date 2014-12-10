package chocan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author lingxi
 * Service class is responsible for storing all the information about a service such as date, 
 * serviceName, time, memberID, providerID and serviceCode
 * 
 */
public class Service {
	
	private String date;
	private String serviceName;
	private Date tdate;
	private int memberID;
	private String memberName;
	private int providerID;
	private int serviceCode;
	private String comment;
	private double fee;
	

	

	/**
	 * Constructor.
	 * @param serviceName Name of service such as "dietitian".
	 * @param date Date of service: mm-dd-yyyy.
	 * @param tdate Date and time of service: mm-dd-yyyy hh:mm:ss
	 * @param memberID ID of member who received this service.
	 * @param providerID ID of provider who received this service.
	 * @param serviceCode 6 digit ID for a service, for example, 100000 stand for dietitian. 
	 * @param comment optional for a service. Any additional info about this service.
	 */
	public Service(String serviceName,String date, Date tdate,int memberID, String memberName, int providerID, int serviceCode, String comment, double fee)
	{ 
		this.serviceName = serviceName;this.tdate=tdate;this.memberID=memberID;this.memberName=memberName;this.providerID=providerID;this.serviceCode=serviceCode;this.date=date;this.comment=comment;this.fee=fee;
	}

	/**
	 * makeService() method creates a new service object. The difference of this method and Constructor is 
	 * this method takes serviceCode and search for the service name in provider directory file.
	 * @param date
	 * @param tdate
	 * @param memberID
	 * @param providerID
	 * @param serviceCode
	 * @param comment
	 * @return
	 */
	public static Service makeService(String date,Date tdate, int memberID, String memberName, int providerID, int serviceCode, String comment)
	{
		String serviceName = "";
		double fee = 0.00;
		
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
				if(serviceInfo[0].equals(String.valueOf(serviceCode)))
				{						
					serviceName = serviceInfo[1];
					fee = Double.parseDouble(serviceInfo[3]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Service(serviceName,date,tdate,memberID, memberName, providerID,serviceCode,comment, fee);
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getProviderID() {
		return providerID;
	}
	public void setProviderID(int providerID) {
		this.providerID = providerID;
	}
	
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
	
	public double getFee() {
		return fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	
}
