package chocan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * 
 * @author lingxi
 * 
 * 	1.ProviderMaintainer Class is responsible for storing and manipulating providers.
 * 	2.When a new ProviderMaintaner class object gets instantiated, it read from a file which stores providers' information,
 * 	  creates provider objects, and dump all providers into an arrayList called providerList.
 *  3.This class can then process those providers in the list and write them back to file after all the changes been done.
 *
 */
public class ProviderMaintainer {
	
	/**
	 * providerList stores all provider objects created from "Providers" file.
	 */
	ArrayList<Provider> providerList = new ArrayList<Provider>();
	
	/**
	 * Constructor. It reads "Provider" file, creates provider objects according to file contents, 
	 * and store them in providerList arrayList
	 * @throws FileNotFoundException
	 */
	public ProviderMaintainer() throws FileNotFoundException
	{
		//this method get all providers from file and write them into arrayList.
		FileReader inputStream = null;
		Scanner scanner = null;
		String delims = "[ ]+";
		
		try{
			inputStream = new FileReader("Providers");
			scanner = new Scanner(inputStream);
			
			while(scanner.hasNext())
			{
				String info = scanner.nextLine();//read a line from file.
				String[] providerInfo = info.split(delims);
				Provider p = new Provider(providerInfo[0],providerInfo[1],providerInfo[2],providerInfo[3],providerInfo[4],Integer.parseInt(providerInfo[5]));
				//put this newly found provider to providerList.
				providerList.add(p);
			} //End while.
			 
			scanner.close();
			inputStream.close();
		} //End try.
		catch(Exception e){
			System.out.println("Error reading providers into file.");
		} //End catch.
		
		
	} //End ProviderMaintainer() constructor.
	
	/**
	 * checkProviderDirectory() method calls checkProviderDirectory() method in Provider class
	 * and prints out provider directory which contains service code + service name.
	 */
	public void checkProviderDirectory()
	{
		((Provider) providerList.get(0)).checkProviderDirectory();
	}

	/**
	 * loginProvider() method logs in a provider by taking that provider's ID, find that provider in providerList, 
	 * and writing current date+time into that provider's loginRecords
	 * @param providerID is the ID number for the provider who wants to log in the system.
	 */
	public void loginProvider(int providerID)
	{
		//get current date.
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println("log in date and time: "+ dateFormat.format(date)); //2014/12/01 15:59:48
		
		for(int i=0;i<providerList.size();i++)
		{
			if(((Provider) providerList.get(i)).getProviderID()==providerID)
			{
				 ((Person) providerList.get(i)).addLoginRecord(date);//add log in date to provider login records.
			}
		}
		
	}
	
	/**
	 * 
	 * @param p
	 */
	public void addProvider(Provider p) {
		providerList.add(p);
	} //End addProvider(Provider) method.
	
	/**
	 * removeProvider() method removes a provider from both providerList as well as "Providers" file.
	 * @param providerID is the ID for the provider who will be deleted from the system.
	 */
	public void removeProvider(int providerID)
	{
		//delete from list.
		String s = null;
		for(int i=0;i<providerList.size();i++)
		{
			if(((Provider) providerList.get(i)).getProviderID()==providerID)
			{
				s = providerList.get(i).toString();
				providerList.remove(i);//add log in date to provider login records.
			}
		}
		//delete from file.
		deleteProviderFromFile(s);
	}
	
