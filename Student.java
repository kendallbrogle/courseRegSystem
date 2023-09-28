import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student extends User implements StudentOperations, Serializable {

	//student aatributes
	private List<Course> registeredCourses;
	private List<Course> courseList;

	//student constructor 
	public Student(String username, String password, String firstName, String lastName) {
		super(username,password, firstName, lastName);
		registeredCourses = new ArrayList<>();
		courseList = new ArrayList<>();
	}

	public void setCourseList(List<Course> courseList){
		this.courseList = courseList;
	}
	//student methods
	public List<Course> viewAllCourses() {
        return courseList;
    }

	public List<Course> viewAvailableCourses() {
		List<Course> availableCourses = new ArrayList<>();
		for (Course course : courseList) {
			if (!course.isFull()) {
				availableCourses.add(course);
			}
		}
		return availableCourses;
	}

	//Student registers for class
	public void registerForCourse(String courseName, int sectionNumber, String firstName, String lastName) {

		Course courseToRegister = getCourseByNameAndSection(courseName, sectionNumber);

		if (courseToRegister != null) {
			if (courseToRegister.isFull()) {
				 System.out.println("Course is full. Cannot enroll.");
			}
			else if (isAlreadyRegistered(courseToRegister)) {
				System.out.println("You are already enrolled in this course.");
			}
			else {
				courseToRegister.addStudent(new Student(this.getUsername(), this.getPassword(), this.getFirstName(), this.getLastName()));
				registeredCourses.add(courseToRegister);
				System.out.println("You've succesffuly registered for " + courseName);
			}
		}
		else {
			System.out.println("Course not found.");
		}
	}
	//Student withdraws from course
	public void withdrawFromCourse(String courseName, int sectionNumber, String firstName, String lastName) {
		Course courseToWithdraw = getCourseByNameAndSection(courseName, sectionNumber);

		if (courseToWithdraw != null) {
			if (registeredCourses.remove(courseToWithdraw)) {
				courseToWithdraw.removeStudent(new Student(this.getUsername(), this.getPassword(), this.getFirstName(), this.getLastName()));
				System.out.println("Withdrawn from the course successfully.");
			}
			else {
				System.out.println("You are not enrolled in this course.");
			}
		}
		else{
			System.out.println("Course not found.");
		}
	}

	//View course details 
	public Course viewCourseDetails(String courseName) {
		for(Course course: viewAllCourses()) {
			if (course.getCourseName().equalsIgnoreCase(courseName)) {
				return course;
			}
		}
		return null; //course isnt found
	}

	public void serializeStudentData(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(this);
            System.out.println("Student data written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Deserialize student data
    public static Student deserializeStudentData(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Student student = (Student) inputStream.readObject();
            System.out.println("Student data loaded from " + fileName);
            return student;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Course getCourseByNameAndSection(String courseName, int sectionNumber) {
        for (Course course : viewAllCourses()) {
            if (course.getCourseName().equalsIgnoreCase(courseName) && course.getSectionNumber() == sectionNumber) {
                return course;
            }
        }
        return null;
    }

    // Helper method to check if the student is already enrolled in a course
    private boolean isAlreadyRegistered(Course course) {
        for (Course registeredCourse : registeredCourses) {
            if (registeredCourse.getCourseId().equals(course.getCourseId())) {
                return true;
            }
        }
        return false;
    }

	@Override
	public List<Course> viewRegisteredCourses(String studentFirstname, String studentLastName) {
		List<Course> studentCourses = new ArrayList<>();
		 
		for (Course course : registeredCourses) {
			List<Student> students = course.getStudents();
			for(Student student: students){
				if(student.getFirstName().equals(studentFirstname) && student.getLastName().equals(studentLastName)){
					studentCourses.add(course);
				}
			}
		}
		return studentCourses;
	}
}