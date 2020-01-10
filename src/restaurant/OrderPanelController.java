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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
public class OrderPanelController implements Initializable {

    @FXML
    private TextField OrderID;
    @FXML
    private TextField FoodID;
    @FXML
    private TextField FoodName;
    @FXML
    private TextField Quantity;
    @FXML
    private ComboBox<Integer> TableNo;
    @FXML
    private ComboBox<String> KitchenNo;
    @FXML
    private TextField TotalPrice;
    @FXML
    private ComboBox<String> OrderType;
    @FXML
    private TableColumn<OrderFood, Integer> OrderIDC;
    @FXML
    private TableColumn<OrderFood, Integer> FoodIDC;
    @FXML
    private TableColumn<OrderFood, String> FoodNameC;
    @FXML
    private TableColumn<OrderFood, Integer> QuantityC;
    @FXML
    private TableColumn<OrderFood, Double> PriceC;
    @FXML
    private TableColumn<OrderFood, Integer> TableNoC;
    @FXML
    private TableColumn<OrderFood, String> OrderTypeC;
    @FXML
    private TableColumn<OrderFood, Integer> KitchenC;
    @FXML
    private TableView<OrderFood> OrderTable;

    
    
    ObservableList<Integer> table = FXCollections.observableArrayList();
    ObservableList<Floor> tablelist = FXCollections.observableArrayList();
    ObservableList<String> AllKitchen = FXCollections.observableArrayList("1","2","3","4","5");
    ObservableList<String> AllOrderType = FXCollections.observableArrayList("Home Delivery","Table");
    
    ObservableList<OrderFood> AllOrder = FXCollections.observableArrayList();
    
    
    
