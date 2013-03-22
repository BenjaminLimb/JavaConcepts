package cit310.case6;

import java.util.HashMap;

public class ApplicationController {
	private static HashMap<String,Handler> handlers;
	static{
		HashMap<String,Handler> handlers = new HashMap<String,Handler>();
		handlers.put("login",new LoginHandler());
	}
	
	public void handleRequest(String command, HashMap<String,Object> data){
		Handler aHandler = handlers.get(command);
		aHandler.handleIt(data);
	}
}
