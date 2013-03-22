package cit310.case6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class KidLoginServerApp implements Runnable{
	private boolean keepRunning = true;
	
	private ApplicationController controller;
	
	public KidLoginServerApp(){
		controller = new ApplicationController();
	}
	
	public void run(){
		try {
			ServerSocket ss = new ServerSocket(8080);
			
			Executor clientConnections = Executors.newCachedThreadPool();
			
			while(keepRunning){
				Socket s = ss.accept();

				// once the connection is made hand it over to a new thread and listen for new client request.s.s
				Session aSession = new Session(s);
				aSession.setController(controller);

				clientConnections.execute(aSession);
			}
				
		} catch (IOException e) {
			e.printStackTrace();
	}	
	}
}


// CREATE TABLE kidlogin.users (username CHAR(50), password CHAR(50));
// INSERT INTO kidlogin.users VALUES ('bob',AES_ENCRYPT('text','12345'));

//INSERT INTO kidlogin.users VALUES ('bob','12345');

