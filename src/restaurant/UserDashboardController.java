
package restaurant;

import java.awt.print.PrinterException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class UserDashboardController implements Initializable {

    @FXML
    private TableColumn<OrderFood, Integer> OrderIdC;
    @FXML
    private TableColumn<OrderFood, String> FoodNameC;
    @FXML
    private TableColumn<OrderFood, Integer> QuantityC;
    @FXML
    private TableColumn<OrderFood, String> OrderTypeC;
    @FXML
    private TableColumn<OrderFood, Double> PriceC;
    @FXML
    private TextField OrderId;
    @FXML
    private TextArea OutPut;
    @FXML
    private TableView<OrderFood> OrderTable;
    
    public Stage stage;    
    private final Label jobStatus = new Label();
    
    DBAction dbaction = new DBAction();
    
    ObservableList<OrderFood> AllOrder = FXCollections.observableArrayList();
    @FXML
    private Label GrandTotalLabel;

    
    
    Double TotalFPrice;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddOutputAction(ActionEvent event) throws SQLException {
         
        OutPut.clear();
        
         String orderid = OrderId.getText();
         if(orderid.equals(""))return;        
         int oid = Integer.parseInt(orderid);

         
         Date d = new Date();
         String date = d.toString();
         
         
         Connection con = dbaction.getConnection();
         
         
         Statement st1 = con.createStatement();
         String quer = "SELECT Table_Id FROM purchase WHERE Order_ID = "+oid;
         ResultSet re = st1.executeQuery(quer);
         
         
         
         
         OutPut.appendText("\t    Green Restaurant\n  220/D Begum Rokeya Sarani\n        Dhaka-1207 Bangladesh\n\tshakilatrai5@gmail.com"
         
                 +"\n  - - - - - - - - - - - - - - - - - - - - - - - -"
                 +"\nOrder ID : "+oid+"\t Table : "
         ); 
         
         String tab = new String();
         while(re.next())
         {
             tab = re.getString("Table_Id");
             
             OutPut.appendText(""+tab+", ");
         }
         
         
         OutPut.appendText(
         
         
                 "\nOrder Time : "+date
                 +"\n  - - - - - - - - - - - - - - - - - - - - - - - -"
                 +"\nFood Name\t Rate\t Qty\t Price"
                 
         );
         
         
        
         
        String query = "SELECT food.Food_Name,food.Selling_Price, purchase.Quantity,  purchase.Sub_Total FROM purchase join food ON purchase.Food_Id = food.Food_Id WHERE purchase.Order_ID ="+oid;
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        
        Statement statement2 = con.createStatement();
        String Query2 = "SELECT order_table.Order_Type FROM order_table WHERE Order_ID = "+oid;
        ResultSet result = statement2.executeQuery(Query2);
        
        String ordertype = "";
        while(result.next())
        {
            ordertype = result.getString("order_table.Order_Type");
        }
        
        while(resultset.next())
        {
            //Get data from Database    
            String foodname = resultset.getString("food.Food_Name");
            Double SellingPrice = resultset.getDouble("food.Selling_Price");
            int quantity = resultset.getInt("purchase.Quantity");
            double totalprice = resultset.getDouble("purchase.Sub_Total");
           
            //Initialize data in Employee object
            OutPut.appendText("\n"+foodname+"\t\t "+SellingPrice+"\t "+quantity+"\t "+totalprice); 
            
        }   
        
        double round = Math.ceil(TotalFPrice);
        double r = round - TotalFPrice;
        
        OutPut.appendText("\n  - - - - - - - - - - - - - - - - - - - - - - - -"
        
                +"\nOrder Total : "+TotalFPrice
                +"\nRounding    : "+r
                +"\nTotal Amount: "+round
                +"\n  - - - - - - - - - - - - - - - - - - - - - - - -"
                +"\n                Thanks For Coming"
                +"\n  - - - - - - - - - - - - - - - - - - - - - - - -"
                +"\nDeveloped By: Shakil Ahmed-GUB-CSE"
                
        );
         
       
         
        
    }

    @FXML
    private void PrintAction(ActionEvent event) throws PrinterException, SQLException {
        
         
         //Update Table Status Unbooked after Checkout
         String orderid = OrderId.getText();
         if(orderid.equals(""))return;        
         int oid = Integer.parseInt(orderid);  
         
         //Database Connection
         Connection con = dbaction.getConnection();
         
         //Search Table
         Statement TableState = con.createStatement();         
         String TableQuery = "SELECT Table_Id FROM purchase WHERE Order_ID = "+oid;
         ResultSet TableResult = TableState.executeQuery(TableQuery);
         
         //Update Table Statement
         Statement statement = con.createStatement();
         
         while(TableResult.next()){         
            
             String query ="UPDATE floor1 set Status = 'UnBooked' WHERE floor1.Table_Id = "+TableResult.getInt("Table_Id");
             statement.executeUpdate(query);         
         }
         //Print Method
         printSetup(OutPut, stage);

         
       
    }
    
    private void printSetup(Node node, Stage owner) 
    {
        // Create the PrinterJob        
        PrinterJob job = PrinterJob.createPrinterJob();
     
        if (job == null) 
        {
            return;
        }
 
        // Show the print setup dialog
        boolean proceed = job.showPrintDialog(owner);
         
        if (proceed) 
        {
            print(job, node);
        }
    }
    
    private void print(PrinterJob job, Node node) 
    {
        // Set the Job Status Message
        jobStatus.textProperty().bind(job.jobStatusProperty().asString());
         
        // Print the node
        boolean printed = job.printPage(node);
     
        if (printed) 
        {
            job.endJob();
        }
    }   
     
    
    

    @FXML
    private void OrderIdAction(ActionEvent event) throws SQLException {
        
         String orderid = OrderId.getText();
        if(orderid.equals(""))return;
        
        int oid = Integer.parseInt(orderid);
        
         AllOrder.clear();
         
          
          AllOrder = getAllOrder(oid);
          
          OrderIdC.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
          FoodNameC.setCellValueFactory(new PropertyValueFactory<>("FoodName"));
          QuantityC.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
          OrderTypeC.setCellValueFactory(new PropertyValueFactory<>("OrderType"));
          PriceC.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));

          OrderTable.setItems(AllOrder);
          
          Connection con = dbaction.getConnection();
          
          Statement st = con.createStatement();
          String query = "SELECT Total FROM order_table WHERE Order_ID = "+oid;
          ResultSet rs = st.executeQuery(query);
          
          double Total = 0;
          while(rs.next())
          {
              Total = rs.getDouble("Total");
          }
          GrandTotalLabel.setText(""+Total);
            TotalFPrice = Total;
    }
    
    
    //Get All Food Order For Specific Order Table
    
   ObservableList<OrderFood> getAllOrder(int OrderId) throws SQLException{
        
        
        ObservableList<OrderFood> Orderlist = FXCollections.observableArrayList();
        
        Connection con = dbaction.getConnection();
        String query = "SELECT purchase.Order_ID, food.Food_Name, purchase.Quantity,  purchase.Sub_Total FROM purchase join food ON purchase.Food_Id = food.Food_Id WHERE purchase.Order_ID ="+OrderId;
        Statement statement = con.createStatement();
        ResultSet resultset = statement.executeQuery(query);
        
        
        Statement statement2 = con.createStatement();
        String Query2 = "SELECT order_table.Order_Type FROM order_table WHERE Order_ID = "+OrderId;
        ResultSet result = statement2.executeQuery(Query2);
        
        String ordertype = "";
        while(result.next())
        {
            ordertype = result.getString("order_table.Order_Type");
        }
        
        while(resultset.next())
        {
            //Get data from Database    
            int orderid = resultset.getInt("purchase.Order_ID");
            String foodname = resultset.getString("food.Food_Name");
            int quantity = resultset.getInt("purchase.Quantity");
            double totalprice = resultset.getDouble("purchase.Sub_Total");
           
            //Initialize data in Employee object
            OrderFood orderfood = new OrderFood(orderid, foodname,quantity,totalprice,ordertype);            
            
            Orderlist.add(orderfood);
            
        }   
        
        return Orderlist;        
        
    }
   
    
    
}
