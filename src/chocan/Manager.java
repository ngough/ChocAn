package chocan;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 * 
 * @author Nate
 *
 */
public class Manager extends Person {
	int managerID;
	
	/**
	 * 
	 * @param n
	 * @param s
	 * @param c
	 * @param st
	 * @param z
	 * @param managerID
	 */
	public Manager(String n, String s, String c, String st, String z, int managerID) {
		super(n, s, c, st, z);
		this.managerID = managerID;
	} //End Manager(String, String, String, String, String, int) constructor.
	
	/**
	 * 
	 * @return
	 */
	public int getManagerID() {
		return managerID;
	} //End getManagerID() method.
	
	/**
	 * 
	 * @param managerID
	 */
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	} //End setManagerID(int) method.
	
	/**
	 * 
	 * @param date
	 * @throws IOException
	 */
	public void writeManagerLoginRecords() throws IOException {
		File logFile = new File(name+"_LoginRecords");//open a temp file.
		FileWriter writer = new FileWriter(logFile,true);//initialize writer.
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		Iterator<Date> iterator = loginRecords.iterator();
		while(iterator.hasNext())
		{
			Date log = (Date) iterator.next();
			String s_log = dateFormat.format(log);
			System.out.println(s_log);
			writer.append(s_log+System.getProperty("line.separator"));
		}
		writer.close();
		return;
	} //End writeManagerLoginRecords(date) method.
	
} //End Manager class.
