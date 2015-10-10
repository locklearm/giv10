/**
 * 
 */
package give10;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author Martin Locklear (locklear.martin@gmail.com)
 *
 */
public class DatabaseConnector {
	
	public static String dbURL = "http://giveten.herokuapp.com/user";
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		getUsers();
	}
	
	public static ArrayList<User> getUsers() throws Exception {
		
		//Open the connection and load the object
		URL url = new URL(dbURL);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    request.connect();

	    // Convert to a JSON object to print data
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
//	    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
//	    JsonArray users = rootobj.get("memberName").getAsJsonArray();
	    JsonArray usersJSON = root.getAsJsonArray();
	    
	    //Create the list to store the users
	    ArrayList<User> users = new ArrayList<>();
	    
	    Iterator<JsonElement> userIter = usersJSON.iterator();
	    while (userIter.hasNext()) {
	    	JsonElement curUserEl = userIter.next();
	    	JsonObject curUserOb = curUserEl.getAsJsonObject();
	    	User u = new User();
	    	
	    	//Fill in the user data
	    	
	    	JsonElement name = curUserOb.get("name");
	    	JsonElement email = curUserOb.get("email");
	    	JsonElement twitterhandle = curUserOb.get("twitterhandle");
	    	JsonElement customerid = curUserOb.get("customerid");
	    	
	    	if (name != null) {
	    		u.name = name.getAsString();
	    	}
	    	if (email != null) {
	    		u.email = email.getAsString();
	    	}
	    	if (twitterhandle != null) {
	    		u.twitterhandle = twitterhandle.getAsString();
	    	}
	    	if (customerid != null) {
	    		u.customerid = customerid.getAsString();
	    	}
	    	
	    	users.add(u);
	    }
	    
	    return users;
	}

}
