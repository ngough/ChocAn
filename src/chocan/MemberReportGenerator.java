package chocan;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * @author Nate
 *
 */
public class MemberReportGenerator {
	
	
	/**
	 * 
	 */
	public MemberReportGenerator() {
		return;
	} //End MemberReportGenerator() constructor.
	
	/**
	 * 
	 * @param memberID
	 * @param memberList
	 */
	public void printMemberReport(Member member) {
		FileWriter         fw          = null;
        File               file        = null;
        //Member             member      = null;
        ArrayList<Service> serviceList = member.getServiceList();
        //boolean            memberFound = false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        //for(int i = 0; i < memberList.size(); i++) {
        //	if(memberList.get(i).getMemberID() == memberID) {
        //		member = memberList.get(i);
        //		serviceList = member.getServiceList();
        //		memberFound = true;
        //		break;
        //	} //End if.
        //} //End for loop.
        //if(memberFound == false) {
        //	System.out.println("The given member ID was not found in the list. Operation unsuccessful.");
        //	return;
        //} //End if.
        
        try {
            file = new File(member.getMemberID()+" "+dateFormat.format(date)+".txt");
            if(!file.exists()) {
                file.createNewFile();
            } //End if.
            fw = new FileWriter(file);
            fw.write("Member Report"+System.getProperty("line.separator"));
            fw.write("******************************"+System.getProperty("line.separator"));
            fw.write("Member Name: "+member.getName()+System.getProperty("line.separator"));
            fw.write("Member No. : "+member.getMemberID()+System.getProperty("line.separator"));
            fw.write("Street     : "+member.getStreet()+System.getProperty("line.separator"));
            fw.write("City       : "+member.getCity()+System.getProperty("line.separator"));
            fw.write("State      : "+member.getState()+System.getProperty("line.separator"));
            fw.write("Zip        : "+member.getZip()+System.getProperty("line.separator"));
            fw.write("Services:"+System.getProperty("line.separator"));
            for(int i = 0; i < serviceList.size(); i++) {
            	fw.write("Date of Service: "+serviceList.get(i).getDate()+System.getProperty("line.separator"));
            	fw.write("Provider Name  : "+serviceList.get(i).getProviderName()+System.getProperty("line.separator"));
            	fw.write("Service Name   : "+serviceList.get(i).getServiceName()+System.getProperty("line.separator"));
            } //End for loop.
            fw.write("******************************"+System.getProperty("line.separator"));
            
            fw.flush();
            fw.close();
        } //End try.
        catch(IOException e) {
        	System.out.println("Error writing the member report!");
        	e.printStackTrace();
        } //End catch.
	} //End printMemberReport(int, ArrayList<member>) method.
	
} //End MemberReportGenerator class.
