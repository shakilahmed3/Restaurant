/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class EmployeePanelController implements Initializable {

    @FXML
    private TextField EmployeeID1;
    @FXML
    private TextField Designation;
    @FXML
    private TextField Salary;
    @FXML
    private DatePicker JoinDate;
    
    @FXML
    private TextArea Address;
    @FXML
    private TextField Phone;
    @FXML
    private TextField FirstName;
    @FXML
    private ComboBox<String> Nationality;
    @FXML
    private ComboBox<String> Gender;
    @FXML
    private TextField Passport_NID;
    @FXML
    private TextField LastName;
    @FXML
    private TextField EmployeeID2;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> Genderlist = FXCollections.observableArrayList("Male","Female","Others");
    
    //Create ObservAble Array List for AllEmployeeController
    static ObservableList<Employee> emplist = FXCollections.observableArrayList();
    static ObservableList<Employee> emplist2 = FXCollections.observableArrayList();
    
    ObservableList<Country> country = FXCollections.observableArrayList();
    ObservableList<String> allcountry = FXCollections.observableArrayList();
    
    DBAction dbaction = new DBAction();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        Gender.setValue("Male");
        Gender.setItems(Genderlist);
        
        try {
            country = dbaction.getAllCountry();
            for(Country country: country)
            {
                String count = country.Country_Name;
                allcountry.addAll(count);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeePanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Nationality.setValue("Select Country");
        Nationality.setItems(allcountry);
        
        
        
        
    }    

    
    
    //Employee Table Value Get And Insert Method
    @FXML
    private void AddEmployee(ActionEvent event) {
        
        try{
            int id = Integer.parseInt(EmployeeID1.getText());
            String designation = this.Designation.getText();
            Date Jdate = Date.valueOf(JoinDate.getValue());
            double salary = Double.parseDouble(Salary.getText());
            
            String i = EmployeeID1.getText();
            String s= Salary.getText();
            if(i.equals("") || designation.equals("") || Jdate.equals("") || s.equals("") )
            {
                JOptionPane.showMessageDialog(null, "Please fill up all field!");
                
            }else{
                
            //System.out.println(""+id+" "+designation+" "+Jdate+" "+salary);
            //Database Action 
            
            //Create Employee Object to get Insert Value For Employee Table
            Employee employee = new Employee(id,designation,Jdate,salary);
            dbaction.InsertEmployee(employee);
            
            
            
            
            JOptionPane.showMessageDialog(null, "Congrats! Employee has been Added");
            //Clear All Field Text After Added
            EmployeeID1.clear();
            Designation.clear();
            Salary.clear();
            JoinDate.setValue(null);
            }
            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Wrong! Please Check All Information!");
        }
               
    }

    @FXML
    private void UpdateEmployee(ActionEvent event) throws SQLException {
        
            String i = EmployeeID1.getText();
            String s = Salary.getText();
            if(i.equals("") && s.equals(""))return;
        
            int id = Integer.parseInt(EmployeeID1.getText());
            double salary = Double.parseDouble(Salary.getText());
            
            String designation = this.Designation.getText();
            Date Jdate = Date.valueOf(JoinDate.getValue());
            if(Designation.equals("") && Jdate.equals(""))return;
            
            Connection con = dbaction.getConnection();
            Statement statement = con.createStatement();
            
            String Query = "UPDATE employee SET Designation = '"+designation+"', Join_Date='"+Jdate+"', Salary = "+salary+" WHERE Employee_ID = "+id;
            
            statement.executeUpdate(Query);
            
            JOptionPane.showMessageDialog(null,"Congrats!! Employee has been Update!");
            
            EmployeeID1.clear();
            Designation.clear();
            Salary.clear();
            JoinDate.setValue(null);
        
        
    }


    
    //Personal Details Table Value get and insert Method
    @FXML
    private void AddPersonalDetails(ActionEvent event) {
        
        try{
        int id = Integer.parseInt(EmployeeID2.getText());
        String firstname = FirstName.getText();
        String lastname = LastName.getText();
        int phone = Integer.parseInt(Phone.getText());
        String gender = Gender.getValue();
        String country = Nationality.getValue();
        String passport = Passport_NID.getText();
        String address = Address.getText();
        
        String i = EmployeeID2.getText();
        String p = Phone.getText();
        
        if(i.equals("")|| p.equals("") || firstname.equals("") || lastname.equals("") || gender.equals("") || country.equals("") || passport.equals("") || address.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please fill up all field!");
        }
        else{
            
           // System.out.println(id+" "+firstname+" "+lastname+" "+phone+" "+gender+" "+country+" "+passport+" "+address);
           
            
            //Database Action
            Employee employee = new Employee(id,firstname,lastname,phone,gender,country,address,passport);
            dbaction.InsertEmployeeInformation(employee);

            
            JOptionPane.showMessageDialog(null, "Congrats! Employee has been Added");
            //Clear All Field Text After Added
            EmployeeID2.clear();
            FirstName.clear();
            LastName.clear();
            Phone.clear();
            Gender.setValue("Male");
            Nationality.setValue("Select Country");
            Passport_NID.clear();
            Address.clear();
                   
        }
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Wrong! Please Check All Information!");
            System.out.println(""+e);
        }
        
    }

    @FXML
    private void UpdatePersonalDetails(ActionEvent event) throws SQLException {
        
        String i = EmployeeID2.getText();
        String p = Phone.getText();
        if(i.equals("") && p.equals(""))return;        
        
        int id = Integer.parseInt(EmployeeID2.getText());
        int phone = Integer.parseInt(Phone.getText());
        
        String firstname = FirstName.getText();
        String lastname = LastName.getText();        
        String gender = Gender.getValue();
        String country = Nationality.getValue();
        String passport = Passport_NID.getText();
        String address = Address.getText();
        
        if(firstname.equals("") && lastname.equals("") && gender.equals("") && country.equals("") && passport.equals("") && address.equals(""))return;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        
        String Query = "UPDATE personal_details SET First_Name = '"+firstname+"', Last_Name = '"+lastname+"', Phone = "+phone+", Gender ='"+gender+"', Country = '"+country+"', Address = '"+address+"', Passport_NID = '"+passport+"' WHERE Employee_ID = "+id;
                
        statement.executeUpdate(Query);
        
        JOptionPane.showMessageDialog(null,"Congrats!! Employee has been Update!");
        
        
            EmployeeID2.clear();
            FirstName.clear();
            LastName.clear();
            Phone.clear();
            Gender.setValue("Male");
            Nationality.setValue("Select Country");
            Passport_NID.clear();
            Address.clear();
        
        
    }

    @FXML
    private void Search1(ActionEvent event) throws SQLException {
                
        try{
        String Id = EmployeeID1.getText();
        if(Id.equals(""))return;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String Query = "SELECT  Designation,Join_Date,Salary FROM employee WHERE Employee_ID = "+Integer.parseInt(Id);
         
        ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
            String des = resultset.getString("Designation");
            String date = resultset.getDate("Join_Date").toString();
            double sal = resultset.getDouble("Salary");
            
            //Convert Date String to Local Date
            //DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
            
            //Set Item in All Text Field
            Designation.setText(des);
            JoinDate.setValue(LocalDate.parse(date, DateTimeFormatter.ISO_DATE));
            Salary.setText(""+sal);
            
             
         }
         
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Please Type Correct Employee ID");
        }
         
         
    }

    @FXML
    private void Search2(ActionEvent event) {
        
       try{
        String Id = EmployeeID2.getText();
        if(Id.equals(""))return;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String query = "SELECT  First_Name,Last_Name,Phone,Gender,Country,Passport_NID,Address FROM personal_details where Employee_ID = "+Integer.parseInt(Id);
          
        ResultSet resultset = statement.executeQuery(query);
         
         while(resultset.next())
         {
            
            String FirstName = resultset.getString("First_Name");
            String LastName = resultset.getString("Last_Name");
            int Phone = resultset.getInt("Phone");
            String Gender = resultset.getString("Gender");
            String country = resultset.getString("Country");
            String Passport = resultset.getString("Passport_NID");
            String Address = resultset.getString("Address");  
            
            this.FirstName.setText(FirstName);
            this.LastName.setText(LastName);
            this.Phone.setText(""+Phone);
            this.Gender.setValue(Gender);
            this.Nationality.setValue(country);
            this.Passport_NID.setText(Passport);
            this.Address.setText(Address);
             
         }
         
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Please Type Correct Employee ID");
        }
         
        
        
    }

  
}
