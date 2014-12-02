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

public class ProviderMaintainer {

	ArrayList providerList = new ArrayList<Provider>();
//	
//	public void writeServiceToFile(Service service)
//	{
//		
//	}
	
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
			}
			 
		}
		catch(Exception e){
			System.out.println("File not found.");
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logOff()
	{
		
	}
	
	public void checkProviderDirectory()
	{
		((Provider) providerList.get(0)).checkProviderDirectory();
	}

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
	
	/*This method verify a provider.*/
	public boolean verifyProvider(int ID)
	{
		boolean found=false;
		for(int i=0;i<providerList.size();i++)
		{
			if(((Provider) providerList.get(i)).getProviderID()==ID)
			{//match given ID to those in arrayList.
				found=true;//remove the provider.
				break;
			}
		}
		return found;
	}
	
	public void printProviders()
	{
		Iterator iterator = providerList.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}
	
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

}
