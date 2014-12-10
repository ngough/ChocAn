package chocan;

public class Operator extends Person {
	int operatorID;
	
	public Operator(String n, String s, String c, String st, String z, int operatorID) {
		super(n, s, c, st, z);
		this.operatorID = operatorID;
		return;
	}
	
	public int getOperatorID() {
		return operatorID;
	}
	
	public void setOperatorID(int operatorID) {
		this.operatorID = operatorID;
		return;
	}
	
}
