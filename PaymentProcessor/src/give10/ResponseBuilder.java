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
	
	private static String ResponseNotRegisteredUser = "Sorry, you have to register at givten.com to donate!";
	private static String ResponseRegisteredUserNoCampaign = "Sorry, you've tried to donate to a campaign that we don't support.";
	private static String ResponseRegisteredUser = "Thanks for supporting! ";
	private static String ResponsePaymentFailed = "Sorry, unable to process your donation.";
	
	public static String buildUnregisteredResponse(Status s) {
		
		return s.getInReplyToStatusId() + ResponseBuilder.ResponseNotRegisteredUser;
		
	}
	
	public static String buildRegisteredUserNoCampaignResponse(Status s) {
		
		return s.getInReplyToStatusId() + ResponseBuilder.ResponseRegisteredUserNoCampaign;
		
	}
	
	public static String buildRegisteredUserResponse(Status s) {
		
		return s.getInReplyToStatusId() + ResponseBuilder.ResponseRegisteredUser;
		
	}

	public static String buildPaymentFailedResponse(Status s) {
		return s.getInReplyToStatusId() + ResponseBuilder.ResponsePaymentFailed;
	}
	
}
