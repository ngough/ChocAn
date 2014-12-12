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
	public ManagerMaintainer() throws IOException {
		managerList = new ArrayList<Manager>();
		
		//this method get all managers from file and write them into arrayList.
		FileReader inputStream = null;
		try{
			inputStream = new FileReader("Managers");
			Scanner file = null;
			file = new Scanner(inputStream);
			
			String delims = "[ ]+";
			while(file.hasNext())
			{
				String info = file.nextLine();//read a line from file.
				String[] managerInfo = info.split(delims);
				Manager m = new Manager(managerInfo[0],managerInfo[1],managerInfo[2],managerInfo[3],managerInfo[4],Integer.parseInt(managerInfo[5]));
				//put this newly found provider to providerList.
				managerList.add(m);
			} //End while.
			 
			file.close();
			inputStream.close();
		} //End try.
		catch(Exception e){
			System.out.println("Managers file not found.");
		} //End catch.
		
		return;
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
		providerMaintainer.printProviderReports();
		memberMaintainer.printMemberReports();
		
		//Print summary report
		int totalConsults = 0;
		double totalFees = 0.0;
		int totalProviders = 0;
		File providerFile = null;
		BufferedReader reader = null;
		String currentLine = new String();
		String delims = "[ ]+";
		File file;
		FileWriter fw;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		
		try {
			providerFile = new File("Providers");
			reader = new BufferedReader(new FileReader(providerFile));
            file = new File("Summary "+dateFormat.format(date));
            if(!file.exists()) {
                file.createNewFile();
            } //End if.
            fw = new FileWriter(file);
            fw.write("Summary Report"+System.getProperty("line.separator"));
            fw.write("******************************"+System.getProperty("line.separator"));
            while((currentLine = reader.readLine()) != null) {			    
            	totalConsults = 0;
            	totalFees = 0.0;
            	String[] providerInfo = currentLine.split(delims);
				if(providerMaintainer.getProvider(Integer.parseInt(providerInfo[5])).getServiceList().size() != 0) {
					fw.write("Provider's name    : "+providerInfo[0]+System.getProperty("line.separator"));
					totalProviders++;
					ArrayList<Service> serviceList = providerMaintainer.getProvider(Integer.parseInt(providerInfo[5])).getServiceList();
					for(int i = 0; i < serviceList.size(); i++) {
						totalConsults++;
						totalFees = totalFees + serviceList.get(i).getFee();
					} //End for loop.
					
					fw.write("Total consultations: "+totalConsults+System.getProperty("line.separator"));
					fw.write("Total fees         : "+totalFees+System.getProperty("line.separator"));
					fw.write("*****"+System.getProperty("line.separator"));
				} //End if.
			} //End while loop.
            
            fw.write("Total providers    : "+totalProviders+System.getProperty("line.separator"));
            fw.write("Total Consultations: "+providerMaintainer.getTotalConsults()+System.getProperty("line.separator"));
            fw.write("Total Fees         : "+providerMaintainer.getTotalFees()+System.getProperty("line.separator"));
            fw.write("******************************"+System.getProperty("line.separator"));
            
            fw.flush();
            fw.close();
            reader.close();
		} //End try.
		catch (IOException e) {
			e.printStackTrace();
		} //End catch.
		
		System.out.println("Reports finished running.");
		return;
	} //End printReports(MemberMaintainer, ProviderMaintainer) method.
	
	public void writeDataFiles() throws IOException {
		Manager currentManager;
		File managerFile;
		PrintWriter pw;
		
		try {
			managerFile = new File("Managers");
			if(!managerFile.exists()) {
				managerFile.createNewFile();
			} //End if.
			pw = new PrintWriter(managerFile);
			pw.close(); //Clear the file.
			//Write to the file.
			pw = new PrintWriter("Managers");
			for(int i = 0; i < managerList.size(); i++) {
				currentManager = managerList.get(i);
				pw.println(currentManager.getName()+" "+currentManager.getStreet()+" "+currentManager.getCity()+" "+currentManager.getState()+" "+currentManager.getZip()+" "+currentManager.getManagerID());
			} //End for.
			
			pw.flush();
			pw.close();
		} //End try.
		catch(IOException e) {
			System.out.println("Error writing back Manager file!");
			e.printStackTrace();
		} //End catch.
		
		return;
	} //End writeDataFiles() method.
	
} //End ManagerMaintainer class.
