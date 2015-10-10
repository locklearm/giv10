/**
 * 
 */
package give10;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Martin Locklear (locklear.martin@gmail.com)
 *
 */
public class PaymentProcessor {

	public static ConcurrentHashMap<String, User> users = new ConcurrentHashMap<String, User>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PaymentProcessor pp = new PaymentProcessor();
		pp.Go();
		
		

	}
	
	public void Go() {
		
		//Start the user fetcher
		Thread userFetchingThread = new Thread(new UserFetcher());
		userFetchingThread.start();
		
		
	}

	
	public class UserFetcher implements Runnable {

		@Override
		public void run() {
			
			//Every 10 seconds, update the users map
			while (true) {
				
				//Update the users table
				try {
					List<User> users = DatabaseConnector.getUsers();
					for (User u : users) {
						PaymentProcessor.users.put(u.twitterhandle, u);
						System.out.println("saving user: " + u.name);
					}
				} catch (Exception e) {
					System.out.println("Problem fetching latest users");
				}
				
				//Sleep for 10 seconds
				try {
					Thread.sleep(5000l);
					String userString = "Users in memory -> ";
					for (User u : PaymentProcessor.users.values()) {
						userString += u.twitterhandle;
						userString += ", ";
					}
					System.out.println(userString);
					Thread.sleep(5000l);
				} catch (InterruptedException e) {
					System.out.println("Exiting the user fetcher");
				}
				
			}
		}
		
	}
}