    DBAction dbaction = new DBAction();
    
    
    /**
     * Initializes the controller class.
     */      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getAllTable();
            countOrderId();
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        KitchenNo.setItems(AllKitchen);
        OrderType.setItems(AllOrderType);      
        
    }

        void countOrderId() throws SQLException
        {
               
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String Query = "SELECT count(Order_ID) as SumOrderId FROM order_table";
         
        int TotalId;
        ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
            int id = resultset.getInt("SumOrderId");
                        
            TotalId = (id + 1);
            
            //Set Item in All Text Field
            OrderID.setText(""+TotalId); 
             
         }
        
            
        }

   
    //Available Table Search Method
    
    void getAllTable() throws SQLException
    {
        
            tablelist = dbaction.getAllTable();
            for(Floor floor: tablelist)
            {
                int table1 = floor.TableId;
                table.addAll(table1);
            }
                   
        if(table.isEmpty())
        {
           TableNo.setValue(0);
        }else{
            TableNo.setItems(table);
        }
        
        
    }
    
    
    
    @FXML
    private void AddFood(ActionEvent event) throws SQLException {
        
         
        String orderid = OrderID.getText();
        String foodid = FoodID.getText();
        String foodname = FoodName.getText();
        String quantity = Quantity.getText();
        int table = TableNo.getValue();
        String kitchen = KitchenNo.getValue();
        String totalprice = TotalPrice.getText();
        String ordertype = OrderType.getValue();
        
        if(orderid.equals("") && foodid.equals("") && foodname.equals("") && quantity.equals("") && kitchen.equals("") && totalprice.equals("") && ordertype.equals(""))return;
        
        
        int oid = Integer.parseInt(orderid);
        int fid = Integer.parseInt(foodid);
        int quan = Integer.parseInt(quantity);
        int kit = Integer.parseInt(kitchen);
        Double tprice = Double.parseDouble(totalprice);
        
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();        
        
        
        
        Statement statement2 = con.createStatement();
        //Calculate Profit for each food
        String Query1 = "SELECT Profit FROM food WHERE  Food_Id  = "+fid;
         
        ResultSet resultset = statement2.executeQuery(Query1);
         
        double Total=0;
         while(resultset.next())
         {
            Double profit = resultset.getDouble("Profit");
                        
            Total = (profit * quan);  
         }
                     
           //Insert Food Item for Temporary
          String Query = "INSERT INTO temptable(Order_ID,FoodID,FoodName,Quantity,OrderType,TableNo,Kitchen,TotalPrice,Profit) VALUES("+oid+","+fid+",'"+foodname+"', "+quan+", '"+ordertype+"',"+table+","+kit+","+tprice+","+Total+")";
          statement.executeUpdate(Query); 
                
          
          //Call get All Order List
          AllOrder.clear();
          
          AllOrder = getAllOrder(oid);
          
          OrderIDC.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
          FoodIDC.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
          FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
          QuantityC.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
          PriceC.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
          TableNoC.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
          OrderTypeC.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
          KitchenC.setCellValueFactory(new PropertyValueFactory<>("KitchenNo"));

          OrderTable.setItems(AllOrder);           
          
          
          JOptionPane.showMessageDialog(null, "Successfully Added food","Welcome",1);
          
          FoodID.clear();
          FoodName.clear();
          Quantity.clear();
          TotalPrice.clear();
        
    }
    
    
    //Get All Food Order For Specific Order Table
    
   ObservableList<OrderFood> getAllOrder(int OrderId) throws SQLException{
        
        
        ObservableList<OrderFood> Orderlist = FXCollections.observableArrayList();
        
        Connection con = dbaction.getConnection();
        String query = "SELECT Order_ID,FoodID,FoodName,Quantity,OrderType,TableNo,Kitchen,TotalPrice FROM temptable WHERE Order_ID = "+OrderId;
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        while(resultset.next())
        {
            //Get data from Database    
            int orderid = resultset.getInt("Order_ID");
            int foodid = resultset.getInt("FoodID");
            String foodname = resultset.getString("FoodName");
            int quantity = resultset.getInt("Quantity");
            String ordertype = resultset.getString("OrderType");
            int table = resultset.getInt("TableNo");
            int kitchen = resultset.getInt("Kitchen");
            double totalprice = resultset.getDouble("TotalPrice");
           
            //Initialize data in Employee object
            OrderFood orderfood = new OrderFood(orderid,foodid,foodname,quantity,table,kitchen,totalprice,ordertype);            
            
            Orderlist.add(orderfood);
            
        }   
        
        return Orderlist;        
        
    }
   
   
    //Get All Food Order For Specific Order Table
    
   ObservableList<OrderFood> getAllOrderId() throws SQLException{
        
        
        ObservableList<OrderFood> Orderlist = FXCollections.observableArrayList();
        
        Connection con = dbaction.getConnection();
        String query = "SELECT Order_ID,FoodID,FoodName,Quantity,OrderType,TableNo,Kitchen,TotalPrice FROM temptable";
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        while(resultset.next())
        {
            //Get data from Database    
            int orderid = resultset.getInt("Order_ID");
            int foodid = resultset.getInt("FoodID");
            String foodname = resultset.getString("FoodName");
            int quantity = resultset.getInt("Quantity");
            String ordertype = resultset.getString("OrderType");
            int table = resultset.getInt("TableNo");
            int kitchen = resultset.getInt("Kitchen");
            double totalprice = resultset.getDouble("TotalPrice");
           
            //Initialize data in Employee object
            OrderFood orderfood = new OrderFood(orderid,foodid,foodname,quantity,table,kitchen,totalprice,ordertype);            
            
            Orderlist.add(orderfood);
            
        }   
        
        return Orderlist;        
        
    }
   
   

    @FXML
    private void OrderFood(ActionEvent event) throws SQLException {
        
        String Id = OrderID.getText();
        if(Id.equals(""))return;
        
        int oid = Integer.parseInt(Id);
        
        Connection con = dbaction.getConnection();
       
        //Statement for Query Order Taable
        Statement statement = con.createStatement();     
        
        //Query for Insert Value in Order Table
        Statement statement1 = con.createStatement();
        
        //Statement to Calculate Total and Total Profit
        Statement statement2 = con.createStatement();
        String Q = "SELECT sum(TotalPrice) as GrandTotal FROM temptable WHERE Order_ID = "+oid;
        double Total=0;
        ResultSet rs1 = statement2.executeQuery(Q);
        while(rs1.next())
        {
            Total = rs1.getDouble("GrandTotal");
        }
        
        
        Statement statement3 = con.createStatement();
        String Q2 = "SELECT sum(Profit) as GrandTotal FROM temptable WHERE Order_ID = "+oid;
        double TotalProfit=0;
        ResultSet rs2 = statement3.executeQuery(Q2);
        while(rs2.next())
        {
            TotalProfit = rs2.getDouble("GrandTotal");
        }
        
        //Query Specific one column for Order Food Table
        String Query = "SELECT Order_ID, OrderType, Kitchen FROM temptable WHERE Order_ID = "+oid+" GROUP BY Order_ID;";
        ResultSet rs = statement.executeQuery(Query);        
        
        while(rs.next())
            
        {
            //Insert Value in Order Table from collecting data in temp table database
            String Query2 = "INSERT INTO order_table(Order_ID, Order_Type,Kitchen,Total,Profit) VALUES("+rs.getInt("Order_ID")+",'"+rs.getString("OrderType")+"',"+rs.getInt("Kitchen")+","+Total+","+TotalProfit+")";
            statement1.executeUpdate(Query2);
        }
        
        
        
        //Statement For query Purchase Table
        Statement statement4 = con.createStatement();
        
         //Query For Insert Value in Purchase Table
        Statement statement5 = con.createStatement();
        
        
        //Query Specific one by one column from purchase table
        String q1 = "SELECT Order_ID, TableNo, FoodID, FoodName, Quantity, TotalPrice, Profit,Kitchen,OrderType FROM temptable WHERE Order_ID = "+oid;
        ResultSet rs4 = statement4.executeQuery(q1);
        
        //For Table Status Update 
        //Update Table Status After Confirming Order.
        Statement tablestatment = con.createStatement();
        LocalDate date = LocalDate.now();        
        Date date1 = Date.valueOf(date);
        
        //System.out.println(""+date1+" "+date);
        
        //Insert Kitchen Food Statement
        Statement kitchenstate = con.createStatement();
        
        
        while(rs4.next())
        {
            //Insert Value in Purchage Table After Collecting data from temp table database
            String Que ="INSERT INTO purchase(Order_ID,Table_Id, Food_Id,Quantity,Sub_Total, Sub_Profit, Selldate) VALUES("+rs4.getInt("Order_ID")+","+rs4.getInt("TableNo")+","+rs4.getInt("FoodID")+","+rs4.getInt("Quantity")+","+rs4.getDouble("TotalPrice")+","+rs4.getDouble("Profit")+",'"+date1+"')";
            statement5.executeUpdate(Que);
            
            //Insert Value in Kitchen Table
            String kitchenQuery= "INSERT INTO kitchen(Order_ID,Food_Name,Quantity,TableNo,Order_Type,kitchenId) VALUES("+rs4.getInt("Order_ID")+",'"+rs4.getString("FoodName")+"',"+rs4.getInt("Quantity")+","+rs4.getInt("TableNo")+",'"+rs4.getString("OrderType")+"',"+rs4.getInt("Kitchen")+")";
            kitchenstate.executeUpdate(kitchenQuery);            
            
            
            //For Table Update Statment
            String Tablequery = "UPDATE floor1 set Status = 'Booked' WHERE floor1.Table_Id = "+rs4.getInt("TableNo");
            tablestatment.executeUpdate(Tablequery);
            
        }
        
        
        //After Completing Order Delete Temporary table data in Database
        Statement statement6 = con.createStatement();
        String DeleteQuery = "DELETE FROM temptable WHERE Order_ID = "+oid;
        statement6.executeUpdate(DeleteQuery);
        
        AllOrder.clear();
        JOptionPane.showMessageDialog(null, "You are Successfully Completed Order! Thank you.", "Welcome!",1);
        
        
    }

    @FXML
    private void UpdateFood(ActionEvent event) {
    }

    @FXML
    private void CancelOrder(ActionEvent event) throws SQLException {
        
        String Id = OrderID.getText();
        if(Id.equals(""))return;
        
        int oid = Integer.parseInt(Id);
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate("DELETE FROM temptable WHERE Order_ID = "+oid);
        
        JOptionPane.showMessageDialog(null, "Successfully Cancel Order!","Welcome",1);
    }

    //Search Food and Set Food name in Food Name Text Field
    @FXML
    private void FoodIDAction(ActionEvent event) {
        
        try{
        String Id = FoodID.getText();
        if(Id.equals(""))return;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String Query = "SELECT Food_Name FROM food WHERE Food_Id  = "+Integer.parseInt(Id);
         
        ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
            String foodname = resultset.getString("Food_Name");
            
           
            //Set Item in All Text Field
            FoodName.setText(foodname);            
            
             
         }
         
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Please Type Correct Food ID","Wrong!",0);
        }
         
        
    }

    //Quantity Action for Calculation Profit and Total Price for Each Items
    @FXML
    private void QuantityAction(ActionEvent event) throws SQLException {
        
        try{
        String Id = FoodID.getText();
        String quantity = Quantity.getText();
        if(Id.equals("") && quantity.equals(""))return;
        
        Double quan = Double.parseDouble(quantity);
        Double Total;
        
        Connection con = dbaction.getConnection();
        Statement statement = con.createStatement();
        String Query = "SELECT Selling_Price FROM food WHERE Food_Id  = "+Integer.parseInt(Id);
         
        ResultSet resultset = statement.executeQuery(Query);
         
         while(resultset.next())
         {
            Double sellingprice = resultset.getDouble("Selling_Price");
                        
            Total = (sellingprice * quan);
            
            //Set Item in All Text Field
            TotalPrice.setText(""+Total);  
             
         }
         
        }catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Please Order At least 1 Food");
        }
         
    }

    @FXML
    private void OrderFieldAction(ActionEvent event) throws SQLException {
        
        String orderid = OrderID.getText();
         
         if(orderid.equals(""))
         {
          AllOrder.clear();
         
          
          AllOrder = getAllOrderId();
          
          OrderIDC.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
          FoodIDC.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
          FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
          QuantityC.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
          PriceC.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
          TableNoC.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
          OrderTypeC.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
          KitchenC.setCellValueFactory(new PropertyValueFactory<>("KitchenNo"));

          OrderTable.setItems(AllOrder);
         }else{
          int oid = Integer.parseInt(orderid);
           AllOrder.clear();         
          
          AllOrder = getAllOrder(oid);
          
          OrderIDC.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
          FoodIDC.setCellValueFactory(new PropertyValueFactory<>("FoodId"));
          FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
          QuantityC.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
          PriceC.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
          TableNoC.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
          OrderTypeC.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
          KitchenC.setCellValueFactory(new PropertyValueFactory<>("KitchenNo"));

          OrderTable.setItems(AllOrder);
         }
          
    }
    
}
