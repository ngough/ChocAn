package chocan;
import java.util.ArrayList;

public class Member extends Person{
	
	private int memberID;
	private ArrayList serviceList = new ArrayList<Service>();
	boolean feeDue;
	
	public Member(String n, String s, String c, String st, String z, int id, boolean fd) {
		super(n, s, c, st, z);
		memberID = id;
		feeDue = fd;
	}
	/*getters and setters.*/
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public ArrayList getServiceList() {
		return serviceList;
	}
	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}
	public boolean isFeeDue() {
		return feeDue;
	}
	public void setFeeDue(boolean feeDue) {
		this.feeDue = feeDue;
	}
	
}
