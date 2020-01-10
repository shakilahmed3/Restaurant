
package restaurant;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class UserPanelController implements Initializable {

    @FXML
    private BorderPane UserPanel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
            UserPanel.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(UserPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void DashboardAction(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("UserDashboard.fxml"));
        UserPanel.setCenter(root);
        
    }

    @FXML
    private void OrderFoodAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("OrderPanel.fxml"));
        UserPanel.setCenter(root);
    }

    @FXML
    private void Kitchen(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Kitchen.fxml"));
        UserPanel.setCenter(root);
    }
    
}
