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
		try{
			inputStream = new FileReader("Providers");
			Scanner file = null;
			file = new Scanner(inputStream);
			
			String delims = "[ ]+";
			while(file.hasNext())
			{
				String info = file.nextLine();//read a line from file.
				String[] providerInfo = info.split(delims);
				Provider p = new Provider(providerInfo[0],providerInfo[1],providerInfo[2],providerInfo[3],providerInfo[4],Integer.parseInt(providerInfo[5]));
				//put this newly found provider to providerList.
				providerList.add(p);
			} //End while.
			 
		} //End try.
		catch(Exception e){
			System.out.println("File not found.");
		} //End catch.
		try {
			inputStream.close();
			
		} //End try.
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //End catch.
	}
	
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

}
