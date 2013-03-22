import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.quickconnect.json.JSONException;
import org.quickconnect.json.JSONInputStream;
import org.quickconnect.json.JSONOutputStream;

public class KidLoginClientApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Open Client Socket");
			
			Socket s = new Socket("localhost",8080);
			JSONOutputStream out = new JSONOutputStream(s.getOutputStream());
			JSONInputStream in = new JSONInputStream(s.getInputStream());
			
			CommunicationBean com = new CommunicationBean();
			com.setCommand("login");
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("username", "bob");
			params.put("password","12345");
			com.setData(params);
			
			System.out.println("Send request to server");
			out.writeObject(com);
			System.out.println("Request sent.");
			HashMap<String,Object> response = (HashMap<String,Object>) in.readObject();
			System.out.println("Response received.");
			
			// change to handler call
			System.out.println(response);
			String r = (String)response.get("command");
			System.out.println("Response:" + r);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
