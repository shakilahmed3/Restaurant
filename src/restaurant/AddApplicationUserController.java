/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant;

import com.mysql.jdbc.Connection;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class AddApplicationUserController implements Initializable {

    @FXML
    private TextField EmployeeId;
    @FXML
    private TextField UserName;
    @FXML
    private TextField Password;
    @FXML
    private TableColumn<AppUser, String> UserNameC;
    @FXML
    private TableColumn<AppUser, String> PasswordC;
    @FXML
    private TextField Search;
   

    @FXML
    private TableColumn<AppUser, Integer> EmployeeIDC;
    
    /**
     * Initializes the controller class.
     */
    
    DBAction dbaction = new DBAction();
    ObservableList<AppUser> UserList = FXCollections.observableArrayList();
    
    @FXML
    private TableView<AppUser> AppUserTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        try {
            SetAllUser();
        } catch (SQLException ex) {
            Logger.getLogger(AddApplicationUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
//Method For App User Table
    void SetAllUser() throws SQLException
    {
        //Clear Initial Table Data
        UserList.clear();
        
        //Set Multiple Selection Method
        AppUserTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                
        
        try {
            UserList= dbaction.getAllAppUser1();
        } catch (SQLException ex) {
            Logger.getLogger(AddApplicationUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Put Value from Database into JavaFX Table
        EmployeeIDC.setCellValueFactory(new PropertyValueFactory<>("ID"));
        UserNameC.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        PasswordC.setCellValueFactory(new PropertyValueFactory<>("Password"));
        
        AppUserTable.setItems(UserList);   
        
        
    }


    @FXML
    private void AddAppUser(ActionEvent event) throws SQLException {
        
        String id = EmployeeId.getText();
        String user = UserName.getText();
        String pass = Password.getText();
        if(id.equals("") && user.equals("") && pass.equals(""))return;
        
        int id1 = Integer.parseInt(id);
        //System.out.println(id1+" "+user+" "+pass);
        
        
        Connection con = dbaction.getConnection();
         Statement state = con.createStatement();
        
        //Query for insert App User
        String query = "INSERT INTO app_user(Employee_ID,UserName,Password) VALUES("+id1+",'"+user+"','"+pass+"')";
        state.executeUpdate(query);
                
         JOptionPane.showMessageDialog(null, "Congrats! Application user has been Added", "Welcome", 1);
         
         EmployeeId.clear();
         UserName.clear();
         Password.clear();
    }

    @FXML
    private void SearchUser(ActionEvent event) throws SQLException {
        
        try{
        String Id = EmployeeId.getText();
        if(Id.equals(""))return;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String Query = "SELECT UserName,Password FROM app_user WHERE Employee_ID = "+Id;
         
        ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
            String user = resultset.getString("UserName");
            String pass = resultset.getString("Password");
            
            
            //Set Item in All Text Field
            UserName.setText(user);
            Password.setText(pass);            
             
         }
         
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Please Type Correct Employee ID");
        }
    }

    @FXML
    private void UpdateAppUser(ActionEvent event) throws SQLException {
        
        String id = EmployeeId.getText();
        String user = UserName.getText();
        String pass = Password.getText();
        if(id.equals("") && user.equals("") && pass.equals(""))return;
        
        int id1 = Integer.parseInt(id);
        //System.out.println(id1+" "+user+" "+pass);
        
        
         Connection con = dbaction.getConnection();
         Statement state = con.createStatement();
        
        //Query for insert App User
        String query = "UPDATE app_user SET UserName = '"+user+"', Password ='"+pass+"' WHERE Employee_ID = "+id1+"";
        state.executeUpdate(query);
                
         JOptionPane.showMessageDialog(null, "Congrats! Application user has been Updated", "Welcome", 1);
         
         EmployeeId.clear();
         UserName.clear();
         Password.clear();
    }

    @FXML
    private void DeleteAppUser(ActionEvent event) throws SQLException {
        
        ObservableList<AppUser> SelectedAppUser = FXCollections.observableArrayList();
        SelectedAppUser = AppUserTable.getSelectionModel().getSelectedItems();
        
         Connection con = dbaction.getConnection();
         Statement statement = con.createStatement();
         
         for(AppUser appuser : SelectedAppUser)
         {
            String Query = "DELETE FROM app_user WHERE Employee_ID =  "+appuser.getID();
            statement.executeUpdate(Query);
         }
         UserList.removeAll(SelectedAppUser);
         
        JOptionPane.showMessageDialog(null, "Employee have Successfully Delate");
        
    }

    @FXML
    private void Search(ActionEvent event) throws SQLException {
        
        UserList.clear();
        
        String Id = Search.getText();
       
        if(Id.equals(""))
        {
            SetAllUser();
        }
        else{                
        
            UserList = dbaction.SearchAppUser(Integer.parseInt(Id));
            
            EmployeeIDC.setCellValueFactory(new PropertyValueFactory<>("ID"));
            UserNameC.setCellValueFactory(new PropertyValueFactory<>("UserName"));
            PasswordC.setCellValueFactory(new PropertyValueFactory<>("Password"));        
            AppUserTable.setItems(UserList);   
             
         }      
         
       }
}
    

