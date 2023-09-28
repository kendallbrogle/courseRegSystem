import java.util.*;
import java.io.*;

public class Main {
    private Admin admin;

    public Main() {
        admin = new Admin("Admin", "Admin001", "Admin", "User");

        String filePath = "courseData.ser";
        File file = new File(filePath);
        Data data = new Data();

        if(file.exists() != true){
            data.readCsvFile();
            data.dataSearlization();
        }
        loadAdminData();
    }

    //Start systme - determine student or admin 
    public void startSystem() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Course Registration System!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Admin Login");
            System.out.println("2. Student Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                adminLogin(scanner);
            } else if (choice == 2) {
                studentLogin(scanner);
            } else if (choice == 3) {
                saveData();
                System.out.println("Course Registration System exited.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    //ADMIN OPERATIONS
    private void adminLogin(Scanner scanner) {
        System.out.print("Enter Admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Admin password: ");
        String password = scanner.nextLine();

        // check if login is valid 
        if (admin.authenticate(username, password)) { 
            adminMenu(scanner);
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }

    //admin menu
    private void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Create a new course");
            System.out.println("2. Delete a course");
            System.out.println("3. Edit a course");
            System.out.println("4. Display information for a given course");
            System.out.println("5. Register a student");
            System.out.println("6. Write full courses to a file");
            System.out.println("7. View students in a course");
            System.out.println("8. View courses for a student");
            System.out.println("9. View all courses");
            System.out.println("10.View courses that are full");
            System.out.println("11.Sort courses by number of students registered");
            System.out.println("12. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

        
            if (choice == 1) {
                createCourse(scanner);
            } else if (choice == 2) {
                deleteCourse(scanner);
            } else if (choice == 3) {
                editCourse(scanner);
            } else if (choice == 4) {
                displayCourseInfo(scanner);
            } else if (choice == 5) {
                registerStudent(scanner);
            } else if (choice == 6) {
                writeFullCoursesToFile(scanner);
            } else if (choice == 7) {
                viewStudentsInCourse(scanner);
            } else if (choice == 8) {
                viewCoursesOfStudent(scanner);
            }else if (choice == 9) {
                viewAllCourses(scanner);
            } else if (choice == 10) {
                viewFullCourses(scanner);
            } else if (choice == 11) {
                sortCoursesByRegistration(scanner);
            } else if (choice == 12) {
                saveData(); 
                System.out.println("Admin logged out.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    //create a course
    private void createCourse(Scanner scanner) {
        System.out.println("Creating a new course...");
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter course id: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter maximum number of students: ");
        int maxStudents = scanner.nextInt();
        System.out.print("Enter course instructors name: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter course section number: ");
        int sectionNumber = scanner.nextInt();
        System.out.print("Enter course location ");
        String location = scanner.nextLine();

        admin.createCourse(courseId, courseName, maxStudents, instructor, sectionNumber, location);
        //courses.add(newCourse);
        System.out.println("Course created successfully.");
    }

    //Delete a course
    private void deleteCourse(Scanner scanner) {
        System.out.println("Deleting a course...");
        System.out.print("Enter course id to delete: ");
        String courseId = scanner.nextLine();
        admin.deleteCourse(courseId);
        System.out.println("Course deleted successfully.");
    }

    //Admin editing a course
    private void editCourse(Scanner scanner) {
        System.out.println("Editing a course...");
        System.out.print("Enter course id to edit: ");
        String newCourseId = scanner.nextLine();
        System.out.print("Enter new course's name: ");
        String newCourseName = scanner.nextLine();
        System.out.print("Enter new course's maximum number of students: ");
        int newMaxStudents = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new course's instructors name: ");
        String newInstructor = scanner.nextLine();
        System.out.print("Enter new course's section number: ");
        int newSectionNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new course's location ");
        String newLocation = scanner.nextLine();

        admin.editCourse(newCourseId, newCourseName, newMaxStudents, newInstructor, newSectionNumber, newLocation);
        System.out.println("Course has been successfully created.");
    }

    private void displayCourseInfo(Scanner scanner) {
        System.out.println("Displaying course information...");
        System.out.print("Enter course id to display: ");
        String courseId = scanner.nextLine();
        
        System.out.println(admin.displayCourseInfo(courseId));
    }
    private void registerStudent(Scanner scanner) {
        System.out.println("Registering a student...");
        System.out.print("Enter student first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter student last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter student username: ");
        String username = scanner.nextLine();
        System.out.print("Enter student password: ");
        String password = scanner.nextLine();
        System.out.print("Enter course id to register: ");
        String courseId = scanner.nextLine();


        //add something to check if its full 
        Student student = new Student(username, password, firstName, lastName);
        admin.registerForCourse(student, courseId);
 
    }
    
    private void writeFullCoursesToFile(Scanner scanner) {
        System.out.println("Writing full courses to a file...");
        admin.writeFullCoursesToFile("full_courses_data.ser");
    }

    private void viewStudentsInCourse(Scanner scanner) {
        System.out.println("Viewing students in a course...");
        System.out.print("Enter course id: ");
        String courseId = scanner.nextLine();
        admin.viewStudentsInCourse(courseId);
    }

    private void viewCoursesOfStudent(Scanner scanner) {
        System.out.println("Viewing courses for a student...");
        System.out.print("Enter student first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter student last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter student username: ");
        String username = scanner.nextLine();
        System.out.print("Enter student password: ");
        String password = scanner.nextLine();

        Student student = new Student(username, password, firstName, lastName);
        admin.viewCoursesOfStudent(student);

    }
    private void viewAllCourses(Scanner scanner){
        System.out.println( admin.viewAllCourses());
   }
   private void viewFullCourses(Scanner scanner){

       admin.viewFullCourses();  
   }
   private void sortCoursesByRegistration(Scanner scanner) {
       admin.sortCoursesByRegistration();
   }

    //STUDENT OPERATIONS
    private void studentLogin(Scanner scanner) {

        //student log in - no authorization 
        System.out.print("Enter Student first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Student last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Student username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Student password: ");
        String password = scanner.nextLine();

        Student student = new Student(username, password, firstName, lastName);
  
        //student menu 
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View all available courses");
            System.out.println("2. View all enrolled courses");
            System.out.println("3. Register for a course");
            System.out.println("4. Withdraw from a course");
            System.out.println("5. View course details");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            List<Course> courseList = admin.getCourseList();
            student.setCourseList(courseList);

            //see available courses
            if (choice == 1) {
                System.out.println("Viewing all available courses...");
                List<Course> availableCourses =  student.viewAvailableCourses();
                for (Course course : availableCourses) {
                    System.out.println(course.getCourseName());
                }
            //See enrolled courses
            } else if (choice == 2) {
                System.out.println("Viewing all enrolled courses...");
                List<Course> enrolledCourses = student.viewRegisteredCourses(firstName, lastName);
                for (Course course : enrolledCourses) {
                    System.out.println(course.getCourseName());
                }
                if (enrolledCourses.size()==0 ) {
                    System.out.println("Not enrolled in any classes currently.");
                }
            //register for course
            } else if (choice == 3) {
                System.out.println("Registering for a course...");
                System.out.print("Enter course name: ");
                String courseName = scanner.nextLine();
                System.out.print("Enter section number: ");
                int sectionNumber = scanner.nextInt();

    
                student.registerForCourse(courseName, sectionNumber, firstName, lastName);
                

            //Withdraw from a course
            } else if (choice == 4) {
                System.out.println("Withdrawing from a course...");
                System.out.print("Enter course name to withdraw: ");
                String courseName = scanner.nextLine();
                System.out.print("Enter section number : ");
                int sectionNumber = scanner.nextInt();
                
                student.withdrawFromCourse(courseName, sectionNumber, firstName, lastName);
                
                 
            //See course details 
            } else if (choice == 5) {
                System.out.println("Viewing course details...");
                System.out.print("Enter course name: ");
                String courseName = scanner.nextLine();
    
                Course courseToView = student.viewCourseDetails(courseName);
                    System.out.println("Course Name: " + courseToView.getCourseName());
                    System.out.println("Course Id: " + courseToView.getCourseId());
                    System.out.println("Section number: " + courseToView.getSectionNumber());
                    System.out.println("Instructor: " + courseToView.getInstructor());
                    System.out.println("Course location: " + courseToView.getLocation());
                    System.out.println("Current enrollment: " + courseToView.getCurrentStudents());
                    System.out.println("Maximum Enrollment: " + courseToView.getMaxStudents());
    
            //log out of program 
            } else if (choice == 6) {
                saveData();
                System.out.println("Student logged out.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    

    private void loadAdminData() {
        // Implement loading of admin data if available (e.g., deserialize from "adminData.ser")
        try (FileInputStream fis = new FileInputStream("courseData.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Course> courseList = (List<Course>) ois.readObject();

            // adds the courses to admin course list
            for(Course course: courseList){
                admin.createCourse(course.getCourseId(), course.getCourseName(), course.getMaxStudents(), course.getInstructor(), course.getSectionNumber(), course.getLocation());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        // Implement saving of data
        try (FileOutputStream fos = new FileOutputStream("courseData.ser")){
            ObjectOutputStream oos = new ObjectOutputStream(fos); 
            List<Course> courseList = new ArrayList<>();
            courseList = admin.viewAllCourses();
            oos.writeObject(courseList);
           System.out.println("Updated serialized and saved to courseData.ser");
       } catch (Exception e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startSystem();
    }
}

