package cit310.case6;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import org.quickconnect.json.JSONInputStream;
import org.quickconnect.json.JSONOutputStream;

public class Session implements Runnable{
	private ApplicationController controller;
	private boolean userAuthenticated = false;
	
	private Socket socket;
	private boolean keepAlive = true;
	private JSONInputStream inJSONStream;
	private JSONOutputStream outJSONStream;
	
	public Session(Socket pSocket){
		socket = pSocket;
		try {
			inJSONStream = new JSONInputStream(socket.getInputStream());
			outJSONStream = new JSONOutputStream(socket.getOutputStream());
		} catch (IOException e){
			e.printStackTrace();
		}
	}	
	@Override
	public void run() {
		try {
			while(keepAlive){
				
				@SuppressWarnings("unchecked")
				HashMap<String,Object> dataIn = (HashMap<String,Object>) inJSONStream.readObject();
				String command = (String) dataIn.get("command");
				
				@SuppressWarnings("unchecked")
				HashMap<String,Object> data = (HashMap<String,Object>) dataIn.get("data");
				
				data.put("session",this);
				
				controller.handleRequest(command, data);			
				}
		} catch (Exception e) {
			System.out.println("Session Terminated");
			// will enter upon client disconnect.
		}	
		
	}
	public ApplicationController getController() {
		return controller;
	}
	public void setController(ApplicationController controller) {
		this.controller = controller;
	}
	public boolean isUserAuthenticated() {
		return userAuthenticated;
	}
	public void setUserAuthenticated(boolean userAuthenticated) {
		this.userAuthenticated = userAuthenticated;
	}
	public JSONInputStream getInJSONStream() {
		return inJSONStream;
	}
	public void setInJSONStream(JSONInputStream inJSONStream) {
		this.inJSONStream = inJSONStream;
	}
	public JSONOutputStream getOutJSONStream() {
		return outJSONStream;
	}
	public void setOutJSONStream(JSONOutputStream outJSONStream) {
		this.outJSONStream = outJSONStream;
	}
 
}
