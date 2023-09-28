import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Course implements Serializable {
	
	//course attributes 
	private String courseId;
	private String courseName;
	private int maxStudents;
	private int currentStudents;
	private String instructor;
	private int sectionNumber;
	private String location;
	private List<Student> students;

	//course contructor 
	public Course(String courseId, String courseName, int maxStudents, String instructor, int sectionNumber, String location) {

		this.courseId = courseId;
		this.courseName = courseName;
		this.maxStudents = maxStudents;
		this.currentStudents = 0;
		this.instructor = instructor;
		this.sectionNumber = sectionNumber;
		this.location = location;
		this.students = new ArrayList<>();

	}

	//course methods getters and setters 

	//COURSE ID
	public String getCourseId(){
		return courseId;
	}
	public void setCourseId(String id){
		this.courseId = id;
	}

	//COURSE NAME 
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name){
		this.courseName = name;
	}


	//MAX STUDENTS
	public int getMaxStudents(){
		return maxStudents;
	}
	public void setMaxStudents(int max) {
		this.maxStudents = max;
	}
	
	//CURRENT STUDENTS
	public int getCurrentStudents(){
		return currentStudents;
	}
	public void setCurrentStudents(int cstudents) {
		this.currentStudents = cstudents;
	}

	//INTSTRUCTOR
	public String getInstructor(){
		return instructor;
	}
	public void setInstructor(String inst) {
		this.instructor = inst;
	} 

	//SECTION NUMBER
	public int getSectionNumber(){
		return sectionNumber;
	}
	public void setSectionNumber(int secnum) {
		this.sectionNumber = secnum;
	}

	//LOCATION
	public String getLocation(){
		return location;
	}
	public void setLocation(String loc) {
		this.location = loc;
	}

	//LIST OF STUDENTS
	public List<Student> getStudents(){
		return students;
	}

	//ADD A STUDENT
	public void addStudent(Student student) {
		if (currentStudents < maxStudents ) {
			students.add(student);
			currentStudents ++;
		} 
		else {
			System.out.println( "Course is full. More students cannot be added." );
		}
	}

	//REMOVE A STUDENT
	public void removeStudent(Student student) {
		if (students.contains(student)) {
			students.remove(student);
			currentStudents --;
		}
		else {
			System.out.println( "Student is not registered in the course.");
		}
	}

	//CHECK IF COURSE IS FULL
	public boolean isFull() {
		return this.maxStudents == this.students.size();
	}

	@Override
	public String toString(){
		return  "Course Name: " + this.courseName + "\nCourse Id: " + this.courseId + "\nCourse Instructor: " + this.instructor + "\nCourse Location: " + this.location;
	}

}