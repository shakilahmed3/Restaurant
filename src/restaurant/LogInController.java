
package restaurant;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class LogInController implements Initializable {

    @FXML
    private TextField UserId;
    @FXML
    private PasswordField UserPassword;
    @FXML
    private ImageView foodimage;

    DBAction dbaction = new DBAction();
    
    Stage stage = new Stage();
    Stage stage2 = new Stage();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        //Add Background image in login panel
        Image foodimg = new Image("/Images/Food3.jpg");
        foodimage.setImage(foodimg);
               
    }    
    
        //Image Icon For Application Icon
        Image image = new Image("/Images/Logo22.png");
        
    
    static ObservableList<AppUser> appuserlist = FXCollections.observableArrayList();
    
    //Admin Login Button Action
    @FXML
    private void adminlogin(ActionEvent event) throws SQLException, IOException {
        
        String username = UserId.getText();
        String password = UserPassword.getText();
       
        appuserlist = dbaction.getAllUser();
        
        int i=0;
        for(AppUser app : appuserlist)
        {
            String usern = app.UserName;
            String pass = app.Password;
            
            if(username.equalsIgnoreCase(usern) && password.equalsIgnoreCase(pass))
            {
                Parent root = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
                Scene scene = new Scene(root);
                stage.getIcons().add(image);
                stage.setTitle("Admin Panel");
                stage.setScene(scene);              
                
                Restaurant.stage.close();
                stage.show();
                i = 1;
                break;
                //JOptionPane.showMessageDialog(null, "Welcome to Admin Panel","Welcome",1);
                
            }else{
                i =0;
            }
            i =0;                        
        }
        //Provide Information to better user interface.
        if(i==1){
            JOptionPane.showMessageDialog(null, "Welcome to Admin Panel","Welcome",1);
        }else{
            JOptionPane.showMessageDialog(null, "Your id or password Wrong!","Wrong Information",0);
        }
        
    }

    //User Login Buttion Action
    @FXML
    private void userlogin(ActionEvent event) throws SQLException, IOException {
        
        String username = UserId.getText();
        String password = UserPassword.getText();
       
        appuserlist = dbaction.getAllAppUser();
        
        int i=0;
        for(AppUser app : appuserlist)
        {
            String usern = app.UserName;
            String pass = app.Password;
            
            if(username.equalsIgnoreCase(usern) && password.equalsIgnoreCase(pass))
            {
                Parent root = FXMLLoader.load(getClass().getResource("UserPanel.fxml"));
                Scene scene = new Scene(root);
                stage.getIcons().add(image);
                stage.setTitle("User Panel");
                stage.setScene(scene);                
                
                Restaurant.stage.close();
                stage.show();
                i = 1;
                break;
                //JOptionPane.showMessageDialog(null, "Welcome to Admin Panel","Welcome",1);
                
            }else{
                i =0;
            }
            i =0;                        
        }
        //Provide Information to better user interface.
        if(i==1){
            JOptionPane.showMessageDialog(null, "Welcome to User Panel","Welcome",1);
        }else{
            JOptionPane.showMessageDialog(null, "Your id or password Wrong!","Wrong Information",0);
        }  
        
        
        
    }
    
}
