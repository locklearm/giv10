/**
 * 
 */
package give10;

import twitter4j.Status;

/**
 * @author Martin Locklear (locklear.martin@gmail.com)
 *
 */
public class ResponseBuilder {
	
	public static String buildUnregisteredResponse(Status s) {
		
		String response = "";
		response += "Sorry @" + s.getUser().getScreenName();
		response += ", you have to register at givten.com to donate!";
		
		return response;
		
	}
	
	public static String buildRegisteredUserNoCampaignResponse(Status s) {

		String response = "";
		response += "Sorry @" + s.getUser().getScreenName();
		response += ", you seem to have tried to donate to a campaign that we don't support... yet.";
		
		return response;
		
	}
	
	public static String buildRegisteredUserResponse(Status s) {
		
		String response = "";
		response += "Thanks @" + s.getUser().getScreenName() + ".";
		response += " Your payment has been processed.";
		
		return response;
		
	}

	public static String buildPaymentFailedResponse(Status s) {
		
		String response = "";
		response += "Sorry @" + s.getUser().getScreenName() + ".";
		response += " We were unable to process your donation.";
		
		return response;
	}
	
}
