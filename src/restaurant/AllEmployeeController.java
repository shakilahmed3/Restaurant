/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class AllEmployeeController implements Initializable {

    @FXML
    private TableColumn<Employee, Integer> EmployeeID;
    @FXML
    private TableColumn<Employee, String> Designation;
    @FXML
    private TableColumn<Employee, Date> JoinDate;
    @FXML
    private TableColumn<Employee, Double> Salary;
    @FXML
    private TableView<Employee> EmployeeTable;
    @FXML
    private TableColumn<Employee, String> FirstName;
    @FXML
    private TableColumn<Employee, String> LastName;
    @FXML
    private TableColumn<Employee, Integer> Phone;
    @FXML
    private TableColumn<Employee, String> Gendar;
    @FXML
    private TableColumn<Employee, String> Country;
    @FXML
    private TableColumn<Employee, String> Passport;
    @FXML
    private TableColumn<Employee, String> Address;
    @FXML
    private TableView<Employee> EmployeeDetailsTable;
    @FXML
    private TableColumn<Employee, Integer> ID;
    @FXML
    private TextField search1;
    @FXML
    private TextField Search2;

    /**
     * Initializes the controller class.
     */
    
    
    DBAction dbaction = new DBAction();
    static ObservableList<Employee> list1 = FXCollections.observableArrayList();
    static ObservableList<Employee> list2 = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //At First clear Value from JAVA FX Table
        EmployeePanelController.emplist2.clear();
        EmployeePanelController.emplist.clear();
        
        
        //This Function Only For Employee Table
        //Create Multiple Row Selected option
        EmployeeTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        EmployeeDetailsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        //Database Action
        DBAction dbaction = new DBAction();  
               
        
        try {
            EmployeePanelController.emplist = dbaction.getAllEmployee();
        } catch (SQLException ex) {
            Logger.getLogger(AllEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        EmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        Designation.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        JoinDate.setCellValueFactory(new PropertyValueFactory<>("JoinDate"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        
        
        EmployeeTable.setItems(EmployeePanelController.emplist);
       
        
        //This Function Only For Employee Details Table

        
        try {
            //Database Action
            EmployeePanelController.emplist2 = dbaction.getAllEmployeeDetails();
        } catch (SQLException ex) {
            Logger.getLogger(AllEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Put Value from Database into JavaFX Table
        ID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Gendar.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
        Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        Passport.setCellValueFactory(new PropertyValueFactory<>("Passport_NID"));
        
        //Set Item in EmployeePanel Controller
        EmployeeDetailsTable.setItems(EmployeePanelController.emplist2);
        
        
        
    }    


    @FXML
    private void ExportAllEmployee(ActionEvent event) {
    }

    @FXML
    private void DeleteEmployee(ActionEvent event) throws SQLException {
        //Create Observable List for get Selected item        
        ObservableList<Employee> SelectedEmployee = FXCollections.observableArrayList();
        SelectedEmployee = EmployeeTable.getSelectionModel().getSelectedItems();
        
        //Remove Selected Item From Database
        DBAction dbaction = new DBAction();
        dbaction.DeleteEmployes(SelectedEmployee);
        
        //Remove Selected Item from Javafx Table
        EmployeePanelController.emplist.removeAll(SelectedEmployee);
        list1.removeAll(SelectedEmployee);
        list2.removeAll(SelectedEmployee);
        
        JOptionPane.showMessageDialog(null, "Employee have Successfully Delate");
        
    }
    
    

    @FXML
    private void ExportEmployee(ActionEvent event) throws SQLException, FileNotFoundException, IOException {
        
        
        Connection con = dbaction.getConnection();
        String query = "SELECT Employee_ID, Designation, Join_Date, Salary FROM employee";
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        
        //Create Excell Worksheet
        Workbook wb = new XSSFWorkbook();
        
        //Create Excell Sheet
        Sheet sheet = wb.createSheet("Employe Details");
        
        
        
        //Create Header Column Row
        Row header = sheet.createRow(0);
        
        //Push Header column Name
        header.createCell(0).setCellValue("Employee ID");
        header.createCell(1).setCellValue("Designation");
        header.createCell(2).setCellValue("Join Date");
        header.createCell(3).setCellValue("Salary");
        
        //Create Index for Multiple row
        int index = 1;
        //Push Content Excell from Database
        while(resultset.next())
        {            //Create Multiple variable row
            Row row = sheet.createRow(index);
            
            //Get data from Database
            int id = resultset.getInt("Employee_ID");
            String des = resultset.getString("Designation");
            Date date = resultset.getDate("Join_Date");
            double sal = resultset.getDouble("Salary");
            
            row.createCell(0).setCellValue(id);
            row.createCell(1).setCellValue(des);
            row.createCell(2).setCellValue(date);
            row.createCell(3).setCellValue(sal);
            
            //Increase row one by one
            index++;            
        }
        
        
        FileOutputStream fileOut = new FileOutputStream("contacts.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
        JOptionPane.showMessageDialog(null, "Your Employee Details Export Sucess!","Welcome",1);
                
    }


    @FXML
    private void ExportEmployeeDetails(ActionEvent event) {
    }

    @FXML
    private void DeleteEmployeeDetails(ActionEvent event) throws SQLException {
        
        //Create Observable List for get Selected item        
        ObservableList<Employee> SelectedEmployee = FXCollections.observableArrayList();
        SelectedEmployee = EmployeeDetailsTable.getSelectionModel().getSelectedItems();
        
        //Remove Selected Item from Database
        DBAction dbaction = new DBAction();
        dbaction.DeleteEmployesDetails(SelectedEmployee);
        
        //Remove Selected Item from Javafx Table
        EmployeePanelController.emplist2.removeAll(SelectedEmployee);
    }

    //Search employe Method for Employee Table
   
    @FXML
    private void SearchEmployee(ActionEvent event) throws SQLException {
        
        list1.clear();
        
        try{
            
        String search = search1.getText();
        if(search.equals("")){
            initialize(null,null);
            
        }
        else{
            
            DBAction dbaction = new DBAction();        
            list1 = dbaction.SearchEmployee(Integer.parseInt(search));
            EmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
            Designation.setCellValueFactory(new PropertyValueFactory<>("Designation"));
            JoinDate.setCellValueFactory(new PropertyValueFactory<>("JoinDate"));
            Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        
            EmployeeTable.setItems(list1);
            
        }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please Type Correct Employee ID");
        }
        
        
    }

    @FXML
    private void SearchEmployeeDetails(ActionEvent event) throws SQLException {
        
        list1.clear();
        list2.clear();
        
        try{
        String search = Search2.getText();
        if(search.equals("")){
            initialize(null,null);
        }
        else{
        
            DBAction dbaction = new DBAction();
        
            list2 = dbaction.SearchEmployeeDetails(Integer.parseInt(search));
        
            //Put Value from Database into JavaFX Table
            ID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
            FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            LastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
            Phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            Gendar.setCellValueFactory(new PropertyValueFactory<>("Gender"));
            Country.setCellValueFactory(new PropertyValueFactory<>("Country"));
            Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            Passport.setCellValueFactory(new PropertyValueFactory<>("Passport_NID"));
        
            //Set Item in EmployeePanel Controller
            EmployeeDetailsTable.setItems(list2);
        }
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please Type Correct Employee ID");
        }
        
        
        
    }
    
}
