
package restaurant;

import java.sql.Date;

/**
 *
 * @author Shakil Ahmed
 */
public class Employee {

public int EmployeeID;
public String Designation;
public Date JoinDate;
public double Salary;

public String FirstName;
public String LastName;
public int Phone;
public String Gender;
public String Country;
public String Address;
public String Passport_NID;

    public Employee() {
    }

    public Employee(int EmployeeID, String Designation, Date JoinDate, double Salary) {
        this.EmployeeID = EmployeeID;
        this.Designation = Designation;
        this.JoinDate = JoinDate;
        this.Salary = Salary;
    }

    public Employee(int EmployeeID, String FirstName, String LastName, int Phone, String Gender, String Country, String Address, String Passport_NID) {
        this.EmployeeID = EmployeeID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Phone = Phone;
        this.Gender = Gender;
        this.Country = Country;
        this.Address = Address;
        this.Passport_NID = Passport_NID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public String getDesignation() {
        return Designation;
    }

    public Date getJoinDate() {
        return JoinDate;
    }

    public double getSalary() {
        return Salary;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public int getPhone() {
        return Phone;
    }

    public String getGender() {
        return Gender;
    }

    public String getCountry() {
        return Country;
    }

    public String getAddress() {
        return Address;
    }

    public String getPassport_NID() {
        return Passport_NID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public void setJoinDate(Date JoinDate) {
        this.JoinDate = JoinDate;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPassport_NID(String Passport_NID) {
        this.Passport_NID = Passport_NID;
    }

    @Override
    public String toString() {
        return "Employee{" + "EmployeeID=" + EmployeeID + ", Designation=" + Designation + ", JoinDate=" + JoinDate + ", Salary=" + Salary + ", FirstName=" + FirstName + ", LastName=" + LastName + ", Phone=" + Phone + ", Gender=" + Gender + ", Nationality=" + Country + ", Address=" + Address + ", Passport_NID=" + Passport_NID + '}';
    }

   
    
}
