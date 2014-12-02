package chocan;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Person {

	String name;
	String street;
	String city;
	String state;
	String zip;
//	String emailAddress;
	ArrayList loginRecords = new ArrayList<Date>();
	
	/* This method adds a login record to list */
	public void addLoginRecord(Date d) {
		loginRecords.add(d);
	}
//
//	public ArrayList<Date> getLoginRecords() {
//		return loginRecords;
//	}
	
	public void printLogins()
	{
		Iterator iterator = loginRecords.iterator();
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
