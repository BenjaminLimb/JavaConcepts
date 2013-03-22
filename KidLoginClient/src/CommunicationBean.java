
import java.io.Serializable;
import java.util.HashMap;

public class CommunicationBean implements Serializable {
	
	private String command;
	private HashMap data;
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public HashMap getData() {
		return data;
	}
	public void setData(HashMap data) {
		this.data = data;
	}
}
