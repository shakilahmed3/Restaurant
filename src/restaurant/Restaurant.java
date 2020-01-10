
package restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**admin
 *
 * @author Shakil Ahmed
 */


public class Restaurant extends Application{

    static Stage stage = new Stage();    
    
    
    public static void main(String[] args) {

           launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Add Software Icon Images
        Image image = new Image("/Images/Logo22.png");
        primaryStage.getIcons().add(image);
        
        //Root Panel for Log insha
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("LogIn.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log In Restaurant");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
    
}
