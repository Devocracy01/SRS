//Import all relevant packages
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.Console;
import java.io.PrintWriter;	
import java.io.FileWriter;

public class SRSTester{
	public static void main(String args[]){
		String newStudent;
		boolean invalidEntry;
		String firstName;
		String lastName;
		String password;
		String userID;
		boolean existingUser = true;
		boolean correctLogin = true;
		boolean isSuccessful = false;
		String postLoginChoice = "";
		Student student1 = null;
		
		//define an ArrayList
		List<Account> signUplist = new ArrayList<Account>();
		List<Account> loginlist = new ArrayList<Account>();		
		//define File and fileScanner objects
		File testFile;
		Scanner fileScanner;
		PrintWriter testFile2;
		PrintWriter testFile3;
		Console console = System.console();
		newStudent = "";
		do {
			PrintToConsole.printBanner("Welcome to the Student Registration System");
			newStudent = console.readLine("New Student? (Y/N):");
			//Verify if the entry is correct
			if (newStudent.equals("Y")) {
				PrintToConsole.printBanner("Welcome to the Student Registration Sign-Up Section");
				firstName = console.readLine("Please Enter your First Name:");
				lastName = console.readLine("Please Enter your Last Name:");
				System.out.println("\n***Your UserID is "+firstName+"."+lastName+"@UCI.edu***\n");
				password = console.readLine("Please Enter Password:");
				try{
					//Instantiate a File Object
					testFile = new File("SignUp.txt");
					//Instantiate a Scanner Object
					fileScanner = new Scanner(testFile);
					if (fileScanner.hasNextLine() == false){
						existingUser = false;
					}
					while (fileScanner.hasNextLine()){
						String[] signUpAttributes = fileScanner.nextLine().split(",");
						String fname = signUpAttributes[0];
						String lname = signUpAttributes[1];
						String pwd = signUpAttributes[2];
						signUplist.add(new Account(fname,lname,pwd));
					}
					//Close the file
					fileScanner.close();
					for (int i=0;i<signUplist.size();i++){
						if ((signUplist.get(i).getAccountLastname().equals(lastName)) && (signUplist.get(i).getAccountFirstname().equals(firstName))) {
							System.out.println("DUPLICATE ENTRY. Student with the same LastName and FirstName Exists.");
						}else{
							existingUser = false;
							break;
						}
					}
					
					if (existingUser == false) {
						//write to the File
						try{
							testFile2 = new PrintWriter(new FileWriter("SignUp.txt",true));
							testFile2.println(firstName+","+lastName+","+password);
							testFile2.close();
							//********Write to Login.txt too
							testFile3 = new PrintWriter(new FileWriter("Login.txt",true));
							testFile3.println(firstName+"."+lastName+"@UCI.edu,"+password);
							testFile3.close();
							System.out.println("\n*******Successfully Registered to the Student Registration System******\n");
							student1 = new Student(firstName,lastName,password);
							//viewCourses(student1);
							isSuccessful = true;
						
						}catch(IOException e){
							e.printStackTrace();
						}
					}
						
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}
				break;
			}else if (newStudent.equals("N")){
				PrintToConsole.printBanner("Welcome to the Student Registration Login Section");
				userID = console.readLine("Please Enter your UserID:");
				password = console.readLine("Please Enter Password:");
				try{
					//Instantiate a File Object
					testFile = new File("Login.txt");
					//Instantiate a Scanner Object
					fileScanner = new Scanner(testFile);
					if (fileScanner.hasNextLine() == false){
						correctLogin = false;
					}
					while (fileScanner.hasNextLine()){
						String[] loginAttributes = fileScanner.nextLine().split(",");
						String existinguserID = loginAttributes[0];
						String existingpwd = loginAttributes[1];
						loginlist.add(new Account(existinguserID,existingpwd));
					}
					//Close the file
					fileScanner.close();
					for (int j=0;j<loginlist.size();j++){
						if ((loginlist.get(j).verifyUserId(userID)) && (loginlist.get(j).verifyPassword(password))) {
							correctLogin = true;
							break;
						}else{
							correctLogin = false;
						}
					}
					if (correctLogin == true) {
						System.out.println("\n*******Successfully Signed In to the Student Registration System******\n");
						student1 = new Student(userID,password);
						isSuccessful = true;
						//viewCourses(student1);
					}else {
						System.out.println("Incorrect Login.");
					}
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}
				break;
			}
		}while ((newStudent != "Y") || (newStudent != "N"));
		if (isSuccessful){
			do{
				System.out.println("Enter 1 to View Courses.\nEnter 2 to Exit the Registration System.\n");
				postLoginChoice = console.readLine("Enter your choice:");
				if (postLoginChoice.equals("1")){
					viewCourses(student1);
					break;
				}else if (postLoginChoice.equals("2")){
					exitRegistrationSystem();
					break;
				}	 
				
			}while ((postLoginChoice != "1") || (postLoginChoice != "2"));
		}
	}
	public static void exitRegistrationSystem(){
		PrintToConsole.printBanner("Logged Off the Student Registration System***************\n******************Exiting the Student Registration System");
	}
	public static void viewCourses(Student s){
		File courseListFile;
		Scanner courseListScanner;
		String choiceInViewCourses;
		Console console = System.console();
		do{
			try{
				courseListFile = new File("CourseList.txt");
				//Instantiate a Scanner Object
				courseListScanner = new Scanner(courseListFile);
				PrintToConsole.printBanner("Welcome to the Available List of Courses Section");
				System.out.println("Course Numbers\t\tAvailable Seats");
				while (courseListScanner.hasNextLine()){
					String[] courseViewAttributes = courseListScanner.nextLine().split(",");
					String courseID = courseViewAttributes[0];
					String availableSeats = courseViewAttributes[6];
					System.out.println(courseID+"\t\t"+availableSeats);
				}
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
		
			System.out.println("\n\nEnter 1 to Register Courses.\nEnter 2 to View Detailed Courses Screen.\nEnter 3 to View Registered Courses.\nEnter 4 to SignOff the Registration System.\n");
			choiceInViewCourses = console.readLine("Enter your choice:");
			if (choiceInViewCourses.equals("1")){
				registerCourses(s);
				break;
			}else if (choiceInViewCourses.equals("2")){
				detailedCourseView(s);
				break;
			}else if (choiceInViewCourses.equals("3")){
				viewRegisteredCourses(s);
				break;
			}else if (choiceInViewCourses.equals("4")){
				exitRegistrationSystem();
				break;
			}
		} while ((choiceInViewCourses != "1") || (choiceInViewCourses != "2") || (choiceInViewCourses != "3") || (choiceInViewCourses != "4"));
	}
	
	public static void registerCourses(Student stu){
		
	}
	
	public static void detailedCourseView(Student stu){
		
	}
	
	public static void viewRegisteredCourses(Student stu){
		
	}
	
	
}