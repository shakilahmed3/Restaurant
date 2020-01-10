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
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class KitchenController implements Initializable {

    @FXML
    private ComboBox<String> KitchenNo;
    @FXML
    private TableView<OrderFood> KitchenTable;
    @FXML
    private TableColumn<OrderFood, Integer> OrderIDC;
    @FXML
    private TableColumn<OrderFood, String> FoodNameC;
    @FXML
    private TableColumn<OrderFood, Integer> QuantityC;
    @FXML
    private TableColumn<OrderFood, Integer> TableNoC;
    @FXML
    private TableColumn<OrderFood, String> OrderTypeC;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<String> AllKitchen = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<OrderFood> AllOrder = FXCollections.observableArrayList();
    DBAction dbaction = new DBAction();
    @FXML
    private TableColumn<OrderFood, Integer> KitchenC;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        KitchenNo.setItems(AllKitchen);
        
        try {
            //Intitialize Method for JAVAFX Kitchen Table Set Item
            getAllOrder();
        } catch (SQLException ex) {
            Logger.getLogger(KitchenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        KitchenTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }    

    
    
    //Kitchen Table Set All Kitchen Order Item After Query    
    void getAllOrder() throws SQLException{
        
          AllOrder.clear();
          
          AllOrder = getAllOrderId();
          
          OrderIDC.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
          FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
          QuantityC.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
          TableNoC.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
          OrderTypeC.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
          KitchenC.setCellValueFactory(new PropertyValueFactory<>("KitchenNo"));

          KitchenTable.setItems(AllOrder);    
        
    }
    
    
    
    
   //Get All Food Order For Specific Order Table    
   ObservableList<OrderFood> getAllOrder(int kitchenid) throws SQLException{
        
        
        ObservableList<OrderFood> Orderlist = FXCollections.observableArrayList();
        
        Connection con = dbaction.getConnection();
        String query = "SELECT * FROM kitchen WHERE kitchenId = "+kitchenid;
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        while(resultset.next())
        {
             //Get data from Database    
            int orderid = resultset.getInt("Order_ID");
            String foodName = resultset.getString("Food_Name");
            int quantity = resultset.getInt("Quantity");
            String ordertype = resultset.getString("Order_Type");
            int table = resultset.getInt("TableNo");
            int kitchen = resultset.getInt("kitchenId");
           
            //Initialize data in Employee object
            OrderFood orderfood = new OrderFood(orderid,foodName,quantity,table,kitchen,ordertype);            
            
            Orderlist.add(orderfood);
            
        }   
        
        return Orderlist;        
        
    }

      //Get All Food Order For Specific Order Table    
   ObservableList<OrderFood> getAllOrderId() throws SQLException{
        
        
        ObservableList<OrderFood> Orderlist = FXCollections.observableArrayList();
        
        Connection con = dbaction.getConnection();
        String query = "SELECT * FROM kitchen";
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        while(resultset.next())
        {
            //Get data from Database    
            int orderid = resultset.getInt("Order_ID");
            String foodName = resultset.getString("Food_Name");
            int quantity = resultset.getInt("Quantity");
            String ordertype = resultset.getString("Order_Type");
            int table = resultset.getInt("TableNo");
            int kitchen = resultset.getInt("kitchenId");
           
            //Initialize data in Employee object
            OrderFood orderfood = new OrderFood(orderid,foodName,quantity,table,kitchen,ordertype);            
            
            Orderlist.add(orderfood);
            
        }   
        
        return Orderlist;        
        
    }
      
    
    
    @FXML
    private void KitchenAction(ActionEvent event) throws SQLException {
        
        String kitchenid = KitchenNo.getValue();
        
        if(kitchenid.equals(""))return;
        
        int Kid = Integer.parseInt(kitchenid);
        
        
          AllOrder.clear();
          
          AllOrder = getAllOrder(Kid);
          
          OrderIDC.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
          FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
          QuantityC.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
          TableNoC.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
          OrderTypeC.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
          KitchenC.setCellValueFactory(new PropertyValueFactory<>("KitchenNo"));

          KitchenTable.setItems(AllOrder);    
        
    }

    @FXML
    private void FoodServe(ActionEvent event) throws SQLException {
        
        //Create Observable List for get Selected item        
        ObservableList<OrderFood> SelectedOrder = FXCollections.observableArrayList();
        SelectedOrder = KitchenTable.getSelectionModel().getSelectedItems();
        
        //Remove Selected Item From Database
         DeleteOrder(SelectedOrder);
        
        //Remove Selected Item from Javafx Table
        AllOrder.removeAll(SelectedOrder);
        
        JOptionPane.showMessageDialog(null, "Order Serve has been Successfull");
        
    }
    
    //Delete Order After Cooking and Serve from Kitchen Table
    void DeleteOrder(ObservableList<OrderFood> SelectedOrder) throws SQLException{
        
        Connection con = dbaction.getConnection();        
        Statement statement = con.createStatement();
         
         for(OrderFood orderfood : SelectedOrder)
         {
            String Query = "DELETE FROM kitchen WHERE Order_ID = "+orderfood.OrderId;
            statement.executeUpdate(Query);
         }
         con.close();
         statement.close();        
        
    }
    
}
