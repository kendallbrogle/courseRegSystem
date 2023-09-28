import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Course> courseList;

    public Data(){
        courseList = new ArrayList<>();
    }

    public void readCsvFile() {
        String csvFile = "MyUniversityCourses.csv";
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // skips the header
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                // Assuming CSV format: name,age
               String[] data = line.split(",");
               Course course = new Course(data[1], data[0], Integer.parseInt(data[2]), data[5], Integer.parseInt(data[6]), data[7]);
               courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dataSearlization(){
        try (FileOutputStream fos = new FileOutputStream("courseData.ser")){
             ObjectOutputStream oos = new ObjectOutputStream(fos); 
            oos.writeObject(courseList);
            System.out.println("Data serialized and saved to courseData.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    } 
    
}
