import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.io.PrintWriter;	
import java.io.FileWriter;
import java.io.IOException;

public class Course{
	private String courseID;
	private String courseStartDate;
	private String courseEndDate;
	private String courseName;
	private String courseSummary;
	private int courseEnrollmentLimit;
	private int courseStudentsEnrolled;
	File courseListFile;
	Scanner courseListScanner;
	PrintWriter courseList;
	List<Course> courselist = new ArrayList<Course>();
	PrintWriter testFile;
	String userID;
	File registeredCourseListFile;
	Scanner registeredCourseListScanner;
	PrintWriter registeredCourseList;
	List<Course> registeredcourselist = new ArrayList<Course>();
	int numberOfCoursesForSameUser = 0;
	
	public Course(String courseID, String courseStartDate, String courseEndDate, String courseName, String courseSummary, int courseEnrollmentLimit, int courseStudentsEnrolled){
		this.courseID = courseID;
		this.courseStartDate = courseStartDate;
		this.courseEndDate = courseEndDate;
		this.courseName = courseName;
		this.courseSummary = courseSummary;
		this.courseEnrollmentLimit = courseEnrollmentLimit;
		this.courseStudentsEnrolled = courseStudentsEnrolled;
	}
	
	public Course(String courseID){
		this.courseID = courseID;
		try{
			courseListFile = new File("CourseList.txt");
			//Instantiate a Scanner Object
			courseListScanner = new Scanner(courseListFile);
			
			//For each line, read the next five values into variables
			while (courseListScanner.hasNextLine()){
				//Uer split 
				String[] courseAttributes = courseListScanner.nextLine().split(",");
				courseID = courseAttributes[0];
				String courseStartDate = courseAttributes[1];
				String courseEndDate = courseAttributes[2];
				String courseName = courseAttributes[3];
				String courseSummary = courseAttributes[4];
				int courseEnrollmentLimit = new Integer(courseAttributes[5]).intValue();
				int courseStudentsEnrolled = new Integer(courseAttributes[6]).intValue();
				
				courselist.add(new Course(courseID, courseStartDate, courseEndDate, courseName, courseSummary, courseEnrollmentLimit, courseStudentsEnrolled));	
			}
			//Close the file
			courseListScanner.close();
			for (int i=0;i<courselist.size();i++){
				if(courselist.get(i).getCourseID().equals(courseID)){
					this.courseStartDate = courseStartDate;
					this.courseEndDate = courseEndDate;
					this.courseName = courseName;
					this.courseSummary = courseSummary;
					this.courseEnrollmentLimit = courseEnrollmentLimit;
					this.courseStudentsEnrolled = courseStudentsEnrolled;
					break;
				}
			}
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public String getCourseID(){
		return courseID;
	}
	
	public String getCourseStartDate(){
		return courseStartDate;
	}
	
	public String getCourseEndDate(){
		return courseEndDate;
	}
	
	public String getCourseName(){
		return courseName;
	}
	
	public String getCourseSummary(){
		return courseSummary;
	}
	
	public int getCourseEnrollmentLimit(){
		return courseEnrollmentLimit;
	}
	
	public int getCourseStudentsEnrolled(){
		return courseStudentsEnrolled;
	}
	
	public void createCourse(){
		try{
			testFile = new PrintWriter(new FileWriter("CourseList.txt",true));
			testFile.println(courseID+","+courseStartDate+","+courseEndDate+","+courseName+","+courseSummary+","+courseEnrollmentLimit+","+courseStudentsEnrolled);
			testFile.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean registerCourses(String userID, String courseID){
		int courseCounter = 0;
		//compare the difference between courseEnrollmentLimit and courseStudentsEnrolled
			try{
				registeredCourseListFile = new File("RegisteredCourseList.txt");
				//Instantiate a Scanner Object
				registeredCourseListScanner = new Scanner(registeredCourseListFile);
				while (registeredCourseListScanner.hasNextLine()){
					//Uer split 
					String[] registeredCourseAttributes = registeredCourseListScanner.nextLine().split(",");
					String registeredUserID = registeredCourseAttributes[0];
					String registeredCourseID = registeredCourseAttributes[1];
					String registeredCourseStartDate = registeredCourseAttributes[2];
					String registeredCourseEndDate = registeredCourseAttributes[3];
					String registeredCourseName = registeredCourseAttributes[4];
					String registeredCourseSummary = registeredCourseAttributes[5];
					//System.out.println(courselist.get(courseCounter).getCourseEnrollmentLimit());
					//System.out.println(courselist.get(courseCounter).getCourseStudentsEnrolled());
					if (((courselist.get(courseCounter).getCourseEnrollmentLimit() - courselist.get(courseCounter).getCourseStudentsEnrolled()) > 0)){
						//System.out.println("hmm more "+(courselist.get(courseCounter).getCourseEnrollmentLimit() - courselist.get(courseCounter).getCourseStudentsEnrolled()));
						if ((registeredCourseID.equals(courseID)) && (registeredUserID.equals(userID))){
							return false;
						}else if (registeredUserID.equals(userID)){
							numberOfCoursesForSameUser++;
						}
					}else{
						return false;
					}
					courseCounter++;
					//System.out.println(courselist.size()+"---"+numberOfCoursesForSameUser);
				}
				if (courselist.size() == numberOfCoursesForSameUser){
					return false;
				}else{
					//register course
					System.out.println("qualifies for registration");
					
					//add a line to RegisteredCourseList.txt
					try{
						
						registeredCourseList = new PrintWriter(new FileWriter("RegisteredCourseList.txt",true));
						registeredCourseList.println(userID+","+courseID+",");
						registeredCourseList.close();
					}catch (IOException e){
						e.printStackTrace();
					}
					//update the last number for the course in courseList.txt
					
				
				}
				//Close the file
				registeredCourseListScanner.close();
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
			
			return true;
	}
	
}