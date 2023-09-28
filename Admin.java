
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.Comparator;

public class Admin extends User implements AdminOperations, Serializable {

	//Admin attricbutes
	private List<Course> courseList;
	private List<Student> registeredStudents;

	// Admin constructor 
	public Admin(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
		courseList = new ArrayList<>();
		registeredStudents = new ArrayList<>();
	}

	//Admin methods 

	public List<Course> getCourseList() {
		return courseList;
	}
	@Override
	public void createCourse(String courseId, String courseName, int maxStudents, String instructor, int sectionNumber, String location) {
		Course createdCourse = new Course(courseId, courseName, maxStudents, instructor, sectionNumber, location );
		courseList.add(createdCourse);
	}

	public void deleteCourse(String courseId) {

		for (Course course : courseList) {
			if (course.getCourseId().equals(courseId)) {
				courseList.remove(course);
				break;
			}
		}
	}

	@Override
	public void editCourse(String courseId, String courseName, int maxStudents, String instructor, int sectionNumber, String location) {

		for (Course course: courseList) {
			if (course.getCourseId().equals(courseId)) {
				course.setCourseName(courseName);
                course.setMaxStudents(maxStudents);
                course.setInstructor(instructor);
                course.setSectionNumber(sectionNumber);
                course.setLocation(location);
                break;
			}
		}
	}

    public Course displayCourseInfo(String courseId) {
        for (Course course : courseList) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
		System.out.println("Course not found");
        return null; // Course not found
    }

    // Register a student 
	@Override
    public void registerForCourse(Student student, String courseId) {

		for (Course course: courseList){
			if (course.getCourseId().equals(courseId)) {
				course.addStudent(student);
			}

		}
    	//Student newStudent = new Student(username, password, firstName, lastName);
    	//registeredStudents.add(newStudent);
    }

	//view all courses
    public List<Course> viewAllCourses(){
    	return courseList;
    }

	//view all full courses
    public List<Course> viewFullCourses() {
    	List<Course> fullCourses = new ArrayList<>();
    	for (Course course : courseList) {
    		if (course.getCurrentStudents() >= course.getMaxStudents()) {
    			fullCourses.add(course);
    		}
    	}
    	return fullCourses;
    }

    public void writeFullCoursesToFile(String filename) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
			List<Course> fullCourses = viewFullCourses();
			outputStream.writeObject(fullCourses); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	//view the students in a course
	public List<Student> viewStudentsInCourse(String courseId) {
		List<Student> studentNames = new ArrayList<>();
		for (Course course : courseList) {
			if (course.getCourseId().equals(courseId)) {
				studentNames = course.getStudents();
			}
		}
		if (studentNames.size() == 0) {
			System.out.println("No students are currently registered.");
		}
		return studentNames;
	}
	
	@Override
	//View the courses a student is in 
    public List<Course> viewCoursesOfStudent(Student student) {
    	List<Course> coursesForStudent = new ArrayList<>();
    	for (Course course : courseList) {
    		if (course.getStudents().contains(student)) {
    			coursesForStudent.add(course);
    		}
    	}
		if ( coursesForStudent.size()==0) {
			System.out.println("Student is not currently registered.");
		}
    	return coursesForStudent;
    }

	
    public void sortCoursesByRegistration() {
		courseList.sort(Comparator.comparingInt(Course::getCurrentStudents).reversed());
    }

    // Exit the program
    public void exitProgram() {
        serializeData("courseData.ser", courseList);  //add
        serializeData("studentData.ser", registeredStudents); // add
        System.out.println("Exiting program.");
        System.exit(0);
    }

    private void serializeData(String fileName, List<?> data) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//check if log in credentials are correct
	public boolean authenticate(String username, String password) {
		boolean login = false;
		if ( username.equals("Admin" ) && password.equals("Admin001")) {
			login = true;
		}
		return login;


	}
   
}
