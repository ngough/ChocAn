package chocan;

import java.util.*;
import java.io.*;

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
            file = new File("ProviderReport-"+providerID+".txt");
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
            
            fw.flush();
            fw.close();
            System.out.println("Report generated successfully!");
        } //End try.
        catch (IOException e) {
            e.printStackTrace();
        } //End catch.
		
        return;
	} //End printProviderReport(int, ArrayList<Provider>) method.

} //End ProviderReportGenerator class.
