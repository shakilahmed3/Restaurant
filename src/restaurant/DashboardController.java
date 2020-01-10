
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Shakil Ahmed
 */
public class DashboardController implements Initializable {

    @FXML
    private Text FoodItem;
    @FXML
    private Text TodayProfit;
    @FXML
    private Text TotalEmployee;
    @FXML
    private Text TotalSell;
    @FXML
    private Text TotalProfit;
    @FXML
    private Text TotalEmployeeSalary;
    @FXML
    private Text TodaySell;

    DBAction dbaction = new DBAction();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            getAllReport();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
    
    void getAllReport() throws SQLException{
        
        
        Connection con = dbaction.getConnection();
        Statement st = con.createStatement();
        
        //Add All Food Count in Dashboard
        String query = "SELECT COUNT(Food_Id) as TotalFood FROM food";        
        ResultSet rs = st.executeQuery(query);
        
        while(rs.next())
        {
            FoodItem.setText(""+rs.getInt("TotalFood"));
        }
        
        
        LocalDate date = LocalDate.now(); 
        //System.out.println(""+date);        
       // Date date1 = Date.valueOf(date);        
         //Add All Food Count in Dashboard
        Statement st2 = con.createStatement();        
        String query2 = "SELECT sum(Sub_Profit) as TodayProfit FROM purchase WHERE Selldate = '"+date+"'";        
        ResultSet rs2 = st2.executeQuery(query2);
        //double todayprofit = 0;
        
        while(rs2.next())
        {
            //todayprofit = rs2.getDouble("TodayProfit");
            TodayProfit.setText(""+rs2.getDouble("TodayProfit"));
        }
            //TodayProfit.setText(""+todayprofit);        
        
            
         
        //Total Sell from Purchase Table Query
        Statement st3 = con.createStatement(); 
        String query3 = "SELECT sum(Total) as TotalSell FROM order_table";        
        ResultSet rs3 = st3.executeQuery(query3);
        while(rs3.next())
        {
            TotalSell.setText(""+rs3.getDouble("TotalSell"));
        }

        
         
        //Total Profit
        Statement st4 = con.createStatement(); 
        String query4 = "SELECT sum(Profit) as TotalProfit FROM order_table";        
        ResultSet rs4 = st4.executeQuery(query4);
        while(rs4.next())
        {
            TotalProfit.setText(""+rs4.getDouble("TotalProfit"));
        }
        
        
         //Today Sell
        Statement st5 = con.createStatement(); 
        String query5 = "SELECT sum(Sub_Total) as TodaySell FROM purchase WHERE Selldate = '"+date+"'";        
        ResultSet rs5 = st5.executeQuery(query5);
        while(rs5.next())
        {
            TodaySell.setText(""+rs5.getDouble("TodaySell"));
        }
            
        
        //Total Salary
        Statement st6 = con.createStatement(); 
        String query6 = "SELECT sum(Salary) as TotalSalary FROM employee";        
        ResultSet rs6 = st6.executeQuery(query6);
        while(rs6.next())
        {
            TotalEmployeeSalary.setText(""+rs6.getDouble("TotalSalary"));
        }
        
        
         //Total Employee
        Statement st7 = con.createStatement(); 
        String query7 = "SELECT count(Employee_ID) as TotalEmployee FROM employee;";        
        ResultSet rs7 = st7.executeQuery(query7);
        while(rs7.next())
        {
            TotalEmployee.setText(""+rs7.getInt("TotalEmployee"));
        }
        
        
    }
}
