package cit310.case6;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataModel {
	private Connection con;
	private PreparedStatement authStatement;
	
	public DataModel(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean authenticateUser(String username, String password){
		try {
			con = DriverManager.getConnection("jdbc:mysql:///mmhome", "student", "student");
			authStatement = con.prepareStatement("Select * from kidlogin.users where username = ? AND password = AES_ENCRYPT('text',?)");
			
			authStatement.setString(1, username);
			authStatement.setString(2, password);
			
			 if(!con.isClosed()){
		    	 ResultSet rs = authStatement.executeQuery();
		    	  if(rs.next()){
		    		  return true;
		    	  }
		      }
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	return false;
	}
}




