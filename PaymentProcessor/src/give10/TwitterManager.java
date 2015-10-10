/**
 * 
 */
package give10;

import java.io.IOException;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Martin Locklear (locklear.martin@gmail.com)
 *
 */
public class TwitterManager {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws TwitterException, IOException {
		
		System.out.println("Yo.");
		
		//Set up credentials
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("Se762MdYJHbi38qgiGG5VPu2g")
		  .setOAuthConsumerSecret("KJgl4mPlqbkRZpDgHOduNQ4h7kRJxOb0V0eErCPol9KFXIKHAH")
		  .setOAuthAccessToken("3820287795-0lLpR9jHcBVRKwjOWnN1gcHy3Rbrasx1af0sYd4")
		  .setOAuthAccessTokenSecret("YvCYqAOKAeUmnWHSqZcsLVPUyW6hNG6FXEOYsll8QywZ5");
		
//		//Set up our twitter connection
//		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
//		Twitter twitter = twitterFactory.getInstance();
//		
//		try {
//			Status status = twitter.updateStatus("Look, I just wrote a program to submit this!");
//			System.out.println("Successfully posted tweet.");
//		} catch (TwitterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Failed to upload a tweet.");
//		}
		
		
		 
	    StatusListener listener = new StatusListener() {
	        public void onStatus(Status status) {
	            System.out.println(status.getUser().getName() + " : " + status.getText());
	            
	            
	            
	            
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }
			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}
	    };
	    
	    TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	    
	    FilterQuery fq = new FilterQuery();
	    fq.count(0);
//	    fq.follow(3820287795l);
	    fq.track(new String[]{"give10", "giv10", "giveten", "givten"});
	    
	    
	    
	    twitterStream.addListener(listener);
	    twitterStream.filter(fq);
	    
	    
	    
	    
	    
	    

	    
	    
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
//	    twitterStream.sample();
	    
//	    twitterStream.notify();
		
		
		System.out.println("Yep.");

	}
	


}
