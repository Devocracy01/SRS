public class Student extends Person {
	private String fname;
	private String lname;
	private String password;
	private String userID;
	private String courseID;
	
	private Account account;
	private Course course;
	
	//Constructor called for first time users while signning up
	public Student(String firstName, String lastName, String password){
		super(firstName,lastName);
		this.password = password;
		this.account = new Account(firstName, lastName, password);
		this.course = new Course(courseID);
	}
	
	public Student(String userID, String password){
		super();
		this.userID = userID;
		this.password = password;
		this.account = new Account(userID,password);
		this.course = new Course(courseID);
	}
	
	public void registerForCourse(){
		
	}
	
	public void unRegisterForCourse(){
		
	}
	
	public void showRegisteredCourseList(){
		
	}
	
	public void printCourseList(){
		
	}
	
	public String getUserID(){
		return account.getUserID();
	}
	
	public String getPassword(){
		return account.getPassword();
	}
	
}
