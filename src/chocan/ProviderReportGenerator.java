package chocan;

import java.util.*;

/**
 * @author Nate
 *
 */
public class ProviderReportGenerator {
	private double feeTotal; //Total fees paid on behalf of the member.
	private int    consultationTotal; //Total number of consultations the member received.
	
	/**
	 * 
	 */
	public ProviderReportGenerator() {
		feeTotal = 0.00;
		consultationTotal = 0;
		return;
	} //End ProviderReportGenerator() constructor.
	
	/**
	 * 
	 * @param providerID
	 * @param providerList
	 */
	public void printProviderReport(int providerID, ArrayList<Provider> providerList) {
		
		
	} //End printProviderReport(int, ArrayList<Provider>) method.

} //End ProviderReportGenerator class.
