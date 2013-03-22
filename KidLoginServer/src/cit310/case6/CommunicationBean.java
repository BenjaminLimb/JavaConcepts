package cit310.case6;

import java.io.Serializable;
import java.util.HashMap;

public class CommunicationBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String command;
	private HashMap<String,Object> data;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public HashMap<String,Object> getData() {
		return data;
	}
	public void setData(HashMap<String,Object> data) {
		this.data = data;
	}
}
