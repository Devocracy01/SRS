
public class Account{
	private String userID;
	private String password;
	private String firstName;
	private String lastName;
	
	//Constructor called for login
	public Account(String userID,String password){
		this.userID = userID;
		this.password = password;
		//super(userID, password);
	}
	
	public Account(String firstName, String lastName, String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		//super(firstName, lastName, password);
		
	}
	
	public boolean verifyUserId(String userID){
		return getUserID().equals(userID);
	}
	
	public boolean verifyPassword(String password){
		return getPassword().equals(password);
	}
	
	public String getUserID(){
		return userID;
	}
	
	public String getPassword(){
		return password;
	}
	
	//Get LastName
	public String getAccountLastname(){
		return firstName;
	}
	
	public String getAccountFirstname(){
		return firstName;
	}
}