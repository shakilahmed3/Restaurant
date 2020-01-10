
package restaurant;

/**
 *
 * @author Shakil Ahmed
 */
public class AppUser {

    int ID;
    int Emp_ID;
    String UserName;
    String Password;

    public AppUser() {
    }
       
    
    public AppUser(int ID, String UserName) {
        this.ID = ID;
        this.UserName = UserName;
    }
    
    public AppUser(int ID, String UserName, String Password) {
        this.ID = ID;
        this.UserName = UserName;
        this.Password = Password;
    }

    public AppUser(int ID, int Emp_ID, String UserName, String Password) {
        this.ID = ID;
        this.Emp_ID = Emp_ID;
        this.UserName = UserName;
        this.Password = Password;
    }

   

    public int getID() {
        return ID;
    }

    public int getEmp_ID() {
        return Emp_ID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEmp_ID(int Emp_ID) {
        this.Emp_ID = Emp_ID;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return "AppUser{" + "ID=" + ID + ", Emp_ID=" + Emp_ID + ", UserName=" + UserName + ", Password=" + Password + '}';
    }

}
