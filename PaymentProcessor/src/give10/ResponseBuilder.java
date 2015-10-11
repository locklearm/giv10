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
	
	public static String buildUnregisteredResponse(Status s, Campaign c) {
		
		String response = "";
		response += "Sorry @" + s.getUser().getScreenName();
		response += ", you have to register at givten.com to donate to " + c.name + "!";
		
		return response;
		
	}
	
	public static String buildRegisteredUserNoCampaignResponse(Status s) {

		String response = "";
		response += "Sorry @" + s.getUser().getScreenName();
		response += ", you seem to have tried to donate to a campaign that we don't support... yet.";
		
		return response;
		
	}
	
	public static String buildRegisteredUserResponse(Status s, Campaign c) {
		
		String response = "";
		response += "Thanks @" + s.getUser().getScreenName() + ".";
		response += " Your donation to " + c.name + " has been processed.";
		
		return response;
		
	}

	public static String buildPaymentFailedResponse(Status s, Campaign c) {
		
		String response = "";
		response += "Sorry @" + s.getUser().getScreenName() + " .";
		response += " We were unable to process your donation to ";
		response += c.name + ".";
		
		return response;
	}
	
}
