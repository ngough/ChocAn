package chocan;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Nate
 *
 */
public class ManagerMaintainer {
	private ArrayList<Manager> managerList;
	
	/**
	 * 
	 */
	public ManagerMaintainer() {
		managerList = new ArrayList<Manager>();
	} //End ManagerMaintainer() constructor.
	
	/**
	 * 
	 * @param managerID
	 */
	public void loginManager(int managerID) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("log in date and time: "+ dateFormat.format(date)); //2014/12/01 15:59:48
		
		for(int i = 0; i < managerList.size(); i++) {
			if(managerList.get(i).getManagerID() == managerID) {
				 managerList.get(i).addLoginRecord(date);//add log in date to provider login records.
			} //End if.
		} //End for loop.
		
		return;
	} //End loginManager(int) method.
	
	public boolean verifyManager(int managerID) {
		boolean verified = false;
		for(int i = 0; i < managerList.size(); i++) {
			if(managerList.get(i).getManagerID() == managerID) {//match given ID to those in arrayList.
				verified = true;
				break;
			} //End if.
		} //End for loop.
		
		return verified;
	} //End verifyManager(int) method.
	
	public Manager getManager(int managerID) {
		Manager m = null;
		for(int i = 0; i < managerList.size(); i++) {
			if(managerList.get(i).getManagerID() == managerID) {//match given ID to those in arrayList.
				m = managerList.get(i);
				break;
			} //End if.
		} //End for loop.
		
		return m;
	} //End getManager(int) method.
	
	public void printReports(MemberMaintainer memberMaintainer, ProviderMaintainer providerMaintainer) {
		
		
		
	} //End printReports(MemberMaintainer, ProviderMaintainer) method.
	
	
} //End ManagerMaintainer class.
