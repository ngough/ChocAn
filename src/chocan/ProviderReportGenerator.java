package chocan;

import java.util.*;
import java.io.*;
import java.text.*;

/**
 * @author Nate
 *
 */
public class ProviderReportGenerator {
	private double feeTotal; //Total fees paid on behalf of the member.
	private int    consultationTotal; //Total number of consultations the member received.
	
	/**
	 * 
	 */
	public ProviderReportGenerator() {
		feeTotal = 0.00;
		consultationTotal = 0;
		return;
	} //End ProviderReportGenerator() constructor.
	
	/**
	 * 
	 * @param providerID
	 * @param providerList
	 */
	public void printProviderReport(int providerID, ArrayList<Provider> providerList) {
		FileWriter         fw =            null;
        File               file =          null;
        Provider           provider =      null;
        ArrayList<Service> serviceList =   null;
        boolean            providerFound = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        
        for(int i = 0; i < providerList.size(); i++) {
        	if(providerList.get(i).getProviderID() == providerID) {
        		provider = providerList.get(i);
        		serviceList = provider.getServiceList();
        		providerFound = true;
        		break;
        	} //End if.
        } //End for loop.
        if(providerFound == false) {
        	System.out.println("The given provider ID was not found in the list. Operation unsuccessful.");
        	return;
        } //End if.
        
        try {
            file = new File(providerID+" "+dateFormat.format(date)+".txt");
            if(!file.exists()) {
                file.createNewFile();
            } //End if.
            fw = new FileWriter(file);
            fw.write("Provider Report"+System.getProperty("line.separator"));
            fw.write("******************************"+System.getProperty("line.separator"));
            fw.write("Provider Name: "+provider.getName()+System.getProperty("line.separator"));
            fw.write("Provider No. : "+provider.getProviderID()+System.getProperty("line.separator"));
            fw.write("Street       : "+provider.getStreet()+System.getProperty("line.separator"));
            fw.write("City         : "+provider.getCity()+System.getProperty("line.separator"));
            fw.write("State        : "+provider.getState()+System.getProperty("line.separator"));
            fw.write("Zip          : "+provider.getZip()+System.getProperty("line.separator"));
            fw.write("Services:"+System.getProperty("line.separator"));
            for(int i = 0; i < serviceList.size(); i++) {
            	fw.write("Date of Service       : "+serviceList.get(i).getDate()+System.getProperty("line.separator"));
            	fw.write("Date and Time Received: "+serviceList.get(i).getTdate().toString()+System.getProperty("line.separator"));
            	fw.write("Member Name           : "+serviceList.get(i).getMemberName()+System.getProperty("line.separator"));
            	fw.write("Member No.            : "+serviceList.get(i).getMemberID()+System.getProperty("line.separator"));
            	fw.write("Service Code          : "+serviceList.get(i).getServiceCode()+System.getProperty("line.separator"));
            	fw.write("Fee                   : "+serviceList.get(i).getFee()+System.getProperty("line.separator"));
            	consultationTotal++;
            	feeTotal = feeTotal + serviceList.get(i).getFee();
            } //End for loop.
            fw.write("Total Consultations: "+consultationTotal+System.getProperty("line.separator"));
            fw.write("Total Fees         : "+feeTotal+System.getProperty("line.separator"));
            fw.write("******************************"+System.getProperty("line.separator"));
            fw.flush();
            fw.close();
            
            //Write EFT report to file.
            file = new File("EFT "+providerID+" "+dateFormat.format(date)+".txt");
            if(!file.exists()) {
                file.createNewFile();
            } //End if.
            fw = new FileWriter(file);
            fw.write(provider.getName()+" "+providerID+" $"+feeTotal+System.getProperty("line.separator"));
            fw.flush();
            fw.close();
            
            System.out.println("Report generated successfully!");
        } //End try.
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error printing report.");
        } //End catch.
		
        return;
	} //End printProviderReport(int, ArrayList<Provider>) method.

} //End ProviderReportGenerator class.
