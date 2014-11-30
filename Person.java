/*Purpose of this program is to create a Person class. 
This Class consists of 2 constructors. One with 2 parameters and amother with 5 prameters.
The object that accesses the constructor with fname and lname as input parameters, should call set Methods
for age, gender and ssn before calling the get methods.
The object that accesses the constructor with 5 parameters can directly call get methods.*/
public class Person implements Comparable<Person>{
	//declare instance variables
	private String firstname,lastname;
	private int age;
	private String ssn;
	private char gender;
	
	//contructor with 2 parameters
	public Person(String fname, String lname){
		firstname = fname;
		lastname = lname;
	}
	//Empty Constructor
	public Person(){
	
	}
	
	//Second constructor with fname, lname, age, gender and ssn
	public Person(String fname, String lname, int personAge, char personGender, String personSSN){
		firstname = fname;
		lastname = lname;
		age = personAge;
		gender = personGender;
		ssn = personSSN;
	}
	
	//GetFullName Method
	public String getFullname(){
		return firstname+" "+lastname;
	}
	
	//Get LastName
	public String getLastname(){
		return lastname;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	//public Methods get and set
	public void setAge(int age){
		this.age = age;
	}
	
	public void setSSN(String ssn){
		this.ssn = ssn;
	}
	
	public void setGender(char gender){
		this.gender = gender;
	}
	
	public int getAge(){
		return age;
	}
	
	public String getSSN(){
		return ssn;
	}
	
	public char getGender(){
		return gender;
	}
	
	//talk Method
	public void talk(){
		System.out.println("Hello my name is "+firstname+" "+lastname);
	}
	
	//Function to show natural ordering
	public int compareTo(Person person){
		return this.lastname.compareTo(person.getLastname());
	}
	
	//Function to display the Object Details
	public String toString(){
		return "Person [FirstName: "+firstname+", LastName: "+lastname+", Age: "+age+" years old, Gender: "+gender+", SSN: "+ssn+"]";
	}
}