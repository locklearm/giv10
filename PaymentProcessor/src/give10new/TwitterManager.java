/**
 * 
 */
package give10new;

import java.io.IOException;
import java.util.ArrayList;

import give10.User;
import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Martin Locklear (locklear.martin@gmail.com)
 *
 */
public class TwitterManager implements StatusListener {
	
	public static String OAuthConsumerKey = "Se762MdYJHbi38qgiGG5VPu2g";
	public static String OAuthConsumerSecret = "KJgl4mPlqbkRZpDgHOduNQ4h7kRJxOb0V0eErCPol9KFXIKHAH";
	public static String OAuthAccessToken = "3820287795-0lLpR9jHcBVRKwjOWnN1gcHy3Rbrasx1af0sYd4";
	public static String OAuthAccessTokenSecret = "YvCYqAOKAeUmnWHSqZcsLVPUyW6hNG6FXEOYsll8QywZ5";
	
	private static TwitterManager instance;
	
	private TwitterStream twStream; 
	private Twitter tw;
	private ArrayList<String> giv10Tags = new ArrayList<String>();
	
	private TwitterManager() {
		
		//Store the filter tags
		//{"give10", "giv10", "giveten", "givten"}
		this.giv10Tags.add("give10");
		this.giv10Tags.add("giv10");
		this.giv10Tags.add("giveten");
		this.giv10Tags.add("Giveten");
		this.giv10Tags.add("givTen");
		this.giv10Tags.add("Give10");
		this.giv10Tags.add("Giv10");
		this.giv10Tags.add("Giveten");
		this.giv10Tags.add("GiveTen");
		this.giv10Tags.add("GivTen");
		
		//Set up credentials
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(OAuthConsumerKey)
		  .setOAuthConsumerSecret(OAuthConsumerSecret)
		  .setOAuthAccessToken(OAuthAccessToken)
		  .setOAuthAccessTokenSecret(OAuthAccessTokenSecret);
		
		//Setup and start the stream
		this.twStream = new TwitterStreamFactory(cb.build()).getInstance();
		this.twStream.addListener(this);
		
		//Setup the filter
		FilterQuery fq = new FilterQuery();
		fq.count(0);
		fq.track(this.giv10Tags.toArray(new String[this.giv10Tags.size()]));
		this.twStream.filter(fq);
		
		//Now we set up the twitter object, so that we can post
		this.tw = new TwitterFactory(cb.build()).getInstance();
		
	}
	public static TwitterManager getInstance() {
		if (TwitterManager.instance == null) {
			TwitterManager.instance = new TwitterManager();
		}
		return TwitterManager.instance;
	}

	/**
	 * This method will be called anytime we see a status that matches our giv10Tags.
	 * @param status
	 */
	@Override
	public void onStatus(Status status) {
		
		//First, we verify whether this is a tweet we are interested in
		if (!this.isGiv10Tweet(status)) {
			return;
		}
		
		//Is this a valid user
		User u = this.getUser(status);
		if (u == null) {
			//If this isn't a registered user, send them a message.
			this.sendResponse(ResponseBuilder.buildUnregisteredResponse(status));
			return;
		}
		
		//Get the campaign
		Campaign camp = this.getCampaign(status);
		if (camp == null) {
			//If there wasn't a campaign, send that response
			this.sendResponse(ResponseBuilder.buildRegisteredUserNoCampaignResponse(status));
			return;
		}
		
		//Process the payment
		boolean isPaymentSuccess = PaymentProcessor.processPayment(u);
		
		if (isPaymentSuccess) {
			//Send the success response
			this.sendResponse(ResponseBuilder.buildRegisteredUserResponse(status));
		}
		else {
			this.sendResponse(ResponseBuilder.buildPaymentFailedResponse(status));
		}
		
	}
	
	private boolean isGiv10Tweet(Status status) {
		
		for (HashtagEntity ht : status.getHashtagEntities()) {
			if (this.giv10Tags.contains(ht.getText())) {
				return true;
			}
		}
		//If we haven't found it at this point
		return false;
	}
	private give10.User getUser(Status status) {
		try {
			String twitterHandle = status.getUser().getScreenName(); 
			give10.User user = DatabaseConnector.getUserFromTwitterHandle(twitterHandle);
			return user;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Problem checking for user's existence");
		}
		return null;
	}
	private Campaign getCampaign(Status status) {
		Campaign c = new Campaign();
		c.name = "TestCamp";
		return c;
	}
	
	private void sendResponse(String s) {
		//TODO
	}
	
	@Override
	public void onException(Exception ex) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onStallWarning(StallWarning warning) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
