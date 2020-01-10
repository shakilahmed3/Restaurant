
package restaurant;

import java.net.URL;
import java.sql.Connection;
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

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class FoodPanelController implements Initializable {

    @FXML
    private TextField FoodId;
    @FXML
    private TextField ByingPrice;
    @FXML
    private TextField SellingPrice;
    @FXML
    private TextField FoodName;
    @FXML
    private TableView<Food> FoodTable;
    @FXML
    private TableColumn<Food, Integer> FoodIDC;
    @FXML
    private TableColumn<Food, String> FoodNameC;
    @FXML
    private TableColumn<Food, Double> ByingPriceC;
    @FXML
    private TableColumn<Food, Double> SellingPriceC;
    @FXML
    private TableColumn<Food, Double> ProfitC;

    DBAction dbaction = new DBAction();
    
    
   ObservableList<Food> foodlist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            SetAllUser();
        } catch (SQLException ex) {
            Logger.getLogger(FoodPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    //Method For App User Table
    void SetAllUser() throws SQLException
    {
        //Clear Initial Table Data
        foodlist.clear();
        
        //Set Multiple Selection Method
        FoodTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);                
        
        
        foodlist = getAllFood();
        
        
        //Put Value from Database into JavaFX Table
        FoodIDC.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
        FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
        ByingPriceC.setCellValueFactory(new PropertyValueFactory<>("ByingPrice"));
        SellingPriceC.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
        ProfitC.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        
        FoodTable.setItems(foodlist);          
        
    }
    
    
    //Get All Food Item Method
     ObservableList<Food> getAllFood() throws SQLException{
        
        ObservableList<Food> list = FXCollections.observableArrayList();         
         
         Connection con = dbaction.getConnection();
         Statement statement = con.createStatement();
         
         String Query = "SELECT Food_Id,Food_Name,Buying_price,Selling_Price,Profit FROM food;";
         
         ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
             int id = resultset.getInt("Food_Id");
             String fname = resultset.getString("Food_Name");
             Double bprice = resultset.getDouble("Buying_price");
             Double sprice = resultset.getDouble("Selling_Price");
             Double profit = resultset.getDouble("Profit");             
             
             Food food = new Food(id,fname,bprice,sprice,profit);
             list.add(food);             
         }
         return list;
    }
    
    
    
    @FXML
    private void AddFood(ActionEvent event) throws SQLException {
        
        String fid = FoodId.getText();
        String FName = FoodName.getText();
        String BPrice = ByingPrice.getText();
        String SPrice = SellingPrice.getText();
        
        if(fid.equals("") && FName.equals("") && BPrice.equals("") && SPrice.equals(""))return;
        
        
        int foodid = Integer.parseInt(fid);
        Double Buyprice = Double.parseDouble(BPrice);
        Double Sellprice = Double.parseDouble(SPrice);
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        
        String Query = "INSERT INTO food(Food_Id,Food_Name,Buying_price,Selling_Price,Profit) VALUES("+foodid+",'"+FName+"',"+Buyprice+", "+Sellprice+","+(Sellprice-Buyprice)+")";
        
        statement.executeUpdate(Query); 
        
        JOptionPane.showMessageDialog(null, "Congrats! Your Food Item Added", "Welcome", 1);
        
        FoodId.clear();
        FoodName.clear();
        ByingPrice.clear();
        SellingPrice.clear();
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
        
        String fid = FoodId.getText();
        String FName = FoodName.getText();
        String BPrice = ByingPrice.getText();
        String SPrice = SellingPrice.getText();
        
        if(fid.equals("") && FName.equals("") && BPrice.equals("") && SPrice.equals(""))return;
        
        
        int foodid = Integer.parseInt(fid);
        Double Buyprice = Double.parseDouble(BPrice);
        Double Sellprice = Double.parseDouble(SPrice);
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        
        String Query = "UPDATE food SET Food_Name = '"+FName+"', Buying_price = "+Buyprice+", Selling_Price = "+Sellprice+", Profit = "+(Sellprice - Buyprice)+" WHERE Food_Id = "+foodid;
        statement.executeUpdate(Query); 
        
        JOptionPane.showMessageDialog(null, "Congrats! Your Food Item Updated", "Welcome", 1);
        
        FoodId.clear();
        FoodName.clear();
        ByingPrice.clear();
        SellingPrice.clear();
        
    }

    
    
    //Search Food for Specific one id
    @FXML
    private void SearchFood(ActionEvent event) throws SQLException {
        
         try{
        String Id = FoodId.getText();
        if(Id.equals(""))return;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String Query = "SELECT Food_Name, Buying_price, Selling_Price FROM food Where  Food_Id = "+Integer.parseInt(Id);
         
        ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
            String foodn = resultset.getString("Food_Name");
            double bprice = resultset.getDouble("Buying_price");
            double sprice = resultset.getDouble("Selling_Price");
            
            
            //Set Item in All Text Field
            FoodName.setText(foodn);
            ByingPrice.setText(""+bprice);
            SellingPrice.setText(""+sprice);            
             
         }
         
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Please Type Correct Food Id", "Wrong",0);
        }
        
    }
    
}
