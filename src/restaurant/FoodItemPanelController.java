/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class FoodItemPanelController implements Initializable {

    @FXML
    private TableView<Food> FoodItemTable;
    @FXML
    private TableColumn<Food, Integer> FoodIDC;
    @FXML
    private TableColumn<Food, String> FoodNameC;
    @FXML
    private TableColumn<Food, Double> BuyingPriceC;
    @FXML
    private TableColumn<Food, Double> SellingPriceC;
    @FXML
    private TableColumn<Food, Double> ProfitC;
    @FXML
    private TextField Search;

    DBAction dbaction = new DBAction();
    ObservableList<Food> foodlist = FXCollections.observableArrayList();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            SetAllUser();
        } catch (SQLException ex) {
            Logger.getLogger(FoodItemPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    
    //Method For App User Table
    void SetAllUser() throws SQLException
    {
        //Clear Initial Table Data
        foodlist.clear();
        
        //Set Multiple Selection Method
        FoodItemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);                
        
        
        foodlist = getAllFood();
        
        
        //Put Value from Database into JavaFX Table
        FoodIDC.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
        FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
        BuyingPriceC.setCellValueFactory(new PropertyValueFactory<>("ByingPrice"));
        SellingPriceC.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
        ProfitC.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        
        FoodItemTable.setItems(foodlist);          
        
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
    private void ExportFoodItem(ActionEvent event) {
    }

    @FXML
    private void SearchFood(ActionEvent event) {
        
        foodlist.clear();
        
        try{
            
        String search = Search.getText();
        if(search.equals("")){
            SetAllUser();
        }
        else{
            
            foodlist = getSpecificFood(Integer.parseInt(search));
            
            FoodIDC.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
            FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
            BuyingPriceC.setCellValueFactory(new PropertyValueFactory<>("ByingPrice"));
            SellingPriceC.setCellValueFactory(new PropertyValueFactory<>("SellingPrice"));
            ProfitC.setCellValueFactory(new PropertyValueFactory<>("Profit"));
        
            FoodItemTable.setItems(foodlist);
            
        }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please Type Correct Employee ID");
        }      
        
    }
    
    //Search Using Specific Food Id
    
    ObservableList<Food> getSpecificFood(int foodid) throws SQLException{
        
        ObservableList<Food> list = FXCollections.observableArrayList();         
         
         Connection con = dbaction.getConnection();
         Statement statement = con.createStatement();
         
         String Query = "SELECT Food_Id,Food_Name,Buying_price,Selling_Price,Profit FROM food WHERE Food_Id ="+foodid;
         
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
    private void DeleteFoodItem(ActionEvent event) throws SQLException {
        
        ObservableList<Food> SelectedFood = FXCollections.observableArrayList();
        SelectedFood = FoodItemTable.getSelectionModel().getSelectedItems();
        
        //Remove Selected Item From Database
        DeleteFoods(SelectedFood);
        
        //Remove Selected Item from Javafx Table
        foodlist.removeAll(SelectedFood);
        
        JOptionPane.showMessageDialog(null, "Food have Successfully Delate","Welcome",1);
    }
    
    
   //Delete Employees Database Action
    void DeleteFoods(ObservableList<Food> SelectedFood) throws SQLException
     {
         Connection con = dbaction.getConnection();
         Statement statement = con.createStatement();
         
         for(Food food : SelectedFood)
         {
            String Query = "DELETE FROM food WHERE Food_Id =  "+food.getFoodId();
            statement.executeUpdate(Query);
         }
     }
    
    
}
