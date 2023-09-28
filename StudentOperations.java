import java.util.List;
import java.io.Serializable;


public interface StudentOperations extends Serializable {

	List<Course> viewAllCourses();

	List<Course> viewRegisteredCourses(String studentFirstname, String studentLastName);

	void registerForCourse(String courseName, int sectionNumber, String firstName, String lastName);

	void withdrawFromCourse(String courseName, int sectionNumber, String firstName, String lastName);

	List<Course> viewAvailableCourses();


} 