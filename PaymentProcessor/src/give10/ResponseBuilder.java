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
	
	private static String ResponseRegisteredUserNoCampaign = "Sorry, you've tried to donate to a campaign that we don't support.";
	private static String ResponseRegisteredUser = "Thanks for supporting! ";
	private static String ResponsePaymentFailed = "Sorry, unable to process your donation.";
	
	public static String buildUnregisteredResponse(Status s) {
		
		String response = "";
		response += "Sorry @" + s.getUser().getScreenName() + " ";
		response += ", you have to register at givten.com to donate!";
		
		return response;
		
	}
	
	public static String buildRegisteredUserNoCampaignResponse(Status s) {
		
		return ResponseBuilder.ResponseRegisteredUserNoCampaign;
		
	}
	
	public static String buildRegisteredUserResponse(Status s) {
		
		return ResponseBuilder.ResponseRegisteredUser;
		
	}

	public static String buildPaymentFailedResponse(Status s) {
		return ResponseBuilder.ResponsePaymentFailed;
	}
	
}
