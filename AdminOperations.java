import java.util.List;
import java.io.Serializable;

public interface AdminOperations extends Serializable  {

	void createCourse(String courseId, String courseName, int maxStudents, String instructor, int sectionNumber, String location);

	void deleteCourse(String courseId);

	void editCourse(String courseId, String courseName, int maxStudents, String instructor, int sectionNumber, String location);

	Course displayCourseInfo(String courseId);

	void registerForCourse(Student student,String courseId);

	List<Course> viewAllCourses();

	List<Course> viewFullCourses();

	void writeFullCoursesToFile(String filename); 

	List<Student> viewStudentsInCourse(String courseId);

	List<Course> viewCoursesOfStudent(Student student);

	void sortCoursesByRegistration();

	boolean authenticate(String username, String password);

}