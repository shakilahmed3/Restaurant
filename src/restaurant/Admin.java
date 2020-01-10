
package restaurant;

/**
 *
 * @author Shakil Ahmed
 */
public class Admin {

    private String UserName;
    private String Password;
    
    //Default constructor
    public Admin() {
    }
    
    //Ovverloading Constructor
    public Admin(String UserName, String Password) {
        this.UserName = UserName;
        this.Password = Password;
    }

    //Set Getter function to get user name and password from database
    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }
    
    //Set Setter function to set user name and password from getter function
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    //Overrride ToString Method
    @Override
    public String toString() {
        return "Admin{" + "UserName=" + UserName + ", Password=" + Password + '}';
    }
    
    
    
    
    
    
    
}