	/**
	 * deleteProviderFromFile() method will delete a provider from "Providers" file. The method takes providerInfo which is
	 * a string and tries to search it in "Providers" file. If it's found, it removes that line from the file.
	 * @param providerInfo a string contains provider's name, address, id. The format is the same as that in "Providers" file.
	 */
	private static void deleteProviderFromFile(String providerInfo)
	{
		File inputFile = new File("Providers");//open input file.
		File tempFile = new File("ProvidersTemp");//open a temp file.

		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedWriter writer = null;//initialize writer.
		try {
			writer = new BufferedWriter(new FileWriter(tempFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String lineToRemove = providerInfo;
		String currentLine;

		try {
			while((currentLine = reader.readLine()) != null) 
			{			    
			    if(currentLine.equals(lineToRemove))
			    	continue;
			    else
			    	writer.write(currentLine+System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//delete file
		File f = new File("Providers");
		boolean success1 = f.delete();		
		boolean success2 = tempFile.renameTo(inputFile);
	}
	
	/**
	 * VerifyProvider() method will search the provider in the providerList by matching an ID number. 
	 * @param ID is the ID for the provider which will be searched.
	 * @return true if the provider is found and false if it's not in the list or file.
	 */
	public boolean verifyProvider(int ID)
	{
		boolean found=false;
		for(int i=0;i<providerList.size();i++)
		{
			if(((Provider) providerList.get(i)).getProviderID()==ID)
			{//match given ID to those in arrayList.
				found=true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * printProviders() will prints out all the providers and their information.
	 */
	public void printProviders()
	{
		Iterator<Provider> iterator = providerList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
	/**
	 * This method takes an ID and search through providerList. It will return the provider with the given ID.
	 * @param providerID is the ID for the provider that we want to get.
	 * @return provider of given ID.
	 */
	public Provider getProvider(int providerID)
	{
		Provider p = null;
		for(int i=0;i<providerList.size();i++)
		{
			if(((Provider) providerList.get(i)).getProviderID()==providerID)
			{//match given ID to those in arrayList.
				p = (Provider) providerList.get(i);
				break;
			}
		}
		return p;
	}
	
	public void printProviderReports() {
		ProviderReportGenerator providerReportGenerator = new ProviderReportGenerator();
		
		for(int i = 0; i < providerList.size(); i++) {
			providerReportGenerator.printProviderReport(providerList.get(i));
		} //End for loop.
		
		return;
	} //End printProviderReport() method.
	
	public int getTotalConsults() {
		int total = 0;
		for(int i = 0; i < providerList.size(); i++) {
			total = total + providerList.get(i).getServiceList().size();
		} //End for loop.
		
		return total;
	} //End getTotalConsults() method.
	
	public int getTotalActiveProviders() {
		int total = 0;
		for(int i = 0; i < providerList.size(); i++) {
			if(providerList.get(i).getServiceList().size() > 0) {
				total = total + 1;
			} //End if.
		} //End for loop.
		
		return total;
	} //End getTotalActiveProviders() method.
	
	public double getTotalFees() {
		double totalFees = 0.0;
		ArrayList<Service> serviceList;
		
		for(int i = 0; i < providerList.size(); i++) {
			if(providerList.get(i).getServiceList().size() > 0) {
				serviceList = providerList.get(i).getServiceList();
				
				for( int j = 0; j < serviceList.size(); j++) {
					totalFees = totalFees + serviceList.get(j).getFee();
				} //End for loop.
			} //End if.
		} //End for loop.
		
		return totalFees;
	} //End getTotalFees() method.
	
	public void readServicesFromFile() {
		//Read services into providers' service lists.
		File serviceFile;
		Scanner scanner = null;
		String currentLine;
		String lineSegment;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date;
		Date tDate;
		int providerNumber;
		int memberNumber;
		String memberName;
		int serviceCode;
		String comment;
		
		try {
			for(int i = 0; i < providerList.size(); i++) {
				serviceFile = new File(providerList.get(i).getName()+"_ServicesFile");
				if(!serviceFile.exists()) {
					//No services for this provider.
					continue;
				} //End if.
				scanner = new Scanner(serviceFile);
				
				//old
				while(scanner.hasNext()) {
					currentLine = scanner.nextLine();
					if(currentLine.contains("********************")) {
						if(scanner.hasNext()) {
							currentLine = scanner.nextLine();
							if(currentLine.contains("Current date and time:")) {
								//get current date when service was entered.
								lineSegment = currentLine.substring(22);
								tDate = dateFormat.parse(lineSegment);
								//Get the date service was received.
								currentLine = scanner.nextLine();
								date = currentLine.substring(27);
								//Get the provider number.
								currentLine = scanner.nextLine();
								providerNumber = Integer.parseInt(currentLine.substring(17));
								//Get the member number.
								currentLine = scanner.nextLine();
								memberNumber = Integer.parseInt(currentLine.substring(15));
								//Get the member name.
								currentLine = scanner.nextLine();
								memberName = currentLine.substring(13);
								//Get the service code.
								currentLine = scanner.nextLine();
								serviceCode = Integer.parseInt(currentLine.substring(14));
								//Get the comments.
								currentLine = scanner.nextLine();
								comment = currentLine.substring(10);
								
								//Create the Service object and add it to this provider's list.
								providerList.get(i).getServiceList().add(Service.makeService(date, tDate, memberNumber, memberName, providerNumber, getProvider(providerNumber).getName(), serviceCode, comment));
								
								Service s = Service.makeService(date, tDate, memberNumber, memberName, providerNumber, getProvider(providerNumber).getName(), serviceCode, comment);
								System.out.println(s.toString());
							} //End if.
							else {
								continue;
							} //End else.
						} //End if.
					} //End if.
				} //End while loop.
			} //End for.
			
			scanner.close();
			System.out.println("Provider service lists have been initialized.");
		} //End try.
		catch(Exception e) {
			System.out.println("Error reading services into providers' service lists.");
			e.printStackTrace();
		} //End catch.
		
		return;
	} //End readServicesFromFile() method.
	
	public void writeDataFiles() {
		Provider currentProvider;
		File providerFile;
		PrintWriter pw;
		
		try {
			providerFile = new File("Providers");
			if(!providerFile.exists()) {
				providerFile.createNewFile();
			} //End if.
			pw = new PrintWriter(providerFile);
			pw.close(); //Clear the file.
			//Write to the file.
			pw = new PrintWriter("Providers");
			for(int i = 0; i < providerList.size(); i++) {
				currentProvider = providerList.get(i);
				pw.println(currentProvider.getName()+" "+currentProvider.getStreet()+" "+currentProvider.getCity()+" "+currentProvider.getState()+" "+currentProvider.getZip()+" "+currentProvider.getProviderID());
			} //End for.
			
			pw.flush();
			pw.close();
		} //End try.
		catch(IOException e) {
			System.out.println("Error writing back Provider file!");
			e.printStackTrace();
		} //End catch.
		
		return;
	} //End writeDataFiles() method.
	
} //End ProviderMaintainer class.
