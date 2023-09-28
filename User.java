
import java.io.Serializable;

public class User implements Serializable {

	//user attributes
	private String username;
	private String password;
	private String firstName;
	private String lastName; 


	//constructor to make a user object
	public User(String username, String password, String firstName, String lastName) {

		this.username = username; 
		this.password = username;
		this.firstName = firstName; 
		this.lastName = lastName;

	}

    //getting the username 
	public String getUsername() {

		return username;
	}

	//getting the password
	public String getPassword() {

		return password;
	}

	//getting the first name
	public String getFirstName() {

		return firstName;
	}

	//getting the last name 
	public String getLastName() {

		return lastName;
	}


	
}

