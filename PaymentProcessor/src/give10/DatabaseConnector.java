/**
 * 
 */
package give10;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
	public static String searchPath = "/find";
	
	public static User getUserFromTwitterHandle(String twitterHandle) throws IOException {
		
		//Open the connection and load the results
		URL url = new URL(DatabaseConnector.dbURL);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    request.connect();

	    // Convert to a JSON text to a manageable object
	    JsonParser jp = new JsonParser(); //from gson
	    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
	    JsonArray usersJSON = root.getAsJsonArray();
	    
	    // If no users were found, return null
	    if (usersJSON.size() < 1) {
	    	return null;
	    }
	    
	    //Get the first user that was returned with this twitter handle
	    JsonObject firstUserOb = usersJSON.get(0).getAsJsonObject();
	    
	    //Return it prepared
	    return DatabaseConnector.createUserObjectFromJson(firstUserOb);
	    
		
	}
	
	private static User createUserObjectFromJson(JsonObject userJsonObject) {

    	//Get the user data
    	JsonElement name = userJsonObject.get("name");
    	JsonElement email = userJsonObject.get("email");
    	JsonElement twitterhandle = userJsonObject.get("twitterhandle");
    	JsonElement customerid = userJsonObject.get("customerid");
    	
    	//Fill in the user object
    	User u = new User();
    	if (name != null) {
    		u.name = name.getAsString();
    	}
    	if (email != null) {
    		u.email = email.getAsString();
    	}
    	if (twitterhandle != null) {
    		u.twitterhandle = twitterhandle.getAsString();
    	}
    	else {
    		return null;  //If there's no twitter handle, then we need to return null (there is a null check later).
    	}
    	if (customerid != null) {
    		u.customerid = customerid.getAsString();
    	}
    	else {
    		return null;  //If there's no customer ID, then we need to return null (there is a null check later).
    	}
		
    	return u;
    	
	}
	
}
