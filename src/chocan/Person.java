package chocan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * @author lingxi
 * Person class is for storing basic information of one person such as name, address, loginRecords.
 * It can be extended to Provider, Manager, Member, and operator.
 */
public class Person {

	String name;
	String street;
	String city;
	String state;
	String zip;
	/**
	 * loginRecords is an arrayList to store all the login date and time.
	 */
	ArrayList<Date> loginRecords = new ArrayList<Date>();
	
	
	/**
	 * addLoginRecords() method adds a login record to loginList.
	 * @param d Date object. It has the form of mm/dd/yyyy hh:mm:ss.
	 */
	public void addLoginRecord(Date d) {
		loginRecords.add(d);
	}
	
	/**
	 * printLogins() method prints out all the loginRecords of a person.
	 */
	public void printLogins()
	{
		Iterator<Date> iterator = loginRecords.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
	}

	/* Bunch of getters and setters */
	
	public Person(String n, String s, String c, String st, String z)
	{
		name = n; street=s;city=c;state=st;zip=z;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

//	public String getEmailAddress() {
//		return emailAddress;
//	}
//
//	public void setEmailAddress(String emailAddress) {
//		this.emailAddress = emailAddress;
//	}

}
