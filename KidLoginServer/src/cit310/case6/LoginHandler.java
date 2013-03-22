package cit310.case6;

import java.util.HashMap;
import org.quickconnect.json.JSONOutputStream;

public class LoginHandler implements Handler{
	
	@Override
	public void handleIt(HashMap<String,Object> data) {
		String username = (String) data.get("username");
		String password = (String) data.get("password");
		Session session = (Session) data.get("session");
		JSONOutputStream out = (JSONOutputStream) session.getOutJSONStream();
		
		try {
			
			DataModel dm = new DataModel();
			
			CommunicationBean response = new CommunicationBean();
			System.out.println("Checking Credentials" + username + ":" +password );
			if(dm.authenticateUser(username, password)){
				session.setUserAuthenticated(true);
				response.setCommand("AuthenticationSuccessful");
				System.out.println("Yes");
			}
			else
			{
				response.setCommand("AuthenticationFailed");
				session.setUserAuthenticated(false);
				System.out.println("No");
			}
			out.writeObject(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
